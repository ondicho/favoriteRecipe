package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

public class RecipeActivity extends AppCompatActivity {
    private String[] recipes=new String[]{"pizza","chicken","seasoned fries","cold salad"};

    private ListView mListView;
    @BindView(R.id.recipesListView) ListView mRecipesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);



        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,recipes);
        mRecipesListView.setAdapter(adapter);

        mRecipesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recipe=((TextView)view).getText().toString();
                Toast.makeText(RecipeActivity.this,recipe,Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String searchedRecipe=intent.getStringExtra("searchedRecipe");
    }
}