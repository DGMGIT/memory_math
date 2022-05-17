package au.edu.jcu.my.memory_math;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameData = new GameData(this);

        if(!gameData.isEmpty("USERS")){
            gameData.adduser("Guest", "");
        }
    }
}