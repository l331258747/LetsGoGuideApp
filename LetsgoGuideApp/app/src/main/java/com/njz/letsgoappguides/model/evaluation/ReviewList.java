package com.njz.letsgoappguides.model.evaluation;

import android.text.TextUtils;

import com.njz.letsgoappguides.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/11/9.
 */

public class ReviewList {

    private int orderId;
    private String orderSn;
    private int guideId;
    private String userId;
    private String userDate;
    private float score;
    private String vedioUrl;
    private String userMobile;
    private String imgUrl;
    private String imageUrls;
    private String userContent;
    private String nickname;
    private int id;
    private String guideContent;
    private String guideDate;

    private float serviceAttitude;
    private float serviceQuality;
    private float travelArrange;
    private float carCondition;
    private boolean havPirvate;


    public int getOrderId() {
        return orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public int getGuideId() {
        return guideId;
    }


    public String getUserId() {
        return userId;
    }

    public String getUserDate() {
        return userDate;
    }

    public float getScore() {
        return score;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public String getImgUrl() {
        if (!TextUtils.isEmpty(imgUrl)) {
            ArrayList<String> list = StringUtils.stringToList(imgUrl);
            imgUrl = list.get(0);
        }
        return imgUrl;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public List<String> getImageUrls() {
        if (TextUtils.isEmpty(imageUrls)) {
            return new ArrayList<>();
        }

        String[] strs = imageUrls.split(",");
        List<String> lists = new ArrayList<>(Arrays.asList(strs));
        return lists;
    }

    public String getUserContent() {
        return userContent;
    }

    public String getNickname() {
        return nickname;
    }

    public int getId() {
        return id;
    }

    public String getGuideContent() {
        return guideContent;
    }

    public void setGuideContent(String str) {
        guideContent = str;
    }

    public String getGuideDate() {
        return guideDate;
    }

    public void setGuideDate() {
        guideDate = "刚刚";
    }

    public float getCarCondition() {
        return carCondition;
    }

    public float getTravelArrange() {
        return travelArrange;
    }

    public float getServiceAttitude() {
        return serviceAttitude;
    }

    public float getServiceQuality() {
        return serviceQuality;
    }

    public String getCarConditionStr() {
        return "车辆状况" + carCondition;
    }

    public String getTravelArrangeStr() {
        if (havPirvate)
            return "行程体验" + serviceQuality;
        return "行程安排" + travelArrange;
    }

    public String getServiceAttitudeStr() {
        return "服务态度" + carCondition;
    }

    public String getServiceQualityStr() {
        if (havPirvate)
            return "方案设计" + serviceQuality;
        return "服务质量" + serviceQuality;
    }
}
