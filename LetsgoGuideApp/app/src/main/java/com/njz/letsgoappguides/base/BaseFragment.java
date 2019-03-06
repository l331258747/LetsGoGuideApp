package com.njz.letsgoappguides.base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njz.letsgoappguides.Myapp;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 *
 */
public abstract class BaseFragment extends RxFragment {

    protected Activity mActivity;
    public Context context;

    protected View mRootView;

    protected Unbinder mUnbinder;

    /**
     * 标志位，标志已经初始化完成
     */
    public boolean isPrepared;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        //已经初始化
        isPrepared = true;

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mActivity = getActivity();
    }

    public abstract void loadData();

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if(mUnbinder!=null) {
//            mUnbinder.unbind();
//            mUnbinder = null;
//        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        super.onActivityCreated(savedInstanceState);
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

    public void startActivity(Class <? extends Activity> clazz){
        mActivity.startActivity(new Intent(mActivity,clazz));
    }

    public <T extends View> T $(int id) {
        return (T) mRootView.findViewById(id);
    }
    public <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }

    public void showShortToast(String msg) {
        ToastUtil.showShortToast(context, msg);
    }

    public void showLongToast(String msg) {
        ToastUtil.showLongToast(context, msg);
    }

    public int getResColor(int resId) {
        return getResources().getColor(resId);
    }
}
