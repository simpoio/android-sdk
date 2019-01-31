package io.simpo.simpobutton.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.simpo.simpobutton.R;
import io.simpo.simpobutton.Simpo;

final public class SimpoInterface extends DialogFragment {

    public static final String URL_ARG = "url";

    private boolean isRedirected;
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("simpo://interface.close")) {
                Simpo.close(getActivity());
            }
            view.loadUrl(url);
            isRedirected = true;
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            view.setVisibility(View.GONE);
        }

        ProgressDialog progressDialog;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isRedirected = false;
        }

        public void onLoadResource(WebView view, String url) {
            if (!isRedirected) {
                if (progressDialog == null && getActivity() != null) {
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

        }

        public void onPageFinished(WebView view, String url) {
            try {
                isRedirected = true;

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    };;

    public static SimpoInterface newInstance(String url) {
        SimpoInterface simpoInterface = new SimpoInterface();

        Bundle args = new Bundle();
        args.putString(URL_ARG, url);
        simpoInterface.setArguments(args);

        return simpoInterface;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(AppCompatDialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.simpo_fragment, container);
        WebView webView = view.findViewById(R.id.dialogWebView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.clearCache(true);
        webView.loadUrl(getArguments().getString(URL_ARG));
        return view;
    }
}