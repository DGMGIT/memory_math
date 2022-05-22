package au.edu.jcu.my.memory_math.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameDisplay.Play;
import au.edu.jcu.my.memory_math.game.gameDisplay.setting.Setting;
import au.edu.jcu.my.memory_math.game.gameDisplay.statistics.Statistics;

public class ModeSelector extends AppCompatActivity {

    private String username;
    private GameData gameData;

    private TextView HSEasy;
    private TextView HSMedium;
    private TextView HShard;

    private Button easy;
    private Button medium;
    private Button hard;

    private Button statistics;
    private Button setting;

    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);

        username = getIntent().getStringExtra("username");
        gameData = new GameData(this);

        HSEasy = findViewById(R.id.Highscore_easy);
        setHighscores(0, HSEasy);
        HSMedium = findViewById(R.id.Highscore_medium);
        setHighscores(1, HSMedium);
        HShard = findViewById(R.id.Highscore_hard);
        setHighscores(2, HShard);

        easy = findViewById(R.id.button_mode_easy);
        easy.setOnClickListener(v -> buttonPressed("easy", 1));
        medium = findViewById(R.id.button_mode_medium);
        medium.setOnClickListener(v -> buttonPressed("medium", 1));
        hard = findViewById(R.id.button_mode_hard);
        hard.setOnClickListener(v -> buttonPressed("hard", 1));

        statistics = findViewById(R.id.button_statistics);
        statistics.setOnClickListener(v -> buttonPressed("hard", 2));

        setting = findViewById(R.id.button_Settings);
        setting.setOnClickListener(v -> buttonPressed("hard", 3));


    }


    public void setHighscores(int i, TextView v) {
        List<String> scores = gameData.getSelectBased("USERNAME", "MODE", 2, username);
        String score = scores.get(i);
        v.setText(String.format("Highscore: %s", score));
    }

    public void buttonPressed(String mode, int i) {
        if (i == 1){
            Intent intent = new Intent(this, Play.class);
            intent.putExtra("username", username);
            intent.putExtra("mode", mode);
            startActivity(intent);
        }
        if (i == 2){
            Intent intent = new Intent(this, Statistics.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        if (i == 3){
            Intent intent = new Intent(this, Setting.class);
            intent.putExtra("speed", speed);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (abc) : {
                if (resultCode == Activity.RESULT_OK) {
                    // your stuff
                }
                break;
            }
        }
}