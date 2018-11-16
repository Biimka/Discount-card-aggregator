package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Fragment1 extends Fragment {

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment1, null);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        (rootView.findViewById(R.id.addDiscountCardFAB)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.fragment2);
            }
        });

        return rootView;
    }

}
