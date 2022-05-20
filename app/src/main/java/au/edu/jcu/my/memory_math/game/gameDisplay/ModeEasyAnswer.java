package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import au.edu.jcu.my.memory_math.R;


public class ModeEasyAnswer extends Fragment {

    public ModeEasyAnswer() {
        // Required empty public constructor
    }

    View root;

    TextView getGuess;
    Button done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_mode_easy_answer, container, false);

        getGuess = root.findViewById(R.id.guess);
        done = root.findViewById(R.id.buttonDone);
        done.setOnClickListener(view -> buttonPressed());

        return root;

    }

    private void buttonPressed() {
        Play play = (Play) getActivity();
        assert play != null;
        String result = (String) getGuess.getText();
        play.checkResults(Integer. parseInt(result));
    }
}