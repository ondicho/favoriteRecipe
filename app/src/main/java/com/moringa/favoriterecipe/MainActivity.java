package com.moringa.favoriterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bindview ()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}