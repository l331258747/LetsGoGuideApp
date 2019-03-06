package com.njz.letsgoappguides.ui.activites.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.BaseFragmentAdapter;
import com.njz.letsgoappguides.customview.widget.MyRatingBar;
import com.njz.letsgoappguides.model.evaluation.Datainfo;
import com.njz.letsgoappguides.presenter.mine.MyEvaluationContract;
import com.njz.letsgoappguides.presenter.mine.MyEvaluationPresenter;
import com.njz.letsgoappguides.ui.fragments.mine.EvaluationFragment;
import com.njz.letsgoappguides.util.reflex.ReflexUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的评价
 */
public class MyEvaluationActivity extends BaseActivity implements MyEvaluationContract.View{


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.myRatingBar)
    MyRatingBar myRatingBar;
    @BindView(R.id.tv_evaluate_count)
    TextView tvEvaluateCount;
    private String[] titles = {"全部", "好评", "中评", "差评"};
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    List<Fragment> fragments = new ArrayList<>();
    BaseFragmentAdapter adapter;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    EvaluationFragment mEvaluationFragment;
    EvaluationFragment mEvaluationFragment1;
    EvaluationFragment mEvaluationFragment2;
    EvaluationFragment mEvaluationFragment3;
    MyEvaluationPresenter mMyEvaluationPresenter;

    @Override
    protected void initView() {
        titleTv.setText("我的评价");
        mMyEvaluationPresenter=new MyEvaluationPresenter(this,context);
        mMyEvaluationPresenter.getEvaluationInfo(1,1,0);
        mEvaluationFragment =  EvaluationFragment.newInstance(EvaluationFragment.DYNAMIC_TYPE);
        mEvaluationFragment1 =  EvaluationFragment.newInstance(EvaluationFragment.DYNAMIC_TYPE1);
        mEvaluationFragment2 =  EvaluationFragment.newInstance(EvaluationFragment.DYNAMIC_TYPE2);
        mEvaluationFragment3 =  EvaluationFragment.newInstance(EvaluationFragment.DYNAMIC_TYPE3);
        fragments.add(mEvaluationFragment);
        fragments.add(mEvaluationFragment1);
        fragments.add(mEvaluationFragment2);
        fragments.add(mEvaluationFragment3);
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
//        viewpager.setCurrentItem(0);
        //设置可以滑动
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(viewpager);
        ReflexUtil.reflex(mTabLayout);
    }



    @Override
    public void getEvaluationSuccess(Datainfo datas) {
        Log.e("test",datas.toString());
        myRatingBar.setRating((int) datas.getGuideScore());
        tvEvaluateCount.setText(datas.getReviewCount()+"条点评");
    }

    @Override
    public void getEvaluationFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_evaluation;
    }

    @OnClick(R.id.left_iv)
    public void leftBack() {
        onBackPressed();
    }



}
