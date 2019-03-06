package com.njz.letsgoappguides.model.mine;

/**
 * Created by Administrator on 2018/11/26.
 */

public class BinkIntoInfo {


    /**
     * cardNo :
     * mobile :
     * backName :
     */

    private Integer id;
    private String cardNo;
    private String mobile;
    private int backType;
    private String backBranch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBackType() {
        return backType;
    }

    public void setBackType(int backType) {
        this.backType = backType;
    }

    public String getBackBranch() {
        return backBranch;
    }

    public void setBackBranch(String backBranch) {
        this.backBranch = backBranch;
    }

    @Override
    public String toString() {
        return "BinkIntoInfo{" +
//                "id=" + id +
                ", cardNo='" + cardNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", backType=" + backType +
                ", backBranch='" + backBranch + '\'' +
                '}';
    }
}
