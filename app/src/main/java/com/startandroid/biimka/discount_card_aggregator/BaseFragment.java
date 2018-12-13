package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;

import androidx.navigation.Navigation;

public class BaseFragment extends MvpAppCompatFragment implements RouterView {

    @Override
    public void navigate(int resId, @Nullable Bundle args) {
       switch (resId){
           case R.id.fragmentCardList:
               Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.fragmentCardList, args);
               break;
           case R.id.fragmentCard:
               Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.fragmentCard, args);
               break;
           case R.id.fragmentBarcode:
               Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.fragmentBarcode, args);
               break;
       }
    }

    @Override
    public void navigateUp() {
        getActivity().onBackPressed();
    }
}
