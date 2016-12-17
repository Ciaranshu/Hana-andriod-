package com.ciranshu.hana;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollingActivity extends AppCompatActivity {
    private NestedListView listView;
    List<String> data ;
    private TimelineAdapter timelineAdapter;
    public FlowerDatabaseHelper fdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (NestedListView) this.findViewById(R.id.listview);
        listView.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(this, getData());//getdata()换为数据库接口
        listView.setAdapter(timelineAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending following list to your E-mail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        fdbHelper = new FlowerDatabaseHelper(this, "userFlower.db", null, 2);
        SQLiteDatabase fdb = fdbHelper.getWritableDatabase();
        Cursor cursor = fdb.query("Flower", null, "id > ?", new String[]{"0"},
                null, null, null);

        if (cursor.moveToFirst()){
            do{
                String nameOfProject = cursor.getString(cursor.getColumnIndex("name"));
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                String _year = Integer.toString(year);
                String _month = Integer.toString(month);
                String _day = Integer.toString(day);
                map.put("title", nameOfProject);
                map.put("date", _year + "-" + _month + "-" + _day);
                map.put("body", description);
                list.add(map);

              }while(cursor.moveToNext());
        }
        cursor.close();

        return list;
    }
}
