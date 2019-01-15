package com.startandroid.biimka.discount_card_aggregator.card_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.startandroid.biimka.discount_card_aggregator.CardListItem;
import com.startandroid.biimka.discount_card_aggregator.RouterView;

@StateStrategyType(AddToEndSingleStrategy.class)
interface CardListView extends MvpView, RouterView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    public void onCardItemLongClicked(CardListItem card);
}
