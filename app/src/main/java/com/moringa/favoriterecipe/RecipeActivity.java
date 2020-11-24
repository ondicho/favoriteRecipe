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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.RecipePuppySearchResponse;
import models.Result;
import networking.RecipePuppyApi;
import networking.RecipePuppyClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {
    private String[] recipes=new String[]{"pizza","chicken","seasoned fries","cold salad"};

    private ListView mListView;
    @BindView(R.id.recipesListView) ListView mRecipesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);



        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,recipes);
        mRecipesListView.setAdapter(adapter);


        Intent intent = getIntent();
        String searchedRecipe=intent.getStringExtra("searchedRecipe");

        RecipePuppyApi client=RecipePuppyClient.getClient();
        Call<RecipePuppySearchResponse> call=client.getRecipe("searchedRecipe");

        call.enqueue(new Callback<RecipePuppySearchResponse>() {
            @Override
            public void onResponse(Call<RecipePuppySearchResponse> call, Response<RecipePuppySearchResponse> response) {
                if(response.isSuccessful()){
                    List<Result> recipeList=response.body().getResults();
                    String[] title=new String[recipeList.size()];
                    String[] recipes=new String[recipeList.size()];

                    for (int i=0;i<title.length;i++){
                        title[i]=recipeList.get(i).getTitle();
                    }
                    for (int i=0;i<recipes.length;i++){
                        recipes[i]=recipeList.get(i).getIngredients();
                    }
                    ArrayAdapter adapter
                            = new ArrayAdapter(RecipeActivity.this, android.R.layout.simple_list_item_1, title);
                    mListView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<RecipePuppySearchResponse> call, Throwable t) {

            }
        });
    }
}