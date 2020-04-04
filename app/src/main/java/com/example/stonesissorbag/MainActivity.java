package com.example.stonesissorbag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME_MESSAGE = "com.example.stonesissorbag.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
    public void goToGame(View view) {

        EditText usernameInput = findViewById(R.id.usernameTextView);
        String username = usernameInput.getText().toString();

        Intent intent = new Intent(this, CurrentGameActivity.class);
        intent.putExtra(USERNAME_MESSAGE, username);
        startActivity(intent);

    }
}
