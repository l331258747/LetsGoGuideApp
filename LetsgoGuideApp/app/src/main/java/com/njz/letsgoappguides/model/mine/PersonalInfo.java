package com.njz.letsgoappguides.model.mine;

import android.os.Parcel;
import android.os.Parcelable;

import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.server.ServicePreviewInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/12/25.
 * 传递给个人资料预览页面的数据
 */

public class PersonalInfo implements Parcelable {

    private String name;
    private String base64Img;//头像
    private String image;//形象图
    private String introduce;
    private String myStory;
    private String language;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(base64Img);
        out.writeString(image);
        out.writeString(introduce);
        out.writeString(myStory);
        out.writeString(language);
    }
    public static final Creator<PersonalInfo> CREATOR = new Creator<PersonalInfo>() {
        @Override
        public PersonalInfo createFromParcel(Parcel in) {
            return new PersonalInfo(in);
        }
        @Override
        public PersonalInfo[] newArray(int size) {
            return new PersonalInfo[size];
        }
    };

    public PersonalInfo() {
    }

    protected PersonalInfo(Parcel in) {
        name = in.readString();
        base64Img = in.readString();
        image = in.readString();
        introduce = in.readString();
        myStory =in.readString();
        language = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "name='" + name + '\'' +
                ", base64Img='" + base64Img + '\'' +
                ", image='" + image + '\'' +
                ", introduce='" + introduce + '\'' +
                ", myStory='" + myStory + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
