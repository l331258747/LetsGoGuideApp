package com.njz.letsgoappguides.model.mine;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/21.
 */

public class FeedBackInfo {

    private String content;
    private String mobile;
    private String ideaImgs;
    private int originType;

    public String getContent() {
        return content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdeaImgs() {
        return ideaImgs;
    }

    public void setIdeaImgs(String ideaImgs) {
        this.ideaImgs = ideaImgs;
    }

    public int getOriginType() {
        return originType;
    }


    public void setContent(String content) {
        this.content = content;
    }



    public void setOriginType(int originType) {
        this.originType = originType;
    }


    @Override
    public String toString() {
        return "FeedBackInfo{" +
                "content='" + content + '\'' +
                ", mobile='" + mobile + '\'' +
                ", ideaImgs='" + ideaImgs + '\'' +
                ", originType=" + originType +
                '}';
    }
}
