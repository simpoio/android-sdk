package io.simpo.simpobutton;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
    private static final String widgetUrl = BuildConfig.SERVER_URL +"/v1/%s/mobile/widget";
    private static final String interfaceUrl = BuildConfig.SERVER_URL +"/v1/%s/mobile/app?data=%s";

    private static final String INTERFACE_TAG = "SimpoInterface";

    private SimpoOptions simpoOptions;
    private String ucid;
    private WebView widgetView;
    private SimpoInterface simpoInterface;

    public Simpo(String ucid, SimpoOptions simpoOptions,  FragmentActivity activity) {
        this.simpoOptions = simpoOptions;
        this.ucid = ucid;
        add(activity);
    }


    public void open(){
        if(widgetView != null) widgetView.setVisibility(View.GONE);
        if(simpoInterface != null) {
            simpoInterface.open();
        }

    }

    public void close() {
        if (widgetView != null) widgetView.setVisibility(View.VISIBLE);
        simpoInterface.close();
    }

    private void add(final FragmentActivity activity) {
        if(simpoOptions.isShowWidget()) {
            widgetView = new WebView(activity);
            widgetView.loadUrl(String.format(widgetUrl, ucid));
            widgetView.setVisibility(View.VISIBLE);
            widgetView.setBackgroundColor(Color.TRANSPARENT);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            RelativeLayout child = new RelativeLayout(activity);
            child.setClickable(false);
            child.setLayoutParams(lp);
            RelativeLayout.LayoutParams layoutParams = getLayoutParamsForButton();
            widgetView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.equals("simpo://widget.click")) {
                        open();
                    }
                    return true;
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                    view.setVisibility(View.GONE);
                }

            });
            child.addView(widgetView, layoutParams);
            widgetView.setId(R.id.widget);
            ((ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0)).addView(child);
        }
        simpoInterface = SimpoInterface.newInstance(generateInterfaceURL());
        simpoInterface.setSimpo(this);
        simpoInterface.showNow(activity.getSupportFragmentManager(), INTERFACE_TAG);
    }

    @NonNull
    private  String generateInterfaceURL() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return String.format(interfaceUrl , ucid, gson.toJson(simpoOptions));

    }

    @NotNull
    private  RelativeLayout.LayoutParams getLayoutParamsForButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(simpoOptions.getWidth(), simpoOptions.getHeight());
        switch (simpoOptions.getPosition()) {
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

}
