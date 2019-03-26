package com.njz.letsgoappguides.ui.activites.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.BaseFragmentAdapter;
import com.njz.letsgoappguides.model.home.OrderDesignInfo;
import com.njz.letsgoappguides.presenter.home.OrderDesingnContract;
import com.njz.letsgoappguides.presenter.home.OrderDesingnPresenter;
import com.njz.letsgoappguides.ui.fragments.home.CustomOffersFragment;
import com.njz.letsgoappguides.ui.fragments.home.CustomTripFragment;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.dialog.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/12/20
 * Function:
 */

public class CustomPlanActivity extends BaseActivity implements  View.OnClickListener,OrderDesingnContract.View {

    TextView tv_time_content,tv_num,tv_finish_price,tv_phone,title_tv,tv_title;
    ImageView left_iv;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    OrderDesingnPresenter planPresenter;

    public String[] titles = {"行程介绍", "报价明细"};
    public List<Fragment> mFragments;

    int orderId;
    String guidePhone;

    OrderDesignInfo customPlanModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_plan;
    }

    @Override
    public void initView() {

        orderId = intent.getIntExtra("ORDER_ID",0);
        guidePhone = intent.getStringExtra("GUIDE_PHONE");

        title_tv = $(R.id.title_tv);
        tv_title = $(R.id.tv_title);
        left_iv = $(R.id.left_iv);
        tv_time_content = $(R.id.tv_time_content);
        tv_num = $(R.id.tv_num);
        tv_finish_price = $(R.id.tv_finish_price);
        tv_phone = $(R.id.tv_phone);
        mTabLayout = $(R.id.tablayout);
        mViewPager = $(R.id.viewpager);
        title_tv.setText("查看方案");

        tv_phone.setOnClickListener(this);
        left_iv.setOnClickListener(this);

        initData();
    }

    public void initData() {
        planPresenter = new OrderDesingnPresenter(context,this);
        planPresenter.orderDesingn(orderId);

    }

    private void initInfo(OrderDesignInfo model) {
        tv_title.setText(model.getTitle());
        tv_time_content.setText(model.getTravelDateText());
        tv_num.setText(model.getPersonNum());
        tv_finish_price.setText("￥" + model.getServePrice());
    }

    public void initViewPage(OrderDesignInfo model) {
        mFragments = new ArrayList<>();
        mFragments.add(CustomTripFragment.newInstance(model.getTravelDesign(),model.getRenegePriceThree(),model.getRenegePriceFive(),model.getServeType()));
        mFragments.add(CustomOffersFragment.newInstance(model.getOfferDetail()));

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_phone:
                if(customPlanModel == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context,guidePhone,customPlanModel.getOrderId(),0,customPlanModel.getGuideId());
                break;
            case R.id.left_iv:
                finish();
                break;
        }
    }

    @Override
    public void orderOfferDesignSuccess(String datas) {

    }

    @Override
    public void orderOfferDesignFailed(String msg) {

    }

    @Override
    public void orderDesingnSuccess(List<OrderDesignInfo> model) {
        if(model != null && model.size() == 1){
            initInfo(model.get(0));
            initViewPage(model.get(0));
            customPlanModel = model.get(0);
        }
    }

    @Override
    public void orderDesingnFailed(String msg) {
        showShortToast(msg);
    }
}
