package com.moringa.favoriterecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Constants.Constants;
import adapters.FirebaseRecipeListAdapter;
import adapters.FirebaseRecipeViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Result;
import util.ItemTouchHelperAdapter;
import util.OnStartDragListener;
import util.SimpleItemTouchHelperCallback;

public class SavedRecipeListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mRecipeReference;
    private FirebaseRecyclerAdapter<Result, FirebaseRecipeViewHolder> mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);


        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mRecipeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPE).child(uid);
        FirebaseRecyclerOptions<Result> options =
                new FirebaseRecyclerOptions.Builder<Result>()
                        .setQuery(mRecipeReference, Result.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecipeListAdapter(options, mRecipeReference, this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
}
