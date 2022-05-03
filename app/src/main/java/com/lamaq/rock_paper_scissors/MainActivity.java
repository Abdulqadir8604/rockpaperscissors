package com.lamaq.rock_paper_scissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView com_move, player_move, arrow, imageView;
    ImageButton rock, paper, scissors, reset;
    TextView playerScore,computerScore, result;
    Random random = new Random();
    int Pscore;
    int Cscore;
    private Vibrator myVib;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        setContentView(R.layout.activity_main);

        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        Animation l2r = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.l2r);
        Animation r2l = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.r2l);
        Animation d2u = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.d2u);
        Animation aror = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aror);

        com_move = findViewById(R.id.compView);
        player_move = findViewById(R.id.playerView);
        arrow = findViewById(R.id.arrow);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        reset = findViewById(R.id.reset);
        scissors = findViewById(R.id.scissors);
        computerScore = findViewById(R.id.ComputerScore);
        playerScore = findViewById(R.id.playerScore);
        result = findViewById(R.id.result);

        myVib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        final int[] hand={R.drawable.rock,R.drawable.paper,R.drawable.scissors};
        computerScore.setText("comp: "+ Cscore);
        playerScore.setText("you: "+ this.Pscore);

        load();

        rock.setOnClickListener(view -> {
            rock.setHapticFeedbackEnabled(true);
            myVib.vibrate(50);
            rock.startAnimation(d2u);
            com_move.startAnimation(bounce);
            player_move.startAnimation(bounce);
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Do something after 5s = 5000ms
                rock.setEnabled(true);
                paper.setEnabled(true);
                scissors.setEnabled(true);
            }, 700);

            final int n = random.nextInt(3);
            com_move.setImageResource(hand[n]);
            player_move.setImageResource(R.drawable.rock);
            if(n==0){
                arrow.setImageResource(R.drawable.arrow_both);
                arrow.startAnimation(aror);
                result.setText("it's a Tie");
            }else if (n==1){
                arrow.setImageResource(R.drawable.arrow_right);
                arrow.startAnimation(l2r);
                Cscore++;
                computerScore.setText("comp: "+ Cscore);
                result.setText("You Lose");
            }else{
                arrow.setImageResource(R.drawable.arrow_left);
                arrow.startAnimation(r2l);
                Pscore++;
                playerScore.setText("You: "+ Pscore);
                result.setText("You Won!");
            }
        });
        paper.setOnClickListener(view -> {
            rock.setHapticFeedbackEnabled(true);
            myVib.vibrate(50);
            paper.startAnimation(d2u);
            com_move.startAnimation(bounce);
            player_move.startAnimation(bounce);
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Do something after 5s = 5000ms
                rock.setEnabled(true);
                paper.setEnabled(true);
                scissors.setEnabled(true);
            }, 700);
            final int n = random.nextInt(3);
            com_move.setImageResource(hand[n]);
            player_move.setImageResource(R.drawable.paper);
            if(n==0){
                arrow.setImageResource(R.drawable.arrow_left);
                arrow.startAnimation(r2l);
                Pscore++;
                playerScore.setText("You: "+ Pscore);
                result.setText("You Won!");
            }else if (n==1){
                arrow.setImageResource(R.drawable.arrow_both);
                arrow.startAnimation(aror);
                result.setText("it's a Tie");
            }else{
                arrow.setImageResource(R.drawable.arrow_right);
                arrow.startAnimation(l2r);
                Cscore++;
                computerScore.setText("comp: "+ Cscore);
                result.setText("You Lose");
            }
        });
        scissors.setOnClickListener(view -> {
            rock.setHapticFeedbackEnabled(true);
            myVib.vibrate(50);
            scissors.startAnimation(d2u);
            com_move.startAnimation(bounce);
            player_move.startAnimation(bounce);
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Do something after 5s = 5000ms
                rock.setEnabled(true);
                paper.setEnabled(true);
                scissors.setEnabled(true);
            }, 700);
            final int n= random.nextInt(3);
            com_move.setImageResource(hand[n]);
            player_move.setImageResource(R.drawable.scissors);
            if(n==0){
                arrow.setImageResource(R.drawable.arrow_right);
                arrow.startAnimation(l2r);
                Cscore++;
                computerScore.setText("comp: "+ Cscore);
                result.setText("You Lose");
            }else if (n==1){
                arrow.setImageResource(R.drawable.arrow_left);
                arrow.startAnimation(r2l);
                Pscore++;
                playerScore.setText("You: "+ Pscore);
                result.setText("You Won!");
            }else{
                arrow.setImageResource(R.drawable.arrow_both);
                arrow.startAnimation(aror);
                result.setText("it's a Tie");
            }
        });
        reset.setOnClickListener(view ->{
            myVib.vibrate(500);
            Pscore = 0;
            playerScore.setText("You: "+ Pscore);
            Cscore = 0;
            computerScore.setText("comp: "+ Cscore);
            result.setText("Start");
            com_move.setImageResource(R.drawable.start);
            player_move.setImageResource(R.drawable.start);
        });
    }

    @Override
    public void onBackPressed() {
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        save();
                        finish();
                        System.exit(0);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        save();
                        break;
                }
            }
        };

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save & exit?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void load() {
        final SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        Pscore = sh.getInt("PlayerScore", 0);
        Cscore = sh.getInt("ComputerScore", 0);
        computerScore.setText("comp: "+ Cscore);
        playerScore.setText("you: "+ Pscore);
    }

    private void save() {
        final SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("PlayerScore", Integer.parseInt(String.valueOf(Pscore)));
        myEdit.putInt("ComputerScore", Integer.parseInt(String.valueOf(Cscore)));
        myEdit.apply();
    }
}