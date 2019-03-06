package com.njz.letsgoappguides.ui.activites.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.calendarDecorators.LunarDecorator;
import com.njz.letsgoappguides.customview.widget.calendarDecorators.PriceModel;
import com.njz.letsgoappguides.customview.widget.calendarDecorators.PrimeDayDisableDecorator;
import com.njz.letsgoappguides.model.server.PriceCalendarChildModel;
import com.njz.letsgoappguides.model.server.ServiceCalPriceInfo;
import com.njz.letsgoappguides.presenter.server.ServerPriceContract;
import com.njz.letsgoappguides.presenter.server.ServerPricePresenter;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.ServiceUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.OnClick;

public class PriceCalendarActivity extends BaseActivity implements OnMonthChangedListener, OnDateSelectedListener, ServerPriceContract.View{

    MaterialCalendarView calendarView;

    private LunarDecorator lunarDecorator;

    private List<PriceModel> priceModels = new ArrayList<>();
    private String year;
    private String month;
    private Date date;
    private List<PriceModel> changePrices = new ArrayList<>();
    private List<PriceModel> priceCalendar = new ArrayList<>();
    float defaultPrice;
    private LoadingDialog loadingDialog;

    int uniqueId;
    int toId;

    ServerPricePresenter mServerPricePresenter;
    private AlertDialog.Builder extBuilder;
    private AlertDialog extDialog;


    @Override
    public void getIntentData() {
        super.getIntentData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_price_calendar;
    }

    @Override
    public void initView() {

        toId=getIntent().getIntExtra("TOID",0);
        uniqueId=getIntent().getIntExtra("uniqueId",0);
        priceCalendar=getIntent().getParcelableArrayListExtra(Constant.PRICECALENDERINFO);
        if(getIntent().getStringExtra(Constant.DEFAULT_PRICE)!=null){//默认价格
            String defaultStr=getIntent().getStringExtra(Constant.DEFAULT_PRICE);
            defaultPrice=Float.parseFloat(defaultStr);
        }else{
            defaultPrice=0;
        }


        calendarView = $(R.id.calendarView);
        initCalendarView();
        initData();

        if(priceCalendar.size()>0){
            changePrices=priceCalendar;
            refreshData();
        }
        if(toId==1){//新增
        }else if(toId==2||toId==3){//修改  复制
            mServerPricePresenter=new ServerPricePresenter(context,this);
            if(!getIntent().getStringExtra("uniquevalue").equals("")){
//            mServerPricePresenter.getServeCalPriceInfo(uniqueId);
                mServerPricePresenter.getMonthPriceInfo(uniqueId,year+""+month+""+"01");
            }
        }

    }


    @Override
    public void onDateSelected(final @NonNull MaterialCalendarView widget,final @NonNull CalendarDay date, boolean selected) {
        final EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);//小数点和整数
        editText.setMaxLines(1);
        extBuilder = new AlertDialog.Builder(this);
        extBuilder.setTitle("请输入当天价格");
        extBuilder.setView(editText);
        extBuilder.setNegativeButton("取消", null);
        extBuilder.setPositiveButton("确定",null);
        extDialog=extBuilder.create();
        extDialog.show();
        extDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceNum=editText.getText().toString().trim();
                if(ServiceUtil.getPriceCheck(priceNum,context)){
                    changePrice(priceNum,date);
                    extDialog.cancel();
                }
            }
        });
