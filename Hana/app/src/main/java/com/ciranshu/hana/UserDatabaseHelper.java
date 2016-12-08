package com.ciranshu.hana;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.name;

/**
 * Created by A on 2016/12/8.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public String sCreateTableCommand;

    public UserDatabaseHelper (Context context, String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context, name, null, version);
        //
        sCreateTableCommand="";
        sCreateTableCommand="create table users ("
                + "id integer primary key autoincrement, "
                + "username text not null, "
                + "password text)";
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //
        if(sCreateTableCommand.isEmpty()) {
            //Log.e("FriendDBHelper", "fail");
            return;
        }
        else {
            db.execSQL(sCreateTableCommand);
            //Log.e("FriendDBHelper", "successful");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        //
    }
}

