package io.simpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.simpo.simpobutton.Simpo;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Simpo.addWidget(this);

    }
}
