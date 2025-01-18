package api;

import bot.ProxyInfo;
import bot.Register;
import com.custom.ClientToken;
import com.google.gson.JsonObject;
import com.spotify.custom.ChallengeOrchestrator;
import com.spotify.custom.Spotify;
import com.spotify.custom.Spotifychallenge;
import okclient.ConscryptSocketFactoryWrapper;
import okhttp3.*;
import org.conscrypt.Conscrypt;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
public class AndroidApiRegister {
    private final OkHttpClient clienttoken_client;
    private final OkHttpClient signup_client;
    private static final String CLIENT_ID = "9a8d2f0ce77a4e248bb71fefcb557637";
    private static final String CLIENT_TOKEN_URL = "https://clienttoken.spotify.com/v1/clienttoken";
    private static final String SIGNUP_URL = "https://spclient.wg.spotify.com/signup/public/v2/account/create";
    private static final String SUBMIT_CAPTCHA = "https://challenge.spotify.com/api/v1/invoke-challenge-command";
    private static final String CHALLENGE_ORCHESTRATOR_URL = "https://spclient.wg.spotify.com/challenge-orchestrator/v1/get-session";
    private static final String COMPLETE_CREATION_URL = "https://spclient.wg.spotify.com/signup/public/v2/account/complete-creation";
    private final String user_agent;
    public String clientToken;

    private final String SPOTIFY_APP_VERSION;
    private final int API_VERSION;
    private final String DEVICE_BRAND;
    private final String DEVICE_MANUFACTURER;
    private final int SCREEN_WIDTH; // Example value, adjust as needed
    private final int SCREEN_HEIGHT; // Example value, adjust as needed
    private final int DENSITY; // Example value, adjust as needed
    private final int ANDROID_VERSION; // Example value, adjust as needed
    private final int gender;
    private final String dob;
    private final String username;
    public final String email;
    public final String password;
    private final String model_code;
    public final String android_id;
    public final String device_id;
    private final int smallestScreenWidthDp;
    static List<String> emails;

