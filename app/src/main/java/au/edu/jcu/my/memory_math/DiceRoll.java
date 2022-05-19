package au.edu.jcu.my.memory_math;

import java.util.Random;

public class DiceRoll {
    Random random = new Random();

    public int roll(int nr) {
        return 1 + random.nextInt(nr);
    }
}


