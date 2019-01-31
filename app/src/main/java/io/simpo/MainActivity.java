package io.simpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.simpo.simpobutton.Simpo;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoUser;
import io.simpo.simpobutton.model.SimpoWidgetPosition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Simpo.init("4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4",
                new SimpoOptions(new SimpoUser("", ""), "", true, SimpoWidgetPosition.BOTTOM_RIGHT, 180 , 180));
        Simpo.addWidget(this);
        Simpo.open(this);
        Simpo.close(this);
    }
}
