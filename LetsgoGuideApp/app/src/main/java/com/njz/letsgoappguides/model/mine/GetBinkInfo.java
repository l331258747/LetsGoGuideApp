package com.njz.letsgoappguides.model.mine;

/**
 * Created by Administrator on 2018/11/27.
 */

public class GetBinkInfo {


    /**
     * guideId : 13
     * mobile : 15111199204
     * backName : 长沙银行
     * id : 5
     * cardNo : 6214837318714007
     * guideName :
     */

    private int guideId;
    private String mobile;
    private String backName;
    private int id;
    private String cardNo;
    private String guideName;
    private int backType;
    private String backTypeName;
    private String backBranch;

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public int getBackType() {
        return backType;
    }

    public void setBackType(int backType) {
        this.backType = backType;
    }

    public String getBackTypeName() {
        return backTypeName;
    }

    public void setBackTypeName(String backTypeName) {
        this.backTypeName = backTypeName;
    }

    public String getBackBranch() {
        return backBranch;
    }

    public void setBackBranch(String backBranch) {
        this.backBranch = backBranch;
    }

    @Override
    public String toString() {
        return "GetBinkInfo{" +
                "guideId=" + guideId +
                ", mobile='" + mobile + '\'' +
                ", backName='" + backName + '\'' +
                ", id=" + id +
                ", cardNo='" + cardNo + '\'' +
                ", guideName='" + guideName + '\'' +
                ", backType=" + backType +
                ", backTypeName='" + backTypeName + '\'' +
                ", backBranch='" + backBranch + '\'' +
                '}';
    }
}
