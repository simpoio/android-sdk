package io.simpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.simpo.simpobutton.fragment.SimpoDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SimpoDialog().showNow(getSupportFragmentManager(), null);
    }
}
