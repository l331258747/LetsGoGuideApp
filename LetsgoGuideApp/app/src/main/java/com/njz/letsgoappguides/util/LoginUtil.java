package com.njz.letsgoappguides.util;

import android.text.TextUtils;
import android.util.Log;

import com.njz.letsgoappguides.model.login.GuideMacroEntityList;

import java.util.List;
import java.util.regex.Pattern;

/**
 *
 */

public class LoginUtil {

    public static boolean verifyPhone(String phone) {
        if (phone.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入电话号码");
        } else if (!StringUtils.isMobileNO(phone)) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的手机号码");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyPhoneGuides(String PhoneGuides) {
        if (PhoneGuides.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入手机号码/导游证");
        } else if (!StringUtils.isMobileGuides(PhoneGuides)) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的手机号码/导游证");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyPassword(String password,String msg1,String msg2) {
        if (password.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), msg1);
        } else if (password.length() < 6) {
            ToastUtil.showShortToast(AppUtils.getContext(), msg2);
        }else if(!StringUtils.isPassword(password)){
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入6-12位的字母、数字或英文字符");
        }else {
            return true;
        }
        return false;
    }


    public static boolean verifyPasswordDouble(String password,String newPassword) {
        if(!TextUtils.equals(password,newPassword)){
            ToastUtil.showShortToast(AppUtils.getContext(), "两次输入的新密码不一致");
        }else{
            return true;
        }
        return false;
    }

    public static boolean verifyVerify(String verify) {
        if (verify.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入验证码");
        } else if (verify.length() != 6) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入6位验证码");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyName(String name) {
        if (name.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入联系人");
        } else if (name.length() < 2) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的联系人");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyIdCard(String idCardNum) {
        if (idCardNum.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入身份证号");
        }
        else if (idCardNum.length()!=18) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入18位身份证号");
        }
        else {
            return true;
        }
        return false;
    }

    public static boolean verifyIdCard2(String idCardNum) {
        if (idCardNum.equals("")) {
//            ToastUtil.showShortToast(AppUtils.getContext(), "请输入身份证号");
        }else {
            return true;
        }
        return false;
    }

    public static boolean verifyNames(String name) {
        if (name.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入姓名");
        } else if(name.length()<2){
            ToastUtil.showShortToast(AppUtils.getContext(), "姓名不能少于两位");
        }else if (StringUtils.isName(name)) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入真实姓名");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyLanguages(List<GuideMacroEntityList> data) {
        if (data==null)return true;
        if(data.size()<1){
            ToastUtil.showShortToast(AppUtils.getContext(), "至少选择一门服务语言");
            return false;
        } else{
            return true;
        }
    }

    public static boolean verifyBinkNames(String name) {
        if(name.equals(""))return true;
        if (!name.equals("")) {
            if(name.length()<2){
                ToastUtil.showShortToast(AppUtils.getContext(), "开户支行名称不能少于两位");
            }else if (StringUtils.isName(name)) {
                ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的开户支行名称");
            } else {
                return true;
            }
        }
        return false;
    }
}
