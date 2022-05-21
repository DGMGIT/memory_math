package au.edu.jcu.my.memory_math.game.background;

import android.content.Context;
import android.media.SoundPool;

import au.edu.jcu.my.memory_math.R;

class AudioManager {
    private final SoundPool soundPool;
    private int sampleId;
    private boolean loadedOkay;

    AudioManager(Context context) {
        soundPool = new SoundPool(5, android.media.AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            loadedOkay = status == 0;
            if (loadedOkay) {
                AudioManager.this.sampleId = sampleId;
            }
        });
        soundPool.load(context, R.raw.quack, 0);
    }

    void playSound(float speed, float volume) {
        if (!loadedOkay) return;
        soundPool.play(sampleId, volume, volume, 1, 0, speed);
    }

    void resume() {
        soundPool.autoResume();
    }
    void pause() {
        soundPool.autoPause();
    }
}

