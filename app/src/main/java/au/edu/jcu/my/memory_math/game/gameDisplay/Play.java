package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.stream.IntStream;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameEngine.DiceRoll;

public class Play extends AppCompatActivity {

    private String username;
    private String mode;
    private int score;
    private int startingDices;
    private GameData gameData;
    private DiceRoll diceRoll;

    int sum;


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
            score = 0;
            startingDices();

            gameData = new GameData(this);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else {
                // Failure! No magnetometer.
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public String getMode() {
        return mode;
    }

    public int getScore() {
        return score;
    }


    public int getNumDice() {
        return startingDices;
    }

    public void runFragment() {
        // Create new fragment and transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.game_display2, ModeEasyRun.class, null);

// Commit the transaction
        transaction.commit();
    }


    public void answerFragment() {
        // Create new fragment and transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.game_display2, ModeEasyAnswer.class, null);

// Commit the transaction
        transaction.commit();
    }


    public void startFragment() {
        // Create new fragment and transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.game_display2, ModeEasyAnswer.class, null);

// Commit the transaction
        transaction.commit();
    }

    public int[] runGame() {
        diceRoll = new DiceRoll();
        sum = 0;
        int[] allDice = diceRoll.multiRoll(startingDices, 6);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sum = IntStream.of(allDice).sum();
            System.out.println("test" + sum);
        }

        return allDice;
    }

    public void checkResults(int i) {
        if(i == sum){
            score += sum;
            startFragment();
        }

    }

    public void startingDices() {
        switch (mode) {
            case "easy":
                startingDices = 3;
            case "medium":
                startingDices = 6;
            case "hard":
                startingDices = 9;
        }
    }


}