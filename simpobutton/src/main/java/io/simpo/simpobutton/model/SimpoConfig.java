package io.simpo.simpobutton.model;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.simpo.simpobutton.BuildConfig;

public class SimpoConfig {
    public static Boolean IS_PROD = BuildConfig.IS_PROD;
    public static String ENV = SimpoConfig.IS_PROD ? "https://app.simpo.io" : "https://staging-app.simpo.io";
    private static final String SDK_VERSION = "v2.2.1";
    private static String WIDGET_URL = ENV + "/v1/%s/mobile/widget?mobileVersion=" + SDK_VERSION;
    private static String INTERFACE_URL = ENV + "/v1/%s/mobile/app?mobileVersion=" + SDK_VERSION + "&data=%s";

    public static String REPORTING_URL = SimpoConfig.IS_PROD ? "https://app.simpo.io/v1/error-reporting" : "https://staging-app.simpo.io/v1/error-reporting";

    public static final String LINK_WIDGET_TAP = "simpo://widget.click";
    public static final String LINK_INTERFACE_CLOSE_CLICK = "simpo://interface.close";
    public static final String LINK_ANNOUNCEMENT_OPENED = "simpo://announcement.opened";
    public static final String LINK_NPS_OPENED = "simpo://nps.opened";
    public static final String LINK_SURVEY_OPENED = "simpo://survey.opened";
    public static final String LINK_GET_CURRENT_PAGE = "simpo://getCurrentPage";
    public static final String LINK_READY = "simpo://ready";

    public static String getWidgetUrl(String ucid) {
        String url = String.format(WIDGET_URL, ucid);
        Log.d("SimpoConfig","Simpo SDK: " + url);
        return url;
    }

    public static String getInterfaceUrl(String ucid, SimpoOptions simpoOptions) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String encodedOptions = "";
        try {
            encodedOptions = URLEncoder.encode(gson.toJson(simpoOptions),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return String.format(INTERFACE_URL, ucid, encodedOptions);
    }

    public static String GetUpdateParamsScript(String jsonParams) {
        return "window.simpo.native.updateParams(" + jsonParams + ");";
    }
}
