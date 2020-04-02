package com.example.stonesissorbag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Game extends AppCompatActivity {

    private String player1;
    private String cpuPlayer;
    private int scorePlayer1;
    private int scoreCpuPlayer;
    private int currentChoicePlayer1;
    private int currentChoiceCpuPlayer;
    public static final int ROCK = 1;
    public static final int SCISSORS = 2;
    public static final int PAPER = 3;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    
}
