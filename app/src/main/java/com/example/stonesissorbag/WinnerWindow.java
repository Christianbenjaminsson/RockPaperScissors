package com.example.stonesissorbag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class WinnerWindow extends AppCompatActivity {

    private TextView headerWinnerWindow = null;
    private GifImageView gifImageView = null;
    private TextView resultTextView = null;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_window);

        headerWinnerWindow = findViewById(R.id.headerWinnerWindow);
        gifImageView = findViewById(R.id.gifImageView);
        resultTextView = findViewById(R.id.resultsWinnerWindow);

        Intent intent = getIntent();
        boolean isWinner = intent.getBooleanExtra(CurrentGameActivity.WINNER_MESSAGE, false);
        if(!isWinner) {
            headerWinnerWindow.setText("You lost");
            gifImageView.setImageResource(R.drawable.losergif);
        }

        String resultText = intent.getStringExtra(CurrentGameActivity.RESULT_MESSAGE);
        resultTextView.setText(resultText);

        username = intent.getStringExtra(MainActivity.USERNAME_MESSAGE);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, CurrentGameActivity.class);
        intent.putExtra(MainActivity.USERNAME_MESSAGE, username);
        startActivity(intent);
    }

    public void changePlayer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
