package au.edu.jcu.my.memory_math.game.background;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.edu.jcu.my.memory_math.R;

public class GameBackground extends Fragment {

    View root;

    private Handler mainHandler;
    private Runnable redraw;
    private boolean isRedrawing;
    private OuterspaceView outerspaceView;
    private List<Dice> dice;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_game_background, container, false);

        audioManager = new AudioManager(getActivity());
        dice = new ArrayList<>();

        // setup redrawing
        mainHandler = new Handler();
        outerspaceView = root.findViewById(R.id.outerspaceView);
        outerspaceView.setDice(dice);
        redraw = () -> {
            if (isRedrawing) {
                moveDucks();
                outerspaceView.invalidate();
                mainHandler.postDelayed(redraw, 24);
            }
        };

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        isRedrawing = true;
        mainHandler.post(redraw);
        audioManager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        isRedrawing = false;
        audioManager.pause();
    }

    public void addDiceToBackground(View view, int x) {
        Random random = new Random();
        int i;
        for (i = 0; i < x; i++){
            if (dice.size() < 100) {
                int width = outerspaceView.getWidth();
                int height = outerspaceView.getHeight();
                float scale = 0.25f + random.nextFloat() * (1 - 0.25f);
                dice.add(new Dice(createBitmap(scale), width, height));
            }
        }
//        else if (view == remove) {
//            if (dice.size() > 0) {
//                int randomIndex = random.nextInt(dice.size());
//                dice.remove(randomIndex);
//            }
//        }
    }

    private void moveDucks() {
        for (Dice dice : dice) {
            boolean bounced = dice.move();
            if (bounced) {
                Random random = new Random();
                float speed = 0.5f + random.nextFloat() * (2 - 0.5f); // range: [0.5,2)
                float volume = 0.5f + random.nextFloat() * (1 - 0.5f); // [0.5, 1)
                audioManager.playSound(speed, volume);
            }
        }
    }

    private Bitmap createBitmap(float scale) {
        Random random = new Random();
        int upperbound = 6;
        //generate random values from 0-24
        int numOfDice = random.nextInt(upperbound);

        Point size = Utilities.computeSizeInDP(getActivity().getWindowManager(), 0.5f);
        Bitmap bitmap;
        switch (numOfDice + 1) {
            case 1:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d4_blank, size);
                break;
            case 3:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d8_blank, size);
                break;
            case 4:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d10_blank, size);
                break;
            case 5:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d12_blank, size);
                break;
            case 6:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d20_blank, size);
                break;
            default:
                bitmap = Utilities.decodeBitmap(getResources(), R.drawable.d6_blank, size);
        }

        int width = Math.round(bitmap.getWidth() * scale);
        int height = Math.round(bitmap.getHeight() * scale);
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }
}
