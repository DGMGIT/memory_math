package au.edu.jcu.my.memory_math.game.gameDisplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import au.edu.jcu.my.memory_math.R;

public class ModeRun extends Fragment {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public ModeRun() {
        // Required empty public constructor
    }

    View root;
    private Handler handler;
    TextView numDisplay;
    int n;
    int t;
    int[] all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_mode_run, container, false);
        numDisplay = root.findViewById(R.id.numDisplay);

        Play play = (Play) getActivity();
        assert play != null;
        all = play.getAll();


        int numOfItem = all.length;
        System.out.println("test length: " + numOfItem);
        int timer = numOfItem * 6000;
        n = 0;
        t = 0;

        new CountDownTimer(timer, 3000) {
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
}