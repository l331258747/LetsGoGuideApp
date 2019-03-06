package com.njz.letsgoappguides.ui.activites.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends BaseActivity {


    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_save)
    TextView titleSave;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;

    ArrayList<String> notime;

    @Override
    protected void initView() {
        notime=getIntent().getStringArrayListExtra(Constant.CALENDARNOTIME);
        if(notime!=null&&notime.size()>0){
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            for (String in:notime) {
                try{
                    Date date =sdf.parse(in);
                    calendarView.setDateSelected(date,true);
                }catch (ParseException e){
                    e.printStackTrace();
                }

            }
        }

        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        calendarView.state().edit()
                .setMinimumDate(new Date())
                .commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }



    @OnClick({R.id.left_iv, R.id.title_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                finish();
                break;
            case R.id.title_save:
                List<CalendarDay> calendarDays=calendarView.getSelectedDates();
                if(calendarDays!=null){
                    ArrayList<String> list=new ArrayList<>();
                    for (CalendarDay claDay:calendarDays) {
                        Date date=claDay.getDate();
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr=sdf.format(date);
                        list.add(dateStr);
                    }
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra(Constant.CALENDARNOTIME, list);
                    activity.setResult(Constant.CALENDARID,intent);
                    activity.finish(); //关闭活动
                }
                break;
        }
    }
}
