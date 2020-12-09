package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import Constants.Constants;
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
        String searchedRecipe = intent.getStringExtra("searchedRecipe");

        RecipePuppyApi client = RecipePuppyClient.getClient();
        Call<RecipePuppySearchResponse> call = client.getRecipe(searchedRecipe);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentRecipe = mSharedPreferences.getString(Constants.PREFERNCES_SEARCHEDRECIPE_KEY, null);

        if (mRecentRecipe != null) {
            client.getRecipe(mRecentRecipe);
        }


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
                    hideProgressBar();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        RecipePuppyApi client = RecipePuppyClient.getClient();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                Call<RecipePuppySearchResponse> call = client.getRecipe(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERNCES_SEARCHEDRECIPE_KEY, location).apply();
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