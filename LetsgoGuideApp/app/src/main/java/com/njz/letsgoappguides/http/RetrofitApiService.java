package com.njz.letsgoappguides.http;

import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.Result;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.authentication.GuideValidInfo;
import com.njz.letsgoappguides.model.authentication.ToAuthenInfo;
import com.njz.letsgoappguides.model.evaluation.Datainfo;
import com.njz.letsgoappguides.model.home.CancelReasonListInfo;
import com.njz.letsgoappguides.model.home.EvaluateModel2;
import com.njz.letsgoappguides.model.home.HomeModel;
import com.njz.letsgoappguides.model.home.OrderDesign2Info;
import com.njz.letsgoappguides.model.home.OrderDesignInfo;
import com.njz.letsgoappguides.model.home.OrderDetailModel;
import com.njz.letsgoappguides.model.home.OrderListModel;
import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;
import com.njz.letsgoappguides.model.home.OrderRefundModel;
import com.njz.letsgoappguides.model.home.ServiceRefundRuleModel;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.authentication.DriveTypeInfo;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.message.NotifyMainModel;
import com.njz.letsgoappguides.model.mine.BatchUploadInfo;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.FeedBackInfo;
import com.njz.letsgoappguides.model.mine.GetBackListInfo;
import com.njz.letsgoappguides.model.mine.GetBinkInfo;
import com.njz.letsgoappguides.model.mine.IncomeInfo;
import com.njz.letsgoappguides.model.mine.IncomeListInfo;
import com.njz.letsgoappguides.model.mine.LabelModel;
import com.njz.letsgoappguides.model.mine.MyInfoData;
import com.njz.letsgoappguides.model.mine.OperationGuideModel;
import com.njz.letsgoappguides.model.mine.OrderSettleBalanceChildModel;
import com.njz.letsgoappguides.model.mine.OrderSettleModel;
import com.njz.letsgoappguides.model.mine.OrderSettltRefundChildModel;
import com.njz.letsgoappguides.model.other.IMUserModel;
import com.njz.letsgoappguides.model.send.SendOrderRefundChildModel;
import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;
import com.njz.letsgoappguides.model.server.NjzGuideServeFormatPriceEntityListBean;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;
import com.njz.letsgoappguides.model.server.ServiceCalPriceInfo;
import com.njz.letsgoappguides.model.server.ServiceDetailInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/10/31.
 */

public interface RetrofitApiService {

    /**
     * 登录
     * @param username
     * @param passwore
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/user/login")
    Observable<Result<Datas>> getLoginInfo(@Field("username")String username, @Field("password")String passwore);

    /**
     * 获取验证码 注册，登录，密码找回
     * @param mobile
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/sms/userSmsSend")
    Observable<Result<String>> userSmsSend(@Field("mobile")String mobile, @Field("type")String type);

    /**
     * 注册
     * @param mobile
     * @param password
     * @param passwordSure
     * @param captcha
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/sms/register")
    Observable<Result<String>> register(@Field("mobile")String mobile, @Field("password")String password ,@Field("passwordSure")String passwordSure,@Field("captcha")String captcha);

    /**
     * 验证码登录
     * @param mobile
     * @param captcha
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/sms/msgCheckLogin")
    Observable<Result<Datas>> msgCheckLogin(@Field("mobile")String mobile, @Field("captcha")String captcha);

    /**
     * 忘记密码
     * @param mobile
     * @param newPassword
     * @param captcha
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/sms/msgResetPassword")
    Observable<Result<String>> msgResetPassword(@Field("mobile")String mobile, @Field("captcha")String captcha ,@Field("newPassword")String newPassword);

    /**
     * 修改手机号
     * @param mobile
     * @param password
     * @param captcha
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/sms/updateMobile")
    Observable<Result<String>> updateMobile(@Field("mobile")String mobile, @Field("captcha")String captcha ,@Field("password")String password);

    /**
     * 修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/user/resetPassword")
    Observable<Result<String>> updataPassword(@Field("password")String password,@Field("newPassword")String newPassword);

    /**
     * 我的评价
     * @param page
     * @param limit
     * @param type
     * @return
     */
    @GET("api/appGuide/user/review")
    Observable<Result<Datainfo>> review(@Query("page")int page, @Query("limit")int limit, @Query("type")int type);

    /**
     * 导游回复评论
     * @param guideContent
     * @param userId
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST("api/appGuide/user/guideReview")
    Observable<Result<String>> guideReview(@Field("guideContent")String guideContent,@Field("userId")String userId,@Field("id")int id,@Field("orderId")int orderId);

    /**
     * 意见反馈
     * @return
     */
    @POST("api/appGuide/feed/save")
    Observable<Result<Datainfo>> feedBackSave(@Body FeedBackInfo infos);

