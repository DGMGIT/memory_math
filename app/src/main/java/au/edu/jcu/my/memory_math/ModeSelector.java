package au.edu.jcu.my.memory_math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ModeSelector extends AppCompatActivity {

    private String username;
    private GameData gameData;

    private TextView HSEasy;
    private TextView HSMedium;
    private TextView HShard;

    private Button easy;
    private Button medium;
    private Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);

        username = getIntent().getStringExtra("username");

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            username = extras.getString("username");
//        }

        gameData = new GameData(this);



        HSEasy = findViewById(R.id.Highscore_easy);
        setHighscores(0, HSEasy);
        HSMedium = findViewById(R.id.Highscore_medium);
        setHighscores(1, HSMedium);
        HShard = findViewById(R.id.Highscore_hard);
        setHighscores(2, HShard);

        easy = findViewById(R.id.button_mode_easy);
        easy.setOnClickListener(v -> buttonPressed());
        medium = findViewById(R.id.button_mode_medium);
        medium.setOnClickListener(v -> buttonPressed());
        hard = findViewById(R.id.button_mode_hard);
        hard.setOnClickListener(v -> buttonPressed());


    }


    public void setHighscores(int i, TextView v) {
        List<String> scores = gameData.getSelectBased("USERNAME", "MODE", 2, username);
        String score = scores.get(i);
        v.setText(String.format("Highscore: %s", score));
    }

    public void buttonPressed() {

    }
}