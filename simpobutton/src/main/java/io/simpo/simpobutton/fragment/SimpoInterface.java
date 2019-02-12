package io.simpo.simpobutton.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import io.simpo.simpobutton.BuildConfig;
import io.simpo.simpobutton.R;
import io.simpo.simpobutton.Simpo;

final public class SimpoInterface extends DialogFragment {

    public static final String URL_ARG = "url";
    private Simpo simpo;
    private View view;
    private WebView webView;
    private static final String TAG = "SimpoInterface";
    private boolean isFirstStart = true;
    private boolean isShowed;


    public static SimpoInterface newInstance(String url) {
        SimpoInterface simpoInterface = new SimpoInterface();

        Bundle args = new Bundle();
        args.putString(URL_ARG, url);
        simpoInterface.setArguments(args);

        return simpoInterface;
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("simpo://interface.close")) {
                simpo.close();
                return true;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            try {
                startActivity(intent);
                simpo.close();
            } catch (ActivityNotFoundException e) {
                Log.e(TAG , " You don't have any browser to open web page", e);
            }
            return true;
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(AppCompatDialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.simpo_fragment, null);
        alertDialogBuilder.setView(view);


        AlertDialog alertDialog = alertDialogBuilder.create();


        webView = view.findViewById(R.id.dialogWebView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebContentsDebuggingEnabled(true);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(getArguments().getString(URL_ARG));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return alertDialog;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if(isFirstStart) {
            Dialog dialog = getDialog();
            if (dialog != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width, height);
            }
            getDialog().hide();
            isFirstStart = false;
        }
        if(!isShowed){
            getDialog().hide();
        }
    }

    public void open() {
        getDialog().show();
        webView.loadUrl("javascript:(function(){if(window.simpo && window.simpo.open) {window.simpo.open();} else {counter = 10; var interval = setInterval(function() {if(window.simpo && window.simpo.open) {window.simpo.open();clearInterval(interval)}counter--;if(!counter){clearInterval();}}, 1000)}})();");
        isShowed = true;
    }

    public void close() {
        if(getDialog() != null) {
            getDialog().hide();
        }
        isShowed = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(simpo != null) {
            simpo.close();
        }
    }

    public void setSimpo(Simpo simpo) {
        this.simpo = simpo;
    }

}
