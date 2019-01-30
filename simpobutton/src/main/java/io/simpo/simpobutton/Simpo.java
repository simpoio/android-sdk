package io.simpo.simpobutton;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final Simpo INSTANCE = new Simpo();

    private SimpoOptions simpoOptions;
    private String ucid;

    private Simpo() {}

    public static void init(String ucid, SimpoOptions options){
        INSTANCE.simpoOptions = options;
        INSTANCE.ucid = ucid;
    }

    public static void show(final AppCompatActivity activity, ViewGroup viewGroup) {

        WebView webView = new WebView(activity);

        webView.loadUrl(String.format(widgetStagingUrl, INSTANCE.ucid));
        webView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout child = new RelativeLayout(activity);
        child.setClickable(false);
        child.setLayoutParams(lp);
        RelativeLayout.LayoutParams layoutParams = getLayoutParamsForButton();
        String url = generateInterfaceURL();
        webView.setWebViewClient(new SimpoWebViewClient(activity, url));
        child.addView(webView, layoutParams);
        viewGroup.addView(child);
    }

    @NonNull
    private static String generateInterfaceURL() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return String.format(interfaceStagingUrl, INSTANCE.ucid, gson.toJson(INSTANCE.simpoOptions));

    }

    @NotNull
    private static RelativeLayout.LayoutParams getLayoutParamsForButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(INSTANCE.simpoOptions.getWidth(), INSTANCE.simpoOptions.getHeight());
        switch (INSTANCE.simpoOptions.getPosition()) {
            case TOP_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                break;
            case TOP_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                break;
            case BOTTOM_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                break;
            case BOTTOM_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                break;
        }
        return layoutParams;
    }

    public static void open(AppCompatActivity activity){
       SimpoDialog.newInstance(generateInterfaceURL()).showNow(Objects.requireNonNull(activity.getSupportFragmentManager()), null);
    }

    public static void close(){

    }
}
