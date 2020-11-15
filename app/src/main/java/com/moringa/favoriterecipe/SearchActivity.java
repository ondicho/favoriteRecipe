package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.searchRecipe) EditText mSearchRecipeEditText;
    @BindView(R.id.searchfForRecipe) Button mSearchRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void onClick(View v) {
        if(v==m)
    }
}