    static {
        try {
            emails = Files.readAllLines(Paths.get("emails.txt"));
            System.out.println(emails.size() + " emails loaded for generation impersonation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void printRequestHeaders(Request request) {
        System.out.println("Request Headers for: " + request.url());
        Headers headers = request.headers();
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i) + ": " + headers.value(i));
        }
        System.out.println();
    }

    public AndroidApiRegister(@NotNull ProxyInfo conf, @NotNull String spotifyappversion, int apiversion, @NotNull String devicemodel, @NotNull String devicebrand,
                                @NotNull String devicemanufacturer, int screenwidth, int screenheight, int density, int androidversion, @NotNull String model_code) throws IOException {
        try {
            this.clienttoken_client = createSpotifyClient(conf);
            this.signup_client = createSpotifyClient(conf);
            
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.model_code = model_code;
        this.user_agent = "Spotify/" + spotifyappversion + " Android/" + apiversion + " (" + model_code + ")";
        this.SPOTIFY_APP_VERSION = spotifyappversion;
        this.API_VERSION = apiversion;
        this.DEVICE_BRAND = devicebrand;
        this.DEVICE_MANUFACTURER = devicemanufacturer;
        this.SCREEN_WIDTH = screenwidth;
        this.SCREEN_HEIGHT = screenheight;
        this.DENSITY = density;
        this.ANDROID_VERSION = androidversion;
        String temp = emails.get(random.nextInt(emails.size())).split(":")[0];
        int atIndex = temp.indexOf('@');
        String local = temp.substring(0, atIndex);
        String domain = temp.substring(atIndex);
        if (Math.random() < 0.5 && local.length() > 0) {
            // Remove a random character
            int removeIndex = (int) (Math.random() * local.length());
            local = local.substring(0, removeIndex) + local.substring(removeIndex + 1);
        } else {
            // Add a random lowercase letter
            char randomChar = (char) ('a' + (int) (Math.random() * 26));
            int insertIndex = (int) (Math.random() * (local.length() + 1));
            local = local.substring(0, insertIndex) + randomChar + local.substring(insertIndex);
        }

        this.email = generateUsername() + "@" + new String[]{"gmail.com", "hotmail.com", "aol.com", "yahoo.com", "msn.com"}[(int)(Math.random() * 5)];

        //this.email = "askedoverlordapeeej3@gmail.com";
        this.username = generateUsername();
        //this.username = generateUsername();
        //this.username = "adolfoyaheardme300@gmail.com";
        //this.password = generatePassword();
        this.password = generatePassword();//"idcanymoreapAA1*";
        this.dob = generateDob();
        this.android_id = generateDeviceId();
        this.gender = ThreadLocalRandom.current().nextInt(1, 3);
        this.smallestScreenWidthDp = getsmallestScreenWidthDp(density, screenwidth, screenheight);
        //System.out.println(user_agent);
        this.device_id = generateRandom40DigitHex();
        //this.hotmailService = hotmailService;
        getClientToken();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandom40DigitHex() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[20];
        secureRandom.nextBytes(randomBytes);

        StringBuilder sb = new StringBuilder(40);
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }


    public int getsmallestScreenWidthDp(int densityDpi, int widthPx, int heightPx) {
        int widthDp = (int) Math.round((double) widthPx * 160 / densityDpi);
        int heightDp = (int) Math.round((double) heightPx * 160 / densityDpi);
        return Math.min(widthDp, heightDp);
    }

    public boolean register() throws IOException {
        Spotifychallenge.Response response = registerAccount();
        if (response != null) {
            if (response.hasChallenge()) {
                String session_id = response.getChallenge().getId();
                ChallengeOrchestrator.ChallengeOrchestratorGetSessionResponse challengeOrchestratorGetSessionResponse =
                        challengeOrchestratorGetSession(session_id);

                // Extract challenge ID and URL
                String challenge_resp = challengeOrchestratorGetSessionResponse.getDetail().getChallengeId().toStringUtf8();
                String challenge_id = "";
                String challenge_url = "";

                // Clean up challenge ID using regex for UUID
                Pattern uuidPattern = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
                Matcher uuidMatcher = uuidPattern.matcher(challenge_resp);
                if (uuidMatcher.find()) {
                    challenge_id = uuidMatcher.group(0);  // Extract clean UUID
                } else {
                    challenge_id = "UUID not found";
                }

                // Clean up challenge URL using regex
                Pattern urlPattern = Pattern.compile("https://[^\\s]*");
                Matcher urlMatcher = urlPattern.matcher(challenge_resp);
                if (urlMatcher.find()) {
                    challenge_url = urlMatcher.group(0);  // Extract clean URL
                } else {
                    challenge_url = "URL not found";
                }

                //System.out.println("Challenge id: " + challenge_id);
                //System.out.println("Challenge url: " + challenge_url);
                System.out.println("Captcha encountered! Trying to solve...");
                Register.captchaEncountered.incrementAndGet();
                if(solveCaptcha(session_id, challenge_id, challenge_url)) {
                    if(submitAccountCreation(session_id)){
                            Register.captchaCount.incrementAndGet();
                            return true;
                    } else {
                        System.out.println("Captcha was solved but we failed to submit the final creation");
                    }
                } else {
                    System.out.println("Failed to solve captcha");
                }
            } else {
                String username = response.getDetails().getUsername();
                System.out.println(username);
                Register.nocaptchaCount.incrementAndGet();
                return true;
            }
        }
        return false;
    }

    String constructSolveCaptchaPayload(String session_id, String challenge_id, String recaptcha_response) {
        // Replace these with your actual values
        // Create the innermost object: {"recaptcha_token": recaptcha_response}
        JsonObject solveObject = new JsonObject();
        solveObject.addProperty("recaptcha_token", recaptcha_response);

        // Wrap it inside "solve": {"solve": {...}}
        JsonObject recaptchaChallengeV1 = new JsonObject();
        recaptchaChallengeV1.add("solve", solveObject);

        // Build the main payload
        JsonObject payload = new JsonObject();
        payload.addProperty("session_id", session_id);
        payload.addProperty("challenge_id", challenge_id);
        payload.add("recaptcha_challenge_v1", recaptchaChallengeV1);

        // Convert payload to JSON string if needed
        String jsonString = payload.toString();

        // Output the JSON string
        //System.out.println(jsonString);
        return jsonString;
    }

    // Register a new account
    private Spotifychallenge.Response registerAccount() throws IOException {
        Spotify.SpotifySignupRequest signupRequest = Spotify.SpotifySignupRequest.newBuilder().setCallbackUrl("https://auth-callback.spotify.com/r/android/music/signup")
                .setAccountDetails(Spotify.SpotifySignupRequest.AccountDetails.newBuilder()
                        .setUsername(username)
                        .setDateOfBirth(dob)
                        .setGender(gender)
                        //tos
                        .setAdditionalDetails(Spotify.SpotifySignupRequest.AccountDetails.AdditionalDetails.newBuilder()
                                .setNestedDetails(Spotify.SpotifySignupRequest.AccountDetails.AdditionalDetails.NestedDetails.newBuilder()
                                        .setField(1))
                                .build())
                        .setEmailAndPassword(Spotify.SpotifySignupRequest.AccountDetails.EmailAndPassword.newBuilder()
                                .setEmail(email)
                                .setPassword(password)
                                .build())
                        .build())
                .setClientInfo(Spotify.SpotifySignupRequest.ClientInfo.newBuilder()
                        .setClientKey("142b583129b2df829de3656f9eb484e6")
                        .setDevicePlatform("Android-ARM")
                        .setAppVersion(SPOTIFY_APP_VERSION)
                        .setUnknownField4("\u0001") //padding?
                        .setDeviceId(android_id)
                        .build())
                .setApplicationDetails(Spotify.SpotifySignupRequest.ApplicationDetails.newBuilder().setAppType("client_mobile"))
                .build();
        //System.out.println(signupRequest);
        // Create the request
        RequestBody body = RequestBody.create(MediaType.parse("application/x-protobuf"), signupRequest.toByteArray());

        Request httpRequest = new Request.Builder()
                .url(SIGNUP_URL)
                .header("Accept", "application/protobuf")
                .header("client-token", clientToken)
                .header("Accept-Language", "en-US")
                .header("User-Agent", user_agent)
                .header("Spotify-App-Version", SPOTIFY_APP_VERSION)
                .header("X-Client-Id", CLIENT_ID)
                .header("App-Platform", "Android")
                .header("Content-Type", "application/x-protobuf")
                .header("Content-Length", String.valueOf(body.contentLength()))
                .header("Accept-Encoding", "gzip, deflate, br")
                .post(body)
                .build();

        // Execute the request
        try (Response response = signup_client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            byte[] responseBytes;
            if ("gzip".equalsIgnoreCase(response.header("Content-Encoding"))) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(response.body().byteStream())) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = gzipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, len);
                    }
                    responseBytes = outputStream.toByteArray();
                }
            } else {
                responseBytes = response.body().bytes();
            }
            return Spotifychallenge.Response.parseFrom(responseBytes);
        }
    }

    private ChallengeOrchestrator.ChallengeOrchestratorGetSessionResponse challengeOrchestratorGetSession(String session_id) throws IOException {
        ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest challengeOrchestratorGetSessionRequest =
                ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest.newBuilder().setSessionId(session_id).build();
        // Create the request
        RequestBody body = RequestBody.create(MediaType.parse("application/x-protobuf"), challengeOrchestratorGetSessionRequest.toByteArray());
        Request httpRequest = new Request.Builder()
                .url(CHALLENGE_ORCHESTRATOR_URL)
                .header("Host", "spclient.wg.spotify.com")
                .header("Accept", "application/protobuf")
                .header("client-token", clientToken)
                .header("Accept-Language", "en-US")
                .header("User-Agent", user_agent)
                .header("Spotify-App-Version", SPOTIFY_APP_VERSION)
                .header("X-Client-Id", CLIENT_ID)
                .header("App-Platform", "Android")
                .header("Content-Type", "application/x-protobuf")
                .header("Content-Length", String.valueOf(body.contentLength()))
                .header("Accept-Encoding", "gzip, deflate, br")
                .post(body)
                .build();
        //printRequestHeaders(httpRequest);

        // Execute the request
        try (Response response = signup_client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            byte[] responseBytes;
            if ("gzip".equalsIgnoreCase(response.header("Content-Encoding"))) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(response.body().byteStream())) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = gzipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, len);
                    }
                    responseBytes = outputStream.toByteArray();
                }
            } else {
                responseBytes = response.body().bytes();
            }
            return ChallengeOrchestrator.ChallengeOrchestratorGetSessionResponse.parseFrom(responseBytes);
        }
    }

    private boolean submitAccountCreation(String session_id) {
        for(int i=0; i<5; i++) {
            ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest challengeOrchestratorGetSessionRequest =
                    ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest.newBuilder().setSessionId(session_id).build();

            // Create the request
            RequestBody body = RequestBody.create(MediaType.parse("application/x-protobuf"), challengeOrchestratorGetSessionRequest.toByteArray());
            Request httpRequest = new Request.Builder()
                    .url(COMPLETE_CREATION_URL)
                    .header("Host", "spclient.wg.spotify.com")
                    .header("Accept", "application/protobuf")
                    .header("client-token", clientToken)
                    .header("Accept-Language", "en-US")
                    .header("User-Agent", user_agent)
                    .header("Spotify-App-Version", SPOTIFY_APP_VERSION)
                    .header("X-Client-Id", CLIENT_ID)
                    .header("App-Platform", "Android")
                    .header("Content-Type", "application/x-protobuf")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .post(body)
                    .build();
            //printRequestHeaders(httpRequest);

            // Execute the request
            try (Response response = signup_client.newCall(httpRequest).execute()) {
                System.out.println("Submit account creation..." + response.code());
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                return response.code() == 200;
            } catch (IOException e) {
                //System.out.println("IOException on submitAccountCreation, retrying... " + i);
            }
        }
        return false;
    }

    private boolean solveCaptcha(String session_id, String challenge_id, String challenge_url) throws IOException {
        String recaptcha_response = null;
        try {
            recaptcha_response = CapSolver.capsolver(challenge_url);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //System.out.println("reCAPTCHA solution: " + recaptcha_response);
        String solved_json = constructSolveCaptchaPayload(session_id, challenge_id, recaptcha_response);
        //System.out.println(solved_json);
        ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest challengeOrchestratorGetSessionRequest =
                ChallengeOrchestrator.ChallengeOrchestratorGetSessionRequest.newBuilder().setSessionId(session_id).build();


        // Create the request
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), solved_json);
        Request httpRequest = new Request.Builder()
                .url(SUBMIT_CAPTCHA)
                .header("Host", "challenge.spotify.com")
                .header("Accept", "application/json")
                .header("Accept-Language", "en-US")
                .header("User-Agent", "Mozilla/5.0 (Linux; Android " + ANDROID_VERSION + "; " + this.model_code+ ") AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.6045.193 Mobile Safari/537.36")
                .header("Content-Type", "application/json")
                .header("Origin", "https://challenge.spotify.com")
                .header("X-Requested-With", "com.android.browser")
                .header("Sec-Fetch-Site", "same-origin")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Ch-Ua", "\"Android WebView\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"")
                .header("Sec-Ch-Ua-Mobile", "?1")
                .header("Referer", challenge_url)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "en-US,en;q=0.9")
                .post(body)
                .build();

        //printRequestHeaders(httpRequest);

        // Execute the request
        try (Response response = signup_client.newCall(httpRequest).execute()) {
            //System.out.println("Captcha submit response code: " + response.code());
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.code() == 200;
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

    public static synchronized OkHttpClient createSpotifyClient(ProxyInfo proxyInfo) throws Exception {
        // 1) Build the base OkHttpClient using the existing method.
        OkHttpClient baseClient = createClient(proxyInfo);

        // 2) Insert Conscrypt and create the Conscrypt-based SSLContext
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
        SSLContext sslContext = SSLContext.getInstance("TLS", "Conscrypt");
        X509TrustManager trustManager = defaultTrustManager();
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());

        // Wrap the SSLSocketFactory
        SSLSocketFactory forcingFactory = new ConscryptSocketFactoryWrapper(sslContext.getSocketFactory());

        OkHttpClient.Builder newBuilder = baseClient.newBuilder()
                .sslSocketFactory(forcingFactory, trustManager)
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .dns(hostname -> {
                    List<InetAddress> all = Dns.SYSTEM.lookup(hostname);
                    List<InetAddress> v4Only = new ArrayList<>();
                    for (InetAddress addr : all) {
                        if (addr instanceof Inet4Address) {
                            v4Only.add(addr);
                        }
                    }
                    return v4Only.isEmpty() ? all : v4Only;
                })
                // Simple logging interceptor
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    System.out.println("[*SpotifyClient*] Sending " + request.method() + " to " + request.url());
                    Response response = chain.proceed(request);
                    Handshake handshake = response.handshake();
                    if (handshake != null) {
                        System.out.println("   TLS => " + handshake.tlsVersion()
                                + ", cipher => " + handshake.cipherSuite());
                    }
                    return response;
                });

        return newBuilder.build();
    }


    // system default trust manager, from your code
    private static X509TrustManager defaultTrustManager() throws Exception {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);
        for (TrustManager tm : tmf.getTrustManagers()) {
            if (tm instanceof X509TrustManager) {
                return (X509TrustManager) tm;
            }
        }
        throw new IllegalStateException("No X509TrustManager found");
    }

    public String getClientToken() throws IOException {
        ClientToken.ClientTokenResponse response = clientToken();

        if (response.getChallenges().getChallengesCount() > 0) {
            ClientToken.Challenge challenge = response.getChallenges().getChallenges(0);
            int length = challenge.getEvaluateHashcashParameters().getLength();
            String prefix = challenge.getEvaluateHashcashParameters().getPrefix();
            // System.out.println("test: " + ClientTokenSolver.solveHashCash("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef", 20));
            String challengeHashSolution = ClientTokenSolver.solveHashCash(prefix, length);
            //System.out.println("Challenge hash solution: " + challengeHashSolution);

            ClientToken.ClientTokenRequest challengeResponse = createChallengeResponseRequest(
                    response.getChallenges().getState(),
                    challengeHashSolution
            );
            response = sendClientTokenRequest(challengeResponse);
        }

        clientToken = response.getGrantedToken().getToken();
        return clientToken;
    }

    private ClientToken.ClientTokenResponse clientToken() throws IOException {
        ClientToken.ClientTokenRequest protoReq = ClientToken.ClientTokenRequest.newBuilder()
                .setRequestType(ClientToken.ClientTokenRequestType.REQUEST_CLIENT_DATA_REQUEST)
                .setClientData(ClientToken.ClientDataRequest.newBuilder()
                        .setClientVersion(SPOTIFY_APP_VERSION)
                        .setClientId(CLIENT_ID)
                        .setConnectivitySdkData(ClientToken.ConnectivitySdkData.newBuilder()
                                .setDeviceId(android_id)
                                .setPlatformSpecificData(ClientToken.PlatformSpecificData.newBuilder()
                                        .setAndroid(ClientToken.NativeAndroidData.newBuilder()
                                                .setScreenDimensions(ClientToken.Screen.newBuilder()
                                                        .setWidth(SCREEN_WIDTH)
                                                        .setHeight(SCREEN_HEIGHT)
                                                        .setSmallestScreenWidthDp(smallestScreenWidthDp)
                                                        .setDpi(DENSITY)
                                                        .setDpi2(DENSITY)
                                                        .build())
                                                .setAndroidVersion(String.valueOf(ANDROID_VERSION))
                                                .setApiVersion(API_VERSION)
                                                .setDeviceName(model_code)
                                                .setModelStr(model_code)
                                                .setBrand(DEVICE_BRAND)
                                                .setManufacturer(DEVICE_MANUFACTURER)
                                                .setCacheSize(32)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        return sendClientTokenRequest(protoReq);
    }

    private ClientToken.ClientTokenRequest createChallengeResponseRequest(String state, String challengeHashSolution) {
        return ClientToken.ClientTokenRequest.newBuilder()
                .setRequestType(ClientToken.ClientTokenRequestType.REQUEST_CHALLENGE_ANSWERS_REQUEST)
                .setChallengeAnswers(ClientToken.ChallengeAnswersRequest.newBuilder()
                        .setState(state)
                        .addAnswers(ClientToken.ChallengeAnswer.newBuilder()
                                .setChallengeType(ClientToken.ChallengeType.CHALLENGE_HASH_CASH)
                                .setHashCash(ClientToken.HashCashAnswer.newBuilder()
                                        .setSuffix(challengeHashSolution)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private void printTlsAndCipherSuiteInfo(Response response) {
        if (response != null) {
            Handshake handshake = response.handshake();
            if (handshake != null) {
                final CipherSuite cipherSuite = handshake.cipherSuite();
                final TlsVersion tlsVersion = handshake.tlsVersion();
                System.out.println(cipherSuite.toString());
                System.out.println(tlsVersion.toString());
            }
        }
    }

    private ClientToken.ClientTokenResponse sendClientTokenRequest(ClientToken.ClientTokenRequest request) throws IOException {
        System.out.println(request.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/x-protobuf"), request.toByteArray());
        Request httpRequest = new Request.Builder()
                .url(CLIENT_TOKEN_URL)
                .header("Cache-Control", "no-cache, no-store, max-age=0")
                .header("Accept", "application/x-protobuf")
                .header("User-Agent", user_agent)
                .header("Content-Type", "application/x-protobuf")
                .header("Content-Length", String.valueOf(body.contentLength()))
                .header("Accept-Encoding", "gzip, deflate, br")
                .post(body)
                .build();



        try (Response response = clienttoken_client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);



            byte[] responseBytes;
            if ("gzip".equalsIgnoreCase(response.header("Content-Encoding"))) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(response.body().byteStream())) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = gzipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, len);
                    }
                    responseBytes = outputStream.toByteArray();
                }
            } else {
                responseBytes = response.body().bytes();
            }
            return ClientToken.ClientTokenResponse.parseFrom(responseBytes);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();
    public static String generateUsername() {
        int USERNAME_LENGTH = random.nextInt(5) + 9;
        StringBuilder sb = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            int index = ThreadLocalRandom.current().nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String generateDeviceId() {
        return String.format("%016x", new java.util.Random().nextLong());
    }

    public static String generateDob() {
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.minusYears(39);
        LocalDate endDate = now.minusYears(18);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return randomDate.format(formatter);
    }

    public static String generatePassword() {
        int length = new SecureRandom().nextInt(3) + 12;
        return new SecureRandom().ints(length, 0, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".length())
                .mapToObj(i -> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".charAt(i))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static boolean verifyEmail(String verificationLink) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "run.py", verificationLink);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.readLine();
                return Boolean.parseBoolean(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String link = "https://wl.spotify.com/ls/click?upn=..."; // Your verification link
        boolean verified = verifyEmail(link);
        System.out.println("Verification result: " + verified);
    }

    private static class Pair<F, S> {
        final F first;
        final S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

}

