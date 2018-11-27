package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CardListFragment extends MvpAppCompatFragment {

    public static CardListFragment newInstance() {
        return new CardListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cardlist, null);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        (rootView.findViewById(R.id.addDiscountCardFAB)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.fragmentCard);
            }
        });

        return rootView;
    }

}
