package com.moringa.favoriterecipe;

import Constants.Constants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.searchRecipe) EditText mSearchRecipeEditText;
    @BindView(R.id.searchfForRecipe) Button mSearchRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreferences.edit();

        mSearchRecipeButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mSearchRecipeButton) {
                    String searchedRecipe = mSearchRecipeEditText.getText().toString();
                    addToSharedPreferences(searchedRecipe);
                    Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
                    intent.putExtra("searchedRecipe", searchedRecipe);
                    startActivity(intent);
                }
            }

    private void addToSharedPreferences(String searchedRecipe) {
        mEditor.putString(Constants.PREFERNCES_SEARCHEDRECIPE_KEY, searchedRecipe).apply();
    }
}