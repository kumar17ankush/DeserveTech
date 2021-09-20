package com.deservetech;

import java.util.*;

public class SnakeNLadder {

    final static int BOARDSIZE = 100;
    final static int MAX = 6;
    final static int MIN = 1;
    List<String> diceTypeList = new ArrayList<>(Arrays.asList("c", "C", "n", "N"));


    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();

    {
        snake.put(99, 54);
        snake.put(70, 55);
        snake.put(52, 42);
        snake.put(14, 7);
        snake.put(95, 72);

        ladder.put(6, 25);
        ladder.put(11, 40);
        ladder.put(60, 85);
        ladder.put(46, 90);
        ladder.put(17, 69);
    }


    public int rollDice(String diceType) {
        int n = 0;
        int rNum = 0;
        if("n".equals(diceType) || "N".equals(diceType)) {
            Random r = new Random();
            n = r.nextInt(7);
            rNum = (n == 0 ? 1 : n);
        }
        if("c".equals(diceType) || "C".equals(diceType)){
            Random r = new Random();
            n = r.nextInt(MAX / 2 - (MIN + 1) / 2 + 1) * 2 + (MIN + 1) / 2 * 2;
            rNum = (n == 0 ? 1 : n);
        }
        return rNum;
    }

    public void startGame() {
        int playerPos = 0;
        int turn = 0;
        Scanner s = new Scanner(System.in);
        String str;
        String diceType;
        int diceValue = 0;
        System.out.println("Select the type of dice");
        System.out.println("Press n for Normal dice OR Press c for Crooked dice");
        diceType = s.next();
        if(!diceTypeList.contains(diceType)){
            System.out.println("Incorrect choice of dice type... Try again!!");
            startGame();
        }
        else {
            do {
                System.out.println("Press r to roll Dice");
                str = s.next();
                diceValue = rollDice(diceType);
                System.out.println("Rolled dice value :: " + diceValue);
                playerPos = calculatePlayerPos(playerPos, diceValue);
                System.out.println("Player position :: " + playerPos);
                if (isWin(playerPos)) {
                    System.out.println("Player wins");
                    return;
                }
                turn++;
            } while ("r".equals(str) && turn<10);
        }
    }


    public int calculatePlayerPos(int playerPos, int diceValue) {
        playerPos = playerPos + diceValue;

        if (playerPos > BOARDSIZE) {
            playerPos = playerPos - diceValue;
            return playerPos;
        }

        if (null != snake.get(playerPos)) {
            System.out.println("swallowed by snake");
            playerPos = snake.get(playerPos);
        }

        if (null != ladder.get(playerPos)) {
            System.out.println("climb up the ladder");
            playerPos = ladder.get(playerPos);
        }
        return playerPos;
    }

    public boolean isWin(int player) {
        return BOARDSIZE == player;
    }
}
