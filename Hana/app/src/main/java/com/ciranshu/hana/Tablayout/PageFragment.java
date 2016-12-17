package com.ciranshu.hana.Tablayout;

import android.app.Dialog;
import android.content.DialogInterface;
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
    int state1=INUSE;
    String nameofproject1="FUCK";
    String description1="FUCKING";
    int needdays1=30;
    int nowdays1=20;
    Calendar newdate1= getInstance();
    String note1="miao";
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

            if(state1==INUSE)
            {
                if(nowdays1==needdays1)
                {
                    state1=DONE;
                }
                //判断是否为完成。
                Calendar tempdata=newdate1;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state1=DYING;
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


            newdate1.getTime();
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
                            dialogInuse(newdate1,state1,nameofproject1,description1, needdays1,nowdays1,note1);
                            //这些参数是前面声明的 用于测试 实质为从数据库获取的该项目的各个信息
                            break;
                        case DONE:
                            dialogDone(state1,nameofproject1,description1);
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
                String n=name.getText().toString();
                String d=des.getText().toString();
                String d1=day.getText().toString();

                //上述信息需要写入database对应位置
                //并且更改按钮状态state1为INUSE
                //下面为当前日期减一
                final Calendar nowdate= getInstance();
                nowdate.getTime();
                nowdate.add(DAY_OF_MONTH,-1);

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
                            String description, int needdays, int nowdays,
                            String note) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View customDialog = layoutInflater.inflate(R.layout.dialog_inuse, null);
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
                nowdate.add(DAY_OF_MONTH,1);//把这个传给数据库作为newdate
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

