package au.edu.jcu.my.memory_math.game.gameDisplay.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.MessageFormat;

import au.edu.jcu.my.memory_math.R;

public class Setting extends AppCompatActivity {

    private SeekBar speedControl;
    private TextView currentSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        speedControl = findViewById(R.id.speedControl);
        currentSpeed = findViewById(R.id.currentSpeed);
        speedControl.setMax(9);
        speedControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentSpeed.setText(MessageFormat.format("{0} seconds", i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}