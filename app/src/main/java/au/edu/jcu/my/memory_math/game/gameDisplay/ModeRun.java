package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import au.edu.jcu.my.memory_math.R;
import au.edu.jcu.my.memory_math.main.MainActivity;

public class ModeRun extends Fragment {

    public ModeRun() {
        // Required empty public constructor
    }

    MediaPlayer myBGMusicRun;

    View root;
    TextView numDisplay;
    int n;
    int t;
    int[] all;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mode_run, container, false);
        numDisplay = root.findViewById(R.id.numDisplay);

        Play play = (Play) getActivity();
        assert play != null;
        all = play.getAll();

        myBGMusicRun=MediaPlayer.create(getActivity(),R.raw.bg2);

        myBGMusicRun.start();

        int speed = play.getSpeed();
        System.out.println("test speed get: " + speed);

        int tickspeed = 1000 * speed;

        int numOfItem = all.length;
        System.out.println("test length: " + numOfItem);
        int timer = numOfItem * (tickspeed * 2);
        n = 0;
        t = 0;

        new CountDownTimer(timer, tickspeed) {
            @Override
            public void onTick(long l) {
                System.out.println("test tick: " + n);
                if (n%2 == 0){
                    String text = "" + all[t];
                    numDisplay.setText(text);
                    System.out.println("test current num: " + t + " num value: " +text);
                    t++;
                }else {
                    if (t < numOfItem){
                        numDisplay.setText("+");
                    }else {
                        numDisplay.setText("=");
                    }
                }
                n++;
            }

            public void onFinish() {
                Play play = (Play) getActivity();
                assert play != null;
                play.changeFragment("ModeAnswer");
            }
        }.start();
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        myBGMusicRun.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        myBGMusicRun.start();
        myBGMusicRun.setLooping(true);
    }
}
