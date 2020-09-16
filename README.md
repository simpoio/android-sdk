# Simpo SDK for Android

## Installation
Installation of the SDK done through JitPack.
For instructions go to https://jitpack.io/#simpoio/android-sdk And follow the instructions.

## Documentation
- #### init(ucid: string, options: SimpoOptions)
  
  options(SimpoUser user, String uuid, boolean showWidget, SimpoWidgetPosition position, int widgetWidth, int widgetHeight)
    - user: <optional>:SimpoUser(String email, String name) 
    - uuid - unique client id
    - showWiget: show or hide the widget 
     - position: can receive one of the below values: 
      * BOTTOM_LEFT
      * BOTTOM_RIGHT
      * TOP_LEFT
      * TOP_RIGHT

- #### open()
  must be called after init, will open Simpo interface

### Example of usage
 ```
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
        
        SimpoPlatform.init("exampleUCIDNumber",
                new SimpoOptions(new SimpoUser("", ""),
                        "myclientuuid",
                        true,
                        SimpoGeneral.SimpoWidgetPosition.BOTTOM_RIGHT,
                        60 , 60),
                this);


 ```
