package okclient;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class SpotifyHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Build new request with custom headers
        Request newReq = original.newBuilder()
                // From the logs, one typical Spotify UA is:
                // "Spotify/9.0.2.459 Android/34 (SM-A155F)"
                .header("User-Agent", "Spotify/9.0.2.459 Android/34 (SM-A155F)")

                //  client token (this might rotate, so it may not always work)
                .header("client-token", "AACwZeBZiVD2QwByTXbhQyTzZTrt3mWvWbd4rG3C97votCpw/2lxV8/M9bVpBqxecKyx9Yy9vWpaiWZ/U3pXyDosy8aJKNywXzJ+2KM1wr8l9OONBT9q3oq9TFZmuXlwFBSGERxBQRqinm5+ZZX39vPHDfeBCuzbkB4nWy2m880aYdMPrLnuKRFxUJENFHXaNNZRviGN1gDHX2mEbtMy8aYafcZxqgzpjK18CPc+kzEwuUu/DqpfoK5/vkawlg8CO9wjNcFWu7dog2MCii6vXVpccMLuc+2DnzPQlteRRGjNUUuFGixOv/u9/9SuSVi0l9Z2rtQEcr/uNw4CLJxLgvXmzZU6wwYy1/xbo/sUkbgkQlsiH0uDTsP4ivc/U2Y")

                // No-Webgate-Authentication:true
                .header("No-Webgate-Authentication", "true")

                // Accept: application/protobuf (or application/x-protobuf)
                .header("Accept", "application/protobuf")

                // Add more Spotify-like headers if you want:
                .header("Spotify-App-Version", "9.0.2.459")
                .header("X-Client-Id", "9a8d2f0ce77a4e248bb71fefcb557637")
                .header("App-Platform", "Android")

                // If your logs show "Accept-Language:en-GB;q=1, en-US;q=0.5" etc.
                .header("Accept-Language", "en-GB;q=1, en-US;q=0.5")

                .build();

        return chain.proceed(newReq);
    }
}
