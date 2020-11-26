package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import adapters.RecipePagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Result;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private RecipePagerAdapter adapterViewPager;
    List<Result> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        mRecipes= Parcels.unwrap(getIntent().getParcelableExtra("recipes"));
        int startingPosition=getIntent().getIntExtra("position",0);
        adapterViewPager=new RecipePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mRecipes);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}