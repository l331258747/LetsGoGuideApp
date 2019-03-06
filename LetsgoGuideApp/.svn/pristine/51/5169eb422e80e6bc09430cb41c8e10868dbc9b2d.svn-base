package com.njz.letsgoappguides.ui.activites.mysetting;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.RegisterItemView;
import com.njz.letsgoappguides.presenter.setting.SetPhoneContract;
import com.njz.letsgoappguides.presenter.setting.SetPhonePresenter;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SetPhoneActivity extends BaseActivity implements SetPhoneContract.View{


    @BindView(R.id.title_tv)
    TextView titleTv;
    SetPhonePresenter mSetPhonePresenter;
    @BindView(R.id.setphone_input_psd)
    RegisterItemView psd;
    @BindView(R.id.setphone_input_phone)
    RegisterItemView phone;
    @BindView(R.id.setphone_input_card)
    RegisterItemView card;
    TextView tvVerify;
    Disposable disposable;


    @Override
    protected void initView() {
        titleTv.setText("修改绑定手机号");
        mSetPhonePresenter=new SetPhonePresenter(this,context);
        tvVerify = card.getRightText();
        phone.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        phone.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        psd.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyPhone(phone.getEtContent()))
                    return;
                mSetPhonePresenter.userSmsSend(phone.getEtContent(),"update");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_phone;
    }


    @Override
    public void SetPhoneSuccess(String msg) {
        ToastUtil.showShortToast(context,"号码更换成功");
        MySelfInfo.getInstance().setMobile(phone.getEtContent());
        finish();
    }

    @Override
    public void SetPhoneFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }

    @Override
    public void userSmsSendSuccess(String msg) {
        verifyEvent();

    }

    @Override
    public void userSmsSendFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }

    @OnClick(R.id.setphone_in_button)
    public void setphoneInButton(){
        if (!LoginUtil.verifyPhone(phone.getEtContent()))
            return;
        if (!LoginUtil.verifyVerify(card.getEtContent()))
            return;
        if (!LoginUtil.verifyPassword(psd.getEtContent(),"请输入密码","请输入不小于六位的密码"))
            return;
        mSetPhonePresenter.SetPhone(phone.getEtContent(),card.getEtContent(),psd.getEtContent());
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @OnClick(R.id.left_iv)
    public void leftBack(){
        onBackPressed();
    }
}
