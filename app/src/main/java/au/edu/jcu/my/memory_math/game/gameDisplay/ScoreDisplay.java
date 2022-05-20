package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.jcu.my.memory_math.R;

public class ScoreDisplay extends Fragment {

    public ScoreDisplay() {
        // Required empty public constructor
    }

    View root;
    private String score;
    private TextView scoreDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_score_display, container, false);

        Play play = (Play) getActivity();

        scoreDisplay = root.findViewById(R.id.scoreDisplay);

        assert play != null;
        String text = "" + play.getScore();
        scoreDisplay.setText(text);


        return root;
    }

    public void setScore(String i) {scoreDisplay.setText(i);;
    }
}