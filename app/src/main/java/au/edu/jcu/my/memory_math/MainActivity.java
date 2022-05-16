package au.edu.jcu.my.memory_math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button testButton;
    Button testAddButton;
    GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameData = new GameData(this);

        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(view -> buttonPressed());

        testAddButton = findViewById(R.id.testAddButton);
        testAddButton.setOnClickListener(view -> addNew());
    }

    public void buttonPressed() {
        Intent intent = new Intent(this, testDB.class);
        startActivity(intent);
    }

    public void addNew() {
        gameData.adduser("new user 01", "new password 01");
        gameData.adduser("new user 02", "new password 02");
    }
}