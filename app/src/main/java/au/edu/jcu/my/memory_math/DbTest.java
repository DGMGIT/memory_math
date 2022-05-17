package au.edu.jcu.my.memory_math;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DbTest extends Fragment implements View.OnClickListener {

    View root;
    Context thisContext;
    Button testButton;
    Button testAddButton;
    GameData gameData;

    public DbTest(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_db_test, container, false);

        thisContext = getActivity();
        gameData = new GameData(thisContext);

//        return root;

//        root = inflater.inflate(R.layout.fragment_db_test, container, false);
//
//        assert container != null;
//        thisContext = container.getContext();
//        gameData = new GameData(thisContext);
//
        testButton = root.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                buttonPressed();
            }
        });

        //        testButton.setOnClickListener(view -> buttonPressed());

        testAddButton = root.findViewById(R.id.testAddButton);
        testAddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addNew();
            }
        });

        //        testAddButton.setOnClickListener(view -> addNew());

        // Inflate the layout for this fragment

        return root;
    }

    public void buttonPressed() {
        Intent intent = new Intent(thisContext, testDB.class);
        startActivity(intent);
    }

    public void addNew() {
        gameData.adduser("new user 01", "new password 01");
        gameData.adduser("new user 02", "new password 02");
        gameData.adduser("new user 03", "easy");

    }

    @Override
    public void onClick(View v) {

    }
}