package com.njz.letsgoappguides.ui.activites;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.RegisterItemView;
import com.njz.letsgoappguides.presenter.RegisterContract;
import com.njz.letsgoappguides.presenter.RegisterPresenter;
import com.njz.letsgoappguides.ui.activites.mysetting.GuideContractActivity;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterActivity extends BaseActivity implements RegisterContract.View{


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.reg_input_phone)
    RegisterItemView regInPhone;
    @BindView(R.id.reg_input_card)
    RegisterItemView regInCard;
    @BindView(R.id.reg_input_pwd)
    RegisterItemView regInPwd;
    @BindView(R.id.reg_input_pwd2)
    RegisterItemView regInPwd2;
    @BindView(R.id.iv_isagreement)
    ImageView ivIsagreement;
    TextView tvVerify;
    RegisterPresenter mRegisterPresenter;
    Disposable disposable;
    boolean isSelect=true;

    @Override
    protected void initView() {
        titleTv.setText("注册-验证手机");
        mRegisterPresenter=new RegisterPresenter(this,context);
        tvVerify = regInCard.getRightText();
        regInPhone.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        regInCard.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        regInPwd.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        regInPwd2.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyPhone(regInPhone.getEtContent()))
                    return;
                mRegisterPresenter.userSmsSend(regInPhone.getEtContent(),"register");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    public void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvVerify.setEnabled(false);//在发送数据的时候设置为不能点击
                        tvVerify.setTextColor(ContextCompat.getColor(context,R.color.color_68));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tvVerify,String.format(getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tvVerify.setEnabled(true);
                        tvVerify.setText("重新发送");
                        tvVerify.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                    }
                });
    }

    @OnClick(R.id.left_iv)
    public void leftBack(){
        onBackPressed();
    }

    @OnClick(R.id.register_in_button)
    public void registerInBtn(){
        if (!LoginUtil.verifyPhone(regInPhone.getEtContent()))
            return;
        if (!LoginUtil.verifyVerify(regInCard.getEtContent()))
            return;
        if (!LoginUtil.verifyPassword(regInPwd.getEtContent(),"请输入密码","请输入不小于六位的密码"))
            return;
        if (!LoginUtil.verifyPasswordDouble(regInPwd.getEtContent(), regInPwd2.getEtContent()))
            return;
        if(!isSelect){
            showShortToast("请同意《那就走用户服务协议》");
            return;
        }
        mRegisterPresenter.msgCheckRegister(regInPhone.getEtContent(),regInPwd.getEtContent(),regInPwd2.getEtContent(),regInCard.getEtContent());
    }

    @OnClick(R.id.tv_agreement)
    public void agreement(){
        Intent intent = new Intent(context, GuideContractActivity.class);
                intent.putExtra("CONTRACT_TYPE",1);
        startActivity(intent);
    }
    @OnClick(R.id.iv_isagreement)
    public void isagreement(){
        if (isSelect) {
            ivIsagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.no_check));
            isSelect=false;
        }else{
            ivIsagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.reg_check));
            isSelect=true;
        }
    }

    @Override
    public void msgCheckRegisterFailed(String msg) {
        showLongToast(msg);
    }

    @Override
    public void msgCheckRegisterSuccess(String str) {
        showShortToast("注册成功");
        finish();
    }

    @Override
    public void userSmsSendFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void userSmsSendSuccess(String str) {
        verifyEvent();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

}
