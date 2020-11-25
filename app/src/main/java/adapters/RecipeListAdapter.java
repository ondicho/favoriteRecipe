package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.favoriterecipe.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Result;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>{
    private List<Result> mRecipes;
    private Context mContext;

    public RecipeListAdapter(Context context,List<Result> recipes){
        mContext=context;
        mRecipes=recipes;
    }
    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recipeImageView) ImageView mRecipeImageView;
        @BindView(R.id.recipeTitleTextView) TextView mRecipeTitleTextView;
        @BindView(R.id.ingredientsTextView) TextView mIngredientsTextView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipe(Result recipe){
            mRecipeTitleTextView.setText(recipe.getTitle());
            mIngredientsTextView.setText(recipe.getIngredients());
        }
    }
    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
