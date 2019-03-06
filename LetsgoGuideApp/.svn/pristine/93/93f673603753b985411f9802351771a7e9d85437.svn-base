package com.njz.letsgoappguides.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.constant.UrlConstant;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/10/31.
 */

public class RetrofitUtil {
    private Context mCntext;
    //声明Retrofit对象
    private Retrofit mRetrofit;
    //声明RetrofitApiService对象
    private RetrofitApiService retrofitApiService;
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private OkHttpClient.Builder okHttpBuilder;
    private static final int DEFAULT_CONNECT_TIMEOUT = 10;//设置连接超时时间
    private static final int DEFAULT_WRITE_TIMEOUT = 30;//设置写入超时时间
    private static final int DEFAULT_READ_TIMEOUT = 30;//设置读取超时时间

    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;

    //-------HttpMethods 单例 start------
    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    //获取单例
    public static RetrofitUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //-------HttpMethods 单例 end------


    /**
     * 获取retrofit
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    private RetrofitUtil(){
        init();
    }

    //初始化Retrofit
    private void init() {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();

        //设置头信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("X-Nideshop-Id", MySelfInfo.getInstance().getId()+"")
                        .addHeader("X-Nideshop-Token", TextUtils.isEmpty(MySelfInfo.getInstance().getUserToken())?"": MySelfInfo.getInstance().getUserToken())
//                        .addHeader("X-Nideshop-Token", "2qdq9zksjslz85hcvjlo5g3shyd0zvn9")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
            @Override
            public void log(String message) {
                if (StringUtils.isJson(message)) {
                    LogUtil.d(message);
                } else {
                    LogUtil.d(message);
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(httpLoggingInterceptor);

        //------设置超时和重新连接 start------
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //------设置超时和重新连接 end------

        mRetrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlConstant.Test_URL)
                .build();
        retrofitApiService = mRetrofit.create(RetrofitApiService.class);
    }

    public RetrofitApiService getRetrofitApiService(){
        return retrofitApiService;
    }

    //用来设置测试环境，正式环境
    public void changeBaseUrl(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        retrofitApiService = mRetrofit.create(RetrofitApiService.class);
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {

        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribe(s);

    }
}
