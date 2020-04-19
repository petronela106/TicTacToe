package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum Player{
        ONE, TWO
    }
    Player currentPlayer = Player.ONE;
     int[] cImage = new int[9];
     Button btn_reset;
     ImageView image0, image1, image2, image3, image4, image5 ,image6, image7, image8;
     boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        for(int i = 0; i < 9; i++){
            if(cImage[0] == cImage[4] && cImage[0] == cImage[8] && cImage[0] != 0){
                btn_reset.setVisibility(View.VISIBLE);
                message();
            }
            else if(i<3 && cImage[i]== cImage[i + 3] && cImage[i] == cImage[i+6] && cImage[i] != 0){
                btn_reset.setVisibility(View.VISIBLE);
                message();
            }
            else if(cImage[2] == cImage[4] && cImage[2] == cImage[6] && cImage[2] != 0){
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
        if(n == 9){
            Toast.makeText(this, "No winner", Toast.LENGTH_SHORT).show();
            gameOver = true;
            btn_reset.setVisibility(View.VISIBLE);
        }
    }

    public void message(){
        if(currentPlayer == Player.ONE){
            Toast.makeText(this, "Payer two has won!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Player one has won!", Toast.LENGTH_SHORT).show();
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
