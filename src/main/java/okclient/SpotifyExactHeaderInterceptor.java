package okclient;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/** Interceptor that injects the same or similar headers as the real Spotify Web/Android client */
class SpotifyExactHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                // Overwrite or add whichever headers you want:
                .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0")
                .header("Accept", "application/json")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Referer", "https://open.spotify.com/")
                .header("Origin", "https://open.spotify.com")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header("TE", "trailers")
                .header("Connection", "keep-alive")
                .header("Host", "clienttoken.spotify.com");

        // If you’re sending Protobuf, set it accordingly.
        // If you’re truly mimicking the web JSON, use "application/json".
        // builder.header("Content-Type", "application/x-protobuf");

        return chain.proceed(builder.build());
    }
}
