package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface RouterView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    public void navigate(@IdRes int resId, @Nullable Bundle args);

    public void navigateUp();
}
