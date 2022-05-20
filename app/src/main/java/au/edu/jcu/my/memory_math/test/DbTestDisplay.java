package au.edu.jcu.my.memory_math.test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;

public class DbTestDisplay extends Fragment {

    View root;
    Context thisContext;
    ListView userList;
    ListView modeList;
    GameData gameData;
    ArrayAdapter<String> adapter;

    public DbTestDisplay(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_db_test, container, false);

        thisContext = getActivity();

        gameData = new GameData(thisContext);

        userList = root.findViewById(R.id.userList);
        modeList = root.findViewById(R.id.modeList);

        displayAll();

        return root;
    }

    private void displayAll() {
        List<String> resultAllUsers = gameData.getAll("USERS");
        List<String> resultAllMode = gameData.getAll("MODE");

        adapter = new ArrayAdapter<>(thisContext, android.R.layout.simple_list_item_1);
        userList.setAdapter(adapter);
        adapter.addAll(resultAllUsers);

        adapter = new ArrayAdapter<>(thisContext, android.R.layout.simple_list_item_1);
        modeList.setAdapter(adapter);
        adapter.addAll(resultAllMode);
    }
}