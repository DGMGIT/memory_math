package au.edu.jcu.my.memory_math.game.gameEngine;

import java.util.stream.IntStream;

import au.edu.jcu.my.memory_math.game.gameDisplay.Play;

public class NumberGame {

    private DiceRoll diceRoll;

    int sum;
    int startingDices;

    public void gameSetUp(int i) {
        startingDices = i;
    }

    public int[] runGame() {
        diceRoll = new DiceRoll();
        sum = 0;
        int[] allDice = diceRoll.multiRoll(startingDices, 6);
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
}
