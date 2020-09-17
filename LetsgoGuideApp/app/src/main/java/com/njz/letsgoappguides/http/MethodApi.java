package com.njz.letsgoappguides.http;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.constant.UrlConstant;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.authentication.GuideValidInfo;
import com.njz.letsgoappguides.model.authentication.ToAuthenInfo;
import com.njz.letsgoappguides.model.home.OrderDesign2Info;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.FeedBackInfo;
import com.njz.letsgoappguides.model.mine.MyInfoData;
import com.njz.letsgoappguides.model.send.SendNotifyMainModel;
import com.njz.letsgoappguides.model.send.SendOrderRefundChildModel;
import com.njz.letsgoappguides.model.server.AutoServiceModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/11/2.
 */

public class MethodApi {


    //-----------login start------------
    public static void login(String mobile, String password, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getLoginInfo(mobile, password); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 发送验证码
     * @param mobile
     * @param type
     * @param subscriber
     */
    public static void userSmsSend(String mobile, String type, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().userSmsSend(mobile, type); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 注册
     * @param mobile
     * @param password
     * @param passwordSure
     * @param captcha
     * @param subscriber
     */
    public static void register(String mobile, String password , String passwordSure, String captcha ,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().register(mobile, password,passwordSure,captcha); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 验证码登录
     * @param mobile
     * @param captcha
     * @param subscriber
     */
    public static void msgCheckLogin(String mobile, String captcha ,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().msgCheckLogin(mobile,captcha); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 重置密码
     * @param mobile
     * @param newPassword
     * @param captcha
     * @param subscriber
     */
    public static void msgResetPassword(String mobile, String captcha ,String newPassword,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().msgResetPassword(mobile,captcha,newPassword); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 修改手机号
     * @param mobile
     * @param password
     * @param captcha
     * @param subscriber
     */
    public static void updateMobile(String mobile, String captcha ,String password,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().updateMobile(mobile,captcha,password); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 修改密码
     * @param password
     * @param newpassword
     * @param subscriber
     */
    public static void updataPassword(String password,String newpassword,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().updataPassword(password,newpassword); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的评价
     * @param page
     * @param limit
     * @param type
     * @param subscriber
     */
    public static void review(int page, int limit, int type, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().review(page,limit,type); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 导游回复评论
     * @param guideContent
     * @param userId
     * @param orderId
     * @param subscriber
     */
    public static void guideReview(String guideContent, String userId,int id, int orderId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().guideReview(guideContent,userId,id,orderId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 意见反馈
     * @param subscriber
     */
    public static void feedBackSave(FeedBackInfo infos, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().feedBackSave(infos);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    /**
     * 认证信息查看
     * @param subscriber
     */
    public static void queryAuthInfo( DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().queryAuthInfo();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 提交实名认证信息
     * @param subscriber
     */
    public static void toAuthInfo(ToAuthenInfo data, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().toAuthInfo(data);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //导游资格认证
    public static void guideValidInfo(GuideValidInfo data, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().guideValidInfo( data);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //车导认证
    public static void driveValidInfo(DriveValidInfo data, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().driveValidInfo( data);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //驾驶证车型
    public static void getDriveType( DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getDriveType( );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //银行卡保存
    public static void binkSave(BinkIntoInfo mBinkIntoInfo, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().binkSave(mBinkIntoInfo );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //获取银行列表
    public static void getBackList( DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getBackList( );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //银行卡修改
    public static void binkUpdate(BinkIntoInfo mBinkIntoInfo, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().binkUpdate(mBinkIntoInfo );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //银行卡查看
    public static void binkInfo( DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().binkInfo( );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //查看收益信息
    public static void getBalanceOrderList(int page, int limit, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getBalanceOrderList(page,limit );
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //待结算订单列表
    public static void querySettlementOrderList(int page, int limit,String search, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().querySettlementOrderList(page,limit ,search);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //结算已完成订单详情
    public static void queryAwaitBalanceChildOrder(int orderId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().queryAwaitBalanceChildOrder(orderId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //结算退款订单详情
    public static void queryRefundChildOrder(int refundId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().queryRefundChildOrder(refundId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //退出登录
    public static void exitLogin(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().exitLogin();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //新增服务
    public static void addService(AutoServiceModel data,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().addService(data);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //得到服务类型（新）
    public static void getServeDicList(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getServeDicList();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //获取目的地列表
    public static void getAreaListAndChildrens(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getAreaListAndChildrens();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }



    //---------首页 start--------
    //orderIndex
    public static void orderIndex(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderIndex(); //在HttpServer中,
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //orderList 订单
    public static void orderList(int payStatus,int orderStatus, int page,int limit,String search,DisposableObserver subscriber) {
        int type  = 0;
        if(payStatus == Constant.ORDER_PAY_WAIT){
            type = 0;
        }else if(payStatus == Constant.ORDER_PAY_ALREADY && orderStatus == Constant.ORDER_TRAVEL_WAIT){
            type = 1;
        }else if(payStatus == Constant.ORDER_PAY_ALREADY && orderStatus == Constant.ORDER_TRAVEL_NO_GO){
            type = 2;
        }else if(payStatus == Constant.ORDER_PAY_ALREADY && orderStatus == Constant.ORDER_TRAVEL_GOING){
            type = 3;
        }else if(payStatus == Constant.ORDER_PAY_FINISH){
            type = 4;
        }else if(payStatus==Constant.ORDER_PAY_ALL){
            type = 8;
        }else if(payStatus==Constant.ORDER_PAY_TOTAL){
            type = 11;
        }
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderList(type,payStatus,orderStatus,page,limit,search); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //orderQueryOrder 订单详情
    public static void orderQueryOrder(int orderId,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderQueryOrder(orderId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    ///api/appGuide/order/refundList 退款单列表
    public static void orderRefundList(int page,int limit,String search,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderRefundList(page,limit,search); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    ///api/appGuide/order/queryRefundOrder 退款单详情
    public static void orderQueryRefundOrder(int refundId,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderQueryRefundOrder(refundId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }


    //api/appGuide/order/refuseOrder 拒绝接单
    public static void orderRefuseOrder(int orderId,String refuseReason,String refuseExplain,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderRefuseOrder(orderId,refuseReason,refuseExplain); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //api/appGuide/order/refuseOrder 私人定制确认接单 与 拒绝接单
    public static void guideSurePersonalServe(int orderId,int planStatus,String refuseReason,String refuseExplain,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().guideSurePersonalServe(orderId,planStatus,refuseReason,refuseExplain); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/njzGuide/order/queryCancelReasonList 拒绝原因列表
    public static void queryCancelReasonList(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().queryCancelReasonList(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/order/sureOrder 确认接单
    public static void orderSureOrder(int orderId,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderSureOrder(orderId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/order/queryOrderReview 通过订单id查评价
    public static void orderQueryOrderReview(int orderId,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderQueryOrderReview(orderId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/order/sureRefundOrder 确认退款
    public static void orderSureRefundOrder(List<SendOrderRefundChildModel> lists, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderSureRefundOrder(lists); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/order/refundRule 退订规则
    public static void orderRefundRule(int serverId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderRefundRule(serverId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/njzGuide/order/viewPlanDesign 获取方案信息
    public static void orderQueryDesingnInfo( int orderId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderQueryDesingnInfo(orderId); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/njzGuide/order/offerDesign 修改(设计)方案
    public static void orderOfferDesign(OrderDesign2Info orderDesignInfo, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().orderOfferDesign(orderDesignInfo); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/order/addOrderNote 新增或更新订单备注
    public static void addOrderNote(int orderId,String orderNote, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().addOrderNote(orderId,orderNote); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //---------首页 end--------


    //-----------other start--------
    //upUpload
    public static void upUpload(File file, DisposableObserver subscriber) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().upUpload(fileBody);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //多图片upUpload
    public static void batchUpload(List<String> files, DisposableObserver subscriber) {
        List<MultipartBody.Part> partList = filesToMultipartBodyParts(files);
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().batchUpload(partList);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    private static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (String fileStr : files) {
            File file = new File(fileStr);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    //电话监听 wiretapping
    public static void wiretapping(int orderId, int serveId, int guideId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().wiretapping(orderId,serveId,guideId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //-----------other end--------


    //-------我的 start-------
    //userGetSign 获取个人标签
    public static void userGetSign(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().userGetSign(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //getUserInfo 个人主页预览
    public static void getUserInfo(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getUserInfo(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/user/update 修改个人资料.
    public static void userUpdate(MyInfoData data,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().userUpdate(data); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //userGetLanguage 获取语言类型
    public static void userGetLanguage(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().userGetLanguage(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //userGetLanguage 获取选中的语言类型
    public static void getCheckLanguage(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getCheckLanguage(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //-------我的 end-------

    //------消息 start-------
    //msgReceiveKindList 获取个人受到消息列表（主页）
    public static void msgReceiveKindList(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().msgReceiveKindList(); //在HttpServer中
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //msgPushGetReceiveMsgList 子消息列表
    public static void msgGetReceiveMsgList(String type,int limit,int page, DisposableObserver subscriber) {
        SendNotifyMainModel mainModel = new SendNotifyMainModel();
        mainModel.setMsgBroad(type);
        mainModel.setLimit(limit);
        mainModel.setPage(page);
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().msgGetReceiveMsgList(type,limit,page);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }


    //------消息 end-------

    //--------服务 start-------
    //api/appGuide/serve/getServe 获取服务类型
    public static void serveGetServe(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().serveGetServe();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //api/appGuide/city/getCity 获取城市
    public static void cityGetCity(DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().cityGetCity();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //serveList 服务列表
    public static void serveList(int page,int limit,String addressId,String serveType,String status,String key,DisposableObserver subscriber) {
        serveType=serveType.equals("-1")?"":serveType;
        addressId=addressId.equals("-1")?"":addressId;
        status=status.equals("-2")?"":status;
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().serveList(page,limit,addressId,serveType,status,key);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //得到修改服务信息
    public static void getUpdaServiceInfo(int njzGuideServeId,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getUpdaServiceInfo(njzGuideServeId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //修改服务信息
    public static void updaServiceInfo(AutoServiceModel infos, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().updaServiceInfo(infos);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //导游服务详情查看
    public static void getServeDetail(int njzGuideServeId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getServeDetail(njzGuideServeId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    //得到当前两个月价格日历信息
    public static void getServeFormatPrice(int njzFormatIdId, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getServeFormatPrice(njzFormatIdId);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //获取某规格某月的时间价格参数
    public static void getMonthPrice(int njzFormatId,String time, DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getMonthPrice(njzFormatId, time);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }


    //--------服务 end-------

    //操作指南
    public static void getOperationGuide( DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getOperationGuide();
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }
    public static void getUserByIMUsername(String username, DisposableObserver subscriber) {
        if(TextUtils.equals(username,"admin")) return;
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().getUserByIMUsername(username);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //-----------------IM start-------------
    public static void saveMessage(String fromId,String toId,String chatType,String msg,DisposableObserver subscriber) {
        Observable observable = RetrofitUtil.getInstance().getRetrofitApiService().saveMessage(fromId,toId,chatType,msg);
        RetrofitUtil.getInstance().toSubscribe(observable, subscriber);
    }

    //-----------------IM end-------------
}
