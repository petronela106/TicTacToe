package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button new_game, resume_game;
     private static boolean status_game = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_game = findViewById(R.id.new_btn);
        new_game.setOnClickListener(this);

        resume_game = findViewById(R.id.resume_btn);
        resume_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(i);
        if(v.getId() == R.id.resume_btn){
            status_game = true;
        }else {
            status_game = false;
        }

    }

    public static boolean isStatus_game() {
        return status_game;
    }

}