//        refreshData();
    }

    public void changePrice(String editTextContext, CalendarDay date){
        //TODO 吧数据添加在改价集合
        for (int i =0;i<priceModels.size();i++){
            if(TextUtils.equals(priceModels.get(i).getTime(), DateUtil.dateToStr(date.getDate()))){
                priceModels.get(i).setPrice(editTextContext);
                changePrices.add(priceModels.get(i));
            }
        }
        refreshData();
    }

    public void initData() {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setCancelable(false);

        lunarDecorator = new LunarDecorator(year, month, priceModels);
        calendarView.addDecorator(lunarDecorator);
        refreshData();
    }

    public void refreshData(){
        loadingDialog.showNoText();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        priceModels.clear();
        for (int i = 0; i < ca.getActualMaximum(Calendar.DATE); i++) {
            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(defaultPrice+"");
            priceModel.setTime(ca.get(Calendar.YEAR) + "-"
                    + DateUtil.getWith0(ca.get(Calendar.MONTH) + 1) + "-"
                    + DateUtil.getWith0(i + 1));
            priceModels.add(priceModel);
        }

        //TODO 从改价集合中修改数据
        for (int j=0;j<changePrices.size();j++){
            for (int i = 0;i<priceModels.size();i++){
                if(TextUtils.equals(priceModels.get(i).getTime(), changePrices.get(j).getTime())){
                    priceModels.get(i).setPrice(changePrices.get(j).getPrice());
                    break;
                }
            }
        }

        //去除重复设置价格对象
        for (int i = 0; i < changePrices.size()-1; i++) {
            for (int j = changePrices.size()-1; j > i; j--) {
                if (changePrices.get(j).getTime().equals(changePrices.get(i).getTime())) {
                    changePrices.remove(changePrices.get(i));
                }
            }
        }

        lunarDecorator.setTime(year, month,priceModels);
        calendarView.invalidateDecorators();
        loadingDialog.dismiss();
    }


    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        this.date = date.getDate();
        year = DateUtil.dateToStr(date.getDate(), "yyyy");
        month = DateUtil.dateToStr(date.getDate(), "MM");
        String dateToStr = DateUtil.dateToStr(date.getDate(), "dd");

        if(toId==2||toId==3){//修改
            mServerPricePresenter.getMonthPriceInfo(uniqueId,year+""+month+""+dateToStr);
        }

        refreshData();
    }

    public void initCalendarView() {
        date = Calendar.getInstance().getTime();
        year = DateUtil.dateToStr(date, "yyyy");
        month = DateUtil.dateToStr(date, "MM");
        //设置当前时间
        calendarView.setCurrentDate(date);

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), instance1.get(Calendar.MONTH), 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.add(Calendar.YEAR, 1);
        instance2.set(instance2.get(Calendar.YEAR), instance2.get(Calendar.MONTH), instance2.getActualMaximum(Calendar.DATE));

        calendarView
                .state()
                .edit()
//                //设置一周的第一天是周日还是周一
                .setFirstDayOfWeek(Calendar.SUNDAY)
                //设置日期范围
//                .setMinimumDate(new Date())
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //设置周的文本
        calendarView.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});
//        calendarView.setSelectionMode( toId == 1?MaterialCalendarView.SELECTION_MODE_SINGLE:MaterialCalendarView.SELECTION_MODE_MULTIPLE );
        calendarView.setSelectionMode( MaterialCalendarView.SELECTION_MODE_SINGLE);
        calendarView.setSelectionColor(Color.parseColor("#ffe6d5"));

        //设置年月的title
        calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                StringBuffer buffer = new StringBuffer();
                int yearOne = day.getYear();
                int monthOne = day.getMonth() + 1;
                buffer.append(yearOne).append("年").append(monthOne).append("月");
                return buffer;
            }
        });
        //日期点击事件
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);

    }


    @OnClick({R.id.left_iv, R.id.title_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                finish();
                break;
            case R.id.title_save:
                Intent intent = new Intent();
                intent.putExtra("uniqueId",uniqueId);
                intent.putParcelableArrayListExtra(Constant.PRICECALENDERINFO, (ArrayList) changePrices);
                activity.setResult(Constant.DEFAULT_PRICEID,intent);
                activity.finish(); //关闭活动
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getServeCalPriceInfoSuccess(List<ServiceCalPriceInfo> datas) {
        if(datas==null)return;
//        for (int i=0;i<datas.size();i++){
//            ServiceCalPriceInfo infos=datas.get(i);
//            List<ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean> info=infos.getNjzGuideServeFormatPriceEntityList();
//            for (int a=0;a<info.size();a++){
//                PriceModel priceModel=new PriceModel();
//                ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean listBean=info.get(a);
//                priceModel.setTime(listBean.getYearInt() + "-"
//                        +  DateUtil.getWith0(listBean.getMonthInt())+ "-"
//                        + DateUtil.getWith0(listBean.getDateInt()));
//                priceModel.setPrice(listBean.getPrice()+"");
//                changePrices.add(priceModel);
//            }
//        }
//        if(changePrices.size()>0){
//            for (PriceModel in:changePrices) {
//                try{
//                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//                    Date date =sdf.parse(in.getTime());
//                    calendarView.setDateSelected(date,true);
//                    calendarView.setSelectionMode( MaterialCalendarView.SELECTION_MODE_MULTIPLE );
//                }catch (ParseException e){
//                    e.printStackTrace();
//                }
//
//            }
//        }
        refreshData();
    }

    @Override
    public void getServeCalPriceInfoFailed(String msg) {
        showShortToast(msg);
    }


    @Override
    public void getMonthPriceInfoSuccess(List<ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean> datas) {
        if(datas==null)return;
        for (int i=0;i<datas.size();i++){
            ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean infos=datas.get(i);
            PriceModel priceModel=new PriceModel();
            priceModel.setTime(infos.getYearInt() + "-"
                    +  DateUtil.getWith0(infos.getMonthInt())+ "-"
                    + DateUtil.getWith0(infos.getDateInt()));
            priceModel.setPrice(infos.getPrice()+"");
            changePrices.add(priceModel);
        }
        refreshData();
    }

    @Override
    public void getMonthPriceInfoFailed(String msg) {
        showShortToast(msg);
    }
}
