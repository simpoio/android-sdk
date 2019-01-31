package io.simpo.simpobutton;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import io.simpo.simpobutton.fragment.SimpoInterface;
import io.simpo.simpobutton.model.SimpoOptions;

final public class Simpo {
    private static final String widgetStagingUrl = "https://staging-app.simpo.io/v1/%s/mobile/widget";
    private static final String interfaceStagingUrl = "https://staging-app.simpo.io/v1/%s/mobile/app?data=%s";
    private static final String widgetUrl = "https://app.simpo.io/v1/%s/mobile/widget";
    private static final String interfaceUrl = "https://app.simpo.io/v1/%s/mobile/app?data=%s";

    private static final Simpo INSTANCE = new Simpo();
    public static final String INTERFACE_TAG = "SimpoInterface";

    private SimpoOptions simpoOptions;
    private String ucid;

    private Simpo() {}

    public static void init(String ucid, SimpoOptions options){
        INSTANCE.simpoOptions = options;
        INSTANCE.ucid = ucid;
    }

    public static void showWidget(final AppCompatActivity activity, View viewGroup) {

        WebView webView = new WebView(activity);
        webView.loadUrl(String.format(widgetStagingUrl, INSTANCE.ucid));
        webView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout child = new RelativeLayout(activity);
        child.setClickable(false);
        child.setLayoutParams(lp);
        RelativeLayout.LayoutParams layoutParams = getLayoutParamsForButton();
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals("simpo://widget.click")) {
                    open(activity);
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.setVisibility(View.GONE);
            }

        });
        child.addView(webView, layoutParams);
        webView.setId(R.id.widget);
        ((ViewGroup) ((ViewGroup) viewGroup).getChildAt(0)).addView(child);
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

    public static void open(FragmentActivity activity){
        SimpoInterface.newInstance(generateInterfaceURL()).showNow(activity.getSupportFragmentManager(), INTERFACE_TAG);
        if(INSTANCE.simpoOptions.isShow()) {
            View widget = activity.findViewById(R.id.widget);
            if(widget != null) widget.setVisibility(View.GONE);
        }
    }

    public static void close(FragmentActivity activity){
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(INTERFACE_TAG);
        if(fragment instanceof SimpoInterface) {
            SimpoInterface simpoInterface = (SimpoInterface)fragment;
            if(!simpoInterface.isRemoving()) {
                simpoInterface.dismiss();
            }
        }
        if(INSTANCE.simpoOptions.isShow()) {
            View widget = activity.findViewById(R.id.widget);
            if(widget != null) widget.setVisibility(View.VISIBLE);
        }
    }

}
