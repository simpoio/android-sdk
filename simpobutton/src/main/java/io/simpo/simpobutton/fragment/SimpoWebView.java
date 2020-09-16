package io.simpo.simpobutton.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebView;

public class SimpoWebView extends WebView {

    public SimpoWebView(Context context) {
        super(context);
    }

    public SimpoWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpoWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SimpoWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

}
