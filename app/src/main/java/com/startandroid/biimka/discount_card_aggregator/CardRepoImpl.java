package com.startandroid.biimka.discount_card_aggregator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardRepoImpl implements CardRepo {

    private DBHelper dbHelper = new DBHelper(App.context);
    private SQLiteDatabase db = dbHelper.getWritableDatabase();
    private static final CardRepoImpl ourInstance = new CardRepoImpl();

    public static CardRepoImpl getInstance() {
        return ourInstance;
    }

    private CardRepoImpl() {
    }

    @Override
    public List<Card> getCards() {
        final List<Card> cards = new ArrayList<>();
        final Cursor cardsCursor = db.query("Card", null, null,
                null, null, null, null);
        if (cardsCursor.moveToFirst()) {
            final int idColIndex = cardsCursor.getColumnIndex("id");
            final int nameColIndex = cardsCursor.getColumnIndex("cardName");
            final int frontImageColIndex = cardsCursor.getColumnIndex("bitmapFront");
            final int backImageColIndex = cardsCursor.getColumnIndex("bitmapBack");
            final int contentBarcodeColIndex = cardsCursor.getColumnIndex("cardId");
            do {
                cards.add(new Card(cardsCursor.getInt(idColIndex),
                        cardsCursor.getString(nameColIndex), cardsCursor.getBlob(frontImageColIndex),
                        cardsCursor.getBlob(backImageColIndex),
                        cardsCursor.getInt(contentBarcodeColIndex)));
            } while (cardsCursor.moveToNext());
        }
        cardsCursor.close();
        return cards;
    }

    @Override
    public Card getCard(long id) {
        final Cursor cardsCursor = db.query("Card", null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cardsCursor.moveToFirst()) {
            return new Card(cardsCursor.getInt(cardsCursor.getColumnIndex("id")),
                    cardsCursor.getString(cardsCursor.getColumnIndex("cardName")),
                    cardsCursor.getBlob(cardsCursor.getColumnIndex("bitmapFront")),
                    cardsCursor.getBlob(cardsCursor.getColumnIndex("bitmapBack")),
                    cardsCursor.getInt(cardsCursor.getColumnIndex("cardId")));
        }
        cardsCursor.close();
        return null;
    }

    @Override
    public void createCard(Card card) {
        final ContentValues contentValuesCard = new ContentValues();
        contentValuesCard.put("cardName", card.getName());
        contentValuesCard.put("bitmapFront", card.getImageFrontBytes());
        contentValuesCard.put("bitmapBack", card.getImageBackBytes());
        db.insert("Card", null, contentValuesCard);

        final ContentValues contentValuesBarcode = new ContentValues();
        contentValuesBarcode.put("content", card.getContentBarcode());
        db.insert("Barcode", null, contentValuesBarcode);
    }

    @Override
    public void updateCard(Card card) {
        final ContentValues contentValuesCard = new ContentValues();
        contentValuesCard.put("id", card.getId());
        contentValuesCard.put("cardName", card.getName());
        contentValuesCard.put("bitmapFront", card.getImageFrontBytes());
        contentValuesCard.put("bitmapBack", card.getImageBackBytes());
        db.update("Card", contentValuesCard, "id = ?",
                new String[]{String.valueOf(card.getId())});

        final ContentValues contentValuesBarcode = new ContentValues();
        contentValuesBarcode.put("content", card.getContentBarcode());
        db.update("Barcode", contentValuesBarcode, "id = ?",
                new String[]{String.valueOf(card.getId())});
    }

    public void deleteCard(long id) {
        db.delete("Barcode", "id = " + id, null);
        db.delete("Card", "id = " + id, null);
    }
}
