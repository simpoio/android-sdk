package io.simpo.simpobutton.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.simpo.simpobutton.R;
import okhttp3.HttpUrl;
import okhttp3.internal.http.HttpHeaders;

public class SimpoDialog extends DialogFragment implements View.OnClickListener {

    public static SimpoDialog newInstance(HttpUrl url) {
        SimpoDialog simpoDialog = new SimpoDialog();

        Bundle args = new Bundle();
        args.putString("url", String.valueOf(url));
        simpoDialog.setArguments(args);

        return simpoDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simpo_fragment, container);
        view.findViewById(R.id.close).setOnClickListener(this);
        WebView webView = view.findViewById(R.id.dialogWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
       // Map<String, String> extraHeaders = new HashMap<>();
       // extraHeaders.put("If-None-Match", "");;
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient() {});
        webView.loadUrl(getArguments().getString("url"));
       // webView.loadUrl("http://google.com");
        return view;
    }


    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
