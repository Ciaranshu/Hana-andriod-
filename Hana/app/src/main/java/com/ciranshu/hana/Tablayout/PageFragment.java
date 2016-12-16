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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ciranshu.hana.MainActivity;
import com.ciranshu.hana.R;

import java.util.ArrayList;
import java.util.List;

import static android.icu.util.Calendar.*;


public class PageFragment extends Fragment {

    public final int EMPTY=0,INUSE=1,DYING=2,DONE=3;
    private Spinner spinner;
    private ArrayAdapter<String> arr_adapter;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ImageButton flower1;

    private ImageButton btn1;

//
    //
int state1=EMPTY;
    String nameofproject1="FUCK";
    String description="FUCKING";
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
        View view = inflater.inflate(R.layout.outside, container, false);
        if(mPage==1)
        {
            //刷新函数
            if(state1==INUSE)
            {
                Calendar tempdata=newdate1;
                tempdata.add(Calendar.DAY_OF_MONTH,2);
                Calendar now=getInstance();
                now.getTime();
                if(now.get(DAY_OF_MONTH)==tempdata.get(DAY_OF_MONTH))
                {
                    state1=DYING;
                }

            }
            //
            newdate1.getTime();
           view = inflater.inflate(R.layout.inside, container, false);

            btn1 = (ImageButton)view.findViewById(R.id.Btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  spinner = (Spinner)getActivity().findViewById(R.id.day);
                    //适配器
                    arr_adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.plan_day_length));
                    //设置样式
                   // arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //加载适配器
                    spinner.setAdapter(arr_adapter);*/
                   switch(state1)//获取Button状态
                    {
                       case EMPTY:
                    dialogEmpty(v.getId());
                    break;
                    case DYING:
                    dialogDying(v.getId());
                        break;
                    case INUSE:
                       dialogInuse(newdate1,state1,nameofproject1,description, needdays1,nowdays1,note1);
                        break;
                     case DONE:
//                            dialogDone(v.getId());
                            break;
               }

        }
        });
        }
        else if (mPage == 2) {

            view = inflater.inflate(R.layout.outside, container, false);
//            flower1 = (ImageButton)view.findViewById(R.id.imageButton1);
//            flower1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog();
//                }
//            });
            //flower1 = (ImageButton)findViewById(R.id.imageButton1);
        }

        return view;
       /* View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;*/

    }



    protected void dialogEmpty(int viewId) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View customDialog = layoutInflater.inflate(R.layout.dialog_empty, null);
       //setSpinnerDays();
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);
        customDialog.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
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
                //上述信息写入database对应位置 通过viewId
                //更改按钮状态为INUSE
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

    protected void dialogDying(int viewId){
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View customDialog = layoutInflater.inflate(R.layout.dialog_dying, null);
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(customDialog);
        dialog.setCanceledOnTouchOutside(false);
        customDialog.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        customDialog.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把这个按钮的信息写为空，给数据库
                dialog.dismiss();
            }
        });
    }

/*   protected void setSpinnerDays() {
        spinner = (Spinner)getActivity().findViewById(R.id.day);
       //适配器
       arr_adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.plan_day_length));
       //设置样式
       arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       //加载适配器
       spinner.setAdapter(arr_adapter);



    }*/

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

