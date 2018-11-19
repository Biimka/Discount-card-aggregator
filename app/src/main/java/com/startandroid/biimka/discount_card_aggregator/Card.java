package com.startandroid.biimka.discount_card_aggregator;

import java.util.Objects;

public class Card {
    private long id;
    private String name;
    private byte[] imageFrontBytes;
    private byte[] imageBackBytes;
    private String formatBarcode;
    private int contentBarcode;

    public Card(long id, String name, byte[] imageFrontBytes, byte[] imageBackBytes, String formatBarcode, int contentBarcode) {
        this.id = id;
        this.name = name;
        this.imageFrontBytes = imageFrontBytes;
        this.imageBackBytes = imageBackBytes;
        this.formatBarcode = formatBarcode;
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

    public byte[] getImageFrontBytes() {
        return imageFrontBytes;
    }

    public void setImageFrontBytes(byte[] imageFrontBytes) {
        this.imageFrontBytes = imageFrontBytes;
    }

    public byte[] getImageBackBytes() {
        return imageBackBytes;
    }

    public void setImageBackBytes(byte[] imageBackBytes) {
        this.imageBackBytes = imageBackBytes;
    }

    public int getContentBarcode() {
        return contentBarcode;
    }

    public void setContentBarcode(int contentBarcode) {
        this.contentBarcode = contentBarcode;
    }

    public String getFormatBarcode() {
        return formatBarcode;
    }

    public void setFormatBarcode(String formatBarcode) {
        this.formatBarcode = formatBarcode;
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
