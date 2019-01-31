package io.simpo;

import android.app.Application;

import io.simpo.simpobutton.Simpo;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoUser;
import io.simpo.simpobutton.model.SimpoWidgetPosition;

public class SimpoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Simpo.init("4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4",
                new SimpoOptions(new SimpoUser("", ""),
                        "",
                        true,
                        SimpoWidgetPosition.BOTTOM_RIGHT,
                        180 , 180));

    }
}
