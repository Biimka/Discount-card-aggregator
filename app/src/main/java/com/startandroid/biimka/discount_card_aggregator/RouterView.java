package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

public interface RouterView {

    public void navigate(@IdRes int resId, @Nullable Bundle args);

    public void navigateUp();
}
