package io.simpo.simpobutton.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.simpo.simpobutton.model.Closure;
import io.simpo.simpobutton.model.SimpoConfig;
import io.simpo.simpobutton.model.SimpoInstance;
import io.simpo.simpobutton.model.SimpoPlatform;
import io.simpo.simpobutton.model.SimpoGeneral;

public class WebViewClientForInterfaceClose extends WebViewClient {

    private static final String URL_ARG = "url";
    private final String TAG = "WebViewClientForInterfaceClose";
    public SimpoPlatform simpoPlatform;
    public SimpoInterface simpoInterface;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        super.shouldOverrideUrlLoading(view, url);

        if(url.equalsIgnoreCase(SimpoConfig.LINK_INTERFACE_CLOSE_CLICK)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_INTERFACE_CLOSE_CLICK);
            simpoPlatform.close();
            return true;
        } else if(url.equalsIgnoreCase(SimpoConfig.LINK_ANNOUNCEMENT_OPENED)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_ANNOUNCEMENT_OPENED);
            simpoPlatform.openWithOutCallSimpoOpen();
            return true;
        } else if(url.equalsIgnoreCase(SimpoConfig.LINK_NPS_OPENED)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_NPS_OPENED);
            simpoPlatform.openWithOutCallSimpoOpen();
            return true;
        } else if(url.equalsIgnoreCase(SimpoConfig.LINK_SURVEY_OPENED)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_SURVEY_OPENED);
            simpoPlatform.openWithOutCallSimpoOpen();
            return true;
        } else if(url.equalsIgnoreCase(SimpoConfig.LINK_GET_CURRENT_PAGE)) {
            if(simpoInterface.getActivity() == null) return true;
            String jsCode = "simpo.native.setCurrentPage('" + simpoInterface.getActivity().getTitle() + "');";
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_GET_CURRENT_PAGE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                simpoInterface.webView.evaluateJavascript(jsCode, null);
            } else {
                simpoInterface.webView.loadUrl(jsCode);
            }

            return true;
        } else if(url.equalsIgnoreCase(SimpoConfig.LINK_READY)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_READY);
            simpoPlatform.setInitialized(true);
            if(SimpoInstance.onReady != null && SimpoInstance.onReady.size() > 0) {
                for(int i = 0; i < SimpoInstance.onReady.size(); i ++) {
                    Closure closure = SimpoInstance.onReady.get(i);
                    closure.exec();
                }
                SimpoInstance.onReady.clear();
                SimpoGeneral.SimpoLog(TAG, "Simpo SDK: call SimpoInstance.OnReady");
            }
            return true;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            simpoInterface.startActivity(intent);
            simpoPlatform.close();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            SimpoGeneral.SimpoLog(TAG, "You don't have any browser to open web page.");
        }

        return true;
    }
}
