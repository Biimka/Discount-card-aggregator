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
        final Cursor cardsCursor = db.query("Cards", null, null,
                null, null, null, null);
        if (cardsCursor.moveToFirst()) {
            final int idColIndex = cardsCursor.getColumnIndex("id");
            final int nameColIndex = cardsCursor.getColumnIndex("cardName");
            final int frontImageColIndex = cardsCursor.getColumnIndex("bitmapFront");
            final int backImageColIndex = cardsCursor.getColumnIndex("bitmapBack");
            final int formatBarcodeColIndex = cardsCursor.getColumnIndex("formatBarcode");
            final int contentBarcodeColIndex = cardsCursor.getColumnIndex("contentBarcode");
            do {
                cards.add(new Card(cardsCursor.getInt(idColIndex),
                        cardsCursor.getString(nameColIndex), cardsCursor.getBlob(frontImageColIndex),
                        cardsCursor.getBlob(backImageColIndex), cardsCursor.getString(formatBarcodeColIndex),
                        cardsCursor.getInt(contentBarcodeColIndex)));
            } while (cardsCursor.moveToNext());
        }
        cardsCursor.close();
        return cards;
    }

    @Override
    public Card getCard(long id) {
        final Cursor cardsCursor = db.query("Cards", null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cardsCursor.moveToFirst()) {
            return new Card(cardsCursor.getInt(cardsCursor.getColumnIndex("id")),
                    cardsCursor.getString(cardsCursor.getColumnIndex("cardName")),
                    cardsCursor.getBlob(cardsCursor.getColumnIndex("bitmapFront")),
                    cardsCursor.getBlob(cardsCursor.getColumnIndex("bitmapBack")),
                    cardsCursor.getString(cardsCursor.getColumnIndex("formatBarcode")),
                    cardsCursor.getInt(cardsCursor.getColumnIndex("contentBarcode")));
        }
        cardsCursor.close();
        return null;
    }

    @Override
    public void createCard(Card card) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("cardName", card.getName());
        contentValues.put("bitmapFront", card.getImageFrontBytes());
        contentValues.put("bitmapBack", card.getImageBackBytes());
        contentValues.put("formatBarcode", card.getFormatBarcode());
        contentValues.put("contentBarcode", card.getContentBarcode());
        db.insert("Cards", null, contentValues);
    }

    @Override
    public void updateCard(Card card) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("id", card.getId());
        contentValues.put("cardName", card.getName());
        contentValues.put("bitmapFront", card.getImageFrontBytes());
        contentValues.put("bitmapBack", card.getImageBackBytes());
        contentValues.put("formatBarcode", card.getFormatBarcode());
        contentValues.put("contentBarcode", card.getContentBarcode());
        db.update("Cards", contentValues, "id = ?",
                new String[]{String.valueOf(card.getId())});
    }

    public void deleteCard(long id) {
        db.delete("Cards", "id = " + id, null);
    }
}
