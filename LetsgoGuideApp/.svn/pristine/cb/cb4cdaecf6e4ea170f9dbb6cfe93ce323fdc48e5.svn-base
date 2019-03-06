package com.njz.letsgoappguides.ui.activites.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.BaseFragmentAdapter;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.customview.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderListTabActivity extends BaseActivity {
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> mFragments;
    private String[] titles = {"未出行", "行程中", "已完成"};

    private OrderListTabFragment orderListTabFragment0;
    private OrderListTabFragment orderListTabFragment1;
    private OrderListTabFragment orderListTabFragment2;

    int payStatus;
    int orderStatus;

    @Override
    protected void initView() {
        payStatus = getIntent().getIntExtra("PAY_STATUS",0);
        orderStatus = getIntent().getIntExtra("ORDER_STATUS",0);

        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mFragments = new ArrayList<>();
        mFragments.add(orderListTabFragment0 = (OrderListTabFragment) OrderListTabFragment.newInstance(Constant.ORDER_PAY_ALREADY,Constant.ORDER_TRAVEL_NO_GO));
        mFragments.add(orderListTabFragment1 = (OrderListTabFragment) OrderListTabFragment.newInstance(Constant.ORDER_PAY_ALREADY,Constant.ORDER_TRAVEL_GOING));
        mFragments.add(orderListTabFragment2 = (OrderListTabFragment) OrderListTabFragment.newInstance(Constant.ORDER_PAY_FINISH,0));

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewpager);

        if(payStatus == Constant.ORDER_PAY_ALREADY){
            if(orderStatus == Constant.ORDER_TRAVEL_GOING)
                viewpager.setCurrentItem(1);
            else
                viewpager.setCurrentItem(0);
        }else if(payStatus == Constant.ORDER_PAY_FINISH){
            viewpager.setCurrentItem(2);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list_tab;
    }

}
