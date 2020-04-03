package com.example.stonesissorbag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CurrentGameActivity extends AppCompatActivity {

    private TextView lastView1 = null;
    private TextView lastView2 = null;
    private TextView resultTextView = null;
    private TextView player1TextView = null;
    private TextView player2TextView = null;
    private Game game = null;
    private int round = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);

        Player player1 = new Player("Player 1", 1);
        Player cpuPlayer = new Player("CPU", 2);
        game = new Game(player1, cpuPlayer);

        resultTextView = findViewById(R.id.resultTextView);
        player1TextView = findViewById(R.id.textViewPlayer1);
        player2TextView = findViewById(R.id.textViewPlayer2);
        player1TextView.setText(player1.getUserName());
        player2TextView.setText(cpuPlayer.getUserName());
        lastView1 = player1TextView;
        lastView2 = player2TextView;

    }

    public void makeMove (View view) {

        int choice = 0;
        int buttonId = view.getId();
        String text = "";

        if (buttonId == R.id.rockButton) {
            text = "Rock";
            choice = Game.ROCK;
        } else if (buttonId == R.id.paperButton) {
            text = "Paper";
            choice = Game.PAPER;
        } else if (buttonId == R.id.scissorsButton) {
            text = "Scissors";
            choice = Game.SCISSORS;
        }

        ConstraintLayout cl = findViewById(R.id.currentGameConstraintLayout);
        setContentView(cl);

        TextView textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setText(text);

        cl.addView(textView);

        int id = textView.getId();

        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);

        cs.connect(id, ConstraintSet.TOP, lastView1.getId(), ConstraintSet.BOTTOM, 10);
        cs.connect(id, ConstraintSet.START, lastView1.getId(), ConstraintSet.START, ConstraintSet.MATCH_CONSTRAINT);

        cs.applyTo(cl);

        lastView1 = textView;

        int cpuChoice = makeMoveForCPU();

        game.addChoice(1, choice, round);
        game.addChoice(2, cpuChoice, round);

        int roundWinner = game.compareChoices(choice, cpuChoice);
        if (roundWinner == Game.PLAYER1_WINS) {
            game.increaseScorePlayer1();
            lastView1.setTextColor(Color.rgb(84, 144, 112));
            lastView1.setTypeface(null, Typeface.BOLD);
        } else if (roundWinner == Game.PLAYER2_WINS) {
            game.increaseScorePlayer2();
            lastView2.setTextColor(Color.rgb(84, 144, 112));
            lastView2.setTypeface(null, Typeface.BOLD);
        }

        round++;

        if(game.getScorePlayer1() > 2 || game.getScorePlayer2() > 2) {
            goToWinner();
        }

        String resultText = game.getScorePlayer1() + " - " + game.getScorePlayer2();
        resultTextView.setText(resultText);


        cl.invalidate();
    }

    private int makeMoveForCPU() {

        Random random = new Random();
        int cpuChoice = random.nextInt(3) + 1;
        String text = "";

        switch (cpuChoice) {
            case Game.ROCK:
                text = "Rock";
                break;
            case Game.PAPER:
                text = "Paper";
                break;
            case Game.SCISSORS:
                text = "Scissors";
                break;
        }

        ConstraintLayout cl = findViewById(R.id.currentGameConstraintLayout);
        setContentView(cl);

        TextView textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setText(text);

        cl.addView(textView);

        int id = textView.getId();
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);

        cs.connect(id, ConstraintSet.TOP, lastView2.getId(), ConstraintSet.BOTTOM, 10);
        cs.connect(id, ConstraintSet.START, lastView2.getId(), ConstraintSet.START, ConstraintSet.MATCH_CONSTRAINT);

        cs.applyTo(cl);

        lastView2 = textView;

        return cpuChoice;
    }

    private void goToWinner() {
        Intent intent = new Intent(this, WinnerWindow.class);
        startActivity(intent);
    }

}
