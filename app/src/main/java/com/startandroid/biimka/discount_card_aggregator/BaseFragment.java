package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;

import androidx.navigation.Navigation;

public class BaseFragment extends MvpAppCompatFragment implements RouterView {

    @Override
    public void navigate(int resId, @Nullable Bundle args) {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(resId, args);
    }

    @Override
    public void navigateUp() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack();
    }
}
