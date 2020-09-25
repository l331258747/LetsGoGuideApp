package com.njz.letsgoappguides.ui.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.LoginItemView;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.http.RetrofitUtil;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.presenter.LoginContract;
import com.njz.letsgoappguides.presenter.LoginPresenter;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.GuideContractActivity;
import com.njz.letsgoappguides.ui.activites.other.WebViewActivity;
import com.njz.letsgoappguides.ui.im.cache.UserCacheManager;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.jpush.JpushAliasUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{


    @BindView(R.id.login_view_phone)
    LoginItemView phone;
    @BindView(R.id.login_view_password)
    LoginItemView password;
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_user_agreement)
    TextView tv_user_agreement;
    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;
    LoginPresenter mPresenter;

    String loginPhone;

    @Override
    public void getIntentData() {
        super.getIntentData();
        loginPhone = intent.getStringExtra("LOGIN_PHONE");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        activity=this;
        mPresenter = new LoginPresenter(this,context);
        if (!TextUtils.isEmpty(loginPhone))
            phone.getEtView().setText(loginPhone);
        password.setEtInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        StringUtils.setHtml(tv_user_agreement, getResources().getString(R.string.login_user_agreement));
        StringUtils.setHtml(tv_privacy_policy, getResources().getString(R.string.login_privacy_policy));

        changeUrl();
    }

    int changeInt = 0;
    private void changeUrl() {
        if(AppUtils.getVersionCodeInt() % 100 == 0) return;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInt = changeInt + 1;
                if(changeInt == 5){
                    changeInt = 0;
                    DialogUtil.getInstance().getEditDialog(context, new DialogUtil.DialogEditCallBack() {
                        @Override
                        public void exectEvent(DialogInterface alterDialog, String str) {
                            RetrofitUtil.getInstance().changeBaseUrl(str+"/");
                        }
                    },30,"http://").show();
                }
            }
        });
    }

    @OnClick(R.id.tv_user_agreement)
    public void userAgreement(){
        Intent intent = new Intent(context, GuideContractActivity.class);
        intent.putExtra("CONTRACT_TYPE",1);
        startActivity(intent);
    }

    @OnClick(R.id.tv_privacy_policy)
    public void privacyPolicy(){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.EXTRA_URL,"http://www.njzou.com/yszcdy/");
        intent.putExtra(Constant.IS_USER_WIDE_VIEW_PORT,true);
        intent.putExtra(Constant.EXTRA_TITLE,"隐私政策");
        startActivity(intent);
    }

    /**
     * 点击登录
     */
    @OnClick(R.id.login_in_button)
    public void login(){
        String emails=phone.getEtContent();
        String passwords=password.getEtContent();
        if (!LoginUtil.verifyPhoneGuides(phone.getEtContent()))
            return;
        if (!LoginUtil.verifyPassword(password.getEtContent(),"请输入密码","请输入不小于六位的密码"))
            return;
        mPresenter.login(emails,passwords);
    }

    @OnClick(R.id.register_in_button)
    public void toRegisterBtn(){
        Intent intent=new Intent(context,RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_tv_code)
    public void toCodeLoginBtn(){
        Intent intent=new Intent(context,CodeLoginActivity.class);
        String phones=phone.getEtContent();
        if(!TextUtils.isEmpty(phones)){
            intent.putExtra("PHONE",phones);
        }
        startActivity(intent);
    }

    @OnClick(R.id.login_tv_pass)
    public void toForgotPsdBtn(){
        Intent intent=new Intent(context,ForgotPsdActivity.class);
        String phones=phone.getEtContent();
        if(!TextUtils.isEmpty(phones)){
            intent.putExtra("PHONE",phones);
        }
        startActivity(intent);
    }


    @Override
    public void loginSuccess(Datas datas) {
        LogUtil.e("....."+datas.getUserVo().toString());
        Log.e("test","loginSuccess");
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
    }


    @Override
    public void loginFailed(String msg) {
        Log.e("test","loginFailed");
        showShortToast(msg);
    }

    private long mExitTime;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            showLongToast("再按一次退出那就走旅行");
            mExitTime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
}