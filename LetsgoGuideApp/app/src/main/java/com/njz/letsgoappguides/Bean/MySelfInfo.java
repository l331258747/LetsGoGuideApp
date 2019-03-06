package com.njz.letsgoappguides.Bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.mine.LabelItemModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;
import com.njz.letsgoappguides.util.GsonUtil;
import com.njz.letsgoappguides.util.SPUtils;
import com.njz.letsgoappguides.util.jpush.JpushAliasUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class MySelfInfo {

    private String userToken;

    private MySelfInfo() {
    }

    private final static class HolderClass {
        private final static MySelfInfo INSTANCE = new MySelfInfo();
    }

    public static MySelfInfo getInstance() {
        return HolderClass.INSTANCE;
    }

    public boolean isLogin() {
        if (!TextUtils.isEmpty(getUserToken())) {
            return true;
        }
        return false;
    }


    public void setData(Datas model) {
        UserVo mUserVo = model.getUserVo();

        SPUtils.getInstance().putString(SPUtils.SP_USER_TOKEN, model.getToken());
        SPUtils.getInstance().putString(SPUtils.SP_USER_BIRTHTIME, mUserVo.getBirthTime());
        SPUtils.getInstance().putString(SPUtils.SP_USER_CARDVIABLE, mUserVo.getCardViable());
        SPUtils.getInstance().putString(SPUtils.SP_USER_CUSTOMSIGN, mUserVo.getCustomSign());
        SPUtils.getInstance().putString(SPUtils.SP_USER_DRIVEVIABLE, mUserVo.getDriveViable());
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEAGE, mUserVo.getGuideAge());
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDESCORE, mUserVo.getGuideScore());
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEVIABLE, mUserVo.getGuideViable());
        SPUtils.getInstance().putString(SPUtils.SP_USER_ID, mUserVo.getId());
        SPUtils.getInstance().putString(SPUtils.SP_USER_IMAGE, mUserVo.getImage());
        SPUtils.getInstance().putString(SPUtils.SP_USER_INTRODUCE, mUserVo.getIntroduce());
        SPUtils.getInstance().putString(SPUtils.SP_USER_LANGUAGE, mUserVo.getLanguage());
        SPUtils.getInstance().putString(SPUtils.SP_USER_MOBILE, mUserVo.getMobile());
        SPUtils.getInstance().putString(SPUtils.SP_USER_MYSTORY, mUserVo.getMyStory());
        SPUtils.getInstance().putString(SPUtils.SP_USER_NAME, mUserVo.getName());
        SPUtils.getInstance().putString(SPUtils.SP_USER_PASSWORD, mUserVo.getPassword());
        SPUtils.getInstance().putString(SPUtils.SP_USER_SERVICECOUNTS, mUserVo.getServiceCounts());
        SPUtils.getInstance().putString(SPUtils.SP_USER_STARTTIME, mUserVo.getStartTime());
        SPUtils.getInstance().putString(SPUtils.SP_USER_USERIMG, mUserVo.getUserImg());
        SPUtils.getInstance().putInt(SPUtils.SP_USER_GENDER, mUserVo.getGender());

        SPUtils.getInstance().putString(SPUtils.SP_USER_DRIVEVIABLENOPASSCAUSE, mUserVo.getDriveViableNoPassCause());
        SPUtils.getInstance().putString(SPUtils.SP_USER_CARDVIABLENOPASSCAUSE, mUserVo.getCardViableNoPassCause());
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEVIABLENOPASSCAUSE, mUserVo.getGuideViableNoPassCause());


        SPUtils.getInstance().putString(SPUtils.SP_USER_LABELS, mUserVo.getTravelMacroEntitys());
        SPUtils.getInstance().putString(SPUtils.SP_USER_FREE_LABELS, mUserVo.getFreeLabel());
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEMACROENTITYLIST, mUserVo.getGuideMacroEntityList());


    }

    public void setImLogin(boolean isLogin) {
        SPUtils.getInstance().putBoolean(SPUtils.SP_IM_LOGIN, isLogin);
    }

    public boolean getImLogin(){
        return SPUtils.getInstance().getBoolean(SPUtils.SP_IM_LOGIN);
    }

    public String getFreeLabels() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_FREE_LABELS);
    }

    public void setFreeLabels(List<LabelItemModel> labels){
        List<String> lists = new ArrayList<>();
        for(LabelItemModel item : labels){
            lists.add(item.getName());
        }
        SPUtils.getInstance().putString(SPUtils.SP_USER_FREE_LABELS, GsonUtil.convertVO2String(lists));
    }

    public void setLabels(List<LabelItemModel> labels) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_LABELS, GsonUtil.convertVO2String(labels));
    }

    public List<LabelItemModel> getLabels() {
        return GsonUtil.convertString2Collection(SPUtils.getInstance().getString(SPUtils.SP_USER_LABELS), new TypeToken<List<LabelItemModel>>() {
        });
    }

