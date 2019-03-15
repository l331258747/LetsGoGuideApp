package com.njz.letsgoappguides.ui.activites.home;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.model.home.CancelReasonListInfo;
import com.njz.letsgoappguides.model.home.PersonRefuseInfo;
import com.njz.letsgoappguides.presenter.home.OrderConfirmContract;
import com.njz.letsgoappguides.presenter.home.OrderConfirmPresenter;
import com.njz.letsgoappguides.presenter.home.OrderRefuseContract;
import com.njz.letsgoappguides.presenter.home.OrderRefusePresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshOrderListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderRefuseActivity extends BaseActivity implements OrderRefuseContract.View,OrderConfirmContract.View{
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.tv_refuse_reason)
    TextView tvRefuseReason;
    @BindView(R.id.et_refuse_explain)
    EditText etRefuseExplain;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    List<String> reasons;
    int orderId;
    boolean isCustom;

    OrderRefusePresenter mPresenter;
    OrderConfirmPresenter mOrderConfirmPresenter;

    @Override
    protected void initView() {
        orderId = getIntent().getIntExtra("ORDER_ID",0);
        isCustom = getIntent().getBooleanExtra("IS_CUSTOM",false);


        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reasons = new ArrayList<>();

        mPresenter = new OrderRefusePresenter(context,this);
        mOrderConfirmPresenter = new OrderConfirmPresenter(context,this);
        mPresenter.queryCancelReasonList();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_refuse;
    }


    @OnClick({R.id.tv_refuse_reason, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refuse_reason:
                AppUtils.HideKeyboard(tvRefuseReason);
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(context,
                        new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                if(reasons.size()>0){
                                    tvRefuseReason.setText(reasons.get(options1));
                                }
                            }
                        })
                        .build();
                pvOptions.setPicker(reasons, null, null);
                pvOptions.show();
                break;
            case R.id.tv_submit:
                if(TextUtils.isEmpty(tvRefuseReason.getText().toString())){
                    showShortToast("请选择取消原因");
                    return;
                }
                if(isCustom){
                    mOrderConfirmPresenter.guideSurePersonalServe(orderId,4,tvRefuseReason.getText().toString(),etRefuseExplain.getText().toString());
                }else{
                    mPresenter.orderRefuseOrder(orderId,tvRefuseReason.getText().toString(),etRefuseExplain.getText().toString());
                }
                break;
        }
    }

    @Override
    public void orderRefuseOrderSuccess(String datas) {
        showShortToast("拒绝成功");
        RxBus2.getInstance().post(new RefreshOrderListEvent());
        finish();
    }

    @Override
    public void orderRefuseOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void queryCancelReasonListSuccess(List<CancelReasonListInfo> datas) {
        if(datas==null)return;
        if(datas.size()>0){
            for (int i=0;i<datas.size();i++){
                String name=datas.get(i).getName();
                reasons.add(name);
            }
        }
    }

    @Override
    public void queryCancelReasonListFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void orderSureOrderSuccess(String datas) {

    }

    @Override
    public void orderSureOrderFailed(String msg) {

    }

    @Override
    public void guideSurePersonalServeSuccess(String datas) {
        showShortToast("拒绝成功");
        RxBus2.getInstance().post(new RefreshOrderListEvent());
        finish();
    }

    @Override
    public void guideSurePersonalServeFailed(String msg) {
        showShortToast(msg);
    }
}
