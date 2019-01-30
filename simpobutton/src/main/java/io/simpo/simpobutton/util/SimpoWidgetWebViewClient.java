package io.simpo.simpobutton.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import io.simpo.simpobutton.fragment.SimpoDialog;

public class SimpoWidgetWebViewClient extends WebViewClient {

    private AppCompatActivity activity;
    private String interfaceUrl;

    public SimpoWidgetWebViewClient(AppCompatActivity activity, String interfaceUrl) {
        this.activity = activity;
        this.interfaceUrl = interfaceUrl;

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals("simpo://widget.click")) {
            SimpoDialog.newInstance(interfaceUrl).showNow(activity.getSupportFragmentManager(), null);
        }
        return true;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        view.setVisibility(View.GONE);
    }

}