    /**
     * 查看认证信息
     * @return
     */
    @GET("api/appGuide/info/queryInfo")
    Observable<Result<AuthenInfo>> queryAuthInfo();

    /**
     * 提交实名认证信息
     * @return
     */
    @POST("api/appGuide/info/update")
    Observable<Result<UserVo>> toAuthInfo(@Body ToAuthenInfo data);
    /**
     * 提交车导认证信息
     * @return
     */
    @POST("api/appGuide/info/update")
    Observable<Result<UserVo>> driveValidInfo(@Body DriveValidInfo data);
    /**
     * 提交导游资格认证信息
     * @return
     */
    @POST("api/appGuide/info/update")
    Observable<Result<UserVo>> guideValidInfo(@Body GuideValidInfo data);


    /**
     * 驾驶证类型
     * @return
     */
    @GET("api/appGuide/user/getDriveType")
    Observable<Result<List<DriveTypeInfo>>> getDriveType();

    /**
     * 银行卡保存
     * @return
     */
    @POST("api/appGuide/back/save")
    Observable<Result<String>> binkSave(@Body BinkIntoInfo data);

    /**
     * 银行卡修改
     * @return
     */
    @POST("api/appGuide/back/update")
    Observable<Result<String>> binkUpdate(@Body BinkIntoInfo data);

    /**
     * 获取银行列表
     * @return
     */
//    @POST("api/appGuide/back/getBackList")
    @POST("api/backAccount/getBackList")
    Observable<Result<List<GetBackListInfo>>> getBackList();

    /**
     * 银行卡查看
     * @return
     */
    @POST("api/appGuide/back/info")
    Observable<Result<GetBinkInfo>> binkInfo();

    /**
     * 收益信息查看
     * @return
     */
    @GET("api/appGuide/yield/queryYetBalanceOrderList")
    Observable<Result<IncomeInfo<IncomeInfo.PageUtilsBean<IncomeListInfo>>>> getBalanceOrderList(@Query("page")int page, @Query("limit")int limit);

    /**
     * 待结算订单列表
     * @return
     */
    @GET("api/appGuide/order/queryAwaitBalanceOrderList")
    Observable<Result<List<OrderSettleModel>>> querySettlementOrderList(
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("search")String search
    );

    /**
     * 结算已完成订单详情
     * @return
     */
    @GET("api/appGuide/order/queryAwaitBalanceChildOrder")
    Observable<Result<OrderSettleBalanceChildModel>> queryAwaitBalanceChildOrder(
            @Query("orderId")int orderId
    );

    /**
     * 结算退款订单详情
     * @return
     */
    @GET("api/appGuide/order/queryRefundChildOrder")
    Observable<Result<OrderSettltRefundChildModel>> queryRefundChildOrder(
            @Query("refundId")int refundId
    );

    /**
     * 退出登录
     * @return
     */
    @GET("api/appGuide/user/logout")
    Observable<Result<String>> exitLogin(
    );

    /**
     * 得到服务类型（新）
     * @return
     */
    @GET("api/njzGuideServe/getNjzGuideServeDicList")
    Observable<Result<GetServeDicListModel>> getServeDicList();

    /**
     * 新增服务
     * @return
     */
    @POST("api/njzGuideServe/upLoadNjzGuideServeNew")
    Observable<Result<String>> addService(@Body AutoServiceModel data);

    /**
     * 获取目的地列表
     * @return
     */
    @POST("api/njzGuideServe/getNjzAreaListAndChildrens")
    Observable<Result<List<GetServiceCityModel>>> getAreaListAndChildrens();






    // --------- start 首页 -----------
    @GET("api/appGuide/order/index")
    Observable<Result<HomeModel>> orderIndex();

    ///api/appGuide/order/list 订单
    @GET("api/appGuide/order/list")
    Observable<Result<BasePageModel<OrderListModel>>> orderList(
            @Query("type")int type,
            @Query("payStatus")int payStatus,
            @Query("orderStatus")int orderStatus,
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("search")String search
    );

    ///api/appGuide/order/list 订单
    @GET("api/appGuide/order/list")
    Observable<Result<BasePageModel<OrderListModel>>> orderList(
            @Query("payStatus")int payStatus,
            @Query("orderStatus")int orderStatus,
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("search")String search
    );

    ///api/appGuide/order/queryOrder 订单详情
    @GET("api/appGuide/order/queryOrder")
    Observable<Result<OrderDetailModel>> orderQueryOrder(
            @Query("orderId")int orderId
    );

