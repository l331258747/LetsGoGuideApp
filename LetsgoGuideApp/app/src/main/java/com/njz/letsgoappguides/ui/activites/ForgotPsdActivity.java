package com.njz.letsgoappguides.ui.activites;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.RegisterItemView;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.presenter.ForgotContract;
import com.njz.letsgoappguides.presenter.ForgotPresenter;
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

public class ForgotPsdActivity extends BaseActivity implements ForgotContract.View{

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.forgot_input_phone)
    RegisterItemView forgotPhone;
    @BindView(R.id.forgot_input_card)
    RegisterItemView forgotCard;
    @BindView(R.id.forgot_input_psd)
    RegisterItemView forgotPsd;
    @BindView(R.id.forgot_input_psd_ag)
    RegisterItemView forgotPsdag;
    TextView tvVerify;
    ForgotPresenter mForgotPresenter;
    Disposable disposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_psd;
    }

    @Override
    protected void initView() {
        titleTv.setText("找回密码");

        String phone=getIntent().getStringExtra("PHONE");
        if(!TextUtils.isEmpty(phone)){
            forgotPhone.setEtContent(phone);
        }

        mForgotPresenter=new ForgotPresenter(this,context);
        tvVerify = forgotCard.getRightText();
        forgotPhone.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        forgotCard.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        forgotPsd.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        forgotPsdag.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyPhone(forgotPhone.getEtContent()))
                    return;
                mForgotPresenter.userSmsSend(forgotPhone.getEtContent(),"findBy");
            }
        });
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
    public void forgotPsdFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void forgotPsdSuccess(String msg) {
        showShortToast("密码修改成功");
        finish();
    }

    @OnClick(R.id.forgotPsd_in_button)
    public void toForgotPsdNextBtn(){
        if (!LoginUtil.verifyPhone(forgotPhone.getEtContent()))
            return;
        if (!LoginUtil.verifyVerify(forgotCard.getEtContent()))
            return;
        if (!LoginUtil.verifyPassword(forgotPsd.getEtContent(),"请输入密码","请输入不小于六位的密码"))
            return;
        if (!LoginUtil.verifyPassword(forgotPsdag.getEtContent(),"请输入新密码","请输入不小于六位的新密码"))
            return;
        if (!LoginUtil.verifyPasswordDouble(forgotPsd.getEtContent(),forgotPsdag.getEtContent()))
            return;
        mForgotPresenter.forgotPsd(forgotPhone.getEtContent(),forgotCard.getEtContent(),forgotPsd.getEtContent());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
