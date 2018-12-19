package com.startandroid.biimka.discount_card_aggregator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, context.getString(R.string.myDataBase), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Barcode("
                + "id integer primary key autoincrement,"
                + "content blob,"
                + "cardId integer,"
                + "FOREIGN KEY (cardId) REFERENCES Card(barcodeId)" + ");");

        sqLiteDatabase.execSQL("create table Card("
                + "id integer primary key autoincrement,"
                + "cardName text,"
                + "bitmapFront blob,"
                + "bitmapBack blob,"
                + "barcodeId integer,"
                + "FOREIGN KEY (barcodeId) REFERENCES Barcode(cardId)" + ");");

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
