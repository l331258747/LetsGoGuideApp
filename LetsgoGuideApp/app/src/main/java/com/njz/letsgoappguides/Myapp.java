package com.njz.letsgoappguides;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.push.EMPushConfig;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.im.HMSPushHelper;
import com.njz.letsgoappguides.ui.im.HxEaseuiHelper;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.SPUtils;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.njz.letsgoappguides.wxapi.UpgradeDialogListener;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.commonsdk.UMConfigure;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/10/31.
 */

public class Myapp extends MultiDexApplication {//extends MultiDexApplication 集成环信后 防止5.0以下版本崩溃问题


    private static Myapp instance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        context = getApplicationContext();

        SPUtils.init(context);
        SPUtils.init(context);
        AppUtils.init(this);
        LogUtil.setShowLog(true);

        initBugly();

        initYouMeng();

        initJpush();

        initHuanXin();

    }

    // 记录是否已经初始化
    private boolean isInit = false;
    private void initHuanXin() {
        int pid = android.os.Process.myPid();
        String processAppName = AppUtils.getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(AppUtils.getPakgeName())) {
            LogUtil.e("enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        if(isInit){
            return ;
        }
        HxEaseuiHelper.getInstance().init(context);

        // 请确保环信SDK相关方法运行在主进程，子进程不会初始化环信SDK（该逻辑在EaseUI.java中）
        if (EaseUI.getInstance().isMainProcess(this)) {
            // 初始化华为 HMS 推送服务, 需要在SDK初始化后执行
            HMSPushHelper.getInstance().initHMSAgent(instance);//TODO-1

            EMPushHelper.getInstance().setPushListener(new PushListener() {
                @Override
                public void onError(EMPushType pushType, long errorCode) {
                    LogUtil.e("Push client occur a error: " + pushType + " - " + errorCode);
                    // TODO: 返回的errorCode仅9xx为环信内部错误，可从EMError中查询，其他错误请根据pushType去相应第三方推送网站查询。
                    // TODO: 开发者会在这个回调中收到使用推送的相关错误信息，各推送类型的error code开发者可以自己去各推送平台官网查询错误原因。
                }

                @Override
                public boolean isSupportPush(EMPushType pushType, EMPushConfig pushConfig) {
                    return super.isSupportPush(pushType, pushConfig);
                    // TODO：开发者可以复写该方法控制设备是否支持某推送的判断。
                }
            });
        }

        // 设置初始化已经完成
        isInit = true;
    }


    private void initJpush() {
        JPushInterface.init(context);
        JPushInterface.setDebugMode(true);
    }

    private void initYouMeng() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * qq、baidu、xiaomi、huawei、ali、vivo、oppo、meizu、c360 qq代表应用宝
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空。
         */
//        UMConfigure.init(context, "5c1740f6b465f561b40002cc", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(false);
    }

    public static Myapp getInstance() {
        return instance;
    }

    private void initBugly(){

        /***** Beta高级设置 *****/
        //true表示app启动自动初始化升级模块; false不会自动初始化; 开发者如果担心sdk初始化影响app启动速度，可以设置为false， 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
        Beta.autoInit = true;
        //true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
        Beta.autoCheckUpgrade = true;
        //设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
        Beta.upgradeCheckPeriod = 60 * 1000;
        //设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
        Beta.initDelay = 3 * 1000;
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        //设置sd卡的Download为更新资源保存目录;后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
        Beta.showInterruptedStrategy = true;
        //只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Beta.upgradeDialogLifecycleListener = new UpgradeDialogListener();
        //第三个参数为SDK调试模式开关 建议在测试阶段建议设置成true，发布时设置为false。
//        CrashReport.initCrashReport(getApplicationContext(), "dc64970fa4", true);
        Bugly.init(getApplicationContext(), "dc64970fa4", true);
    }
}
