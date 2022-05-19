package au.edu.jcu.my.memory_math;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends Fragment implements View.OnClickListener {

    View root;
    Context thisContext;
    GameData gameData;
    TextView LoginUsername;
    TextView loginPassword;
    Button loginButton;
    Button loginButtonLeft;
    Button loginButtonRight;
    int userID = 0;

    public Login() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        thisContext = getActivity();
        gameData = new GameData(thisContext);

        LoginUsername = root.findViewById(R.id.loginUsername);
        setLoginUsername();

        loginPassword = root.findViewById(R.id.loginPassword);


        loginButton = root.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordChecker();
            }
        });

        loginButtonLeft = root.findViewById(R.id.loginButtonLeft);
        loginButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressedLeft();
            }
        });

        loginButtonRight = root.findViewById(R.id.loginButtonRight);
        loginButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressedRight();
            }
        });


        return root;
    }

    public void setLoginUsername() {
        List<String> users = gameData.getSelect("USERS", 0);
        String user = users.get(userID);
        if (user.equals("Guest")) {
            root.findViewById(R.id.loginPassword).setVisibility(View.GONE);
        } else {
            root.findViewById(R.id.loginPassword).setVisibility(View.VISIBLE);

//            loginPassword.setVisibility(View.VISIBLE);
        }
        LoginUsername.setText(user);
    }


    public void passwordChecker() {
        String givenPassword = loginPassword.getText().toString();
        List<String> Passwords = gameData.getSelect("USERS", 1);
        String userPassword = Passwords.get(userID);
        if (givenPassword.equals(userPassword)) {
            buttonPressed();
        }else {
            Toast.makeText(thisContext, "Wrong Password", Toast.LENGTH_SHORT).show();
        }

    }

    public void buttonPressedLeft() {
        userID -= 1;
        int maxUsers = gameData.numUsers();
        if (userID < 0) {
            userID = maxUsers - 1;
        }
        setLoginUsername();
    }

    public void buttonPressedRight() {
        userID += 1;
        int maxUsers = gameData.numUsers();
        if (userID >= maxUsers) {
            userID = 0;
        }
        setLoginUsername();
    }

    public void buttonPressed() {

        List<String> users = gameData.getSelect("USERS", 0);
        String name = users.get(userID);

        Intent intent = new Intent(thisContext, ModeSelector.class);
        intent.putExtra("username",name);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {

    }

}