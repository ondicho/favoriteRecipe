package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import models.Result;
import com.moringa.favoriterecipe.RecipeDetailFragment;

import java.util.ArrayList;
import java.util.List;


public class RecipePagerAdapter extends FragmentPagerAdapter {
    private List<Result> mRecipes;
    public RecipePagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> recipes) {
        super(fm, behavior);
        mRecipes=recipes;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return RecipeDetailFragment.newInstance(mRecipes.get(position));
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRecipes.get(position).getTitle();
    }
}
