package com.njz.letsgoappguides.model.server;

import java.util.List;

/**
 * Created by Administrator on 2018/12/13.
 */

public class ServiceDetailInfo {


    /**
     * njzGuideServeEntity : {"mustPlay":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181213/15201638558a47.jpg,http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181213/152016512c83a8.jpg,","serveType":4,"guideId":36,"title":"测试测试","operator":"","renegePriceThree":"4,7","addressId":36,"costExplain":"你现在在","upDate":null,"playTime":null,"id":90,"forceDownReason":"","serveFeature":"微信上","address":"长沙","servePrice":0,"forceDownDate":null,"serveMinNum":1,"servePriceSeg":"","serveMaxNum":1,"isEnabled":0,"name":"EEE","commentId":0,"putDate":"2018-12-13 15:20:52","renegePriceFive":"1,3","serveTypeName":"特色体验","status":0}
     * saleNum : 0
     * njzGuideServeFormatEntityGroupList : []
     */

    private ServicePreviewInfo njzGuideServeEntity;
    private int saleNum;
    private List<?> njzGuideServeFormatEntityGroupList;

    public ServicePreviewInfo getNjzGuideServeEntity() {
        return njzGuideServeEntity;
    }

    public void setNjzGuideServeEntity(ServicePreviewInfo njzGuideServeEntity) {
        this.njzGuideServeEntity = njzGuideServeEntity;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public List<?> getNjzGuideServeFormatEntityGroupList() {
        return njzGuideServeFormatEntityGroupList;
    }

    public void setNjzGuideServeFormatEntityGroupList(List<?> njzGuideServeFormatEntityGroupList) {
        this.njzGuideServeFormatEntityGroupList = njzGuideServeFormatEntityGroupList;
    }



}
