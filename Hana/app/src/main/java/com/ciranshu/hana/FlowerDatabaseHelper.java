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
            + "month integer, "
            + "day integer, "
            + "needDays integer, "
            + "nowDays integer, "
            + "description text, "
            + "is_finished integer)";

    public static final String CREATE_FLOWER_NOTE_1 = "create table flower1note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_2 = "create table flower2note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_3 = "create table flower3note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_4 = "create table flower4note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_5 = "create table flower5note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_6 = "create table flower6note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_7 = "create table flower7note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_8 = "create table flower8note ( "
            + "id integer primary key autoincrement, "
            + "note text)";

    public static final String CREATE_FLOWER_NOTE_9 = "create table flower9note ( "
            + "id integer primary key autoincrement, "
            + "note text)";
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
        db.execSQL(CREATE_FLOWER);
        db.execSQL(CREATE_FLOWER_NOTE_1);
        db.execSQL(CREATE_FLOWER_NOTE_2);
        db.execSQL(CREATE_FLOWER_NOTE_3);
        db.execSQL(CREATE_FLOWER_NOTE_4);
        db.execSQL(CREATE_FLOWER_NOTE_5);
        db.execSQL(CREATE_FLOWER_NOTE_6);
        db.execSQL(CREATE_FLOWER_NOTE_7);
        db.execSQL(CREATE_FLOWER_NOTE_8);
        db.execSQL(CREATE_FLOWER_NOTE_9);
        Toast.makeText(mContext, "Create succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists Flower");
        onCreate(db);
    }
}
