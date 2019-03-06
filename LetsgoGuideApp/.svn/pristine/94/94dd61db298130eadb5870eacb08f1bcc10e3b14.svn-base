package com.njz.letsgoappguides.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Myapp;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder mUnbinder;
    public Context context;
    public Activity activity;
    public Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_base);
        // 强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //强制隐藏软键盘  覆盖布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        setContentView(getLayoutId());
        initStatusLayout();

        //ButterKnife 的绑定
        mUnbinder = ButterKnife.bind(this);
//        mApplication = (Myapp) getApplication();
        context = this;
        activity=this;

        ActivityCollect.getAppCollect().addActivity(this);

        getIntentData();
        initView();
    }

    /**
     * 子类如果需要多状态布局，需要重写此方法来配置多状态布局
     * 子类需要执行两部操作
     * Step1 初始化StatusLayout 配置多状态布局
     * Step2 添加到 ContentView的相应位置中
     */
    public void initStatusLayout() {

    }

    /**
     * 获取传递的参数
     **/
    public void getIntentData() {
        intent = getIntent();
    }

    /**
     * 初始化操作
     */
    protected abstract void initView();

    /**
     * 子类需要提供布局ID
     *
     * @return activity对应的  布局id
     */
    protected abstract int getLayoutId();


    public void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mUnbinder != null) {
//            mUnbinder.unbind();
//            mUnbinder = null;
//        }
    }


    public void showShortToast(String msg) {
        ToastUtil.showShortToast(context, msg);
    }

    public void showLongToast(String msg) {
        ToastUtil.showLongToast(this, msg);
    }

    private long mExitTime;
    @Override
    public void onBackPressed() {
        if(activity instanceof MainActivity){
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showLongToast("再按一次退出那就走旅行");
                mExitTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return false;
    }

    public int getResColor(int resId) {
        return getResources().getColor(resId);
    }

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    public <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }

    //-------------start 适配 字体
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
    //-------------end 适配 字体

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        MobclickAgent.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        MobclickAgent.onPause(this);
    }

}
