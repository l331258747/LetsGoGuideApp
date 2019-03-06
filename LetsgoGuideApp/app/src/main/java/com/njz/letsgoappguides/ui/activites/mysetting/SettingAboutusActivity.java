package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们
 */

public class SettingAboutusActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView titleTv;

    @Override
    protected void initView() {
        titleTv.setText("关于我们");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_aboutus;
    }

    @OnClick(R.id.left_iv)
    public void leftBack() {
        finish();
    }


    @OnClick(R.id.rl_id)
    public void onViewClicked() {
        Intent intent = new Intent(context, GuideContractActivity.class);
        intent.putExtra("CONTRACT_TYPE",1);
        startActivity(intent);
    }
}
