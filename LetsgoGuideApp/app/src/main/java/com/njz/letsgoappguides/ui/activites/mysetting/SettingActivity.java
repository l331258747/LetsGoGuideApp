package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.ActivityCollect;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.MineTextView;
import com.njz.letsgoappguides.presenter.ExitLoginContract;
import com.njz.letsgoappguides.presenter.ExitLoginPresenter;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.activites.LoginActivity;
import com.njz.letsgoappguides.ui.activites.other.WebViewActivity;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.CacheUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.CleanCacheEvent;
import com.tencent.bugly.beta.Beta;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingActivity extends BaseActivity implements ExitLoginContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.setting_mtv_phone)
    MineTextView settingPhone;
    @BindView(R.id.setting_mtv_clear)
    MineTextView settingClear;
    @BindView(R.id.setting_mtv_our)
    MineTextView settingOur;
    @BindView(R.id.system_setting_upload)
    MineTextView settingUpload;
    Disposable disCleanCache;
    LoadingDialog loadingDialog;
    ExitLoginPresenter mExitLoginPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        titleTv.setText("系统设置");
        mExitLoginPresenter=new ExitLoginPresenter(this,context);
        initdata();
        try {
            String cacheSize = CacheUtil.getTotalCacheSize(AppUtils.getContext());
            settingClear.setContent(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        settingOur.setContent("当前版本 " + AppUtils.getVersionName());

        settingUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.checkUpgrade();
            }
        });
    }

    public void initdata(){
        if(MySelfInfo.getInstance().isLogin()){
            settingPhone.setContent(StringUtils.hidePhone(MySelfInfo.getInstance().getMobile()));
        }else{
            startActivity(new Intent(context, LoginActivity.class));
        }
        loadingDialog = new LoadingDialog(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
    }

    @OnClick(R.id.system_setting_privacy)
    public void setSystemSettingPrivacy(){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.EXTRA_URL,"http://www.njzou.com/yszcdy");
        intent.putExtra(Constant.IS_USER_WIDE_VIEW_PORT,true);
        intent.putExtra(Constant.EXTRA_TITLE,"隐私政策");
        startActivity(intent);
    }

    @OnClick(R.id.left_iv)
    public void leftBack(){
        onBackPressed();
    }

    @OnClick(R.id.setting_mtv_phone)
    public void settingMtvPhone(){
        startActivity(new Intent(context,SetPhoneActivity.class));
    }
    @OnClick(R.id.setting_mtv_psd)
    public void settingMtvPsd(){
        startActivity(new Intent(context,UpdataPsdActivity.class));
    }
    @OnClick(R.id.setting_mtv_clear)
    public void settingMtvClear(){
        if(settingClear.getContent().equals("0k"))return;
        loadingDialog.showDialog("清理中...");
        disCleanCache = RxBus2.getInstance().toObservable(CleanCacheEvent.class, new Consumer<CleanCacheEvent>() {
            @Override
            public void accept(CleanCacheEvent s) throws Exception {
                loadingDialog.dismiss();
                settingClear.setContent("0k");
                disCleanCache.dispose();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheUtil.clearAllCache(AppUtils.getContext());
                RxBus2.getInstance().post(new CleanCacheEvent());
            }
        }).start();
    }
    @OnClick(R.id.setting_mtv_sult)
    public void settingMtvSult(){
        startActivity(new Intent(context,FeedbackActivity.class));
    }
    @OnClick(R.id.setting_mtv_our)
    public void settingMtvOur(){
        startActivity(new Intent(context,SettingAboutusActivity.class));
    }
    @OnClick(R.id.set_in_button)
    public void exitLogin(){
        showShortToast("退出成功");
        MySelfInfo.getInstance().loginOff();
        ActivityCollect.getAppCollect().finishAllActivity();
        startActivity(new Intent(context,LoginActivity.class));
//        mExitLoginPresenter.exitlogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void exitloginSuccess(String str) {
    }

    @Override
    public void exitloginFailed(String msg) {
    }
}
