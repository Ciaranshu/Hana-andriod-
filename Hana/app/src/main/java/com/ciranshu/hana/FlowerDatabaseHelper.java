package com.ciranshu.hana;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by chtt on 2016/12/6.
 */
public class FlowerDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_FLOWER = "create table Flower ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "year integer, "
            + "month integer"
            + "day integer"
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    //table name (Category) should be input
    //db name is from user name

    private Context mContext;

    public FlowerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
     //   db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists Flower");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
