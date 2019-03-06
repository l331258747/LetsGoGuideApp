package com.njz.letsgoappguides.model.login;

import android.text.TextUtils;

import com.njz.letsgoappguides.model.mine.LabelItemModel;
import com.njz.letsgoappguides.util.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class UserVo {

    private String myStory;
    private String guideScore;
    private String image;
    private String cardViable;
    private String userImg;
    private int gender;
    private String driveViable;
    private String introduce;
    private String mobile;
    private List<GuideMacroEntityList> guideMacroEntityList;
    private String language;
    private String birthTime;
    private String guideAge;
    private String guideViable;
    private String password;
    private List<LabelItemModel> signList;
    private String customSign;
    private String name;
    private String startTime;
    private String id;
    private String serviceCounts;
    private int workYear;
    private String driveViableNoPassCause;
    private String guideViableNoPassCause;
    private String cardViableNoPassCause;
    private String[] signs;

    public String[] getSigns() {
        return signs;
    }

    public String getTravelMacroEntitys() {
        if(signList == null)
            return GsonUtil.convertVO2String(new ArrayList<>());
        return GsonUtil.convertVO2String(signList);
    }

    public List<LabelItemModel> getLabelList() {
        List<LabelItemModel> labels = new ArrayList<>();
        labels.addAll(signList);
        for (String str : GsonUtil.convertJson2Array(customSign)){
            LabelItemModel item = new LabelItemModel();
            item.setName(str);
            item.setSelect(true);
            labels.add(item);
        }
        return labels;
    }

    public List<LabelItemModel> getLabelLists() {
        List<LabelItemModel> labels = new ArrayList<>();
        labels.addAll(signList);
        return labels;
    }
    public String getFreeLabel() {
        return customSign;
    }



    public String getMyStory() {
        return myStory;
    }

    public String getGuideScore() {

        return guideScore;
    }
    public float getGuideScoreint() {

        return Float.valueOf(guideScore);
    }



    public String getImage() {
        return image;
    }

    public String getCardViable() {
        if(cardViable==null)
            return "";
        return cardViable;
    }

    public String getUserImg() {
        return userImg;
    }

    public int getGender() {
        return gender;
    }

    public String getDriveViable() {
        if(driveViable==null)
            return "";
        return driveViable;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGuideMacroEntityList() {
        if(guideMacroEntityList ==null)
            return GsonUtil.convertVO2String(new ArrayList<>());
        return GsonUtil.convertVO2String(guideMacroEntityList);
    }

    public String getLanguage() {
        return language;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public String getGuideAge() {
        return guideAge;
    }

    public String getGuideViable() {
        if(guideViable==null)
            return "";
        return guideViable;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomSign() {
        return customSign;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }

    public String getServiceCounts() {
        return serviceCounts;
    }

    public int getWorkYear() {
        return workYear;
    }

    public String getDriveViableNoPassCause() {
        if(driveViableNoPassCause==null)
            return "";
        return driveViableNoPassCause;
    }

    public String getGuideViableNoPassCause() {
        if(guideViableNoPassCause==null)
            return "";
        return guideViableNoPassCause;
    }

    public String getCardViableNoPassCause() {
        if(cardViableNoPassCause==null)
            return "";
        return cardViableNoPassCause;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "myStory='" + myStory + '\'' +
                ", guideScore='" + guideScore + '\'' +
                ", image='" + image + '\'' +
                ", cardViable='" + cardViable + '\'' +
                ", userImg='" + userImg + '\'' +
                ", gender=" + gender +
                ", driveViable='" + driveViable + '\'' +
                ", introduce='" + introduce + '\'' +
                ", mobile='" + mobile + '\'' +
                ", guideMacroEntityList=" + guideMacroEntityList +
                ", language='" + language + '\'' +
                ", birthTime='" + birthTime + '\'' +
                ", guideAge='" + guideAge + '\'' +
                ", guideViable='" + guideViable + '\'' +
                ", password='" + password + '\'' +
                ", customSign='" + customSign + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", id='" + id + '\'' +
                ", serviceCounts='" + serviceCounts + '\'' +
                '}';
    }
}
