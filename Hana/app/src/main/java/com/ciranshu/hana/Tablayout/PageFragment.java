package com.ciranshu.hana.Tablayout;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ciranshu.hana.FlowerDatabaseHelper;
import com.ciranshu.hana.MainActivity;
import com.ciranshu.hana.NestedListView;
import com.ciranshu.hana.R;
import com.ciranshu.hana.TimelineAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.icu.util.Calendar.*;


public class PageFragment extends Fragment {

    public final int EMPTY=0,INUSE=1,DYING=2,DONE=3;
    private ArrayAdapter<String> arr_adapter;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    public FlowerDatabaseHelper fdbHelper;
    //该按钮
    public ImageButton btn1;
    public ImageButton btn2;
    public ImageButton btn3;
    public ImageButton btn4;
    public ImageButton btn5;
    public ImageButton btn6;
    public ImageButton btn7;
    public ImageButton btn8;
    public ImageButton btn9;



    //初始变量 用于测试
    //一号小组
    int state1=EMPTY;
    String nameofproject1="FUCK";
    String description1="FUCKING";
    int needdays1=30;
    int nowdays1=20;
    Calendar newdate1= getInstance();;
    //
    //二号小组
    int state2=INUSE;
    String nameofproject2="FUCK";
    String description2="FUCKING";
    int needdays2=30;
    int nowdays2=20;
    Calendar newdate2= getInstance();
    //
    //三号小组
    int state3=INUSE;
    String nameofproject3="FUCK";
    String description3="FUCKING";
    int needdays3=30;
    int nowdays3=20;
    Calendar newdate3= getInstance();
    //
    //四号小组
    int state4=INUSE;
    String nameofproject4="FUCK";
    String description4="FUCKING";
    int needdays4=30;
    int nowdays4=20;
    Calendar newdate4= getInstance();
    //
    //五号小组
    int state5=INUSE;
    String nameofproject5="FUCK";
    String description5="FUCKING";
    int needdays5=30;
    int nowdays5=20;
    Calendar newdate5= getInstance();
    //
    //六号小组
    int state6=INUSE;
    String nameofproject6="FUCK";
    String description6="FUCKING";
    int needdays6=30;
    int nowdays6=20;
    Calendar newdate6= getInstance();
    //
    //七号小组
    int state7=INUSE;
    String nameofproject7="FUCK";
    String description7="FUCKING";
    int needdays7=30;
    int nowdays7=20;
    Calendar newdate7= getInstance();
    //
    //八号小组
    int state8=INUSE;
    String nameofproject8="FUCK";
    String description8="FUCKING";
    int needdays8=30;
    int nowdays8=20;
    Calendar newdate8= getInstance();
    //
    //九号小组
    int state9=INUSE;
    String nameofproject9="FUCK";
    String description9="FUCKING";
    int needdays9=30;
    int nowdays9=20;
    Calendar newdate9= getInstance();
    String note9="miao";
    //

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inside, container, false);
        if(mPage==1)
        {
            //刷新函数
            fdbHelper = new FlowerDatabaseHelper(getActivity(), "userFlower.db", null, 2);
            SQLiteDatabase fdb = fdbHelper.getWritableDatabase();
            Cursor cursor = fdb.query("Flower", new String[]{"id"}, "id = ?",
                    new String[]{"1"}, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    nameofproject1 = cursor.getString(cursor.getColumnIndex("name"));
                    needdays1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("needDays")));
                    nowdays1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("nowDays")));
                    int year = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
                    int month = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
                    int day = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
                    newdate1.getTime();
                    newdate1.set(Calendar.YEAR, year);
                    newdate1.set(Calendar.MONTH, month);
                    newdate1.set(Calendar.DAY_OF_MONTH, day);
                    state1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("is_finished")));
                } while(cursor.moveToNext());
            }
            cursor.close();
            //第1个按钮
            if(state1==INUSE)
            {
                if(nowdays1==needdays1)
                {
                    state1=DONE;
                    fdbHelper = new FlowerDatabaseHelper(getActivity(), "userFlower.db", null, 2);
                    fdb = fdbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("is_finished", 3);
                    fdb.update("Flower", values, "id = ?", new String[]{"1"});
                }
                //判断是否为完成。
                Calendar tempdata=newdate1;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state1=DYING;
                    fdbHelper = new FlowerDatabaseHelper(getActivity(), "userFlower.db", null, 2);
                    fdb = fdbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("is_finished", 2);
                    fdb.update("Flower", values, "id = ?", new String[]{"1"});
                }
                //判断是否死亡
            }
            btn1 = (ImageButton)view.findViewById(R.id.Btn1);

            if(state1==EMPTY)
                btn1.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state1==INUSE)
                btn1.setImageDrawable(getResources().getDrawable(R.drawable.flower1));
            else if(state1==DYING)
                btn1.setImageDrawable(getResources().getDrawable(R.drawable.die1));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state1)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate1,state1,nameofproject1,description1, needdays1,nowdays1);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state1,nameofproject1,description1);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第2个按钮
            if(state2==INUSE)
            {
                if(nowdays2==needdays2)
                {
                    state2=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate2;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state1=DYING;
                }
                //判断是否死亡
            }
            btn2 = (ImageButton)view.findViewById(R.id.Btn2);

            if(state2==EMPTY)
                btn2.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state2==INUSE)
                btn2.setImageDrawable(getResources().getDrawable(R.drawable.flower2));
            else if(state2==DYING)
                btn2.setImageDrawable(getResources().getDrawable(R.drawable.die2));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state2)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate2,state2,nameofproject2,description2, needdays2,nowdays2);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state2,nameofproject2,description2);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第3个按钮
            if(state3==INUSE)
            {
                if(nowdays3==needdays3)
                {
                    state3=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate3;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state1=DYING;
                }
                //判断是否死亡
            }
            btn3 = (ImageButton)view.findViewById(R.id.Btn3);

            if(state3==EMPTY)
                btn3.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state3==INUSE)
                btn3.setImageDrawable(getResources().getDrawable(R.drawable.flower3));
            else if(state3==DYING)
                btn3.setImageDrawable(getResources().getDrawable(R.drawable.die3));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state3)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate3,state3,nameofproject3,description3, needdays3,nowdays3);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state3,nameofproject3,description3);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第4个按钮
            if(state4==INUSE)
            {
                if(nowdays4==needdays4)
                {
                    state4=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate4;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state4=DYING;
                }
                //判断是否死亡
            }
            btn4 = (ImageButton)view.findViewById(R.id.Btn4);

            if(state4==EMPTY)
                btn4.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state4==INUSE)
                btn4.setImageDrawable(getResources().getDrawable(R.drawable.flower4));
            else if(state4==DYING)
                btn4.setImageDrawable(getResources().getDrawable(R.drawable.die4));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state4)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate4,state4,nameofproject4,description4, needdays4,nowdays4);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state4,nameofproject4,description4);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第5个按钮
            if(state5==INUSE)
            {
                if(nowdays5==needdays5)
                {
                    state5=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate5;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state5=DYING;
                }
                //判断是否死亡
            }
            btn5 = (ImageButton)view.findViewById(R.id.Btn5);

            if(state5==EMPTY)
                btn5.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state5==INUSE)
                btn5.setImageDrawable(getResources().getDrawable(R.drawable.flower5));
            else if(state5==DYING)
                btn5.setImageDrawable(getResources().getDrawable(R.drawable.die5));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state5)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate5,state5,nameofproject5,description5, needdays5,nowdays5);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state5,nameofproject5,description5);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第6个按钮
            if(state6==INUSE)
            {
                if(nowdays6==needdays6)
                {
                    state6=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate6;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state6=DYING;
                }
                //判断是否死亡
            }
            btn6 = (ImageButton)view.findViewById(R.id.Btn6);

            if(state6==EMPTY)
                btn6.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state6==INUSE)
                btn6.setImageDrawable(getResources().getDrawable(R.drawable.flower6));
            else if(state6==DYING)
                btn6.setImageDrawable(getResources().getDrawable(R.drawable.die6));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state6)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate6,state6,nameofproject6,description6, needdays6,nowdays6);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state6,nameofproject6,description6);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第7个按钮
            if(state7==INUSE)
            {
                if(nowdays7==needdays7)
                {
                    state7=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate7;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state7=DYING;
                }
                //判断是否死亡
            }
            btn7 = (ImageButton)view.findViewById(R.id.Btn7);

            if(state7==EMPTY)
                btn7.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state7==INUSE)
                btn7.setImageDrawable(getResources().getDrawable(R.drawable.flower7));
            else if(state7==DYING)
                btn7.setImageDrawable(getResources().getDrawable(R.drawable.die7));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state7)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate7,state7,nameofproject7,description7, needdays7,nowdays7);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state7,nameofproject7,description7);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第8个按钮
            if(state8==INUSE)
            {
                if(nowdays8==needdays8)
                {
                    state8=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate8;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state8=DYING;
                }
                //判断是否死亡
            }
            btn8 = (ImageButton)view.findViewById(R.id.Btn8);

            if(state8==EMPTY)
                btn8.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state8==INUSE)
                btn8.setImageDrawable(getResources().getDrawable(R.drawable.flower8));
            else if(state8==DYING)
                btn8.setImageDrawable(getResources().getDrawable(R.drawable.die8));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state8)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate8,state8,nameofproject8,description8, needdays8,nowdays8);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state8,nameofproject8,description8);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

            //第9个按钮
            if(state9==INUSE)
            {
                if(nowdays9==needdays9)
                {
                    state9=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate9;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state9=DYING;
                }
                //判断是否死亡
            }
            btn9 = (ImageButton)view.findViewById(R.id.Btn9);

            if(state9==EMPTY)
                btn9.setImageDrawable(getResources().getDrawable(R.drawable.pot));
            else if(state9==INUSE)
                btn9.setImageDrawable(getResources().getDrawable(R.drawable.flower9));
            else if(state9==DYING)
                btn9.setImageDrawable(getResources().getDrawable(R.drawable.die9));

            //newdate1.getTime();
            //view = inflater.inflate(R.layout.inside, container, false);


            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(state9)//获取Button状态
                    {
                        case EMPTY:
                            dialogEmpty();
                            break;
                        case DYING:
                            dialogDying();
                            break;
                        case INUSE:
                            dialogInuse(newdate9,state9,nameofproject9,description9, needdays9,nowdays9);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state9,nameofproject9,description9);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                    }

                }
            });

        }
        else if (mPage == 2) {

            view = inflater.inflate(R.layout.outside, container, false);

        }
        return view;
    }



    protected void dialogEmpty() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View customDialog = layoutInflater.inflate(R.layout.dialog_empty, null);
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);
        customDialog.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                EditText name;
                EditText day;
                EditText des;
                name=(EditText)customDialog.findViewById(R.id.name_1) ;
                day=(EditText)customDialog.findViewById(R.id.day_1) ;
                des=(EditText)customDialog.findViewById(R.id.des_1);
                String flowerName=name.getText().toString();
                String description=des.getText().toString();
                String needDays=day.getText().toString();

                final Calendar nowdate= getInstance();
                nowdate.getTime();
                nowdate.add(DAY_OF_MONTH,-1);
                int year=nowdate.get(YEAR);
                int month=nowdate.get(MONTH);
                int date=nowdate.get(DAY_OF_MONTH);

                fdbHelper = new FlowerDatabaseHelper(getActivity(), "userFlower.db", null, 2);
                SQLiteDatabase fdb = fdbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("name", flowerName);
                values.put("year", year);
                values.put("month", month);
                values.put("day", date);
                values.put("needDays", needDays);
                values.put("nowDays", 0);
                values.put("is_finished", 1);
                values.put("description", description);
                fdb.insert("Flower", null, values);

                state1 = INUSE;

                //上述信息需要写入database对应位置
                //并且更改按钮状态state1为INUSE
                //下面为当前日期减一
                dialog.dismiss();
            }
        });
        customDialog.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    protected void dialogDying(){
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View customDialog = layoutInflater.inflate(R.layout.dialog_dying, null);
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);
        customDialog.findViewById(R.id.confirm_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        customDialog.findViewById(R.id.delete_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把这个按钮的信息全部写为空，给数据库
                dialog.dismiss();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dialogInuse(Calendar newdate, int State, String nameofproject,
                            String description, int needdays, int nowdays) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View customDialog = layoutInflater.inflate(R.layout.dialog_inuse, null);
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);

        //变量说明
        TextView Checkable=(TextView)customDialog.findViewById(R.id.Checkable);
        TextView Nameofproject=(TextView)customDialog.findViewById(R.id.name_2);
        TextView Description=(TextView)customDialog.findViewById(R.id.des_2);
        ImageButton Check=(ImageButton)customDialog.findViewById(R.id.Check);
        ImageButton Delete=(ImageButton)customDialog.findViewById(R.id.delete_2);
        ProgressBar progressBar=(ProgressBar)customDialog.findViewById(R.id.progressBar);
        //内容
        Nameofproject.setText(nameofproject);
        Description.setText(description);
        progressBar.setMax(needdays);
        progressBar.setProgress(nowdays);
        //按当前日期和上次打卡日期对比
        final Calendar nowdate= getInstance();
        nowdate.getTime();
        if ((nowdate.get(Calendar.MONTH)==newdate.get(Calendar.MONTH))&&
                (nowdate.get(Calendar.DAY_OF_MONTH)==newdate.get(Calendar.DAY_OF_MONTH)))
        {
            Checkable.setText("已打卡");
        }
        newdate.add(DAY_OF_MONTH,1);
        if(newdate==nowdate)
        {
            Checkable.setText("未打卡");
        }

        //下面是在弹出框里出发的按钮函数

//        打卡
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowdate.add(DAY_OF_MONTH,1);
                EditText Note=(EditText)customDialog.findViewById(R.id.Note);
                String myNote=Note.getText().toString();
                //把这个传给数据库作为newdate
                dialog.dismiss();
            }
        });
        //删除
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把这个按钮的信息写为空，给数据库
                dialog.dismiss();
            }
        });
    }


    public void dialogDone( int state, String nameofproject,
                            String description) {
        //需要舒畅在这个界面加时间轴


        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View customDialog = layoutInflater.inflate(R.layout.dialog_done, null);
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);

        ListView listView = (ListView) customDialog.findViewById(R.id.listviewDone);
        listView.setDividerHeight(0);
        //TimelineAdapter timelineAdapter = new TimelineAdapter(customDialog, getData());
        //getdata()换为数据库接口
        //listView.setAdapter(timelineAdapter);

        //变量说明
        TextView Nameofproject = (TextView) customDialog.findViewById(R.id.name_4);
        TextView Description = (TextView) customDialog.findViewById(R.id.des_4);
        ImageButton Delete = (ImageButton) customDialog.findViewById(R.id.delete_4);
        ImageButton Confirm = (ImageButton) customDialog.findViewById(R.id.confirm_4);
        //展示内容
        Nameofproject.setText(nameofproject);
        Description.setText(description);

        //确定按钮 离开弹窗
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //删除
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把这个按钮的信息写为空，给数据库
                dialog.dismiss();
            }
        });
    }
}

