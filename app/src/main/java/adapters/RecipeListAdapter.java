package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.favoriterecipe.R;

import java.util.List;

import org.parceler.Parcels;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Result;
import com.moringa.favoriterecipe.RecipeDetailActivity;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private List<Result> mRecipes;
    private Context mContext;

    public RecipeListAdapter(Context context,List<Result> recipes){
        mContext=context;
        mRecipes=recipes;
    }



    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeImageView) ImageView mRecipeImageView;
        @BindView(R.id.recipeTitleTextView) TextView mRecipeTitleTextView;
        @BindView(R.id.ingredientsTextView) TextView mIngredientsTextView;
//        @BindView(R.id.thumbnailTextView) TextView mThumbnailTextView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        public void bindRecipe(Result recipe){
            mRecipeTitleTextView.setText(recipe.getTitle());
            mIngredientsTextView.setText(recipe.getIngredients());
//            mThumbnailTextView.setText(recipe.getThumbnail());

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent=new Intent(mContext,RecipeDetailActivity.class);
            intent.putExtra("position",itemPosition);
            intent.putExtra("recipes",Parcels.wrap(mRecipes));
            mContext.startActivity(intent);
        }
    }
    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,parent,false);
       RecipeViewHolder viewHolder=new RecipeViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


}
