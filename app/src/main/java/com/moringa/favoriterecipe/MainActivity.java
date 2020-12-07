package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @BindView(R.id.findRecipeButton) Button mFindRecipeButton;
    @BindView(R.id.viewSavedRecipesButton) Button mViewSavedRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mFindRecipeButton.setOnClickListener(this);
        mViewSavedRecipesButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mFindRecipeButton) {
            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
            startActivity(intent);
        }
//        if (v == mViewSavedRecipesButton){
//            Intent intent = new Intent(MainActivity.this, SavedRListActivity.class);
//            startActivity(intent);
//        }
    }

}