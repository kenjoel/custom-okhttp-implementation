package api;

import bot.ProxyInfo;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.spotify.login5v3.ClientInfoOuterClass;
import com.spotify.login5v3.Hashcash;
import com.spotify.login5v3.Login5;
import com.spotify.login5v3.Login5.LoginResponse;
import okhttp3.*;
import org.conscrypt.Conscrypt;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Random;

public class Login5Api {

    private String device_id;
    private OkHttpClient client;
    private static final byte[] ntz8tab = new byte[256 * 4];
    private static final int[] trailingZeroLookup = new int[]{
            32, 0, 1, 26, 2, 23, 27, 0, 3, 16, 24, 30, 28, 11, 0, 13, 4, 7, 17, 0, 25, 22,
            31, 15, 29, 10, 12, 6, 0, 21, 14, 9, 5, 20, 8, 19, 18
    };

    static {
        for (int i = 0; i < 256; i++) {
            ntz8tab[i * 4] = (byte) (i / 2);
            ntz8tab[i * 4 + 1] = 0;
            ntz8tab[i * 4 + 2] = (byte) (i % 2);
            ntz8tab[i * 4 + 3] = 0;
        }
    }


    public static synchronized OkHttpClient createClient(ProxyInfo proxyInfo) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true);
        // Proxy configuration
        builder.proxy(new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress(proxyInfo.proxyAddress, proxyInfo.proxyPort)));

        builder.proxyAuthenticator((route, response) -> {
            String credential = Credentials.basic(proxyInfo.proxyUsername, proxyInfo.proxyPassword);
            return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();
        });
        return builder.build();
    }

    public static String getDeviceId() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[20];
        random.nextBytes(tokenBytes);

        StringBuilder hexString = new StringBuilder();
        for (byte b : tokenBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public Login5Api(){
        //Setup();
    }

    public void Setup() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        this.device_id = getDeviceId();
        String proxy = "4a133fe88f688e98fd41__cr.us:be8396dd30db37ca@gw.dataimpulse.com:823";
        String proxy_credentials = proxy.split("@")[0];
        String proxy_connection_info = proxy.split("@")[1];
        String username = proxy_credentials.split(":")[0];
        String password = proxy_credentials.split(":")[1];
        String ip = proxy_connection_info.split(":")[0];
        int port = Integer.parseInt(proxy_connection_info.split(":")[1]);
        ProxyInfo proxyInfo = new ProxyInfo(ip, port, username, password);
        this.client = createClient(proxyInfo);
    }

    public boolean login(String username, String password) throws IOException {
        int retries = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Setup();

                Login5.LoginRequest d = Login5.LoginRequest.newBuilder()
                        .setClientInfo(ClientInfoOuterClass.ClientInfo.newBuilder().setClientId("65b708073fc0480ea92a077233ca87bd").build())
                        .setPassword(com.spotify.login5v3.Credentials.Password.newBuilder()
                                .setId(username)
                                .setPassword(password)
                                .setPadding(ByteString.copyFrom(String.valueOf(900000000000L + (long)(Math.random() * 100000000000L)), StandardCharsets.UTF_8))
                                .build())
                        .setChallengeSolutions(Login5.ChallengeSolutions.newBuilder().build())
                        .build();

                byte[] requestBody = d.toByteArray();

                RequestBody body = RequestBody.create(MediaType.parse("application/x-protobuf"), requestBody);
                Request request = new Request.Builder()
                        .url("https://login5.spotify.com/v3/login")
                        .addHeader("Accept", "application/x-protobuf")
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                LoginResponse e = LoginResponse.parseFrom(response.body().byteStream());
                Hashcash.HashcashChallenge hch = e.getChallenges().getChallenges(0).getHashcash();
                ByteString hpref = hch.getPrefix();
                int hlen = hch.getLength();
                ByteString ctx = e.getLoginContext();

                byte[] s = shc(ctx.toByteArray(), hpref.toByteArray(), hlen);
                //System.out.println("S: " + bytesToHex(s));

                Login5.ChallengeSolution sol = Login5.ChallengeSolution.newBuilder()
                        .setHashcash(Hashcash.HashcashSolution.newBuilder()
                                .setSuffix(ByteString.copyFrom(s))
                                .setDuration(Duration.newBuilder().setNanos(new Random().nextInt(80000)+200000).build())
                                .build())
                        .build();

                d = d.toBuilder()
                        .setLoginContext(ctx)
                        .setChallengeSolutions(d.getChallengeSolutions().toBuilder().addSolutions(sol).build())
                        .build();

                requestBody = d.toByteArray();
                body = RequestBody.create(MediaType.parse("application/x-protobuf"), requestBody);
                request = new Request.Builder()
                        .url("https://login5.spotify.com/v3/login")
                        .addHeader("Accept", "application/x-protobuf")
                        .post(body)
                        .build();

                response = client.newCall(request).execute();

                e = LoginResponse.parseFrom(response.body().byteStream());
                String responseString = e.toString();

                if (responseString.contains("INVALID_CREDENTIALS")) {
                    if(retries < 2) {
                        System.out.println("Retrying... " + retries);
                        retries++;
                        continue;
                    }
                    return false;
                } else if (responseString.contains("access_token")) {
                    System.out.println(responseString);
                    return true;
                } else {
                    System.out.println("Status: " + response.code());
                    System.out.println(e.toString());
                    System.out.println("Error: " + responseString);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }

        return false;
    }

    private static int countTrailingZeroBits(int value) {
        return trailingZeroLookup[(-value & value) % 37];
    }

    private static boolean hasTenTrailingBits(byte[] bytesData) {
        return bytesData[bytesData.length - 1] == 0 && (countTrailingZeroBits(bytesData[bytesData.length - 2]) >= 2);
    }

    private static byte[] positionalIncrement(byte[] values, int position) {
        byte[] array = Arrays.copyOf(values, values.length);

        int valueAt = array[position] & 0xFF;

        if (valueAt >= 0xFF) {
            array[position] = 0;
            return positionalIncrement(array, position - 1);
        }

        array[position]++;
        return array;
    }

    private static byte[] shc(byte[] context, byte[] prefix, int length) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] contextHash = sha1.digest(context);
        byte[] seed = Arrays.copyOfRange(contextHash, 12, 20);

        byte[] byteArray = new byte[16];
        System.arraycopy(seed, 0, byteArray, 0, 8);

        int countmax = 0;

        while (countmax <= 5000) {
            byte[] hashInput = new byte[prefix.length + byteArray.length];
            System.arraycopy(prefix, 0, hashInput, 0, prefix.length);
            System.arraycopy(byteArray, 0, hashInput, prefix.length, byteArray.length);

            byte[] hashOutput = sha1.digest(hashInput);

            if (!hasTenTrailingBits(hashOutput)) {
                byteArray = positionalIncrement(byteArray, byteArray.length - 1);
                byteArray = positionalIncrement(byteArray, 7);

                countmax++;
                continue;
            }

            return byteArray;
        }

        return new byte[0];
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}