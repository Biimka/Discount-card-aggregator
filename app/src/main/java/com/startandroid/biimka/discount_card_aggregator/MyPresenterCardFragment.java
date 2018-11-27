package com.startandroid.biimka.discount_card_aggregator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MyPresenterCardFragment extends MvpPresenter<MyViewCardFragment> {
    void setCard() {
        getViewState().setNameCard();
        getViewState().onImageFrontClick();
        getViewState().onImageBackClick();
        getViewState().onButtonCreateSaveClick();
        getViewState().onBarcodeClick();
    }
}
