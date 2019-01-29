package io.simpo.simpobutton.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import io.simpo.simpobutton.fragment.SimpoDialog;

public class SimpoWebViewClient extends WebViewClient {

    private static final String TAG = "Simpo Widget";
    private Context context;

    public SimpoWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return true;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (url.equals("simpo://widget.click")) {
            new SimpoDialog().showNow(Objects.requireNonNull(getFragmentManager(context)), null);
        }
    }



    private static FragmentManager getFragmentManager(Context context) {
        try{
            final FragmentActivity activity = (FragmentActivity) context;

            return activity.getSupportFragmentManager();

        } catch (ClassCastException e) {
            Log.d(TAG, "Can't get the fragment manager with this");
        }
        return null;
    }

}
