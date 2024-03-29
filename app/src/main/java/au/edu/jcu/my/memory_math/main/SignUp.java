package au.edu.jcu.my.memory_math.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.ModeSelector;

public class SignUp extends Fragment implements View.OnClickListener {

    View root;
    Context thisContext;
    GameData gameData;
    TextView signupUsername;
    TextView signupPassword;
    Button signupButton;

    public void signup() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        thisContext = getActivity();
        gameData = new GameData(thisContext);

        signupUsername = root.findViewById(R.id.signupUsername);
        signupPassword = root.findViewById(R.id.signupPassword);

        signupButton = root.findViewById(R.id.signupButton);
        signupButton.setOnClickListener(v -> addNew(signupUsername.getText().toString(), signupPassword.getText().toString()));

        return root;
    }

    public void buttonPressed() {
        Intent intent = new Intent(thisContext, ModeSelector.class);
        startActivity(intent);
//        getActivity().finish();
    }

    public void addNew(String name, String password) {
        List<String> usernames = gameData.getSelect("USERS", 0);
        int maxUsers = gameData.numRows("USERS");
        for (int i = 0; i < maxUsers; i++) {
            String username = usernames.get(i);
            if (username.equals(name)) {
                Toast.makeText(thisContext, "username already created", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        gameData.adduser(name, password);
        buttonPressed();
    }

    @Override
    public void onClick(View v) {

    }

}