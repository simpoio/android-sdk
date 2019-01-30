package io.simpo.simpobutton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import io.simpo.simpobutton.fragment.SimpoDialog;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.util.SimpoWebViewClient;

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
        sWebView.setWebViewClient(new SimpoWebViewClient(context, String.format(interfaceStagingUrl, ucid, ucid)));
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
