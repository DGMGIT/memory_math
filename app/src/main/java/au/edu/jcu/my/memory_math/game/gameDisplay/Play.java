package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameEngine.NumberGame;

public class Play extends AppCompatActivity {

    SensorManager sensorManager;

    public NumberGame numberGame;

    private String username;
    private String mode;

    private int score;
    private int[] all;
    private int speed;

    private TextView scoreDisplay;
    private String ModeStartWOrOutSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //import data
        username = getIntent().getStringExtra("username");
        mode = getIntent().getStringExtra("mode");
        speed = getIntent().getIntExtra("speed", 3);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_display2, ModeStartWSensor.class, null)
                    .commit();
        }

        scoreDisplay = findViewById(R.id.scoreDisplay);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            ModeStartWOrOutSensor = "ModeStartWSensor";
        } else {
            ModeStartWOrOutSensor = "ModeStartWOutSensor";
        }

        // starts game
        setValues();
    }

    public void setValues() {
        numberGame = new NumberGame();
        int startingDices = startingDices();
        System.out.println("test mode switch: " + startingDices);
        numberGame.gameSetUp(startingDices);
        setScore(0);
        gameLoop(0);
    }

    public void gameLoop(int i) {
        if (i != 0) {
            if (numberGame.checkResults(i)) {
                score += i;
                changeFragment(ModeStartWOrOutSensor);
                setScore(score);
            } else {
                end();
            }
        }
        all = numberGame.runGame();
    }

    public void setScore(int i) {
        String text = "" + i;
        scoreDisplay.setText(text);
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

    public int getSpeed() {
        return speed;
    }

    public int startingDices() {
        switch (mode) {
            case "EASY":
                return 3;
            case "MEDIUM":
                return 6;
            case "HARD":
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
                transaction.replace(R.id.game_display2, ModeStartWSensor.class, null);
                break;
        }
        // Commit the transaction
        transaction.commit();
    }

    public void end() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("mode", mode);
        returnIntent.putExtra("score", score);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}