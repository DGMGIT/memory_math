package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameEngine.NumberGame;

public class Play extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor accelerometer;

    public NumberGame numberGame;

    private String username;
    private String mode;
    private int score = 0;
    private int[] all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //import data
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_display2, ModeStartWOutSensor.class, null)
                    .commit();

            username = getIntent().getStringExtra("username");
            mode = getIntent().getStringExtra("mode");
        }

        // starts game
        setValues();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            // Failure! No magnetometer.
        }
    }

    public void setValues() {
        numberGame = new NumberGame();
        int startingDices = startingDices();
        System.out.println("test mode switch: " + startingDices);
        numberGame.gameSetUp(startingDices);
        gameLoop(0);
    }

    public void gameLoop(int i) {
        if (i != 0) {
            if (numberGame.checkResults(i)) {
                changeFragment("ModeStartWOutSensor");
            } else {
                finish();
            }
        }
        all = numberGame.runGame();
//        addDiceToBackground(this, all.length);
    }

    public String getUsername() {
        return username;
    }

    public String getMode() {
        return mode;
    }

    public int[] getAll() {
        return all;
    }

    public int getScore() {
        return score;
    }

    public int startingDices() {
        switch (mode) {
            case "easy":
                return 3;
            case "medium":
                return 6;
            case "hard":
                return 9;
        }
        return 0;
    }


    public void changeFragment(String OpenFragment) {
        // Create new fragment and transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment
        switch (OpenFragment) {
            case "ModeRun":
                transaction.replace(R.id.game_display2, ModeRun.class, null);
                break;
            case "ModeAnswer":
                transaction.replace(R.id.game_display2, ModeAnswer.class, null);
                break;
            case "ModeStartWOutSensor":
                transaction.replace(R.id.game_display2, ModeStartWOutSensor.class, null);
                break;
            case "ModeStartWSensor":
                transaction.replace(R.id.game_display2, ModeStartWOutSensor.class, null);
                break;
        }

// Commit the transaction
        transaction.commit();
    }
}