//    public int getUserId(){
//        return SPUtils.getInstance().getInt(SPUtils.SP_USER_ID);
//    }

    public String getId() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_ID);
    }

    public String getImId(){
        return "g_" + SPUtils.getInstance().getString(SPUtils.SP_USER_ID);
    }

    public String getUserToken() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_TOKEN);
    }

    public String getUserImg() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_USERIMG);
    }

    public void setUserImg(String str){
        SPUtils.getInstance().putString(SPUtils.SP_USER_USERIMG,str);
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMyStory() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_MYSTORY);
    }

    public void setMyStory(String myStory) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_MYSTORY,myStory);
    }

    public String getGuideScore() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_GUIDESCORE);
    }

    public void setGuideScore(String guideScore) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDESCORE,guideScore);
    }

    public String getImage() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_IMAGE);
    }

    public void setImage(String image) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_IMAGE,image);
    }

    public String getCardViable() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_CARDVIABLE);
    }

    public void setCardViable(String cardViable) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_CARDVIABLE,cardViable);
    }

    public String getGender() {
        return SPUtils.getInstance().getInt(SPUtils.SP_USER_GENDER) == 2?"女":"男";
    }

    public void setGender(String gender) {
        SPUtils.getInstance().putInt(SPUtils.SP_USER_GENDER, TextUtils.equals("女",gender)?2:1);
    }

    public String getDriveViable() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_DRIVEVIABLE);
    }

    public void setDriveViable(String driveViable) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_DRIVEVIABLE,driveViable);
    }

    public String getDriveViableNoPassCause() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_DRIVEVIABLENOPASSCAUSE);
    }
    public String getCardViableNoPassCause() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_CARDVIABLENOPASSCAUSE);
    }
    public String getGuideViableNoPassCause() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_GUIDEVIABLENOPASSCAUSE);
    }

    public void setDriveViableNoPassCause(String reason) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_DRIVEVIABLENOPASSCAUSE,reason);
    }
    public void setCardViableNoPassCause(String reason) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_CARDVIABLENOPASSCAUSE,reason);
    }
    public void setGuideViableNoPassCause(String reason) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEVIABLENOPASSCAUSE,reason);
    }



    public String getIntroduce() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_INTRODUCE);
    }

    public void setIntroduce(String introduce) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_INTRODUCE,introduce);
    }

    public String getMobile() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_MOBILE);
    }

    public void setMobile(String mobile) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_MOBILE,mobile);
    }

    public List<GuideMacroEntityList> getGuideMacroEntityList() {
        return GsonUtil.convertString2Collection(SPUtils.getInstance().getString(SPUtils.SP_USER_GUIDEMACROENTITYLIST), new TypeToken<List<GuideMacroEntityList>>() {
        });
    }

    public void setGuideMacroEntityList(List<GuideMacroEntityList> guideMacroEntityList) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEMACROENTITYLIST, GsonUtil.convertVO2String(guideMacroEntityList));
    }

    public List<GuideMacroEntityList> getServiceMacroEntityList() {
        return GsonUtil.convertString2Collection(SPUtils.getInstance().getString(SPUtils.SP_USER_SERVICEMACROENTITYLIST), new TypeToken<List<GuideMacroEntityList>>() {
        });
    }

    public void setServiceMacroEntityList(List<GuideMacroEntityList> guideMacroEntityList) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_SERVICEMACROENTITYLIST, GsonUtil.convertVO2String(guideMacroEntityList));
    }

    public String getLanguage() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_LANGUAGE);
    }

    public void setLanguage(String language) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_LANGUAGE,language);
    }

    public String getBirthTime() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_BIRTHTIME);
    }

    public void setBirthTime(String birthTime) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_BIRTHTIME,birthTime);
    }

    public String getGuideAge() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_GUIDEAGE);
    }

    public void setGuideAge(String guideAge) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEAGE,guideAge);
    }

    public String getGuideViable() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_GUIDEVIABLE);
    }

    public void setGuideViable(String guideViable) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_GUIDEVIABLE,guideViable);
    }

    public String getPassword() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_PASSWORD);
    }

    public void setPassword(String password) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_PASSWORD,password);
    }

    public String getCustomSign() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_CUSTOMSIGN);
    }

    public void setCustomSign(String customSign) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_CUSTOMSIGN,customSign);
    }

    public String getName() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_NAME);
    }

    public void setName(String name) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_NAME,name);
    }

    public String getStartTime() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_STARTTIME);
    }

    public void setStartTime(String startTime) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_STARTTIME,startTime);
    }

    public String getServiceCounts() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_SERVICECOUNTS);
    }

    public void setServiceCounts(String serviceCounts) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_SERVICECOUNTS,serviceCounts);
    }

    public void setServiceIden(int serviceIden){
        SPUtils.getInstance().putInt(SPUtils.SP_USER_SERVICE_IDENTIFICATION,serviceIden);
    }

    public int getServiceIden(){
        return SPUtils.getInstance().getInt(SPUtils.SP_USER_SERVICE_IDENTIFICATION,0);
    }

    public void setServiceLangages(List<String> langages){
        SPUtils.getInstance().putString(SPUtils.SP_USER_SERVICE_LANGAGES, GsonUtil.convertVO2String(langages));
    }

    public List<String> getServiceLangages(){
        return GsonUtil.convertString2Collection(SPUtils.getInstance().getString(SPUtils.SP_USER_SERVICE_LANGAGES), new TypeToken<List<String>>() {
        });
    }
    public void setServiceCitys(List<GetServiceCityModel> datas){
        SPUtils.getInstance().putString(SPUtils.SP_USER_SERVICE_CITYS, GsonUtil.convertVO2String(datas));
    }

    public List<GetServiceCityModel> getServiceCitys(){
        return GsonUtil.convertString2Collection(SPUtils.getInstance().getString(SPUtils.SP_USER_SERVICE_CITYS), new TypeToken<List<GetServiceCityModel>>() {
        });
    }

    public void loginOff() {
        SPUtils.getInstance().logoff();
        JpushAliasUtil.cancleTagAndAlias();

        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                LogUtil.e("im 退出成功");
                MySelfInfo.getInstance().setImLogin(false);
            }

            @Override
            public void onError(int i, String s) {
                LogUtil.e("im 退出失败 logout error" + i + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

}
