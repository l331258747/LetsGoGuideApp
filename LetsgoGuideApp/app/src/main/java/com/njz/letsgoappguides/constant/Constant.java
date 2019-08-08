package com.njz.letsgoappguides.constant;

/**
 * Created by Administrator on 2018/11/2.
 */

public class Constant {

    public static final int SUCCESS=0;


    //banner图默认时间 轮播
    public static final int BANNER_RUNNING_TIME = 4000;

    //文件路径
    public static final String BASE_PATH = "letsgoguideapp";
    public static final String LOG_PATH = "log";
    public static final String IMAGE_PATH = "image";

    //分页默认参数
    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_PAGE = 1;

    //订单 0代付款，1已付款，2已完成
    public static final int ORDER_PAY_WAIT = 0;//待付款
    public static final int ORDER_PAY_ALREADY = 1;//已支付
    public static final int ORDER_PAY_FINISH = 2;//已完成
//  public static final int ORDER_PAY_CANCEL = 3;//已取消
    public static final int ORDER_PAY_REFUND = 4;//退款单（包含已取消 ）
    public static final int ORDER_PAY_ALL = 100;//所有的
    public static final int ORDER_PAY_TOTAL = 11;//所有的

    public static final int ORDER_WAIT_PAY = 0;//待付款
    public static final int ORDER_WAIT_PAYING = 1;//付款中

    public static final int ORDER_PLAN_GUIDE_WAIT = 0;//导游待确认
    public static final int ORDER_PLAN_PLANING = 1;//方案设计中
    public static final int ORDER_PLAN_USER_WAIT = 2;//游客待确认

    //0:导游待确认  1:未出行 2：行程中
    public static final int ORDER_TRAVEL_WAIT = 0;//导游待确认
    public static final int ORDER_TRAVEL_NO_GO = 1;//未出行
    public static final int ORDER_TRAVEL_GOING = 2;//行程中
    public static final int ORDER_TRAVEL_FINISH = 3;//行程结束
    public static final int ORDER_TRAVEL_REFUSE = 4;//导游拒绝接单

    public static final int ORDER_EVALUATE_NO = 0;//未点评
    public static final int ORDER_EVALUATE_YES = 1;//已点评

    public static final int ORDER_REFUND_WAIT = 0;//导游待审核
    public static final int ORDER_REFUND_PROCESS = 1;//退款中
    public static final int ORDER_REFUND_FINISH = 2;//已退款
    public static final int ORDER_REFUND_CANCEL = 3;//已取消
    public static final int ORDER_REFUND_PLAN_REFUSE = 4;//私人定制，导游拒绝

    //结算
    public static final int ORDER_NOSEELT_WAIT = 0;//待结算
    public static final int ORDER_SEELT_WAIT = 1;//已结算

    public static final int SERVER_TYPE_GUIDE_ID = 1;
    public static final int SERVER_TYPE_FEATURE_ID = 4;
    public static final int SERVER_TYPE_CUSTOM_ID = 3;
    public static final int SERVER_TYPE_HOTEL_ID = 5;
    public static final int SERVER_TYPE_TICKET_ID = 6;
    public static final int SERVER_TYPE_CAR_ID = 2;

    //xdpy向导陪游 tsty特色体验 srdz私人定制 ddjd代订酒店 ddmp代订门票 jsjz接送机/站
    public static final String SERVICE_TYPE_SHORT_GUIDE = "xdpy";
    public static final String SERVICE_TYPE_SHORT_CUSTOM = "srdz";
    public static final String SERVICE_TYPE_SHORT_JSJZ = "jsjz";
    public static final String SERVICE_TYPE_SHORT_TSTY = "tsty";
    public static final String SERVICE_TYPE_SHORT_DDJD = "ddjd";
    public static final String SERVICE_TYPE_SHORT_DDMP = "ddmp";

    //------消息 start-------

    public static final String NOTIFY_TYPE_SYSTEM_MSG = "xtxx";//系统消息
    public static final String NOTIFY_TYPE_INTERACTION = "lyhd";//驴友互动
    public static final String NOTIFY_TYPE_DISCOUNT = "yhhd";//优惠活动Discount
    public static final String NOTIFY_TYPE_COMMON_MSG = "xxtx";//消息提醒
    public static final String NOTIFY_TYPE_ORDER_MSG = "ddxx";//订单消息
    public static final String NOTIFY_TYPE_VERIFY_MSG = "shxx";//审核消息
    public static final String NOTIFY_TYPE_REFUND_MSG = "ttxx";//退款消息
    public static final String NOTIFY_TYPE_REPORT_MSG = "jbxx";//举报消息
    public static final String NOTIFY_TYPE_OPINION_MSG = "yjxx";//意见消息Opinion

    //跳转信息
    public static final String NOTIFY_SKIP_FSD = "FSD";//动态详情
    public static final String NOTIFY_SKIP_OD = "OD";//订单详情
    public static final String NOTIFY_SKIP_ORD = "ORD";//退款单详情
    public static final String NOTIFY_SKIP_UD = "UD";//用户详情
    public static final String NOTIFY_SKIP_GD = "GD";//导游详情
    public static final String NOTIFY_SKIP_RD = "RD";//评价详情
    public static final String NOTIFY_SKIP_SD = "SD";//审核详情

    //------消息 end-------


    public static final int CALENDARID=102;
    public static final String CALENDARNOTIME="CALENDARNOTIME";//非空闲时间

    public static final int PREVIEWID=1;
    public static final int PREVIEWIDDETAIL=2;

    public static final String DEFAULT_PRICE="DEFAULT_PRICE";
    public static final int DEFAULT_PRICEID=110;
    public static final String PRICECALENDERINFO="PRICECALENDERINFO";

    public static final int STORYID=111;
    public static final String STORYINFO="STORYINFO";


    /**
     * url 跳转链接
     **/
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    public static final String IS_USER_WIDE_VIEW_PORT = "is_UseWideViewPort";


    //im 密码
    public static final String IM_PASSWORD = "najiuzou@im";

    public static final String IM_NICKNAME = "nickname";
    public static final String IM_HEADIMG = "headimg";
    public static final String IM_ID = "id";

}
