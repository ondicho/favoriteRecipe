package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.favoriterecipe.R;
import com.moringa.favoriterecipe.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import Constants.Constants;
import models.Result;

public class FirebaseRecipeViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView mRecipeImageView;
    View mView;
    Context mContext;

    public FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        mView=itemView;
        mContext=itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRecipe(Result recipe){
        mRecipeImageView=(ImageView) mView.findViewById(R.id.imageView);
        TextView  titleTextview=(TextView) mView.findViewById(R.id.recipeTitleTextView);
        TextView  ingredientsTextview=(TextView) mView.findViewById(R.id.ingredientsTextView);
        TextView  thumbnailTextView=(TextView) mView.findViewById(R.id.thumbnailTextView);

        Picasso.get().load(recipe.getThumbnail()).into(mRecipeImageView);

        titleTextview.setText(recipe.getTitle());
        ingredientsTextview.setText(recipe.getIngredients());

    }

    @Override
    public void onClick(View v) {
        final ArrayList<Result> recipes=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    recipes.add(dataSnapshot.getValue(Result.class));
                }
                int itemPosition=getLayoutPosition();
                Intent intent=new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position",itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(recipes));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
