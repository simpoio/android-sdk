package io.simpo.simpobutton.model;

import android.util.Log;

import java.util.ArrayList;

public class SimpoInstance {
    public static ISSimpo instance;
    public static ArrayList<Closure> onReady = new ArrayList<>();

    public static void open() {
        if(!getIsInitialized()) {
            Log.d("SimpleInstance", "Simpo SDK: Simpo has not been initialized yet");
            return;
        }
        instance.open();
    }

    public static void close() {
        if(!getIsInitialized()) {
            Log.d("SimpleInstance", "Simpo SDK: Simpo has not been initialized yet");
            return;
        }
        instance.close();
    }

    public static boolean getIsInitialized() {
        return (instance != null && instance.getIsInitialized());
    }

    public static void updateParams(String jsonParams) {
        instance.updateParams(jsonParams);
    }

}
