package com.example.testcreaterelements;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testcreaterelements.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.textScore.setText("Score : " + GlobalStats.score);

        binding.btnStart.setOnClickListener(v -> {

            GlobalStats.score = 0;
            binding.textScore.setText("Score : " + GlobalStats.score);
            binding.textEndGame.setVisibility(View.GONE);

            new CountDownTimer(10000, 800) {
                @Override
                public void onTick(long millisUntilFinished) {
                    createBubble(generateMarginTop(), generateMarginEnd());
                }

                @Override
                public void onFinish() {
                    binding.relativeRoot.removeAllViews();
                    binding.textEndGame.setVisibility(View.VISIBLE);

                }
            }.start();

        });
    }

    @SuppressLint("SetTextI18n")
    private void createBubble(int marginTop, int marginEnd) {
        binding.relativeRoot.removeAllViews();

        TextView textView = new TextView(MainActivity.this);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(getDP(40), getDP(70));
        params2.setMargins(0,marginTop,marginEnd,0);
        params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params2.addRule(RelativeLayout.ALIGN_PARENT_END);

        textView.setLayoutParams(params2);
        textView.setBackgroundResource(R.drawable.bubble4);
        binding.relativeRoot.addView(textView);

        textView.setOnClickListener(v -> {

            GlobalStats.score++;
            binding.textScore.setText("Score : " + GlobalStats.score);
            binding.relativeRoot.removeAllViews();

        });
    }

    private int generateMarginEnd(){
        Random random = new Random();
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (random.nextInt(275) * scale + 0.5f);
    }

    private int getDP(int pixel){
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (pixel * scale + 0.5f);
    }

    private int generateMarginTop(){
        Random random = new Random();
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (random.nextInt(421) * scale + 0.5f);
    }
}