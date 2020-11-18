package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.searchRecipe)
    EditText mSearchRecipeEditText;
    @BindView(R.id.searchfForRecipe)
    Button mSearchRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSearchRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mSearchRecipeButton) {
                    String searchedRecipe = mSearchRecipeEditText.getText().toString();
                    Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
                    intent.putExtra("searchedRecipe", searchedRecipe);
                    startActivity(intent);
                }
            }
        });
    }
}