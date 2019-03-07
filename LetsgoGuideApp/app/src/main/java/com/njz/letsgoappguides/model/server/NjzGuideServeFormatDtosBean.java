package com.njz.letsgoappguides.model.server;

import java.util.List;

public class NjzGuideServeFormatDtosBean {
    /**
     * njzGuideServeFormatDic : xdpy_yy
     * guideServeFormatName : 向导陪游语言类型0
     * servePriceSelect : 20181129$10,20181130$10,20181212$11.11,20180909$11.11,20180914$11.11,20180915$11.11
     * serveDefaultPrice : 100
     */

    private Long id;
    private String njzGuideServeFormatDic;
    private String guideServeFormatName;
    List<NjzGuideServeFormatPriceEntities> njzGuideServeFormatPriceEntities;
    private float serveDefaultPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNjzGuideServeFormatDic() {
        return njzGuideServeFormatDic;
    }

    public void setNjzGuideServeFormatDic(String njzGuideServeFormatDic) {
        this.njzGuideServeFormatDic = njzGuideServeFormatDic;
    }

    public String getGuideServeFormatName() {
        return guideServeFormatName;
    }

    public void setGuideServeFormatName(String guideServeFormatName) {
        this.guideServeFormatName = guideServeFormatName;
    }

    public List<NjzGuideServeFormatPriceEntities> getServePriceSelect() {
        return njzGuideServeFormatPriceEntities;
    }

    public void setServePriceSelect(List<NjzGuideServeFormatPriceEntities> njzGuideServeFormatPriceEntities) {
        this.njzGuideServeFormatPriceEntities = njzGuideServeFormatPriceEntities;
    }

    public float getServeDefaultPrice() {
        return serveDefaultPrice;
    }

    public void setServeDefaultPrice(float serveDefaultPrice) {
        this.serveDefaultPrice = serveDefaultPrice;
    }

    @Override
    public String toString() {
        return "NjzGuideServeFormatDtosBean{" +
                "id=" + id +
                ", njzGuideServeFormatDic='" + njzGuideServeFormatDic + '\'' +
                ", guideServeFormatName='" + guideServeFormatName + '\'' +
                ", njzGuideServeFormatPriceEntities=" + njzGuideServeFormatPriceEntities +
                ", serveDefaultPrice=" + serveDefaultPrice +
                '}';
    }



}