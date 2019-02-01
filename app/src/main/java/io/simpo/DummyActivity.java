package io.simpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.simpo.simpobutton.Simpo;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoUser;
import io.simpo.simpobutton.model.SimpoWidgetPosition;

public class DummyActivity extends AppCompatActivity {

    private Simpo simpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        simpo = new Simpo("4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4",
                new SimpoOptions(new SimpoUser("", ""),
                        "",
                        false,
                        SimpoWidgetPosition.BOTTOM_RIGHT,
                        180 , 180),
                this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpo.open();
    }
}
