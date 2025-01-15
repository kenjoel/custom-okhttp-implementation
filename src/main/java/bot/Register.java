package bot;

import api.AndroidApiRegister;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.conscrypt.Conscrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Proxy;
import java.security.Provider;
import java.security.Security;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Register {
    private ExecutorService executorService;
    static Random random = new Random();
    private volatile boolean running = true;
    private static int NUM_THREADS = 4;
    private static int THREAD_DELAY_MS = 10;
    public static ReentrantLock lock = new ReentrantLock();
    static Gson gson = new Gson();
    static JsonArray devices;
    // Changed to AtomicInteger for thread safety
    static AtomicInteger registered = new AtomicInteger(0);
    private static final Map<Integer, Integer> apiToAndroidMap = new HashMap<>();
    // Changed to AtomicInteger for thread safety
    public static AtomicInteger captchaEncountered = new AtomicInteger(0);
    public static AtomicInteger captchaCount = new AtomicInteger(0);
    public static AtomicInteger nocaptchaCount = new AtomicInteger(0);
    // Maximum total attempts
    private static int MAX_TOTAL_ATTEMPTS = 14000;
    static String COUNTRY = "MX";

    static {
        try {
            devices = gson.fromJson(new FileReader("devices.json"), JsonArray.class);
            int removedCount = 0;

            for (Iterator<JsonElement> iterator = devices.iterator(); iterator.hasNext(); ) {
                JsonElement element = iterator.next();
                JsonElement modelCode = element.getAsJsonObject().get("model_code");

                if (modelCode == null || modelCode.isJsonNull() || modelCode.getAsString().isEmpty()) {
                    iterator.remove();
                    removedCount++;
                }
            }

            System.out.println("Number of devices removed: " + removedCount);
            System.out.println(devices.size() + " devices loaded from devices.json");
            // Initialize API to Android version mapping
            apiToAndroidMap.put(34, 14);  // Android 14
            apiToAndroidMap.put(33, 13);  // Android 13
            apiToAndroidMap.put(32, 12);  // Android 12L
            apiToAndroidMap.put(31, 12);  // Android 12
            apiToAndroidMap.put(30, 11);  // Android 11
            apiToAndroidMap.put(29, 10);  // Android 10
            apiToAndroidMap.put(28, 9);   // Android 9 Pie
            apiToAndroidMap.put(27, 8);   // Android 8.1 Oreo
            apiToAndroidMap.put(26, 8);   // Android 8.0 Oreo
            apiToAndroidMap.put(25, 7);   // Android 7.1 Nougat
            apiToAndroidMap.put(24, 7);   // Android 7.0 Nougat
            apiToAndroidMap.put(23, 6);   // Android 6.0 Marshmallow
            apiToAndroidMap.put(22, 5);   // Android 5.1 Lollipop
            apiToAndroidMap.put(21, 5);   // Android 5.0 Lollipop
            apiToAndroidMap.put(20, 4);   // Android 4.4W KitKat (for Wearables)
            apiToAndroidMap.put(19, 4);   // Android 4.4 KitKat
        } catch (FileNotFoundException e) {
            throw new RuntimeException("devices.json file not found", e);
        }
    }

    public Register(int numThreads, int maxTotalAttempts) {
        NUM_THREADS = numThreads;
        MAX_TOTAL_ATTEMPTS = maxTotalAttempts;
    }
        public void startGeneration() throws IOException, InterruptedException {
            executorService = Executors.newFixedThreadPool(NUM_THREADS);
            CountDownLatch latch = new CountDownLatch(NUM_THREADS);

            for (int i = 0; i < NUM_THREADS; i++) {
                executorService.execute(() -> {
                    try {
                        while (running) {
                            // Atomically check if maximum total attempts reached
                            int currentTotal = captchaCount.get() + nocaptchaCount.get();
                            if (currentTotal >= MAX_TOTAL_ATTEMPTS) {
                                break;
                            }

                            try {

                                ProxyInfo proxyInfo = new ProxyInfo("gw.dataimpulse.com", 823,
                                        "5a3573b10bf038203a1f__cr.us", "8183cec856572dc8");


                                JsonObject selectedDevice = devices.get(random.nextInt(devices.size())).getAsJsonObject();
                                //JsonObject selectedDevice = devices.get(560).getAsJsonObject();
                                JsonObject deviceInfo = new JsonObject();

                                // Copy all properties from selectedDevice to deviceInfo
                                for (String key : selectedDevice.keySet()) {
                                    if (key.equals("api_versions") || key.equals("screen_dimensions") || key.equals("density")) {
                                        // For multi-option fields, select a single option
                                        String[] parts = selectedDevice.get(key).getAsString().split(";");
                                        String selectedOption = parts[random.nextInt(parts.length)];
                                        if (key.equals("density")) {
                                            deviceInfo.addProperty("density", Integer.parseInt(selectedOption));
                                        } else if (key.equals("api_versions")) {
                                            deviceInfo.addProperty("api_version", Integer.parseInt(selectedOption));
                                        } else {
                                            deviceInfo.addProperty("screen_dimensions", selectedOption);
                                        }

                                        // Check if it's the "api_versions" key to add the corresponding Android version (os_version)
                                        if (key.equals("api_versions")) {
                                            int apiVersion = Integer.parseInt(selectedOption); // Convert the selected API version to an integer
                                            Integer androidVersion = getAndroidVersion(apiVersion); // Get the corresponding Android version
                                            deviceInfo.addProperty("os_version", androidVersion); // Add the Android OS version to deviceInfo
                                        }
                                    } else {
                                        deviceInfo.add(key, selectedDevice.get(key));
                                    }
                                }

                                System.out.println(deviceInfo.toString());

                                String model = deviceInfo.get("model").getAsString(); // Copy device model for name, intentional
                                String model_code = deviceInfo.get("model_code").getAsString();
                                String brand = deviceInfo.get("brand").getAsString();
                                String app_version = "9.0.2.459";
                                int api_version = deviceInfo.get("api_version").getAsInt();
                                String manufacturer = deviceInfo.get("manufacturer").getAsString();
                                String screen_dimensions = deviceInfo.get("screen_dimensions").getAsString();
                                int screen_x = Integer.parseInt(screen_dimensions.split("x")[0]);
                                int screen_y = Integer.parseInt(screen_dimensions.split("x")[1]);
                                int density = deviceInfo.get("density").getAsInt();
                                int android_os_version = getAndroidVersion(api_version);

                                //HotmailService hotmailService = new HotmailService();


                                AndroidApiRegister registerApi = new AndroidApiRegister(
                                        proxyInfo,
                                        app_version,
                                        api_version,
                                        model,
                                        brand,
                                        manufacturer,
                                        screen_x,
                                        screen_y,
                                        density,
                                        android_os_version,
                                        model_code
                                );

                                boolean success = registerApi.register();

                                if (success) {
                                    // Increment registered count atomically
                                    registered.incrementAndGet();

                                    //StreamerMetrics.addAccountToDatabaseGenerated(registerApi.email, registerApi.password, COUNTRY, false, registerApi.device_id, deviceInfo, null, registerApi.android_id);
                                    // Save to file with thread safety
                                    lock.lock();
                                    try (FileWriter writer = new FileWriter("generated.txt", true)) {
                                        writer.write(registerApi.email + ":" + registerApi.password + "|" + deviceInfo.toString() + "\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        lock.unlock();
                                    }

                                    // Assuming nocaptchaCount is incremented from another class upon successful registration
                                } else {
                                    System.out.println("Failed to register");
                                    // Assuming captchaCount is incremented from another class upon failed registration
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                // Continue to next attempt
                                continue;
                            }

                            System.out.println("Created (NO CAPTCHA): " + nocaptchaCount.get() + ", " + "Created (CAPTCHA): " + captchaCount.get());

                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }
            // Wait for all threads to finish
            latch.await();
            executorService.shutdown();
        }

    public void stopGeneration() {
        running = false;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        /*ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        // Using CountDownLatch to wait for all threads to finish
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        // Submit NUM_THREADS tasks to the executor
        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.execute(() -> {
                try {
                    while (true) {
                        // Atomically check if maximum total attempts reached
                        int currentTotal = captchaCount.get() + nocaptchaCount.get();
                        if (currentTotal >= MAX_TOTAL_ATTEMPTS) {
                            break;
                        }

                        try {
                            *//*Session.Configuration conf = new Session.Configuration.Builder()
                                    .setCacheEnabled(false)
                                    .setRetryOnChunkError(true)
                                    .setProxyAddress("rp.proxyscrape.com")
                                    .setProxyType(Proxy.Type.HTTP)
                                    .setProxyPort(6060)
                                    .setProxyEnabled(true)
                                    .setProxyUsername("4i4vxwpuhzosxnr-odds-5+100-country-us")
                                    .setProxyPassword("gkcsygexph6x10g")
                                    .setProxyAuth(true)
                                    .setStoreCredentials(false)
                                    .build();*//*

                            *//*Session.Configuration conf = new Session.Configuration.Builder()
                                    .setCacheEnabled(false)
                                    .setRetryOnChunkError(true)
                                    .setProxyAddress("rp.proxyscrape.com")
                                    .setProxyType(Proxy.Type.HTTP)
                                    .setProxyPort(6060)
                                    .setProxyEnabled(true)
                                    .setProxyUsername("4i4vxwpuhzosxnr-odds-5+100-country-us")
                                    .setProxyPassword("gkcsygexph6x10g")
                                    .setProxyAuth(true)
                                    .setStoreCredentials(false)
                                    .build();*//*

                             *//*Session.Configuration conf = new Session.Configuration.Builder()
                                    .setCacheEnabled(false)
                                    .setRetryOnChunkError(true)
                                    .setProxyAddress("mx.smartproxy.com")
                                    .setProxyType(Proxy.Type.HTTP)
                                    .setProxyPort(20000)
                                    .setProxyEnabled(true)
                                    .setProxyUsername("mobiletest2")
                                    .setProxyPassword("pfj9xiE=965cFVqyCw")
                                    .setProxyAuth(true)
                                    .setStoreCredentials(false)
                                    .build();*//*

                            JsonObject selectedDevice = devices.get(random.nextInt(devices.size())).getAsJsonObject();
                            JsonObject deviceInfo = new JsonObject();

                            // Copy all properties from selectedDevice to deviceInfo
                            for (String key : selectedDevice.keySet()) {
                                if (key.equals("api_versions") || key.equals("screen_dimensions") || key.equals("density")) {
                                    // For multi-option fields, select a single option
                                    String[] parts = selectedDevice.get(key).getAsString().split(";");
                                    String selectedOption = parts[random.nextInt(parts.length)];
                                    if (key.equals("density")) {
                                        deviceInfo.addProperty("density", Integer.parseInt(selectedOption));
                                    } else if (key.equals("api_versions")) {
                                        deviceInfo.addProperty("api_version", Integer.parseInt(selectedOption));
                                    } else {
                                        deviceInfo.addProperty("screen_dimensions", selectedOption);
                                    }

                                    // Check if it's the "api_versions" key to add the corresponding Android version (os_version)
                                    if (key.equals("api_versions")) {
                                        int apiVersion = Integer.parseInt(selectedOption); // Convert the selected API version to an integer
                                        Integer androidVersion = getAndroidVersion(apiVersion); // Get the corresponding Android version
                                        deviceInfo.addProperty("os_version", androidVersion); // Add the Android OS version to deviceInfo
                                    }
                                } else {
                                    deviceInfo.add(key, selectedDevice.get(key));
                                }
                            }

                            System.out.println(deviceInfo.toString());

                            String model = deviceInfo.get("model").getAsString(); // Copy device model for name, intentional
                            String model_code = deviceInfo.get("model_code").getAsString();
                            String brand = deviceInfo.get("brand").getAsString();
                            String app_version = "8.9.90.127";
                            int api_version = deviceInfo.get("api_version").getAsInt();
                            String manufacturer = deviceInfo.get("manufacturer").getAsString();
                            String screen_dimensions = deviceInfo.get("screen_dimensions").getAsString();
                            int screen_x = Integer.parseInt(screen_dimensions.split("x")[0]);
                            int screen_y = Integer.parseInt(screen_dimensions.split("x")[1]);
                            int density = deviceInfo.get("density").getAsInt();
                            int android_os_version = getAndroidVersion(api_version);

                            AndroidApiRegister registerApi = new AndroidApiRegister(
                                    conf,
                                    app_version,
                                    api_version,
                                    model,
                                    brand,
                                    manufacturer,
                                    screen_x,
                                    screen_y,
                                    density,
                                    android_os_version,
                                    model_code
                            );

                            boolean success = registerApi.register();

                            if (success) {
                                // Increment registered count atomically
                                registered.incrementAndGet();

                                StreamerMetrics.addAccountToDatabaseGenerated(registerApi.email, registerApi.password, COUNTRY, false, registerApi.device_id, deviceInfo, null, registerApi.android_id);
                                // Save to file with thread safety
                                lock.lock();
                                try (FileWriter writer = new FileWriter("generated.txt", true)) {
                                    writer.write(registerApi.email + ":" + registerApi.password + "|" + deviceInfo.toString() + "\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    lock.unlock();
                                }

                                // Assuming nocaptchaCount is incremented from another class upon successful registration
                            } else {
                                System.out.println("Failed to register");
                                // Assuming captchaCount is incremented from another class upon failed registration
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Continue to next attempt
                            continue;
                        }

                        System.out.println("Created (NO CAPTCHA): " + nocaptchaCount.get() + ", " + "Created (CAPTCHA): " + captchaCount.get());

                        // Optional: Add a small delay to prevent overwhelming the API
                        try {
                            TimeUnit.MILLISECONDS.sleep(THREAD_DELAY_MS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        // Shutdown the executor service after all tasks are submitted
        executorService.shutdown();
        // Wait for all threads to finish
        latch.await();
        System.out.println("\nAll registration tasks completed.");
        System.out.println("Total Registered: " + registered.get());
        System.out.println("Total CAPTCHA: " + captchaCount.get());
        System.out.println("Total NO CAPTCHA: " + nocaptchaCount.get());*/
    }

    static int getAndroidVersion(int apiVersion) {
        return apiToAndroidMap.getOrDefault(apiVersion, 0);
    }
}
