package com.njz.letsgoappguides.model.other;

/**
 * Created by LGQ
 * Time: 2018/11/12
 * Function:
 */

public class BannerModel {


    /**
     * imgName : 导游app首页banner测试
     * imgUrl : https://goss4.vcg.com/creative/vcg/800/new/gic13822671.jpg
     * toUrl : XXX
     * guideId : 0
     * orderNum : 1
     * serveId : 0
     * id : 809
     * type : 3
     * status : 1
     */

    private String imgName;
    private String imgUrl;
    private String toUrl;
    private int guideId;
    private int orderNum;
    private int serveId;
    private int id;
    private int type;
    private int status;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "imgName='" + imgName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", toUrl='" + toUrl + '\'' +
                ", guideId=" + guideId +
                ", orderNum=" + orderNum +
                ", serveId=" + serveId +
                ", id=" + id +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
