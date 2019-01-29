package io.simpo.simpobutton.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;

import java.util.Objects;

import io.simpo.simpobutton.R;

public class SimpoDialog extends DialogFragment implements View.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simpo_fragment, container);
        view.findViewById(R.id.close).setOnClickListener(this);
        WebView webView = view.findViewById(R.id.dialogWebView);
        webView.loadUrl("https://google.com/");
        return view;
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
