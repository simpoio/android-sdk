package io.simpo.simpobutton.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getArguments().getString(URL_ARG));
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Simpo.close(getActivity());
    }
}