    ///api/appGuide/order/refundList 退款单列表
    @GET("api/appGuide/order/refundList")
    Observable<Result<BasePageModel<OrderRefundModel>>> orderRefundList(
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("search")String search
    );

    ///api/appGuide/order/queryRefundOrder 退款单详情
    @GET("api/appGuide/order/queryRefundOrder")
    Observable<Result<OrderRefundDetailModel>> orderQueryRefundOrder(
            @Query("refundId")int refundId
    );

    ///api/appGuide/order/refuseOrder 拒绝接单
    @FormUrlEncoded
    @POST("api/appGuide/order/refuseOrder")
    Observable<Result<String>> orderRefuseOrder(
            @Field("orderId")int orderId,
            @Field("refuseReason")String refuseReason,
            @Field("refuseExplain")String refuseExplain
    );

    //api/njzGuide/order/queryCancelReasonList 获取拒绝原因列表
    @GET("api/njzGuide/order/queryCancelReasonList")
    Observable<Result<List<CancelReasonListInfo>>> queryCancelReasonList(
    );

    //api/appGuide/order/sureOrder 确认接单
    @GET("api/appGuide/order/sureOrder")
    Observable<Result<String>> orderSureOrder(
            @Query("orderId")int orderId
    );

    //api/appGuide/order/queryOrderReview 通过订单id查评价
    @GET("api/appGuide/order/queryOrderReview")
    Observable<Result<EvaluateModel2>> orderQueryOrderReview(
            @Query("orderId")int orderId
    );

    //api/appGuide/order/sureRefundOrder 确认退款
    @POST("api/appGuide/order/sureRefundOrder")
    Observable<Result<String>> orderSureRefundOrder(
            @Body List<SendOrderRefundChildModel> lists
    );

//    ///api/appGuide/order/refundRule 退订规则
//    @GET("api/appGuide/order/refundRule")
//    Observable<Result<ServiceRefundRuleModel>> orderRefundRule(
//            @Query("id")int serverId
//    );

    //api/njzGuide/order/queryNjzGuideChildDetailVoByChildOrderId 退订规则（新）
    @GET("api/njzGuide/order/queryNjzGuideChildDetailVoByChildOrderId")
    Observable<Result<ServiceRefundRuleModel>> orderRefundRule(
            @Query("njzChildOrderId")int njzChildOrderId
    );

    //api/njzGuide/order/viewPlanDesign 获取方案信息
    @GET("api/njzGuide/order/viewPlanDesign")
    Observable<Result<List<OrderDesignInfo>>> orderQueryDesingnInfo(
            @Query("orderId")int orderId
    );

    //api/njzGuide/order/offerDesign 修改(设计)方案
    @POST("api/njzGuide/order/offerDesign")
    Observable<Result<String>> orderOfferDesign(
            @Body OrderDesign2Info orderDesignInfo
    );


    //api/njzGuide/order/guideSurePersonalServe 私人定制确认接单 与 拒绝接单
    @FormUrlEncoded
    @POST("api/njzGuide/order/guideSurePersonalServe")
    Observable<Result<String>> guideSurePersonalServe(
            @Field("orderId")int orderId,
            @Field("planStatus")int planStatus,
            @Field("refuseReason")String refuseReason,
            @Field("refuseExplain")String refuseExplain
    );

    ///api/order/addOrderNote 新增或更新订单备注
    @GET("api/order/addOrderNote")
    Observable<Result<String>> addOrderNote(
            @Query("orderId")int orderId,
            @Query("orderNote")String orderNote
    );



    // --------- end 首页 -----------


    //---------start other ----------
    ///sys/oss/upload 图片上传
    @Multipart
    @POST("sys/oss/upload")
    Observable<Result<String>> upUpload(
            @Part MultipartBody.Part file
    );

    //多图片上传
    @Multipart
    @POST("sys/oss/batchUpload")
    Observable<Result<BatchUploadInfo>> batchUpload(
            @Part List<MultipartBody.Part> files
    );

    //---------end other ----------

    //------start 我的-------
    ///api/appGuide/user/getSign 获取个人标签
    @GET("api/appGuide/user/getSign")
    Observable<Result<List<LabelModel>>> userGetSign(
    );

    //api/appGuide/user/info 预览个人主页
    @GET("api/appGuide/user/info")
    Observable<Result<UserVo>> getUserInfo(
    );

    //api/appGuide/user/singleUpdate 修改个人资料.
    @POST("api/appGuide/user/singleUpdate")
    Observable<Result<String>> userUpdate(
            @Body MyInfoData data
    );

