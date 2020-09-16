package io.simpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.simpo.simpobutton.model.SimpoInstance;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoPlatform;
import io.simpo.simpobutton.model.SimpoUser;
import io.simpo.simpobutton.model.SimpoGeneral;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpoPlatform.init("edc559b217e0d70ee45ee31efac344d9a5ead012bb",
                new SimpoOptions(new SimpoUser("", ""),
                        "",
                        true,
                        SimpoGeneral.SimpoWidgetPosition.BOTTOM_RIGHT,
                        60 , 60),
                this);

        findViewById(R.id.open_interface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpoInstance.open();
            }
        });
        findViewById(R.id.open_new_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DummyActivity.class));
            }
        });

    }
}
