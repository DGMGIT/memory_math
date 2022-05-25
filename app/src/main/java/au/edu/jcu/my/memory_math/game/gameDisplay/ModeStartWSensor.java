package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import au.edu.jcu.my.memory_math.R;

public class ModeStartWSensor extends Fragment implements SensorEventListener {

    public ModeStartWSensor() {
        // Required empty public constructor
    }

    View root;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    FragmentActivity act;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mode_start_w_out_sensor, container, false);


        act = getActivity();

        senSensorManager = (SensorManager) requireActivity().getSystemService(getActivity().SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener((SensorEventListener) getActivity(), senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        return root;
    }

    public void onPause() {
        super.onPause();
        senSensorManager.unregisterListener((SensorEventListener) act);
    }

    public void onResume() {
        super.onResume();
        senSensorManager.registerListener((SensorEventListener) act, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

//    public void onSensorChanged(SensorEvent sensorEvent) {


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;


                if (speed > SHAKE_THRESHOLD) {
                    buttonPressed();
                }

            }

            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void buttonPressed() {
        Play play = (Play) getActivity();
        assert play != null;
        play.changeFragment("ModeRun");
    }
}