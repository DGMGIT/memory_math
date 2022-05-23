package au.edu.jcu.my.memory_math.game.gameDisplay.setting;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.MessageFormat;

import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.game.gameDisplay.Play;
import au.edu.jcu.my.memory_math.game.gameDisplay.statistics.Statistics;

public class Setting extends AppCompatActivity {

    private SeekBar speedControl;
    private TextView currentSpeed;
    private Button buttonDone;

    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        speed = getIntent().getIntExtra("speed", 4);
        System.out.println("test speed default: " + speed);

        speedControl = findViewById(R.id.speedControl);
        currentSpeed = findViewById(R.id.currentSpeed);
        buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(v -> buttonPressed());

        speedControl.setMax(9);
        speedControl.setProgress(speed - 1);
        currentSpeed.setText(MessageFormat.format("{0} seconds", speed));

        speedControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentSpeed.setText(MessageFormat.format("{0} seconds", i + 1));
                speed = i + 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void buttonPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("speed", speed);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}