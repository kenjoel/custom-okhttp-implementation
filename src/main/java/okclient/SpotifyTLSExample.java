package okclient;

import okhttp3.*;
import org.conscrypt.Conscrypt;
import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates a Spotify-like OkHttp client using:
 *  - TLS 1.3 + specific cipher suites in your enumerated order
 *  - Certificate pinning (placeholder hash!)
 *  - Conscrypt for more fine-grained TLS control if needed
 */
/* ok Http version 4.12 */

public class SpotifyTLSExample {

    public static String fetchSpotify() throws Exception {
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
        SSLContext sslContext = SSLContext.getInstance("TLS", "Conscrypt");
        X509TrustManager trustManager = defaultTrustManager();
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLSocketFactory rawSocketFactory = sslContext.getSocketFactory();
        SSLSocketFactory forcingFactory = new ConscryptSocketFactoryWrapper(rawSocketFactory);

        // 3) Build a pinned certificate list
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add("clienttoken.spotify.com",
//                        "sha256/z2i0X89DOe9Z/VuObQqQRdSxLY7/guBMFgzTl1a2Z2A=")
//                .add("spclient.wg.spotify.com",
//                        "sha256/z2i0X89DOe9Z/VuObQqQRdSxLY7/guBMFgzTl1a2Z2A=")
                .add("clienttoken.spotify.com",
                        "sha256/arIn0Xotkora1pfMdMYgxHh3Q4uIyce7KOOZpQ6akU8=")
                .build();

        // 4) Create a custom ConnectionSpec with your enumerated ciphers & TLS versions
        ConnectionSpec customConnectionSpec = new ConnectionSpec.Builder(true)
                .tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2)
                .cipherSuites(
                        // EXACT order from your pcap/hook logs:
                        CipherSuite.TLS_AES_128_GCM_SHA256,
                        CipherSuite.TLS_AES_256_GCM_SHA384,
                        CipherSuite.TLS_CHACHA20_POLY1305_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                        CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384,
                        CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA
                )
                .build();

        // 5) Build the OkHttpClient to use Conscrypt's sslSocketFactory
        OkHttpClient client = new OkHttpClient.Builder()
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080)))
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .sslSocketFactory(forcingFactory, trustManager)
                .certificatePinner(certificatePinner)
                .connectionSpecs(Arrays.asList(customConnectionSpec, ConnectionSpec.CLEARTEXT))
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true) // or a stricter verifier
                .addInterceptor(chain -> {
                    // Debug logging of requests & handshake info:
                    Request request = chain.request();
                    System.out.println("[*] Sending " + request.method() + " to " + request.url());

                    Response response = chain.proceed(request);
                    Handshake handshake = response.handshake();
                    if (handshake != null) {
                        System.out.println("   TLS => " + handshake.tlsVersion()
                                + ", cipher => " + handshake.cipherSuite());
                    }
                    return response;
                })
                // Timeouts as needed
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        HttpUrl url = HttpUrl.parse("https://clienttoken.spotify.com/v1/clienttoken");

        final byte[] CLIENTTOKEN_PAYLOAD = new byte[] {
                // From: \x08\x01\x12\xe8\x01
                (byte)0x08, (byte)0x01, (byte)0x12, (byte)0xe8, (byte)0x01,

                // \x0a\x09 => 0x0a,0x09
                (byte)0x0a, (byte)0x09,
                // "9.0.2.459" => ASCII
                (byte)0x39, (byte)0x2e, (byte)0x30, (byte)0x2e, (byte)0x32, (byte)0x2e, (byte)0x34, (byte)0x35, (byte)0x39,

                // \x12 => 0x12
                (byte)0x12,
                // space => 0x20
                (byte)0x20,
                // "9a8d2f0ce77a4e248bb71fefcb557637" => ASCII
                (byte)0x39, (byte)0x61, (byte)0x38, (byte)0x64, (byte)0x32, (byte)0x66, (byte)0x30, (byte)0x63,
                (byte)0x65, (byte)0x37, (byte)0x37, (byte)0x61, (byte)0x34, (byte)0x65, (byte)0x32, (byte)0x34,
                (byte)0x38, (byte)0x62, (byte)0x62, (byte)0x37, (byte)0x31, (byte)0x66, (byte)0x65, (byte)0x66,
                (byte)0x63, (byte)0x62, (byte)0x35, (byte)0x35, (byte)0x37, (byte)0x36, (byte)0x33, (byte)0x37,

                // \x1a => 0x1a
                (byte)0x1a,
                // \xb8\x01 => 0xb8,0x01
                (byte)0xb8, (byte)0x01,
                // \x0a\xa3\x01 => 0x0a,0xa3,0x01
                (byte)0x0a, (byte)0xa3, (byte)0x01,
                // \x0a\xa0\x01 => 0x0a,0xa0,0x01
                (byte)0x0a, (byte)0xa0, (byte)0x01,
                // \x0a\x0f => 0x0a,0x0f
                (byte)0x0a, (byte)0x0f,
                // \x08\xb8\x08\x10\xcd\x10\x18\x80\x03 => ...
                (byte)0x08, (byte)0xb8, (byte)0x08, (byte)0x10, (byte)0xcd, (byte)0x10, (byte)0x18, (byte)0x80, (byte)0x03,
                // space => 0x20
                (byte)0x20,
                // \xc2\x03 => 0xc2,0x03
                (byte)0xc2, (byte)0x03,
                // ( => 0x28
                (byte)0x28,
                // \xc2\x03 => 0xc2,0x03
                (byte)0xc2, (byte)0x03,
                // \x12\x02 => 0x12,0x02
                (byte)0x12, (byte)0x02,
                // "14" => ASCII => 0x31, 0x34
                (byte)0x31, (byte)0x34,
                // \x18 => 0x18
                (byte)0x18,
                // "\"" => 0x22
                (byte)0x22,
                // "\"" => 0x22
                (byte)0x22,
                // \x08 => 0x08
                (byte)0x08,
                // "SM-A155F" => ASCII => 0x53,0x4D,0x2D,0x41,0x31,0x35,0x35,0x46
                (byte)0x53, (byte)0x4D, (byte)0x2D, (byte)0x41, (byte)0x31, (byte)0x35, (byte)0x35, (byte)0x46,
                // * => 0x2A
                (byte)0x2a,
                // \x08 => 0x08
                (byte)0x08,
                // "SM-A155F" again
                (byte)0x53, (byte)0x4D, (byte)0x2D, (byte)0x41, (byte)0x31, (byte)0x35, (byte)0x35, (byte)0x46,
                // 2 => 0x32
                (byte)0x32,
                // \x07 => 0x07
                (byte)0x07,
                // "samsung" => 0x73, 0x61, 0x6d, 0x73, 0x75, 0x6e, 0x67
                (byte)0x73, (byte)0x61, (byte)0x6d, (byte)0x73, (byte)0x75, (byte)0x6e, (byte)0x67,
                // : => 0x3a
                (byte)0x3a,
                // \x07 => 0x07
                (byte)0x07,
                // "samsung" again
                (byte)0x73, (byte)0x61, (byte)0x6d, (byte)0x73, (byte)0x75, (byte)0x6e, (byte)0x67,
                // @ => 0x40
                (byte)0x40,
                //  space => 0x20
                (byte)0x20,
                // 'J' => 0x4A
                (byte)0x4A,
                // '_' => 0x5F
                (byte)0x5F,
                // 'com.spotify.music/d6a6dced4a85f24204bf9505ccc1fce114cadb32/e9fb7c78-87df-4081-922d-730ba34115b2'
                (byte)0x63, (byte)0x6f, (byte)0x6d, (byte)0x2e, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x74,
                (byte)0x69, (byte)0x66, (byte)0x79, (byte)0x2e, (byte)0x6d, (byte)0x75, (byte)0x73, (byte)0x69,
                (byte)0x63, (byte)0x2f, (byte)0x64, (byte)0x36, (byte)0x61, (byte)0x36, (byte)0x64, (byte)0x63,
                (byte)0x65, (byte)0x64, (byte)0x34, (byte)0x61, (byte)0x38, (byte)0x35, (byte)0x66, (byte)0x32,
                (byte)0x34, (byte)0x32, (byte)0x30, (byte)0x34, (byte)0x62, (byte)0x66, (byte)0x39, (byte)0x35,
                (byte)0x30, (byte)0x35, (byte)0x63, (byte)0x63, (byte)0x63, (byte)0x31, (byte)0x66, (byte)0x63,
                (byte)0x65, (byte)0x31, (byte)0x31, (byte)0x34, (byte)0x63, (byte)0x61, (byte)0x64, (byte)0x62,
                (byte)0x33, (byte)0x32, (byte)0x2f, (byte)0x65, (byte)0x39, (byte)0x66, (byte)0x62, (byte)0x37,
                (byte)0x63, (byte)0x37, (byte)0x38, (byte)0x2d, (byte)0x38, (byte)0x37, (byte)0x64, (byte)0x66,
                (byte)0x2d, (byte)0x34, (byte)0x30, (byte)0x38, (byte)0x31, (byte)0x2d, (byte)0x39, (byte)0x32,
                (byte)0x32, (byte)0x64, (byte)0x2d, (byte)0x37, (byte)0x33, (byte)0x30, (byte)0x62, (byte)0x61,
                (byte)0x33, (byte)0x34, (byte)0x31, (byte)0x31, (byte)0x35, (byte)0x62, (byte)0x32,
                // \x12\x10 => 0x12,0x10
                (byte)0x12, (byte)0x10,
                // 'f2ef802338d8edfd' => ASCII => 0x66, 0x32, ...
                (byte)0x66, (byte)0x32, (byte)0x65, (byte)0x66, (byte)0x38, (byte)0x30, (byte)0x32, (byte)0x33,
                (byte)0x33, (byte)0x38, (byte)0x64, (byte)0x38, (byte)0x65, (byte)0x64, (byte)0x66, (byte)0x64
        };



        RequestBody protoBody = RequestBody.create(
                CLIENTTOKEN_PAYLOAD,
                MediaType.parse("application/x-protobuf")
        );

        // 6) Example request:
        assert url != null;
        Request req = new Request.Builder()
                .url(url)
                .post(protoBody)
                .build();

        // 7) Execute the call & retrieve body:
        try (Response resp = client.newCall(req).execute()) {
            System.out.println("Response => " + resp.code() + " " + resp.message());
            if (resp.body() != null) {
                // The real Spotify response is also Protobuf, so printing it as string may be gibberish
                System.out.println("Response body bytes => " + resp.body().contentLength());
                return resp.body().toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * Returns the system default X509TrustManager.
     * (OkHttp needs an X509TrustManager to do sslSocketFactory(...) properly.)
     */
    private static X509TrustManager defaultTrustManager() throws Exception {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);
        TrustManager[] tms = tmf.getTrustManagers();
        for (TrustManager tm : tms) {
            if (tm instanceof X509TrustManager) {
                return (X509TrustManager) tm;
            }
        }
        throw new IllegalStateException("No X509TrustManager found");
    }

    /**
     * If you truly want to skip all checks, you can replace the default trust manager
     * with this one. Not recommended in production, obviously.
     */
    static class TrustAllX509TrustManager implements X509TrustManager {
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }



    /**
     * New method that uses impersonator to create an iOS-like or Chrome-like TLS fingerprint,
     * then calls the same Spotify endpoint with the same request body.
     */
//    public static String fetchSpotifyImpersonator() throws Exception {
//        // 1) Choose an impersonation type:
//        ImpersonatorApi api = ImpersonatorFactory.macChrome();
//
//        // 2) Build OkHttpClient with that fingerprint + your headers
//        OkHttpClientFactory factory = OkHttpClientFactory.create(api);
//        OkHttpClient client = factory.newHttpClient()
//                .newBuilder()
//                .protocols(Arrays.asList(Protocol.HTTP_1_1))
//                .addInterceptor(new OkHttpFullLoggingInterceptor())
//                .addInterceptor(new SpotifyExactHeaderInterceptor())  // <= your custom
//                .addInterceptor(chain -> {
//                    Request request = chain.request();
//                    System.out.println("[IMP] Sending " + request.method() + " to " + request.url());
//                    Response response = chain.proceed(request);
//                    System.out.println("[IMP] Got => " + response.code() + " " + response.message());
//                    return response;
//                })
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(15, TimeUnit.SECONDS)
//                .build();
//
//        // 3) Construct the request as usual
//        HttpUrl url = HttpUrl.parse("https://clienttoken.spotify.com/v1/clienttoken");
//
//        String JSON_PAYLOAD =
//                "{\"client_data\":{\"client_version\":\"1.2.54.219.g19a93a5d\","
//                        + "\"client_id\":\"d8a5ed958d274c2e8ee717e6a4b0971d\","
//                        + "\"js_sdk_data\":{\"device_brand\":\"unknown\",\"device_model\":\"unknown\",\"os\":\"linux\","
//                        + "\"os_version\":\"unknown\",\"device_id\":\"22062ccb1da300b102c40cdcebd88b1c\","
//                        + "\"device_type\":\"computer\"}}}";
//
//        RequestBody jsonBody = RequestBody.create(
//                JSON_PAYLOAD,
//                MediaType.parse("application/json")
//        );
//

//        Request req = new Request.Builder()
//                .url(url)
//                .post(jsonBody)
//                .build();
//
//        // 4) Execute & capture raw bytes
//        try (Response resp = client.newCall(req).execute()) {
//            System.out.println("[IMP] Response => " + resp.code() + " " + resp.message());
//            if (resp.body() != null) {
//                byte[] rawBytes = resp.body().bytes();
//                // If you want to return the exact raw bytes, you could do so:
//                // return new String(rawBytes, StandardCharsets.ISO_8859_1);
//                // or parse them as a Protobuf message, etc.
//
//                System.out.println("[IMP] length => " + rawBytes.length);
//                return Arrays.toString(rawBytes);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


    /**

    /** For local testing. */
    public static void main(String[] args) throws Exception {
        String responseBody = fetchSpotify();
        System.out.println("Body => " + responseBody.toString());

//        System.out.println("\n=== Using impersonator (chrome) Approach ===");
//        String impersonatorResult = fetchSpotifyImpersonator();
//        System.out.println("Body => " + impersonatorResult);
    }
}