package io.simpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import io.simpo.simpobutton.Simpo;
import io.simpo.simpobutton.fragment.SimpoDialog;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Simpo.init(this, "4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4",
                new SimpoOptions(new SimpoUser(), "sdfasdfasdf", true, "bottom_right", 256 , 256));
        Simpo.show(this, (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0));
    }
}
