package com.startandroid.biimka.discount_card_aggregator.card;

import android.graphics.Bitmap;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.zxing.integration.android.IntentResult;

@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> {

    public void onFrontSideClick() {
        getViewState().selectFrontImage();
    }

    public void onFrontSideImageChoosen(Bitmap bitmap) {
        getViewState().setFrontImage(bitmap);
    }

    public void onBackSideClick() {
        getViewState().selectBackImage();
    }

    public void onBackSideImageChoosen(Bitmap bitmap) {
        getViewState().setBackImage(bitmap);
    }

    public void onBarcodeClick() {
        getViewState().scanBarcode();
    }

    public void onBarcodeScanned(IntentResult result) {
        getViewState().setBarcode(result);
    }

    public void createUpdateCard() {
        getViewState().navigate();
    }
}