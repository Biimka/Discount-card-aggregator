package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CardListFragment extends MvpAppCompatFragment {

    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cardlist, null);

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
