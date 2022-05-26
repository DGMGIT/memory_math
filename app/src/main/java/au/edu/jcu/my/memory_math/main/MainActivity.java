package au.edu.jcu.my.memory_math.main;

//main activity of app contains the login and signup features also used for testing during development.

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;

public class MainActivity extends AppCompatActivity {

    GameData gameData;
    MediaPlayer myBGMusic;

    Button login_Button;
    Button signup_Button;

    View login;
    View signup;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameData = new GameData(this);

        myBGMusic=MediaPlayer.create(MainActivity.this,R.raw.bg1);

        myBGMusic.start();

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

    @Override
    protected void onPause() {
        super.onPause();
        myBGMusic.pause();
        position = myBGMusic.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myBGMusic.seekTo(position);
        myBGMusic.start();
        myBGMusic.setLooping(true);
    }
}