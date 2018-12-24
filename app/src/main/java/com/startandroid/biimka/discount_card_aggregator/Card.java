package com.startandroid.biimka.discount_card_aggregator;

import android.graphics.Bitmap;

import java.util.Objects;

public class Card {
    private long id;
    private String name;
    private Bitmap imageFrontBytes;
    private Bitmap imageBackBytes;
    private long contentBarcode;

    public Card(long id, String name, Bitmap imageFrontBytes, Bitmap imageBackBytes,
                long contentBarcode) {
        this.id = id;
        this.name = name;
        this.imageFrontBytes = imageFrontBytes;
        this.imageBackBytes = imageBackBytes;
        this.contentBarcode = contentBarcode;
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

    public Bitmap getImageFrontBytes() {
        return imageFrontBytes;
    }

    public void setImageFrontBytes(Bitmap imageFrontBytes) {
        this.imageFrontBytes = imageFrontBytes;
    }

    public Bitmap getImageBackBytes() {
        return imageBackBytes;
    }

    public void setImageBackBytes(Bitmap imageBackBytes) {
        this.imageBackBytes = imageBackBytes;
    }

    public long getContentBarcode() {
        return contentBarcode;
    }

    public void setContentBarcode(long contentBarcode) {
        this.contentBarcode = contentBarcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return id == card.id &&
                Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
