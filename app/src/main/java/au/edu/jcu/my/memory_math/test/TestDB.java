package au.edu.jcu.my.memory_math.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;

public class TestDB extends AppCompatActivity {

    ListView userList;
    ListView modeList;
    GameData gameData;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        gameData = new GameData(this);

        userList = findViewById(R.id.userList);
        modeList = findViewById(R.id.modeList);

        displayAll();

    }

    private void displayAll() {
        List<String> resultAllUsers = gameData.getAll("USERS");
        List<String> resultAllMode = gameData.getAll("MODE");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        userList.setAdapter(adapter);
        adapter.addAll(resultAllUsers);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        modeList.setAdapter(adapter);
        adapter.addAll(resultAllMode);
    }
}