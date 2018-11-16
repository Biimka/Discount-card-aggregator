package com.startandroid.biimka.discount_card_aggregator.card_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.startandroid.biimka.discount_card_aggregator.Card;
import com.startandroid.biimka.discount_card_aggregator.MyAdapter;
import com.startandroid.biimka.discount_card_aggregator.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CardListFragment extends MvpAppCompatFragment {

    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cardlist, null);

        final Toolbar toolbar = rootView.findViewById(R.id.toolbarCardList);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        adapter = new MyAdapter();
        adapter.setListener(new MyAdapter.Listener() {
            @Override
            public void onItemClicked(Card card) {
            }

            @Override
            public void onItemClickedLong(Card card) {
            }
        });

        final RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        (rootView.findViewById(R.id.addDiscountCardFAB)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.fragmentCard);
            }
        });

        return rootView;
    }

}
