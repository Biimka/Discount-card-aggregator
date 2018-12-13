package com.startandroid.biimka.discount_card_aggregator.card;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.zxing.integration.android.IntentResult;
import com.startandroid.biimka.discount_card_aggregator.RouterView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CardView extends MvpView, RouterView {
    void setName(String name);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void selectFrontImage();

    void setFrontImage(Bitmap image);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void selectBackImage();

    void setBackImage(Bitmap image);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void scanBarcode();

    void setBarcode(IntentResult result);
}