package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    enum Player{
        ONE, TWO
    }
    Player currentPlayer = Player.ONE;
     int[] cImage = new int[9];
     int score1, score2;
     Button btn_reset, back_btn;
     TextView p1_score, p2_score;
     ImageView image0, image1, image2, image3, image4, image5 ,image6, image7, image8;
     boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        image0 = findViewById(R.id.image0);
        image0.setOnClickListener(this);
        image1 = findViewById(R.id.image1);
        image1.setOnClickListener(this);
        image2 = findViewById(R.id.image2);
        image2.setOnClickListener(this);
        image3 = findViewById(R.id.image3);
        image3.setOnClickListener(this);
        image4 = findViewById(R.id.image4);
        image4.setOnClickListener(this);
        image5 = findViewById(R.id.image5);
        image5.setOnClickListener(this);
        image6 = findViewById(R.id.image6);
        image6.setOnClickListener(this);
        image7 = findViewById(R.id.image7);
        image7.setOnClickListener(this);
        image8 = findViewById(R.id.image8);
        image8.setOnClickListener(this);

        btn_reset = findViewById(R.id.reset_btn);
        btn_reset.setOnClickListener(this);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        if(MainActivity.isStatus_game() == true ){
            SharedPreferences score_p1 = this.getSharedPreferences("score1", Context.MODE_PRIVATE);
            score1 = score_p1.getInt("score1", 0);

            SharedPreferences score_p2 = this.getSharedPreferences("score2", Context.MODE_PRIVATE);
            score2 = score_p2.getInt("score2", 0);
        }else {
            score1 = 0;
            score2 = 0;
        }

        p1_score = findViewById(R.id.p1_score);
        p1_score.setText("Player 1:\n" + score1);

        p2_score = findViewById(R.id.p2_score);
        p2_score.setText("Player 2:\n" + score2);

    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.reset_btn) {
                resetGame();
            }else{
                imageViewTapped(v);
            }
    }

    public void imageViewTapped(View imageView){
        ImageView imageTapped = (ImageView) imageView;

        int  tiTag = Integer.parseInt(imageTapped.getTag().toString());

        if(cImage[tiTag] == 0 && gameOver == false){
            if(currentPlayer == Player.ONE){
        imageTapped.setImageResource(R.drawable.x);
          cImage[tiTag] = 1;
          currentPlayer = Player.TWO;
        }else if(currentPlayer == Player.TWO){
            imageTapped.setImageResource(R.drawable.zero);
            cImage[tiTag] = 2;
            currentPlayer = Player.ONE;
        }
        checkGame();}
    }


    public void checkGame(){
        int n = 0;
        if(cImage[0] == cImage[4] && cImage[0] == cImage[8] && cImage[0] != 0){
            btn_reset.setVisibility(View.VISIBLE);
            message();
        }else if(cImage[2] == cImage[4] && cImage[2] == cImage[6] && cImage[2] != 0){
            btn_reset.setVisibility(View.VISIBLE);
            message();
        }
        for(int i = 0; i < 9; i++){
            if(i<3 && cImage[i]== cImage[i + 3] && cImage[i] == cImage[i+6] && cImage[i] != 0){
                btn_reset.setVisibility(View.VISIBLE);
                message();
            }

            else if(i%3 == 0 && cImage[i] == cImage[i+1]  && cImage[i] == cImage[i+2] && cImage[i] != 0){
                btn_reset.setVisibility(View.VISIBLE);
                message();
            }
        }
        for(int i = 0; i < 9; i++){
            if(cImage[i] != 0){
                n++;
            }
        }

        if(n == 9 && gameOver != true){
            Toast.makeText(this, "No winner", Toast.LENGTH_SHORT).show();
            gameOver = true;
            btn_reset.setVisibility(View.VISIBLE);
        }
    }

    public void message(){
        if(currentPlayer == Player.ONE){
            Toast.makeText(this, "Payer two has won!", Toast.LENGTH_SHORT).show();

            score2++;

            SharedPreferences score_p2 = getSharedPreferences("score2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = score_p2.edit();
            editor2.putInt("score2", score2);
            editor2.commit();

            p2_score.setText("Player 2:\n" + score2);
        }else if(currentPlayer == Player.TWO){
            Toast.makeText(this, "Player one has won!", Toast.LENGTH_SHORT).show();

            score1++;

            SharedPreferences score_p1 = getSharedPreferences("score1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = score_p1.edit();
            editor1.putInt("score1", score1);
            editor1.commit();

            p1_score.setText("Player 1:\n" + score1);
        }
        gameOver = true;
        }

    public  void  resetGame(){
            for(int i = 0 ; i < 9; i++){
                cImage[i] = 0;
            }

        currentPlayer = Player.ONE;

        image0.setImageDrawable(null);
        image1.setImageDrawable(null);
        image2.setImageDrawable(null);
        image3.setImageDrawable(null);
        image4.setImageDrawable(null);
        image5.setImageDrawable(null);
        image6.setImageDrawable(null);
        image7.setImageDrawable(null);
        image8.setImageDrawable(null);


        gameOver = false;
        btn_reset.setVisibility(View.GONE);
    }
}
