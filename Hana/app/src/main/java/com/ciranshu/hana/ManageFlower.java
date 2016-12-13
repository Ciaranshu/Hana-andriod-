package com.ciranshu.hana;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by chtt on 2016/12/6.
 */

public class ManageFlower extends Activity{
    public FlowerDatabaseHelper dbHelper;
    public void addFlower(String userName, String flowerName, int year, int month, int day,
                          int hour, int minute, int second, int frequency){
        dbHelper = new FlowerDatabaseHelper(this, userName + ".db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        final String CREATE_FLOWER_NOTE = "create table " + flowerName + "_note ("
                + "id integer primary key autoincrement, "
                + "imagePath text, "
                + "note text, "
                + "label text)";
        db.execSQL(CREATE_FLOWER_NOTE);

        ContentValues values = new ContentValues();
        values.put("name", flowerName);
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);
        values.put("is_finished", 0);
        db.insert("Flower", null, values);
    }

    public void queryFlower(String userName) {
        dbHelper = new FlowerDatabaseHelper(this, userName + ".db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Flower", null, null, null,
                null, null, null);
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                int frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
            } while(cursor.moveToNext());
        }
        cursor.close();
    }

    public void addNote(String userName, String flowerName, String imagePath,
                        String note, String label) {
        dbHelper = new FlowerDatabaseHelper(this, userName + ".db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("imagePath", imagePath);
        values.put("note", note);
        values.put("label", label);
        db.insert(flowerName + "_note", null, values);
    }

    public void queryNote(String userName, String flowerName) {
        dbHelper = new FlowerDatabaseHelper(this, userName + ".db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(flowerName + "_note", null, null, null,
                null, null, null);
        if (cursor.moveToFirst()){
            do{
                String imagePath = cursor.getString(cursor.getColumnIndex("imagePath"));
                String note = cursor.getString(cursor.getColumnIndex("note"));
                String label = cursor.getString(cursor.getColumnIndex("label"));
            } while(cursor.moveToNext());
        }
        cursor.close();
    }
}
