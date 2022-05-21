package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import au.edu.jcu.my.memory_math.R;

public class ModeStartWOutSensor extends Fragment implements View.OnClickListener {

    public ModeStartWOutSensor() {
        // Required empty public constructor
    }

    View root;
    Button start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mode_start_w_out_sensor, container, false);

        start = root.findViewById(R.id.buttonRun);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressed();
            }
        });

        return root;

    }

    public void buttonPressed() {
        Play play = (Play) getActivity();
        assert play != null;
        play.changeFragment("ModeRun");
    }

    @Override
    public void onClick(View view) {

    }
}