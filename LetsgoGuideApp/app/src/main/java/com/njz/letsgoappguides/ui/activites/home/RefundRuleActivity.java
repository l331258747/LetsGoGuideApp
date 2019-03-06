package com.njz.letsgoappguides.ui.activites.home;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.model.home.ServiceRefundRuleModel;
import com.njz.letsgoappguides.presenter.home.OrderRefundRuleContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundRulePresenter;

/**
 * Created by LGQ
 * Time: 2018/10/19
 * Function:
 */

public class RefundRuleActivity extends BaseActivity implements OrderRefundRuleContract.View{

    TextView tv_refund_rule_50,tv_refund_rule_30;

    OrderRefundRulePresenter mPresenter;

    TitleView titleView;

    int serviceId;

    @Override
    public void getIntentData() {
        super.getIntentData();
        serviceId = intent.getIntExtra("serviceId",0);
        Log.e("test",serviceId+"__________");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund_rule;
    }

    @Override
    public void initView() {
        titleView = $(R.id.titleView);
        tv_refund_rule_30 = $(R.id.tv_refund_rule_30);
        tv_refund_rule_50 = $(R.id.tv_refund_rule_50);

        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initData();
    }

    public void initData() {
        mPresenter = new OrderRefundRulePresenter(context,this);
        mPresenter.orderRefundRule(serviceId);
    }


    @Override
    public void orderRefundRuleSuccess(ServiceRefundRuleModel datas) {
        if(TextUtils.isEmpty(datas.getRenegePriceThree()) || TextUtils.isEmpty(datas.getRenegePriceFive()))
            return;
        tv_refund_rule_30.setText(String.format(getResources().getString(R.string.refund_rule_30),datas.getRenegePriceThree().replace(",","-")));
        tv_refund_rule_50.setText(String.format(getResources().getString(R.string.refund_rule_50),datas.getRenegePriceFive().replace(",","-")));
    }

    @Override
    public void orderRefundRuleFailed(String msg) {
        showShortToast(msg);
    }
}
