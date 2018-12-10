package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class MainActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbarSecond = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarSecond);
    }
}
