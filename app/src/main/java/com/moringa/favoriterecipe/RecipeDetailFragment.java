package com.moringa.favoriterecipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringa.favoriterecipe.R;
import models.Result;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailFragment extends Fragment {

//    @BindView(R.id.imageView) ImageView mImageLabel;
    @BindView(R.id.recipeTitleTextView) TextView mTitleLabel;
    @BindView(R.id.ingredientsTextView) TextView mIngredientsLabel;
    @BindView(R.id.thumbnailTextView) TextView mThumbnailLabel;
    @BindView(R.id.saveRecipeButton) TextView mSaveRecipeButton;

    private Result mRecipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RecipeDetailFragment newInstance(Result recipe) {
       RecipeDetailFragment reciepeDetailFragment=new RecipeDetailFragment();
       Bundle args=new Bundle();
       args.putParcelable("recipe", Parcels.wrap(recipe));
       reciepeDetailFragment.setArguments(args);
       return reciepeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe=Parcels.unwrap(getArguments().getParcelable("recipe"));
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this,view);
//        Picasso.get().load(mRecipe.getHref()).into(mImageLabel);

        List<Result> ingredients=new ArrayList<>();

        mTitleLabel.setText(mRecipe.getTitle());
        mIngredientsLabel.setText(mRecipe.getIngredients());
        mThumbnailLabel.setText(mRecipe.getThumbnail());

        return view;
    }
}