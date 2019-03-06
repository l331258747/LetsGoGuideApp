package com.njz.letsgoappguides.http;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.base.ActivityCollect;
import com.njz.letsgoappguides.model.Result;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.activites.LoginActivity;
import com.njz.letsgoappguides.util.ProgressCancelListener;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/11/2.
 */

public class ProgressSubscriber<T> extends DisposableObserver<Result> implements ProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;
    private ResponseCallback mResponseCallback;

    private Context context;

    /**
     * 有对话框
     * @param mResponseCallback 成功回调监听
     * @param context                    上下文
     */
    public ProgressSubscriber(ResponseCallback mResponseCallback, Context context) {
        this(mResponseCallback,context,true);
    }

    public ProgressSubscriber(ResponseCallback mResponseCallback, Context context,  boolean show) {
        this.mResponseCallback = mResponseCallback;
        this.context = context;

        mProgressDialogHandler = new ProgressDialogHandler(context, this, false,show);
    }
    public ProgressSubscriber(Context context,boolean show) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, false,show);
    }
    public ProgressSubscriber(ResponseCallback mResponseCallback) {
        this.mResponseCallback = mResponseCallback;
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }


    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }



    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
        mProgressDialogHandler = null;
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        try {

            if (e instanceof SocketTimeoutException) {//请求超时
                mResponseCallback.onFault("-网络连接超时");
            } else if (e instanceof ConnectException) {//网络连接超时
                mResponseCallback.onFault("-网络连接失败");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                mResponseCallback.onFault("-安全证书异常");
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    mResponseCallback.onFault("-网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    mResponseCallback.onFault("-请求的地址不存在");
                } else {
                    mResponseCallback.onFault("-请求失败");
                }
            } else if (e instanceof UnknownHostException) {//域名解析失败
                mResponseCallback.onFault("-域名解析失败");
            } else {
                mResponseCallback.onFault("-error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
            dismissProgressDialog();
            mProgressDialogHandler = null;
        }

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     * @param t
     */
    @Override
    public void onNext(@NonNull Result t) {
        LogUtil.e("code:"+t.getCode());
        LogUtil.e("result"+t.toString());
        LogUtil.e("msg:"+t.getMsg());
//        LogUtil.e("data:"+t.getData().toString());
        LogUtil.e("url:"+t.getUrl());

//        if(t.getCode()==0){
//            mResponseCallback.onSuccess(t.getData());
//        }else{
//            mResponseCallback.onFault(t.getMsg());
//
//            if(t.getCode() == 401){
//                DialogUtil.getInstance().getDefaultDialog(context, t.getMsg(), "去登录", new DialogUtil.DialogCallBack() {
//                    @Override
//                    public void exectEvent(DialogInterface alterDialog) {
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                    }
//                }).show();
//            }
//        }
        if(t.getCode()==0 && t.getErrno() == 0){
            if (!TextUtils.isEmpty(t.getUrl())){
                mResponseCallback.onSuccess(t.getUrl());
                return;
            }
            if(t.getData()==null||t.getData().equals("")){
                mResponseCallback.onSuccess("");
                return;
            }
            mResponseCallback.onSuccess(t.getData());
        }else{
            if(!TextUtils.isEmpty(t.getMsg())){
                mResponseCallback.onFault(t.getMsg());
            }else if(!TextUtils.isEmpty(t.getErrmsg())){
                mResponseCallback.onFault(t.getErrmsg());
            }else{
                mResponseCallback.onFault("--");
            }

            if(t.getCode() == 401){//强制登录
                MySelfInfo.getInstance().loginOff();
                context.startActivity(new Intent(context, LoginActivity.class));
                ActivityCollect.getAppCollect().finishAllActivity();

//                DialogUtil.getInstance().getDefaultDialog(context, t.getMsg(), "去登录", new DialogUtil.DialogCallBack() {
//                    @Override
//                    public void exectEvent(DialogInterface alterDialog) {
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                        ActivityCollect.getAppCollect().finishAllActivity();
//                    }
//                }).show();
            }else if(t.getErrno() == 401){
                MySelfInfo.getInstance().loginOff();
                context.startActivity(new Intent(context, LoginActivity.class));
                ActivityCollect.getAppCollect().finishAllActivity();
//                DialogUtil.getInstance().getDefaultDialog(context, t.getErrmsg(), "去登录", new DialogUtil.DialogCallBack() {
//                    @Override
//                    public void exectEvent(DialogInterface alterDialog) {
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                        ActivityCollect.getAppCollect().finishAllActivity();
//                    }
//                }).show();
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }




}
