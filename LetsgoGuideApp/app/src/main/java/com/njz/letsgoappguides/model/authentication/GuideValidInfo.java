package com.njz.letsgoappguides.model.authentication;

/**
 * Created by Administrator on 2018/11/20.
 */

public class GuideValidInfo {

    private String guideCardNum;
    private String startTime;
    private String guideValidStartTime;
    private String guideValidEndTime;
    private String guidePhoto;
    private int type;


    public String getGuideCardNum() {
        return guideCardNum;
    }

    public void setGuideCardNum(String guideCardNum) {
        this.guideCardNum = guideCardNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getGuideValidStartTime() {
        return guideValidStartTime;
    }

    public void setGuideValidStartTime(String guideValidStartTime) {
        this.guideValidStartTime = guideValidStartTime;
    }

    public String getGuideValidEndTime() {
        return guideValidEndTime;
    }

    public void setGuideValidEndTime(String guideValidEndTime) {
        this.guideValidEndTime = guideValidEndTime;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GuideValidInfo{" +
                "guideCardNum='" + guideCardNum + '\'' +
                ", startTime='" + startTime + '\'' +
                ", guideValidStartTime='" + guideValidStartTime + '\'' +
                ", guideValidEndTime='" + guideValidEndTime + '\'' +
                ", guidePhoto='" + guidePhoto + '\'' +
                ", type=" + type +
                '}';
    }
}
