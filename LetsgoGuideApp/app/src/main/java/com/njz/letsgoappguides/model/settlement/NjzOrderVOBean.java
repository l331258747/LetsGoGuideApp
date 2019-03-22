package com.njz.letsgoappguides.model.settlement;

import java.util.List;

public class NjzOrderVOBean {

    private String guideSureTime;
    private String lastPayTime;
    private String beBalancedDate;
    private String endDate;
    private String payTime;
    private int guideId;
    private int orderStatus;
    private String payType;
    private float payPrice;
    private float orderPrice;
    private int id;
    private String sureTime;
    private float balancePrice;
    private String beforTravel;
    private String guideMobile;
    private String guideName;
    private String orderNo;
    private String specialRequire;
    private int balanceStatus;
    private String mobile;
    private String balanceTime;
    private int payingStatus;
    private String cancelTime;
    private String createTime;
    private String name;
    private String location;
    private int reviewStatus;
    private int payStatus;
    private String startDate;
    private List<NjzChildOrderVOSBean> njzChildOrderVOS;

    public List<NjzChildOrderVOSBean> getNjzChildOrderVOS() {
        return njzChildOrderVOS;
    }

}