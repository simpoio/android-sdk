package io.simpo;

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
        Simpo.init("4cgtr29zxft8kwuwwtcwdym6ulp21fsiehbkjzncmu4",
                new SimpoOptions(new SimpoUser("", ""), "", true, SimpoWidgetPosition.BOTTOM_RIGHT, 180 , 180));
        Simpo.addWidget(this);

        findViewById(R.id.open_interface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Simpo.open(MainActivity.this);
            }
        });

        //Simpo.close(this);
    }
}
