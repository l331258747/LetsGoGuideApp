package com.njz.letsgoappguides.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.MineItemView;
import com.njz.letsgoappguides.customview.widget.MineItemView2;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.presenter.mine.GetLUserinfoPresenter;
import com.njz.letsgoappguides.presenter.mine.GetUserinfoContract;
import com.njz.letsgoappguides.ui.activites.mine.MyEvaluationActivity;
import com.njz.letsgoappguides.ui.activites.mine.MyIncomeActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.AuthenticationActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.CarAuthenticationActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.GuideAuthenticationActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.OperationGuideActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.PersonalActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.SettingActivity;
import com.njz.letsgoappguides.ui.activites.other.WebViewActivity;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MysettingFragment extends BaseFragment implements View.OnClickListener,GetUserinfoContract.View  {

    @BindView(R.id.iv_head)
    ImageView ivhead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.setting_tv_person)
    TextView tvPerson;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.mysetting_evaluatuin)
    MineItemView2 evaluatuin;
    @BindView(R.id.miv_mysetting_per)
    MineItemView mivMysettingPer;
    @BindView(R.id.miv_mysetting_perdr)
    MineItemView mivMysettingPerdr;
    @BindView(R.id.miv_mysetting_percar)
    MineItemView mivMysettingPercar;
    @BindView(R.id.mysetting_income)
    MineItemView2 mysettingIncome;
    @BindView(R.id.mine_phone)
    MineItemView2 mine_phone;
    @BindView(R.id.mine_customer)
    MineItemView2 mine_customer;
    int[] leftdraw = {R.mipmap.my_uncertified,
            R.mipmap.my_uncertified,
            R.mipmap.my_audit,
            R.mipmap.my_authen,
    };
    String[] str = {"未认证","认证失败","认证中","认证成功"};//{"未认证","认证失败",,"认证中","认证成功"};

    SwipeRefreshLayout swipeRefreshLayout;
    boolean isLoad = false;
    GetLUserinfoPresenter mGetLUserinfoPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mysetting;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout=$(R.id.swipe_refresh_layout);

        mGetLUserinfoPresenter=new GetLUserinfoPresenter(context,this);
        initSwipeLayout();
        initData();
        ivhead.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvPerson.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        evaluatuin.setOnClickListener(this);
        mivMysettingPer.setOnClickListener(this);
        mivMysettingPerdr.setOnClickListener(this);
        mivMysettingPercar.setOnClickListener(this);
        mysettingIncome.setOnClickListener(this);
        mine_phone.setOnClickListener(this);
        mine_customer.setOnClickListener(this);
    }

    void initData() {
        if (MySelfInfo.getInstance().isLogin()) {//登录状态
            tvName.setText(MySelfInfo.getInstance().getName());
            GlideUtil.LoadCircleImage(context, MySelfInfo.getInstance().getUserImg(), ivhead);
            if ("" != MySelfInfo.getInstance().getCardViable() && "" != MySelfInfo.getInstance().getGuideViable() && "" != MySelfInfo.getInstance().getDriveViable()) {
                int card = Integer.parseInt(MySelfInfo.getInstance().getCardViable());
                int guide = Integer.parseInt(MySelfInfo.getInstance().getGuideViable());
                int drive = Integer.parseInt(MySelfInfo.getInstance().getDriveViable());
                initlay(card, mivMysettingPer);
                initlay(guide, mivMysettingPerdr);
                initlay(drive, mivMysettingPercar);
            }
        }
    }

    public void initlay(int viable, MineItemView mit) {
        switch (viable) {
            case -1:
                mit.setContent(str[0]);
                mit.setleftDrawable(context, leftdraw[0]);
                break;
            case 0:
                mit.setContent(str[1]);
                mit.setleftDrawable(context, leftdraw[1]);
                break;
            case 1:
                mit.setContent(str[2]);
                mit.setleftDrawable(context, leftdraw[2]);
                break;
            case 2:
                mit.setContent(str[3]);
                mit.setleftDrawable(context, leftdraw[3]);
                break;
        }
    }


    //初始化SwipeLayout
    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_theme));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoad) return;
                isLoad = true;
                getData();
            }
        });
    }

    public void getData() {
        swipeRefreshLayout.setRefreshing(true);
        mGetLUserinfoPresenter.getUserinfo(false);
    }


    @Override
    public void loadData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //可见的并且是初始化之后才加载
        if (isPrepared && isVisibleToUser) {
            getData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_tv_person:
            case R.id.iv_head:
            case R.id.tv_name:
                startActivity(new Intent(context, PersonalActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.mysetting_evaluatuin:
                startActivity(new Intent(context, MyEvaluationActivity.class));
                break;
            case R.id.miv_mysetting_per:
                startActivity(new Intent(context, AuthenticationActivity.class));
                break;
            case R.id.miv_mysetting_perdr:
                startActivity(new Intent(context, GuideAuthenticationActivity.class));
                break;
            case R.id.miv_mysetting_percar:
                startActivity(new Intent(context, CarAuthenticationActivity.class));
                break;
            case R.id.mysetting_income:
                startActivity(new Intent(context, MyIncomeActivity.class));
                break;
            case R.id.mine_phone:
                DialogUtil.getInstance().showCustomerMobileDialog(context);
                break;
            case R.id.mine_customer:
//                startActivity(new Intent(context, OperationGuideActivity.class));
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Constant.EXTRA_URL,"https://mp.weixin.qq.com/mp/homepage?__biz=MzU5MzY2OTI5MQ==&hid=1&sn=c9ee18cd9ae252e08acf0951a7c4d077");
                intent.putExtra(Constant.EXTRA_TITLE,"那就走");
                startActivity(intent);
                break;
        }
    }


    @Override
    public void getUserinfoSuccess(UserVo str) {
        if(str==null)return;
        String cardViable=str.getCardViable();
        String guideViable=str.getGuideViable();
        String driveViable=str.getDriveViable();
        MySelfInfo.getInstance().setCardViable(cardViable);
        MySelfInfo.getInstance().setGuideViable(guideViable);
        MySelfInfo.getInstance().setDriveViable(driveViable);
        MySelfInfo.getInstance().setCardViableNoPassCause(str.getCardViableNoPassCause());
        MySelfInfo.getInstance().setGuideViableNoPassCause(str.getGuideViableNoPassCause());
        MySelfInfo.getInstance().setDriveViableNoPassCause(str.getDriveViableNoPassCause());
        initData();
        swipeRefreshLayout.setRefreshing(false);
        isLoad = false;
    }

    @Override
    public void getUserinfoFailed(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        isLoad = false;
    }
}