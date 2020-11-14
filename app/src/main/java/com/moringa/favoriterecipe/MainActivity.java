package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @BindView(R.id.findRecipeButton) Button mFindRecipeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if(v==mFindRecipeButton){
            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
        }
    }
}