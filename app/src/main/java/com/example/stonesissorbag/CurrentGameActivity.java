package com.example.stonesissorbag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CurrentGameActivity extends AppCompatActivity {
    private View lastView1 = null;
    private View lastView2 = null;
    private TextView resultTextView = null;
    private TextView player1TextView = null;
    private TextView player2TextView = null;
    private Game game = null;
    private int round = 1;
    private Button rockButton = null;
    private Button paperButton = null;
    private Button scissorsButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);
        lastView1 = findViewById(R.id.textViewPlayer1);
        lastView2 = findViewById(R.id.textViewPlayer2);
        resultTextView = findViewById(R.id.resultTextView);
        player1TextView = findViewById(R.id.textViewPlayer1);
        player2TextView = findViewById(R.id.textViewPlayer2);
       //rockButton = findViewById(R.id.rockButton);
        //paperButton = findViewById(R.id.paperButton);
        //scissorsButton = findViewById(R.id.scissorsButton);

       /* rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRockButton(v);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(v);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(v);
            }
        });*/

        Player player1 = new Player("Player 1", 1);
        Player cpuPlayer = new Player("CPU", 2);
        player1TextView.setText(player1.getUserName());
        player2TextView.setText(cpuPlayer.getUserName());
        game = new Game(player1, cpuPlayer);
    }

    public void handleRockButton(View view) {

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.currentGameConstraintLayout);
        setContentView(cl);
        TextView textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setText("Rock");
        cl.addView(textView);

        int id = textView.getId();

        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);

        cs.connect(id, ConstraintSet.TOP, lastView1.getId(), ConstraintSet.BOTTOM, 10);

        cs.applyTo(cl);

        lastView1 = textView;

        int cpuChoice = makeMoveForCPU();

        game.addChoice(1, Game.ROCK, round);
        game.addChoice(2, cpuChoice, round);

        int roundWinner = game.compareChoices(Game.ROCK, cpuChoice);
        if (roundWinner == Game.PLAYER1_WINS) {
            game.increaseScorePlayer1();
        } else if (roundWinner == Game.PLAYER2_WINS) {
            game.increaseScorePlayer2();
        }

        round++;

        String resultText = game.getScorePlayer1() + " - " + game.getScorePlayer2();
        resultTextView.setText(resultText);

        cl.invalidate();
    }

    public void makeMove (View view) {

        //Drawable image = null;
        int choice = 0;
        int buttonId = view.getId();
        String text = "";

        if (buttonId == R.id.rockButton) {
            //image = this.getResources().getDrawable(R.drawable.rock);
            text = "Rock";
            choice = Game.ROCK;
        } else if (buttonId == R.id.paperButton) {
            //image = this.getResources().getDrawable(R.drawable.paper);
            text = "Paper";
            choice = Game.PAPER;
        } else if (buttonId == R.id.scissorsButton) {
            //image = this.getResources().getDrawable(R.drawable.scissors);
            text = "Scissors";
            choice = Game.SCISSORS;
        }

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.currentGameConstraintLayout);
        setContentView(cl);

        //ImageView imageView = new ImageView(this);
        //imageView.setId(View.generateViewId());
        //imageView.setBackground(image);

        TextView textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setText(text);

        //cl.addView(imageView);
        cl.addView(textView);

        //int id = imageView.getId();
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
        } else if (roundWinner == Game.PLAYER2_WINS) {
            game.increaseScorePlayer2();
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
        //Drawable image = null;
        String text = "";

        switch (cpuChoice) {
            case Game.ROCK:
                //image = this.getResources().getDrawable(R.drawable.rock);
                text = "Rock";
                break;
            case Game.PAPER:
                //image = this.getResources().getDrawable(R.drawable.paper);
                text = "Paper";
                break;
            case Game.SCISSORS:
               // image = this.getResources().getDrawable(R.drawable.scissors);
                text = "Scissors";
                break;
        }

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.currentGameConstraintLayout);
        setContentView(cl);

        //ImageView imageView = new ImageView(this);
        //imageView.setId(View.generateViewId());
        //imageView.setBackground(image);

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
