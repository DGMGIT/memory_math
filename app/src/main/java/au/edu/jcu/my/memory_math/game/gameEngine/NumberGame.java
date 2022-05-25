package au.edu.jcu.my.memory_math.game.gameEngine;

import java.util.Random;
import java.util.stream.IntStream;

public class NumberGame {


    Random random = new Random();

    int[] rolls;
    int sum;
    int startingDices;

    public void gameSetUp(int i) {
        startingDices = i;
    }

    public int[] runGame() {
        sum = 0;
        int[] allDice = multiRoll(startingDices, 6);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sum = IntStream.of(allDice).sum();
            System.out.println("test sum: " + sum);
        }

        startingDices += 1;

        return allDice;
    }

    public boolean checkResults(int i) {
        return i == sum;
    }

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
