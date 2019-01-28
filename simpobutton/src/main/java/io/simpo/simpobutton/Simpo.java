package io.simpo.simpobutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import io.simpo.simpobutton.model.SimpoOptions;

public class Simpo {
    @SuppressLint("StaticFieldLeak")
    private static WebView sWebView;
    private static SimpoOptions sSimpoOptions;
    private static final String sButtonContentUrl = "https://staging-app.simpo.io/v1/%s/mobile/widget";

    static void init(Context context, String ucid, SimpoOptions options) {
        WebView webView = new WebView(context);
        sSimpoOptions = options;
        webView.loadUrl(String.format(sButtonContentUrl, ucid));
    }

    static void show(ViewGroup viewGroup, String position) {

    }

    static void destroy() {
        sWebView = null;
    }
}
