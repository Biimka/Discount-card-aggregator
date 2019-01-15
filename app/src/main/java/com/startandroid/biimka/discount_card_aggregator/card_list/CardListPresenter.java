package com.startandroid.biimka.discount_card_aggregator.card_list;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.startandroid.biimka.discount_card_aggregator.CardListItem;

@InjectViewState
public class CardListPresenter extends MvpPresenter<CardListView> {
    public void onCardItemLongClick(CardListItem card) {
        getViewState().onCardItemLongClicked(card);
    }

    public void toPutBundle(@IdRes int idRes, @Nullable Bundle args) {
        getViewState().navigate(idRes, args);
    }
}
