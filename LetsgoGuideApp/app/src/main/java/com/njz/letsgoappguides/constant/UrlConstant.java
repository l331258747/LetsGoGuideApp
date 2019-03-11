package com.njz.letsgoappguides.constant;

import com.njz.letsgoappguides.util.AppUtils;

/**
 * Created by Administrator on 2018/10/31.
 */

public class UrlConstant {

    public static final String Test_URL = getUrl();//本地

    public static String getUrl(){
        if(AppUtils.getVersionCodeInt() % 100 == 0){
            return "http://www.njiuzou.com/";//外网地址
        }else{
            return "http://192.168.100.115:9091/";//本地
//            return "http://114.115.161.62:8088/guide-admin/";//测试地址  18357025945 654321

//            return "http://www.njiuzou.com/";//外网地址
        }
    }

}
