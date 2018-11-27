package com.startandroid.biimka.discount_card_aggregator;

import com.arellomobile.mvp.MvpView;

public interface CardView extends MvpView {
    void setNameCard();

    void onImageFrontClick();

    void onImageBackClick();

    void onBarcodeClick();

    void onButtonCreateSaveClick();
}