package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CapSolver {
    public static String API_KEY = "CAP-0CE48AE2FA17948932EEEE1575211F9C";
    public static String SITE_KEY = "6LeO36obAAAAALSBZrY6RYM1hcAY7RLvpDDcJLy3";
    private static final Gson gson = new Gson();

    public static String capsolver(String website_url) throws IOException, InterruptedException {
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> task = new HashMap<>();
        task.put("type", "ReCaptchaV2TaskProxyLess");
        task.put("websiteKey", SITE_KEY);
        task.put("websiteURL", website_url);
        task.put("isInvisible", false);
        task.put("userAgent", "Mozilla/5.0 (Linux; Android 7.1.2; SM-N976N Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/112.0.5615.135 Mobile Safari/537.36");
        task.put("minScore", 0.6);
        task.put("pageAction", "challenge");
        param.put("clientKey", API_KEY);
        param.put("task", task);
        String taskId = createTask(param);

        if (Objects.equals(taskId, "")) {
            System.out.println("Failed to create task");
            return null;
        }
        System.out.println("Got taskId: " + taskId + " / Getting result...");
        while (true) {
            Thread.sleep(1000);
            String token = getTaskResult(taskId);
            if (Objects.equals(token, null)) {
                continue;
            }
            System.out.println(token);
            return token;
        }
    }

    public static String requestPost(String url, Map<String, Object> param) throws IOException {
        URL ipapi = new URL(url);
        HttpURLConnection c = (HttpURLConnection) ipapi.openConnection();
        c.setRequestMethod("POST");
        c.setDoOutput(true);

        try (OutputStream os = c.getOutputStream()) {
            os.write(gson.toJson(param).getBytes("UTF-8"));
        }

        c.connect();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()))) {
            return reader.lines().reduce("", String::concat);
        }
    }

    public static String createTask(Map<String, Object> param) throws IOException {
        String parsedJsonStr = requestPost("https://api.capsolver.com/createTask", param);
        JsonObject responseJson = gson.fromJson(parsedJsonStr, JsonObject.class);
        return responseJson.get("taskId").getAsString();
    }

    public static String getTaskResult(String taskId) throws IOException {
        Map<String, Object> param = new HashMap<>();
        param.put("clientKey", API_KEY);
        param.put("taskId", taskId);
        String parsedJsonStr = requestPost("https://api.capsolver.com/getTaskResult", param);
        JsonObject responseJson = gson.fromJson(parsedJsonStr, JsonObject.class);

        String status = responseJson.get("status").getAsString();
        if (status.equals("ready")) {
            JsonObject solution = responseJson.getAsJsonObject("solution");
            return solution.get("gRecaptchaResponse").getAsString();
        }
        if (status.equals("failed") || responseJson.get("errorId").getAsInt() != 0) {
            System.out.println("Solve failed! response: " + parsedJsonStr);
            return "error";
        }
        return null;
    }
}