package io.simpo.simpobutton.model;

import android.os.Build;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ReportsHelper {

    public static void LogError(String msg, String context) {

        Map<String, String> nvc = new HashMap<>();
        nvc.put("tags", "native, android");
        nvc.put("message", msg);
        nvc.put("location", "native-mobile");
        nvc.put("context", context);

        try {
            OutputStream out = null;
            URL url = new URL(SimpoConfig.REPORTING_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            } else {
                writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
            }

            writer.write(nvc.toString());
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
