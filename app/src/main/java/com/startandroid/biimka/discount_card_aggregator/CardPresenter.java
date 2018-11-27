package com.startandroid.biimka.discount_card_aggregator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> {
    void setCard() {
        getViewState().setNameCard();
        getViewState().onImageFrontClick();
        getViewState().onImageBackClick();
        getViewState().onButtonCreateSaveClick();
        getViewState().onBarcodeClick();
    }
}