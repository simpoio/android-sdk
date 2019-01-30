package io.simpo.simpobutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.util.SimpoWebViewClient;
import okhttp3.HttpUrl;

public class Simpo {
    private static final String widgetStagingUrl = "https://staging-app.simpo.io/v1/%s/mobile/widget";
    private static final String interfaceStagingUrl = "https://staging-app.simpo.io/v1/%s/mobile/app?data=%s";
    private static final String widgetUrl = "https://app.simpo.io/v1/%s/mobile/widget";
    private static final String interfaceUrl = "https://app.simpo.io/v1/%s/mobile/app?data=%s";
    @SuppressLint("StaticFieldLeak")
    private static WebView sWebView;
    private static SimpoOptions sSimpoOptions;
    private static String ucid;

    public static void init(Context context, String ucid, SimpoOptions options) {
        Simpo.ucid = ucid;
        WebView webView = new WebView(context);
        sSimpoOptions = options;
        webView.loadUrl(String.format(widgetStagingUrl, ucid));
        webView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(Color.TRANSPARENT);
        sWebView = webView;
    }

    public static void show(final Context context, ViewGroup viewGroup) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout child = new RelativeLayout(context);
        child.setClickable(false);
        child.setLayoutParams(lp);
        RelativeLayout.LayoutParams layoutParams = getLayoutParamsForButton();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();


        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("app.simpo.io")
                .addPathSegments("v1/4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4/mobile")
                .addPathSegment("app")
                .addQueryParameter("data", gson.toJson(sSimpoOptions))
                .build();
        sWebView.setWebViewClient(new SimpoWebViewClient(context, url));
        child.addView(sWebView, layoutParams);
        viewGroup.addView(child);
    }

    @NotNull
    private static RelativeLayout.LayoutParams getLayoutParamsForButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(sSimpoOptions.getWidth(), sSimpoOptions.getHeight());
        switch (sSimpoOptions.getPosition()) {
            case "top_left":
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                break;
            case "top_right":
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                break;
            case "bottom_left":
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                break;
            case "bottom_right":
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                break;
        }
        return layoutParams;
    }


    public static void destroy() {
        sWebView = null;
        sSimpoOptions = null;
    }
}
