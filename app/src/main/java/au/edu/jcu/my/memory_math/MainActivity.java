package au.edu.jcu.my.memory_math;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GameData gameData;

    Button login_Button;
    Button signup_Button;

    View login;
    View signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameData = new GameData(this);

        if(!gameData.isEmpty("USERS")){
            gameData.adduser("Guest", "");
        }
        login = findViewById(R.id.fragment_login);
        signup = findViewById(R.id.fragment_signup);

        login_Button = findViewById(R.id.loginMenu);
        login_Button.setOnClickListener(view -> show(login, signup));

        signup_Button = findViewById(R.id.signupMenu);
        signup_Button.setOnClickListener(view -> show(signup, login));
    }

    private void show(View x, View y) {
        x.setVisibility(View.VISIBLE);
        y.setVisibility(View.GONE);


    }
}