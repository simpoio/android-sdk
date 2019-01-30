package io.simpo.simpobutton.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import io.simpo.simpobutton.fragment.SimpoDialog;
import okhttp3.HttpUrl;

public class SimpoWebViewClient extends WebViewClient {

    private static final String TAG = "Simpo Widget";
    private AppCompatActivity activity;
    private HttpUrl interfaceUrl;

    public SimpoWebViewClient(AppCompatActivity activity, HttpUrl interfaceUrl) {
        this.activity = activity;
        this.interfaceUrl = interfaceUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals("simpo://widget.click")) {
            SimpoDialog.newInstance(interfaceUrl).showNow(Objects.requireNonNull(getFragmentManager(activity)), null);
        }
        return true;
    }

    private static FragmentManager getFragmentManager(AppCompatActivity activity) {
        try{

            return activity.getSupportFragmentManager();

        } catch (ClassCastException e) {
            Log.d(TAG, "Can't get the fragment manager with this");
        }
        return null;
    }

}
