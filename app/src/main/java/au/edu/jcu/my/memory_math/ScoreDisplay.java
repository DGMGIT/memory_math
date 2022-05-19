package au.edu.jcu.my.memory_math;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreDisplay extends Fragment {

    public ScoreDisplay() {
        // Required empty public constructor
    }

    private View root;
    private String score;
    private TextView scoreDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_score_display, container, false);

        if(score.equals("")){

        }

        return root;
    }
}