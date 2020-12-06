package com.moringa.favoriterecipe;

import Constants.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import networking.RecipePuppyApi;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentRecipe;

    private DatabaseReference mSearchedRecipeReference;
    private ValueEventListener mSearchedRecipeReferenceListener;
    @BindView(R.id.searchRecipe) EditText mSearchRecipeEditText;
    @BindView(R.id.searchfForRecipe) Button mSearchRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedRecipeReference = FirebaseDatabase.getInstance()
                .getReference().child(Constants.FIREBASE_CHILD_SEARCHED_RECIPE);

        mSearchedRecipeReferenceListener =mSearchedRecipeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    String searchedRecipe = recipeSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreferences.edit();

        mSearchRecipeButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
//                getRecipe(query);
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


    @Override
    public void onClick(View v) {
        if (v == mSearchRecipeButton) {
            String searchedRecipe = mSearchRecipeEditText.getText().toString();
            saveRecipeToFirebase(searchedRecipe);
                    addToSharedPreferences(searchedRecipe);
            Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
            intent.putExtra("searchedRecipe", searchedRecipe);
            startActivity(intent);
        }
    }

    public void saveRecipeToFirebase(String searchedRecipe) {
        mSearchedRecipeReference.push().setValue(searchedRecipe);
    }


        private void addToSharedPreferences(String searchedRecipe) {
        mEditor.putString(Constants.PREFERNCES_SEARCHEDRECIPE_KEY, searchedRecipe).apply();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedRecipeReference.removeEventListener(mSearchedRecipeReferenceListener);
    }
}