    //api/appGuide/user/getLanguage 获取语言类型
    @GET("api/appGuide/user/getLanguage")
    Observable<Result<List<GuideMacroEntityList>>> userGetLanguage(
    );

    //api/njzGuideServe/getLanguageListOfGuide 获取选中的语言类型
    @GET("api/njzGuideServe/getLanguageListOfGuide")
    Observable<Result<List<GuideMacroEntityList>>> getCheckLanguage(
    );

    //------end 我的-------

    //------start 消息-----
    ///api/appGuide/msg/receiveKindList 获取个人受到消息列表（主页）
    @GET("api/appGuide/msg/receiveKindList")
    Observable<Result<List<NotifyMainModel>>> msgReceiveKindList(
    );

    //api/appGuide/msg/getReceiveMsgList
    @FormUrlEncoded
    @POST("api/appGuide/msg/getReceiveMsgList")
    Observable<Result<BasePageModel<NotifyMainModel>>> msgGetReceiveMsgList(
            //String type,int limit,int page
            @Field("type")String type,
            @Field("limit")int limit,
            @Field("page")int page
    );
    //------end 消息-----

    //-----start 服务------
    //api/appGuide/serve/getServe 获取服务类型
    @GET("api/appGuide/serve/getServe")
    Observable<Result<List<ServerTypeModel>>> serveGetServe(
    );

/*    //api/appGuide/city/getCity 获取城市
    @GET("api/appGuide/city/getCity")
    Observable<Result<List<CityModel>>> cityGetCity(
    );*/

    //api/njzGuideServe/getNjzAreaIdAndNameVoList 获取城市
    @GET("api/njzGuideServe/getNjzAreaIdAndNameVoList")
    Observable<Result<List<CityModel>>> cityGetCity(
    );



/*    //api/appGuide/serve/list
    @GET("api/appGuide/serve/list")
    Observable<Result<BasePageModel<ServerListModel>>> serveList(
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("address")int address,
            @Query("serveType")int serveType,
            @Query("status")int status,
            @Query("key")String key
    );*/
    //api/njzGuideServe/getNjzGuideServeList
    @GET("api/njzGuideServe/getNjzGuideServeList")
    Observable<Result<BasePageModel<ServerListModel>>> serveList(
            @Query("page")int page,
            @Query("limit")int limit,
            @Query("addressId")String addressId,
            @Query("serveType")String serveType,
            @Query("status")String status,
            @Query("keyword")String keyword
    );

    //修改页服务详情
    //api/njzGuideServe/getSomeNjzGuideServe
    @GET("api/njzGuideServe/getSomeNjzGuideServe")
    Observable<Result<GetUpdateServiceInfo>> getUpdaServiceInfo(
            @Query("njzGuideServeId")int njzGuideServeId
    );

    //更新服务(新)
    //api/njzGuideServe/updateNjzGuideServeNew
    @POST("api/njzGuideServe/updateNjzGuideServeNew")
    Observable<Result<String>> updaServiceInfo(
            @Body AutoServiceModel infos
            );

    //导游服务详情查看
    //api/njzGuideServe/getNjzGuideServeDetail
    @GET("api/njzGuideServe/getNjzGuideServeDetail")
    Observable<Result<ServiceDetailInfo>> getServeDetail(
            @Query("njzGuideServeId")int njzGuideServeId
            );

    //得到当前两个月的价格日历信息
    //api/njzGuideServe/getThisAndNextMonthNjzGuideServeFormatPrice
    @GET("api/njzGuideServe/getThisAndNextMonthNjzGuideServeFormatPrice")
    Observable<Result<List<ServiceCalPriceInfo>>> getServeFormatPrice(
            @Query("njzFormatIdId")int njzFormatIdId
            );

    //获取某规格某月的时间价格参数
    //api/njzGuideServe/getMonthNjzGuideServeFormatPrice
    @GET("api/njzGuideServe/getMonthNjzGuideServeFormatPrice")
    Observable<Result<List<NjzGuideServeFormatPriceEntityListBean>>> getMonthPrice(
            @Query("njzFormatId")int njzFormatId,
            @Query("time")String time
    );



    //-----end 服务------

    //操作指南
    //api/operationGuide/getOperationGuide
    @GET("api/operationGuide/getOperationGuide")
    Observable<Result<OperationGuideModel>> getOperationGuide(
    );

    @GET("api/im/getUserByIMUsername")
    Observable<Result<IMUserModel>> getUserByIMUsername(
        @Query("username")String username
    );

}
