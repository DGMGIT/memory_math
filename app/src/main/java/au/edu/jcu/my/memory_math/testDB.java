package au.edu.jcu.my.memory_math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class testDB extends AppCompatActivity {


    private ListView userList;
    GameData gameData;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        gameData = new GameData(this);

        List<String> resultID = gameData.getSelect(0);
        List<String> resultUserName = gameData.getSelect(1);
        List<String> resultPassword = gameData.getSelect(2);
        System.out.println("result:" + resultID.toString() + resultUserName.toString() + resultPassword.toString());

        List<String> resultAll = gameData.getAll();


        userList = findViewById(R.id.userList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        userList.setAdapter(adapter);
        adapter.addAll(resultAll);
    }
}