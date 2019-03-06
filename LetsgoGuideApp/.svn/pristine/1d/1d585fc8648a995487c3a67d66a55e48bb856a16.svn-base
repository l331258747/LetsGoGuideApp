package com.njz.letsgoappguides.util.other;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.njz.letsgoappguides.customview.widget.MineEditView;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.DateUtil;

import java.util.Date;



public class DateViewUtils {

    public static void initDate(final MineEditView mev, Context context){
        AppUtils.HideKeyboard(mev);
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(context,
                new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mev.setContent(DateUtil.dateToStr(date));
                    }
                })
//                .setDate(DateUtil.getSelectedDate(mev.getContent()))// 如果不设置的话，默认是系统时间*/
//                .setRangDate(DateUtil.getStartDate(), DateUtil.getEndDate())//起始终止年月日设定
                .build();
        pvTime.show();
    }
}
