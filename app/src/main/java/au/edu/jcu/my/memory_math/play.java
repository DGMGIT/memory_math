package au.edu.jcu.my.memory_math;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class play extends AppCompatActivity {

    private String username;
    private String mode;
    private GameData gameData;

    SensorManager sensorManager;
    Sensor accelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_display2, ModeStartWOutSensor.class, null)
                    .commit();

            username = getIntent().getStringExtra("username");
            mode = getIntent().getStringExtra("mode");
            gameData = new GameData(this);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else {
                // Failure! No magnetometer.
            }
        }
    }
}