package com.ciranshu.hana.Tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ciranshu.hana.FlowerDatabaseHelper;
import com.ciranshu.hana.R;


public class PageFragment extends Fragment {
    public String userName;
    public FlowerDatabaseHelper fdbHelper;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ImageButton flower1;
    private ImageButton btn1;

    public static PageFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outside, container, false);
        if(mPage==1)
        {
            view = inflater.inflate(R.layout.inside, container, false);
            btn1 = (ImageButton)view.findViewById(R.id.Btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    queryFlower(userName);
                    dialog();
                }
            });
        }
        else if (mPage == 2) {
            view = inflater.inflate(R.layout.outside, container, false);
            flower1 = (ImageButton)view.findViewById(R.id.imageButton1);
            final String flower_state = "";
            flower1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(flower_state){
                        case "生长中":
                            Context context = null;
                            fdbHelper = new FlowerDatabaseHelper(context, userName + ".db", null, 1);
                            SQLiteDatabase db = fdbHelper.getWritableDatabase();
                            Cursor cursor = db.query("Flower", new String[]{"id"}, "id = ?",
                                    new String[]{"1"},
                                    null, null, null);
                            cursor.moveToFirst();
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            int year = cursor.getInt(cursor.getColumnIndex("year"));
                            int month = cursor.getInt(cursor.getColumnIndex("month"));
                            int day = cursor.getInt(cursor.getColumnIndex("day"));
                            int hour = cursor.getInt(cursor.getColumnIndex("hour"));
                            int minute = cursor.getInt(cursor.getColumnIndex("minute"));
                            int second = cursor.getInt(cursor.getColumnIndex("second"));
                            int frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
                            int is_finished = 0;
                            cursor.close();
                            break;

                        case "未种植":
                            addFlower(null, null, 0, 0, 0, 0, 0, 0, 0);
                            break;
                        case "死亡":
                            break;
                    }
                    dialog();
                }
            });
            //flower1 = (ImageButton)findViewById(R.id.imageButton1);
        }
        return view;
       /* View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;*/

    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("你是智障吗？");

        builder.setTitle("Warning");

        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void addFlower(String userName, String flowerName, int year, int month, int day,
                          int hour, int minute, int second, int frequency){
        Context context = null;
        fdbHelper = new FlowerDatabaseHelper(context, userName + ".db", null, 1);
        SQLiteDatabase db = fdbHelper.getWritableDatabase();

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
        values.put("hour", hour);
        values.put("minute", minute);
        values.put("second", second);
        values.put("is_finished", 0);
        db.insert("Flower", null, values);
    }

    public void queryFlower(String userName) {
        Context context = null;
        fdbHelper = new FlowerDatabaseHelper(context, userName + ".db", null, 1);
        SQLiteDatabase db = fdbHelper.getWritableDatabase();

        Cursor cursor = db.query("Flower", null, null, null,
                null, null, null);
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                int hour = cursor.getInt(cursor.getColumnIndex("hour"));
                int minute = cursor.getInt(cursor.getColumnIndex("minute"));
                int second = cursor.getInt(cursor.getColumnIndex("second"));
                int frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
            } while(cursor.moveToNext());
        }
        cursor.close();
    }
}