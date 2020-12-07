package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import adapters.RecipeListAdapter;
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
    public static final String TAG=RecipeActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentRecipe;


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private RecipeListAdapter mAdapter;

    public List<Result> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String searchedRecipe=intent.getStringExtra("searchedRecipe");

        RecipePuppyApi client=RecipePuppyClient.getClient();
        Call<RecipePuppySearchResponse> call=client.getRecipe(searchedRecipe);

        call.enqueue(new Callback<RecipePuppySearchResponse>() {
            @Override
            public void onResponse(Call<RecipePuppySearchResponse> call, Response<RecipePuppySearchResponse> response) {
                Log.d(TAG, "onResponse: Successful");
                if(response.isSuccessful()){
                  recipes=response.body().getResults();
                  mAdapter=new RecipeListAdapter(RecipeActivity.this,recipes);

                  mRecyclerView.setAdapter(mAdapter);
                  RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(RecipeActivity.this);
                  mRecyclerView.setLayoutManager(layoutManager);
                  mRecyclerView.setHasFixedSize(true);

                    showRecipes();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<RecipePuppySearchResponse> call, Throwable t) {
            Log.e(TAG, "onFailure: ",t );
            hideProgressBar();
            showFailureMessage();
            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showRecipes() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}