package io.simpo.simpobutton.model;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import io.simpo.simpobutton.R;
import io.simpo.simpobutton.fragment.SimpoInterface;
import io.simpo.simpobutton.fragment.WebViewClientForWidgetClick;

import static android.view.View.GONE;

public class SimpoPlatform implements ISSimpo {

    private static String SIMPOPLATFORM_TAG = "SimpoPlatform";

    public String jsonParams;
    public String script;
    private String ucid;
    private SimpoOptions simpoOptions;
    private boolean isInitialized;

    private WebView widgetView;
    private SimpoInterface simpoInterface;

    public void onReceiveValue(Object result) {
        if(result != null) {
            String json = result.toString();
            ReportsHelper.LogError(json, "SimpoPlatform=>UpdateParams(" + jsonParams + "); script:" + script + "\"");
            SimpoGeneral.SimpoLog(SIMPOPLATFORM_TAG, "Simpo SDK: OnReceiveValue:" + json);
        }
    }

    public static void init(String ucid, SimpoOptions simpoOptions, FragmentActivity activity) {
        if(SimpoInstance.instance != null) {
            SimpoGeneral.SimpoLog(SIMPOPLATFORM_TAG, "Simpo SDK: Simpo.Instance object has been already initialized");
            return;
        }

        SimpoInstance.instance = new SimpoPlatform(ucid, simpoOptions, activity);
    }

    private SimpoPlatform(String ucid, SimpoOptions simpoOptions, FragmentActivity activity) {
        this.simpoOptions = simpoOptions;
        this.ucid = ucid;
        add(activity);
    }

    @Override
    public void open() {
        if(widgetView != null) {
            widgetView.setVisibility(GONE);
        }

        if(simpoInterface != null) {
            simpoInterface.open(true);
        }
    }

    public void openWithOutCallSimpoOpen() {
        if(widgetView != null)
            widgetView.setVisibility(GONE);

        if(simpoInterface != null)
            simpoInterface.open(false);
    }

    @Override
    public void close() {
        if(widgetView != null)
            widgetView.setVisibility(View.VISIBLE);
        simpoInterface.close();
    }

    @Override
    public void updateParams(final String params) {
        final String strScript = SimpoConfig.GetUpdateParamsScript(params);
        simpoInterface.webView.loadUrl("javascript:" + strScript);
        jsonParams = params;
        script = strScript;

        SimpoGeneral.SimpoLog(SIMPOPLATFORM_TAG, "Simpo SDK:" + strScript);

    }

    private void add(FragmentActivity activity) {
        if(simpoOptions.isShowWidget()) {
            widgetView = new WebView(activity);
            widgetView.loadUrl(SimpoConfig.getWidgetUrl(ucid));
            widgetView.setVisibility(View.VISIBLE);
            widgetView.setBackgroundColor(Color.TRANSPARENT);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            RelativeLayout child = new RelativeLayout(activity);
            child.setClickable(false);
            child.setLayoutParams(lp);

            RelativeLayout.LayoutParams layoutParams = getLayoutParamsForButton(activity);
            widgetView.setWebViewClient(new WebViewClientForWidgetClick(this));
            child.addView(widgetView, layoutParams);
            widgetView.setId(R.id.widget);
            ((ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0)).addView(child);
        }

        String interfaceUrl = SimpoConfig.getInterfaceUrl(ucid, simpoOptions);

        simpoInterface = SimpoInterface.newInstance(interfaceUrl);
        simpoInterface.setSimpo(this);
        simpoInterface.showNow(activity.getSupportFragmentManager(), SIMPOPLATFORM_TAG);
    }

    @Override
    public Boolean getIsInitialized() {
        return isInitialized;
    }

    public void setInitialized(Boolean flag) {
        this.isInitialized = flag;
    }

    public void deinitialize(FragmentActivity activity) {
        isInitialized = false;
        ((ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0)).removeView((RelativeLayout)widgetView.getParent());
        this.ucid = null;
        this.simpoOptions = null;
        SimpoInstance.instance = null;
    }

    @NotNull
    private  RelativeLayout.LayoutParams getLayoutParamsForButton(Activity activity) {
        float density = activity.getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)(simpoOptions.getWidth() * density), (int)(simpoOptions.getHeight() * density));

        switch (simpoOptions.getPosition()) {
            case TOP_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                else
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

                layoutParams.setMargins(0, (int)((float)60 * density), 0, 0);
                break;
            case TOP_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                else
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                layoutParams.setMargins(0, (int)((float)60 * density), 0, 0);
                break;
            case BOTTOM_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                else
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case BOTTOM_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                else
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
        }
        return layoutParams;
    }
}
