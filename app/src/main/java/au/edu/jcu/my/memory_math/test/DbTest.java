package au.edu.jcu.my.memory_math.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;


public class DbTest extends Fragment implements View.OnClickListener {

    View root;
    Context thisContext;
    Button testButton;
    Button testAddButton;
    GameData gameData;

    public DbTest() {

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
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressed();
            }
        });

        //        testButton.setOnClickListener(view -> buttonPressed());

        testAddButton = root.findViewById(R.id.testAddButton);
        testAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew();
            }
        });

        //        testAddButton.setOnClickListener(view -> addNew());

        // Inflate the layout for this fragment

        return root;
    }

    public void buttonPressed() {
        Intent intent = new Intent(thisContext, TestDB.class);
        startActivity(intent);
    }

    public void addNew() {
//        gameData.deleteall("test");
//        gameData.adduser("new user 01", "new password 01");
//        gameData.adduser("new user 02", "new password 02");
//        gameData.adduser("new user 03", "easy");
//        gameData.adduser("test", "easy");
        scoreSubmit("EASY", 101);

    }

    @Override
    public void onClick(View v) {

    }

    public void scoreSubmit(String mode, int score) {

        List<Integer> S = gameData.getSelectBasedInt("USERNAME", mode, 1, "test");
        int SL = S.size();
        List<Integer> HS = gameData.getSelectBasedInt("USERNAME", "MODE", 1, "test");
        Integer lastScore = S.get(SL - 1);

        System.out.println("test updata ++++++++++=============+++++++++++++");
        switch (mode) {
            case "EASY":
                Integer hs0 = HS.get(0);
                if (score > hs0) {
                    gameData.updateHighscore("test", "MODE", score, mode);
                    System.out.println("Test: " + score + mode);
                }
                break;
            case "MEDIUM":
                Integer hs1 = HS.get(1);
                if (score > hs1) {
                    gameData.updateHighscore("test", "MODE", score, mode);
                    System.out.println("Test: " + score + mode);
                }
                break;
            case "HARD":
                Integer hs2 = HS.get(2);
                if (score > hs2) {
                    gameData.updateHighscore("test", "MODE", score, mode);
                    System.out.println("Test: " + score + mode);
                }
                break;
        }

        System.out.println(S);

        String CTL;
        if (score > lastScore) {
            CTL = "+";
        } else if (score < lastScore) {
            CTL = "-";
        } else {
            CTL = "~";
        }
        System.out.println("Test: " + score + CTL);
        gameData.addScore("test", mode, score, CTL);

    }
}