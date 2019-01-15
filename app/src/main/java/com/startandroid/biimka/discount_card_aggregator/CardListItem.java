package com.startandroid.biimka.discount_card_aggregator;

import android.graphics.Bitmap;

import java.util.Objects;

public class CardListItem {
    private long id;
    private String name;
    private Bitmap imageFront;

    public CardListItem(long id, String name, Bitmap imageFront) {
        this.id = id;
        this.name = name;
        this.imageFront = imageFront;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImageFront() {
        return imageFront;
    }

    public void setImageFront(Bitmap imageFront) {
        this.imageFront = imageFront;
    }
}
