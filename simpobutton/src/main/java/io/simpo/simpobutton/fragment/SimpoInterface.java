package io.simpo.simpobutton.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;

import io.simpo.simpobutton.R;
import io.simpo.simpobutton.model.SimpoPlatform;

public class SimpoInterface extends AppCompatDialogFragment {

    private static final String URL_ARG = "url";
    private final WebViewClientForInterfaceClose webViewClient = new WebViewClientForInterfaceClose();
    private SimpoPlatform simpo;
    private View view;
    public WebView webView;
    private boolean isFirstStart = true;
    private boolean isShowed;

    private static final String TAG = "SimpoInterface";

    public static SimpoInterface newInstance(String url) {
        SimpoInterface simpoInterface = new SimpoInterface();

        Bundle args = new Bundle();
        args.putString(URL_ARG, url);
        simpoInterface.setArguments(args);

        return simpoInterface;
    }

    public void setSimpo(SimpoPlatform simpo) {
        this.simpo = simpo;
        webViewClient.simpoPlatform = simpo;
        webViewClient.simpoInterface = this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(AppCompatDialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    @NonNull
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.simpo_fragment, null);
        alertDialogBuilder.setView(view);

        AlertDialog alertDialog = alertDialogBuilder.create();

        webView = view.findViewById(R.id.dialogWebView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
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
            if (dialog != null)
            {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width, height);
            }
            if(getDialog() != null) getDialog().hide();
            isFirstStart = false;
        }

        if(!isShowed && getDialog() != null) getDialog().hide();
    }

    public void open(boolean calJSSimpoOpen) {
        if(getDialog() != null) getDialog().show();
        if(calJSSimpoOpen) {
            webView.loadUrl("javascript:(function(){if(window.simpo && window.simpo.open) {window.simpo.open();} else {counter = 10; var interval = setInterval(function() {if(window.simpo && window.simpo.open) {window.simpo.open();clearInterval(interval)}counter--;if(!counter){clearInterval();}}, 1000)}})();");
        }
        isShowed = true;
    }

    public void close() {
        if(getDialog() != null) {
            getDialog().hide();
        }
        isShowed = false;
    }

    public void deinitialize(FragmentActivity activity) {
        simpo.deinitialize(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        if(simpo != null) {
            simpo.close();
        }
    }

}
