package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameEngine.NumberGame;

public class Play extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor accelerometer;

    GameData gameData;
    public NumberGame numberGame;

    private String username;
    private String mode;

    private int score;
    private int[] all;
    private int speed;

    private TextView scoreDisplay;

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
            speed = getIntent().getIntExtra("speed", 3);
        }

        scoreDisplay = findViewById(R.id.scoreDisplay);

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
        setScore(0);
        gameLoop(0);
    }

    public void gameLoop(int i) {
        if (i != 0) {
            if (numberGame.checkResults(i)) {
                score += i;
                changeFragment("ModeStartWOutSensor");
                setScore(score);
            } else {
                scoreSubmit();
                finish();

            }
        }
        all = numberGame.runGame();
    }

    public void scoreSubmit() {
        gameData = new GameData(this);

        int SL = gameData.numRows(mode);
        List<Integer> S = gameData.getSelectBasedInt("USERNAME", mode, 1, username);
        List<Integer> HS = gameData.getSelectBasedInt("USERNAME", "MODE", 1, username);
        Integer lastScore = S.get(SL);

        switch (mode) {
            case "EASY":
                Integer hs0 = HS.get(0);
                if(score > hs0){
                    gameData.updateHighscore(username,mode,score);
                    System.out.println("Test: "+ score + mode);
                }
                break;
            case "MEDIUM":
                Integer hs1 = HS.get(1);
                if(score > hs1){
                    gameData.updateHighscore(username,mode,score);
                    System.out.println("Test: "+ score + mode);
                }
                break;
            case "HARD":
                Integer hs2 = HS.get(2);
                if(score > hs2){
                    gameData.updateHighscore(username,mode,score);
                    System.out.println("Test: "+ score + mode);
                }
                break;
        }

        System.out.println(S);

        String CTL;
        if(score > lastScore){
            CTL = "+";
        }else if (score < lastScore) {
            CTL = "-";
        } else {
            CTL = "~";
        }
        System.out.println("Test: "+ score + CTL);
        gameData.addScore(username, mode, score, CTL);

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
                transaction.replace(R.id.game_display2, ModeStartWOutSensor.class, null);
                break;
        }

// Commit the transaction
        transaction.commit();
    }
}