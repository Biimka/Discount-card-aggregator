package com.startandroid.biimka.discount_card_aggregator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CardRepoImpl implements CardRepo {

    private static final String TABLE_CARD = "Card";
    private static final String ID = "id";
    private static final String CARD_NAME = "cardName";
    private static final String BITMAP_FRONT = "bitmapFront";
    private static final String BITMAP_BACK = "bitmapBack";
    private static final String CARD_ID = "cardId";
    private static final String TABLE_BARCODE = "Barcode";
    private static final String CONTENT_BARCODE = "contentBarcode";
    private static final String BARCODE_ID = "barcodeId";

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
        final Cursor cardsCursor = db.query(TABLE_CARD, null, null,
                null, null, null, null);
        if (cardsCursor.moveToFirst()) {
            final int idColIndex = cardsCursor.getColumnIndex(ID);
            final int nameColIndex = cardsCursor.getColumnIndex(CARD_NAME);
            final int frontImageColIndex = cardsCursor.getColumnIndex(BITMAP_FRONT);
            final int backImageColIndex = cardsCursor.getColumnIndex(BITMAP_BACK);
            final int contentBarcodeColIndex = cardsCursor.getColumnIndex(CARD_ID);
            do {
                cards.add(new Card(cardsCursor.getInt(idColIndex),
                        cardsCursor.getString(nameColIndex),
                        dbHelper.getBitmap(cardsCursor.getBlob(frontImageColIndex)),
                        dbHelper.getBitmap(cardsCursor.getBlob(backImageColIndex)),
                        cardsCursor.getInt(contentBarcodeColIndex)));
            } while (cardsCursor.moveToNext());
        }
        cardsCursor.close();
        return cards;
    }

    @Override
    public Card getCard(long id) {
        final Cursor cardsCursor = db.query(TABLE_CARD, null, ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cardsCursor.moveToFirst()) {
            return new Card(cardsCursor.getInt(cardsCursor.getColumnIndex(ID)),
                    cardsCursor.getString(cardsCursor.getColumnIndex(CARD_NAME)),
                    dbHelper.getBitmap(cardsCursor.getBlob(cardsCursor.getColumnIndex(BITMAP_FRONT))),
                    dbHelper.getBitmap(cardsCursor.getBlob(cardsCursor.getColumnIndex(BITMAP_BACK))),
                    cardsCursor.getInt(cardsCursor.getColumnIndex(CARD_ID)));
        }
        cardsCursor.close();
        return null;
    }

    @Override
    public void createCard(Card card) {
        final ContentValues contentValuesCard = new ContentValues();
        contentValuesCard.put(CARD_NAME, card.getName());
        contentValuesCard.put(BITMAP_FRONT, dbHelper.getBytes(card.getImageFrontBytes()));
        contentValuesCard.put(BITMAP_BACK, dbHelper.getBytes(card.getImageBackBytes()));
        db.insert(TABLE_CARD, null, contentValuesCard);

        final ContentValues contentValuesBarcode = new ContentValues();
        contentValuesBarcode.put(CONTENT_BARCODE, card.getContentBarcode());
        db.insert(TABLE_BARCODE, null, contentValuesBarcode);
    }

    @Override
    public void updateCard(Card card) {
        final ContentValues contentValuesCard = new ContentValues();
        contentValuesCard.put(ID, card.getId());
        contentValuesCard.put(CARD_NAME, card.getName());
        contentValuesCard.put(BITMAP_FRONT, dbHelper.getBytes(card.getImageFrontBytes()));
        contentValuesCard.put(BITMAP_BACK, dbHelper.getBytes(card.getImageBackBytes()));
        db.update(TABLE_CARD, contentValuesCard, ID + " = ?",
                new String[]{String.valueOf(card.getId())});

        final ContentValues contentValuesBarcode = new ContentValues();
        contentValuesBarcode.put(CONTENT_BARCODE, card.getContentBarcode());
        db.update(TABLE_BARCODE, contentValuesBarcode, ID + " = ?",
                new String[]{String.valueOf(card.getId())});

    }

    public void deleteCard(long id) {
        db.delete(TABLE_BARCODE, ID + " = " + id, null);
        db.delete(TABLE_CARD, ID + " = " + id, null);
    }

    public static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, context.getString(R.string.myDataBase), null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table " + TABLE_BARCODE + "("
                    + ID + " integer primary key autoincrement,"
                    + CONTENT_BARCODE + " blob,"
                    + CARD_ID + " integer,"
                    + "FOREIGN KEY (" + CARD_ID + ") REFERENCES "
                    + TABLE_CARD + "(" + BARCODE_ID + ")" + ");");

            sqLiteDatabase.execSQL("create table " + TABLE_CARD + "("
                    + ID + " integer primary key autoincrement,"
                    + CARD_NAME + " text,"
                    + BITMAP_FRONT + " blob,"
                    + BITMAP_BACK + " blob,"
                    + BARCODE_ID + " integer,"
                    + "FOREIGN KEY (" + BARCODE_ID + ") REFERENCES "
                    + TABLE_BARCODE + "(" + CARD_ID + ")" + ");");

        }

        public byte[] getBytes(Bitmap image) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return outputStream.toByteArray();
        }

        public Bitmap getBitmap(byte[] bytes) {
            return BitmapFactory.decodeByteArray(bytes,
                    0, bytes.length);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        }
    }
}
