package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchActivity extends AppCompatActivity {

    Animation topAnimation,BottomAnimation;
    @BindView(R.id.imageView2) ImageView animImageView;
    @BindView(R.id.animTextView) TextView animTextView;

    private static  int SPLASH_SCREEN=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.topanimation);
        BottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottomanimation);

        animImageView.setAnimation(topAnimation);
        animTextView.setAnimation(BottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}