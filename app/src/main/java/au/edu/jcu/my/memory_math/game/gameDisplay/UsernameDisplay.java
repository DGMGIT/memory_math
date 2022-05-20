package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.jcu.my.memory_math.R;


public class UsernameDisplay extends Fragment {


    public UsernameDisplay(){

    }

    View root;
    String username;
    String Mode;
    TextView usernameDisplay;
    TextView modeDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_username_display, container, false);

        Play play = (Play) getActivity();

        usernameDisplay = root.findViewById(R.id.usernameDisplay);
        modeDisplay = root.findViewById(R.id.modeDisplay);

        assert play != null;
        usernameDisplay.setText(play.getUsername());
        modeDisplay.setText(play.getMode());

        return root;
    }
}