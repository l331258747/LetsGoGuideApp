package com.njz.letsgoappguides.ui.activites.mysetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.presenter.setting.UpdataPsdContract;
import com.njz.letsgoappguides.presenter.setting.UpdataPsdPresenter;
import com.njz.letsgoappguides.util.LoginUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdataPsdActivity extends BaseActivity implements UpdataPsdContract.View{

    @BindView(R.id.title_tv)
    TextView titleTv;
    UpdataPsdPresenter mUpdataPsdPresenter;
    @BindView(R.id.updata_et_psd)
    EditText updataEtPsd;
    @BindView(R.id.updata_et_newpsd)
    EditText updataEtNewpsd;
    @BindView(R.id.updata_et_newpsds)
    EditText updataEtNewpsds;


    @Override
    protected void initView() {
        titleTv.setText("修改密码");
        updataEtPsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        updataEtNewpsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        updataEtNewpsds.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mUpdataPsdPresenter=new UpdataPsdPresenter(this,context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_updata_psd;
    }

    @OnClick(R.id.updata_in_button)
    public void updataInButton(){
        String psd=updataEtPsd.getText().toString().trim();
        String newpsd=updataEtNewpsd.getText().toString().trim();
        String newpsds=updataEtNewpsds.getText().toString().trim();
        if (!LoginUtil.verifyPassword(psd,"请输入密码","请输入不小于六位的密码")
                ||!LoginUtil.verifyPassword(newpsd,"请输入新密码","请输入不小于六位的新密码")
                ||!LoginUtil.verifyPassword(newpsds,"请输入确认密码","请输入不小于六位的确认密码"))
            return;
        if (!LoginUtil.verifyPasswordDouble(newpsd,newpsds))
            return;
        mUpdataPsdPresenter.updataPsd(psd,newpsd);
    }

    @Override
    public void updataPsdSuccess(String str) {
        showShortToast("修改成功");
        finish();
    }

    @Override
    public void updataPsdFailed(String msg) {
        showShortToast(msg);
    }

    @OnClick(R.id.left_iv)
    public void leftBack(){
        onBackPressed();
    }
}
