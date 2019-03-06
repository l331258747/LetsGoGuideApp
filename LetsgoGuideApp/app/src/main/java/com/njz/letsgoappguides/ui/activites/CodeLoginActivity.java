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

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.ActivityCollect;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.RegisterItemView;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.presenter.CodeLoginContract;
import com.njz.letsgoappguides.presenter.CodeLoginPresenter;
import com.njz.letsgoappguides.presenter.RegisterContract;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.im.cache.UserCacheManager;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.jpush.JpushAliasUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CodeLoginActivity extends BaseActivity implements CodeLoginContract.View{


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.codeLogin_input_phone)
    RegisterItemView codeLoginPhone;
    @BindView(R.id.codeLogin_input_card)
    RegisterItemView codeLoginCard;
    TextView tvVerify;
    CodeLoginPresenter mCodeLoginPresenter;
    Disposable disposable;

    @Override
    protected void initView() {
        titleTv.setText("动态码登录");

        String phone=getIntent().getStringExtra("PHONE");
        if(!TextUtils.isEmpty(phone)){
            codeLoginPhone.setEtContent(phone);
        }
        mCodeLoginPresenter=new CodeLoginPresenter(this,context);
        tvVerify = codeLoginCard.getRightText();
        codeLoginPhone.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        codeLoginCard.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        tvVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyPhone(codeLoginPhone.getEtContent()))
                    return;
                mCodeLoginPresenter.userSmsSend(codeLoginPhone.getEtContent(),"login");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @OnClick(R.id.codeLogin_in_button)
    public void codeLoginInbtn(){
        if (!LoginUtil.verifyPhone(codeLoginPhone.getEtContent()))
            return;
        if (!LoginUtil.verifyVerify(codeLoginCard.getEtContent()))
            return;
        mCodeLoginPresenter.msgCheckLogin(codeLoginPhone.getEtContent(),codeLoginCard.getEtContent());
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
    public void userSmsSendSuccess(String str) {
        verifyEvent();
    }

    @Override
    public void userSmsSendFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void msgCheckLoginSuccess(Datas datas) {
        LogUtil.e("....."+datas.getToken());
        MySelfInfo.getInstance().setData(datas);

        UserCacheManager.save(MySelfInfo.getInstance().getImId(),MySelfInfo.getInstance().getName(),MySelfInfo.getInstance().getUserImg());

        LogUtil.e("getRegistrationID:"+ JPushInterface.getRegistrationID(context));
        JpushAliasUtil.setTagAndAlias();

        if(AppUtils.getVersionCodeInt() % 100 != 0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(MySelfInfo.getInstance().getImId(), Constant.IM_PASSWORD);
                        LogUtil.e("im 注册成功");
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        int errorCode = e.getErrorCode();
                        String message = e.getMessage();
                        LogUtil.e("im 注册失败");
                        LogUtil.e("errorCode:" + errorCode);
                        LogUtil.e("message:" + message);
                    }
                }
            }).start();
        }

        EMClient.getInstance().login(MySelfInfo.getInstance().getImId(), Constant.IM_PASSWORD, new EMCallBack() {
            @Override
            public void onSuccess() {
                // 加载所有会话到内存
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtil.e("im 登录成功");

                MySelfInfo.getInstance().setImLogin(true);
            }

            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.e("im 登录失败 code: " + i + ",message: " + s);
                        LogUtil.e("code: " + i + ",message: " + s);
                        MySelfInfo.getInstance().setImLogin(false);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });


        Intent intent=new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollect.getAppCollect().finishActivity(LoginActivity.class);
    }

    @Override
    public void msgCheckLoginFailed(String msg) {
        showShortToast(msg);
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
