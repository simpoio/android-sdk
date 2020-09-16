package io.simpo.simpobutton.model;

import android.util.Log;

import io.simpo.simpobutton.BuildConfig;

public class UtilsGeneral {

    public static void SimpoLog(String tag, String text) {
        if(SimpoConfig.IS_PROD) {

        } else {
            Log.e(tag, text);
        }
    }

    public enum SimpoWidgetPosition {
        BOTTOM_LEFT(0),
        BOTTOM_RIGHT(1),
        TOP_LEFT(2),
        TOP_RIGHT(3);

        private final int value;

        private SimpoWidgetPosition(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
