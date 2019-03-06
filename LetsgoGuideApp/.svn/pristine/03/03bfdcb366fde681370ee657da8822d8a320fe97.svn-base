package com.njz.letsgoappguides.model.mine;

import android.text.TextUtils;


import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.util.GsonUtil;
import com.njz.letsgoappguides.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class MyInfoData {
    String name;
    String base64Img;
    List<GuideMacroEntityList> guideMacroEntityList;
    String image;
    String introduce;
    String myStory;
    String sign;
    String customSign;


    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }

    public List<GuideMacroEntityList> getGuideMacroEntityList() {
        return guideMacroEntityList;
    }

    public void setGuideMacroEntityList(List<GuideMacroEntityList> guideMacroEntityList) {
        this.guideMacroEntityList = guideMacroEntityList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getMyStory() {
        return myStory;
    }

    public void setMyStory(String myStory) {
        this.myStory = myStory;
    }

    public String getFreeLabel() {
        return customSign;
    }

    public void setFreeLabel(List<LabelItemModel> freeLabels) {
        if( freeLabels == null || freeLabels.size() == 0) {
            this.customSign = GsonUtil.convertVO2String(new ArrayList<String>());
            return;
        }
        List<String> values = new ArrayList<>();
        for (LabelItemModel labelItemModel: freeLabels){
            values.add(labelItemModel.getName());
        }
        this.customSign = GsonUtil.convertVO2String(values);
    }

    public String getTheLabel() {
        return sign;
    }

    public void setTheLabel(String theLabel) {
        this.sign = theLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clean(){

    }

    @Override
    public String toString() {
        return "MyInfoData{" +
                "name='" + name + '\'' +
                ", base64Img='" + base64Img + '\'' +
                ", guideMacroEntityList=" + guideMacroEntityList +
                ", image='" + image + '\'' +
                ", introduce='" + introduce + '\'' +
                ", myStory='" + myStory + '\'' +
                ", sign='" + sign + '\'' +
                ", customSign='" + customSign + '\'' +
                '}';
    }
}