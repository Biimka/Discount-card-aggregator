package com.startandroid.biimka.discount_card_aggregator;

import java.util.List;

public interface CardRepo {
    public List<Card> getCards();

    public Card getCard(long id);

    public void createCard(String name, byte[] frontImage, byte[] backImage, long barcode);

    public void updateCard(Card card);

    public void deleteCard(long id);
}
