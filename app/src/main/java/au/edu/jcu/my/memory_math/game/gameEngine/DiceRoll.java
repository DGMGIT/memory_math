package au.edu.jcu.my.memory_math.game.gameEngine;

import java.util.Random;


public class DiceRoll {

    Random random = new Random();

    int[] rolls;

    public int[] multiRoll(int tr, int nr) {
        rolls = new int[tr];
        for (int i = 0; i < tr; i++) {
            int x = roll(nr);
            rolls[i] = x;
        }
        return rolls;
    }

    public int roll(int nr) {
        return 1 + random.nextInt(nr);
    }
}


