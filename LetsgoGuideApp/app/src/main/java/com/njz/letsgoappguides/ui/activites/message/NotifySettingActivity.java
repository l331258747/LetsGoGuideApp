package com.njz.letsgoappguides.ui.activites.message;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.MsgSettingView;
import com.njz.letsgoappguides.customview.widget.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LGQ
 * Time: 2018/11/22
 * Function:
 */

public class NotifySettingActivity extends BaseActivity {


    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.msgSettingView_ptqxwyj)
    MsgSettingView msgSettingViewPtqxwyj;
    @BindView(R.id.msgSettingView_dqddd)
    MsgSettingView msgSettingViewDqddd;
    @BindView(R.id.msgSettingView_pj)
    MsgSettingView msgSettingViewPj;
    @BindView(R.id.msgSettingView_tk)
    MsgSettingView msgSettingViewTk;
    @BindView(R.id.msgSettingView_xc)
    MsgSettingView msgSettingViewXc;
    @BindView(R.id.msgSettingView_ddzdqx)
    MsgSettingView msgSettingViewDdzdqx;
    @BindView(R.id.msgSettingView_tkzdqr)
    MsgSettingView msgSettingViewTkzdqr;

    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;


    boolean Ptqxwyj_app = false;
    boolean Ptqxwyj_phone = true;
    boolean Dqddd_app = false;
    boolean Dqddd_phone = true;
    boolean Pj_app = false;
    boolean Pj_phone = true;
    boolean Tk_app = false;
    boolean Tk_phone = false;
    boolean Xc_app = true;
    boolean Xc_phone = true;
    boolean Ddzdqx_app = true;
    boolean Ddzdqx_phone = false;
    boolean Tkzdqr_app = true;
    boolean Tkzdqr_phone = false;

    @Override
    protected void initView() {
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        msgSettingViewPtqxwyj.init(Ptqxwyj_app,Ptqxwyj_phone);
        msgSettingViewDqddd.init(Dqddd_app,Dqddd_phone);
        msgSettingViewPj.init(Pj_app,Pj_phone);
        msgSettingViewTk.init(Tk_app,Tk_phone);
        msgSettingViewXc.init(Xc_app,Xc_phone);
        msgSettingViewDdzdqx.init(Ddzdqx_app,Ddzdqx_phone);
        msgSettingViewTkzdqr.init(Tkzdqr_app,Tkzdqr_phone);

        setClick(msgSettingViewPtqxwyj);
        setClick(msgSettingViewDqddd);
        setClick(msgSettingViewPj);
        setClick(msgSettingViewTk);
        setClick(msgSettingViewXc);
        setClick(msgSettingViewDdzdqx);
        setClick(msgSettingViewTkzdqr);
    }


    public void setClick(final MsgSettingView view) {
        view.setBtnClickLisener(new MsgSettingView.RadioClickLisener() {
            @Override
            public void onApplicationClick() {
                view.setApplicationRemind();
            }

            @Override
            public void onPhoneClick() {
                view.setPhoneRemind();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify_setting;
    }

    @OnClick({R.id.tv_reset, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset:

                break;
            case R.id.tv_submit:

                break;
        }
    }
}
