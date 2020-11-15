package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import butterknife.BindView;

public class RecipeActivity extends AppCompatActivity {

    private ListView mListView;
    @BindView(R.id.recipesListView) ListView mRecipesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,recipes);
        mRecipesListView.setAdapter(adapter);

    }
}