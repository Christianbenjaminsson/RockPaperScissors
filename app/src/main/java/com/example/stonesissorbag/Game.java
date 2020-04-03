package com.example.stonesissorbag;


public class Game {

    private String player1;
    private String cpuPlayer;
    private int scorePlayer1;
    private int scoreCpuPlayer;
    private int currentChoicePlayer1;
    private int currentChoiceCpuPlayer;
    public static final int ROCK = 1;
    public static final int SCISSORS = 2;
    public static final int PAPER = 3;
    public static final int DRAW = 0;
    public static final int PLAYER1_WINS = 1;
    public static final int PLAYER2_WINS = 2;
    public static final int INVALID_INPUT = -1;

    public Game(String player1, String cpuPlayer) {
        this.player1 = player1;
        this.cpuPlayer = cpuPlayer;
        this.scorePlayer1 = 0;
        this.scoreCpuPlayer = 0;
        currentChoicePlayer1 = 0;
        currentChoiceCpuPlayer = 0;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getCpuPlayer() {
        return cpuPlayer;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scoreCpuPlayer;
    }

    public void increaseScorePlayer1() {
        scorePlayer1++;
    }

    public void increaseScoreCpuPlayer() {
        scoreCpuPlayer++;
    }

    public int compareChoices(int choice1, int choice2) {

        int result;

        if (choice1 == choice2) {
            result = DRAW;
        } else  switch(choice1) {
            case ROCK:
                result = choice2 == SCISSORS ?  PLAYER1_WINS :  PLAYER2_WINS;
                break;
            case SCISSORS:
                result = choice2 == PAPER ?  PLAYER1_WINS :  PLAYER2_WINS;
                break;
            case PAPER:
                result = choice2 == ROCK ?  PLAYER1_WINS :  PLAYER2_WINS;
                break;
            default:
                result = INVALID_INPUT;
        }
        return result;
    }
}
