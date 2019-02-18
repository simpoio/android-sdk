package io.simpo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.simpo.simpobutton.Simpo;
import io.simpo.simpobutton.model.SimpoOptions;
import io.simpo.simpobutton.model.SimpoUser;
import io.simpo.simpobutton.model.SimpoWidgetPosition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Simpo simpo =new Simpo("269a08e2bc49da75e932e946ae5450a6a4260effc8",
                new SimpoOptions(new SimpoUser("", ""),
                        "",
                        true,
                        SimpoWidgetPosition.BOTTOM_RIGHT,
                        180 , 180),
                this);

        findViewById(R.id.open_interface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpo.open();
            }
        });
        findViewById(R.id.open_new_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DummyActivity.class));
            }
        });

        //simpo.close();
    }
}
