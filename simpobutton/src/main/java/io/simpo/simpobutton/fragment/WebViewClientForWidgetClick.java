package io.simpo.simpobutton.fragment;

import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.simpo.simpobutton.model.SimpoConfig;
import io.simpo.simpobutton.model.SimpoPlatform;
import io.simpo.simpobutton.model.SimpoGeneral;

public class WebViewClientForWidgetClick extends WebViewClient {

    private final String TAG = "WebViewClientForWidgetClick";
    private SimpoPlatform simpoPlatform;

    public WebViewClientForWidgetClick(SimpoPlatform simpo) {
        simpoPlatform = simpo;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        super.shouldOverrideUrlLoading(view, url);

        if(url.equalsIgnoreCase(SimpoConfig.LINK_WIDGET_TAP)) {
            SimpoGeneral.SimpoLog(TAG, "Simpo SDK: received simpo message: " + SimpoConfig.LINK_WIDGET_TAP);
            simpoPlatform.open();
        }

        return true;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        view.setVisibility(View.GONE);
    }
}
