package com.njz.letsgoappguides.customview.widget.calendarDecorators;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.text.style.LineBackgroundSpan;
import android.util.Log;

import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 作者：徐敏敏 on 2016/12/20 0020 14:27
 * 邮箱：15067596185@163.com
 */
public class LunarSpan implements LineBackgroundSpan {
    private String year;
    private String month;
    List<PriceModel> priceModels;

    public LunarSpan() {
    }

    public LunarSpan(String year, String month, List<PriceModel> priceModels) {
        this.year = year;
        this.month = month;
        this.priceModels = priceModels;
    }

    public LunarSpan(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public void setTime(String year, String month, List<PriceModel> priceModels) {
        this.year = year;
        this.month = month;
        this.priceModels = priceModels;
    }

    public void setTime(String year, String month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        StringBuffer buffer = new StringBuffer();
        int day = Integer.valueOf(text.toString());
        buffer.append(year).append("-").append(month).append("-").append(DateUtil.getWith0(day));

//        Date parse = DateUtil.str2Date(buffer.toString(), "yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(parse);

//        String chinaDayString = "";
//        if(priceModels !=null && priceModels.size() > 0){
//            chinaDayString = "￥"+ priceModels.get(day-1).getPrice();
//        }

        if (priceModels.size() > 0) {
            if (!TextUtils.equals(priceModels.get(0).getYear(), year)
                    && !TextUtils.equals(priceModels.get(0).getMonth(), month)) {
                return;
            }
        }

        String chinaDayString = "";
        for (int i = 0; i < priceModels.size(); i++) {
            if (TextUtils.equals(priceModels.get(i).getTime(), buffer.toString())) {
                chinaDayString = "￥" + priceModels.get(i).getPrice();
                break;
            }
        }


        Paint paint = new Paint();
        paint.setTextSize(AppUtils.dip2px(10));
//        paint.setColor(Color.parseColor("#ff7c00"));
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText(chinaDayString, (right - left) / 2, (bottom - top) / 2 + AppUtils.dip2px(17), paint);
    }

}