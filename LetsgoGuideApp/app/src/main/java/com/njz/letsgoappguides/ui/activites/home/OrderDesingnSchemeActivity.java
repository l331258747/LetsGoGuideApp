package com.njz.letsgoappguides.ui.activites.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.MineTextView;
import com.njz.letsgoappguides.model.home.OrderDesign2Info;
import com.njz.letsgoappguides.model.home.OrderDesignInfo;
import com.njz.letsgoappguides.presenter.home.OrderDesingnContract;
import com.njz.letsgoappguides.presenter.home.OrderDesingnPresenter;
import com.njz.letsgoappguides.ui.activites.service.ServicesFeatureActivity;
import com.njz.letsgoappguides.util.ServiceUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.CalendarEvent;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshOrderListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 方案设计
 */

public class OrderDesingnSchemeActivity extends BaseActivity implements OrderDesingnContract.View {

    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.desingn_title)
    MineTextView desingnTitle;
    @BindView(R.id.desingn_time)
    MineTextView desingnTime;
    @BindView(R.id.desingn_price)
    EditText desingnPrice;
    @BindView(R.id.ll_desingn_scheme)
    MineTextView llDesingnScheme;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.ll_desingn_priceinfo)
    EditText llDesingnPriceinfo;
    @BindView(R.id.edit_text_11)
    TextView editText11;
    @BindView(R.id.edit_text_12)
    EditText editText12;
    @BindView(R.id.edit_text_13)
    EditText editText13;
    @BindView(R.id.edit_text_21)
    TextView editText21;
    @BindView(R.id.edit_text_22)
    EditText editText22;
    @BindView(R.id.edit_text_23)
    EditText editText23;
    @BindView(R.id.ll_penalty)
    LinearLayout llPenalty;
    @BindView(R.id.desingn_btn_sub)
    TextView desingnBtnSub;

    String myDesingn="";
    int id;//方案id
    int order_id;//订单id
    Disposable disposable;
    OrderDesingnPresenter mOrderDesingnPresenter;
    int status;
    List<String> markerDays=new ArrayList<>();
   String markDays;

    @Override
    protected void initView() {
        order_id=getIntent().getIntExtra("ORDER_ID",0);
        status=getIntent().getIntExtra("STATUS",0);
        switch (status){
            case 1://设计方案
                titleTv.setText("方案设计");
                break;
            case 2://修改方案
                titleTv.setText("方案修改");
                break;
        }

        desingnPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mOrderDesingnPresenter=new OrderDesingnPresenter(context,this);

        llDesingnScheme.setFocusables(false, new View.OnClickListener() {//行程设计
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ServicesFeatureActivity.class);
                intent.putExtra("ID",103);
                intent.putExtra("myDesingn",myDesingn);
                startActivityForResult(intent,103);
            }
        });

        desingnTime.setFocusables(false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {//行程时间
                Intent intent=new Intent(context,RangeCalendarActivity.class);
                startActivity(intent);
            }
        });

        mOrderDesingnPresenter.orderDesingn(order_id);
        if(myDesingn.equals("")||myDesingn.equals("<br>")){
        }else{
            llDesingnScheme.setContent("已编辑");
        }

        disposable=RxBus2.getInstance().toObservable(CalendarEvent.class, new Consumer<CalendarEvent>() {
            @Override
            public void accept(CalendarEvent calendarEvent) throws Exception {
                desingnTime.setEditContent(calendarEvent.getStartTime()+"  "+calendarEvent.getDays()+"  "+calendarEvent.getEndTime());
                markerDays=calendarEvent.getMarkerDays();
                Log.e("test","markerDays"+markerDays.toString());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_desingn_scheme;
    }


    @OnClick({R.id.left_iv, R.id.ll_desingn_scheme, R.id.desingn_btn_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                new AlertDialog.Builder(context).setTitle("您确定要放弃此次编辑？").setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).setNegativeButton("取消", null).show();
                break;
            case R.id.ll_desingn_scheme:
                break;
            case R.id.desingn_btn_sub:
                if(checkName()){
                    OrderDesign2Info designInfo=new OrderDesign2Info();
                    designInfo.setId(id);//方案id
                    designInfo.setOrderId(order_id);
                    designInfo.setTitle(desingnTitle.getEditContent());
                    if(markerDays.size()>0){
                        markDays=markerDays.toString().substring(1,markerDays.toString().length()-1);
                    }
                    designInfo.setTravelDate(markDays);
                    String edit11= editText11.getText().toString().trim();
                    String edit12= editText12.getText().toString().trim();
                    float edit13= Float.valueOf(editText13.getText().toString().trim());
                    String edit21= editText21.getText().toString().trim();
                    String edit22= editText22.getText().toString().trim();
                    float edit23= Float.valueOf(editText23.getText().toString().trim());
                    designInfo.setRenegePriceThree(edit11+","+edit12+","+(edit13/100));
                    designInfo.setRenegePriceFive(edit21+","+edit22+","+(edit23/100));
                    designInfo.setServePrice(Float.valueOf(desingnPrice.getText().toString()));
                    designInfo.setTravelDesign(myDesingn);
                    designInfo.setOfferDetail(llDesingnPriceinfo.getText().toString());
                    designInfo.setGuideId(MySelfInfo.getInstance().getId());
                    mOrderDesingnPresenter.orderOfferDesign(designInfo);
                }
                break;
        }
    }

    public boolean checkName(){
        if(desingnTitle.getEditContent().equals("")){
            showShortToast("请输入标题");
            return false;
        }else if(desingnTime.getEditContent().equals("")){
            showShortToast("请选择行程时间");
            return false;
        }else if(!ServiceUtil.getEndPrice(desingnPrice.getText().toString(),context)){//最终报价
            return false;
        }else if(myDesingn.equals("")){
            showShortToast("请输入行程设计内容");
            return false;
        }else if(llDesingnPriceinfo.getText().toString().equals("")){
            showShortToast("请输入报价明细");
            return false;
        }else if(editText11.getText().toString().equals("")
                || editText12.getText().toString().equals("")
                || editText13.getText().toString().equals("")
                || editText21.getText().toString().equals("")
                || editText22.getText().toString().equals("")
                || editText23.getText().toString().equals("")){
            showShortToast("请输入违约金参数");
            return false;
        }else{
            return true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {  //行程设计
            case 103: //返回的请求码
                //操作
                myDesingn = data.getExtras().getString("myDesingn");
                if (!myDesingn.equals("")) {
                    llDesingnScheme.setEditContent("已编辑");
                }
                break;
        }
    }

    //------------获取方案信息start--------------
    @Override
    public void orderDesingnSuccess(List<OrderDesignInfo> datas) {
        if(datas==null)return;
        if(datas.size()>0){
            OrderDesignInfo infos=datas.get(0);
            Log.e("test",datas.toString());
            id=infos.getId();
            desingnTitle.setEditContent(infos.getTitle());

            String dates=infos.getTravelDateText();
            markDays=infos.getTravelDate();
            desingnTime.setEditContent(dates);
            switch (status){
                case 1://设计方案
                    myDesingn="";//行程设计
                    llDesingnPriceinfo.setText("");
                    break;
                case 2://修改方案
                    desingnPrice.setText(String.valueOf(infos.getServePrice()));
                    myDesingn=infos.getTravelDesign();//行程设计
                    if(myDesingn.equals("")||myDesingn.equals("<br>")){
                    }else{
                        llDesingnScheme.setEditContent("已编辑");
                    }
                    llDesingnPriceinfo.setText(infos.getOfferDetail());
                    //-----------违约金-----------
                    String three=infos.getRenegePriceThree();
                    if(!TextUtils.isEmpty(three)){
                        String[] threes=three.split(",");
                        if(threes.length>=2) {
                            editText11.setText(threes[0]);
                            editText12.setText(threes[1]);
                            if(threes.length == 3){
                                editText13.setText(getProportion(threes[2]));
                            }
                        }
                    }
                    String five=infos.getRenegePriceFive();
                    if(TextUtils.isEmpty(five)){
                        String[] fives=five.split(",");
                        if(fives.length>=2){
                            editText21.setText(fives[0]);
                            editText22.setText(fives[1]);
                            if(fives.length == 3){
                                editText23.setText(getProportion(fives[2]));
                            }
                        }
                    }
                    //-----------违约金end-----------
                    break;
            }
        }
    }

    public String getProportion(String str){
        int value = (int) (Float.valueOf(str)*100);
        return value+"";
    }

    @Override
    public void orderDesingnFailed(String msg) {
        showShortToast(msg);
    }
    //------------获取方案信息end--------------


    //------------修改方案新start--------------
    @Override
    public void orderOfferDesignSuccess(String datas) {
        showShortToast("提交成功");
        RxBus2.getInstance().post(new RefreshOrderListEvent());
        finish();
    }

    @Override
    public void orderOfferDesignFailed(String msg) {
        showShortToast(msg);
    }
    //------------修改方案新end--------------


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable !=null && !disposable.isDisposed()){
            disposable.dispose();}
    }
}
