package com.njz.letsgoappguides.util;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2018/11/2.
 * String 工具类
 */

public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNullorEmpty(String str) {
        return (null == str || "".equals(str)) ? true : false;
    }

    public static String hidePhone(String str){
        if(TextUtils.isEmpty(str)) return "";
        return str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 得到字符串值
     *
     * @param object
     * @return
     */
    public static String getValue(Object object) {
        return object == null ? "" : object.toString();
    }


    /**
     * 判断是否是手机号码标准格式
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if(isNullorEmpty(mobiles)) return false;

        Pattern p = Pattern
                .compile("^((13[0-9])|(14[5-9])|(15[0-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[1,8,9]))\\d{8}$");
        Matcher m = p.matcher(mobiles.trim());
        return m.matches();
    }

    /**
     * 判断是否是手机号码或导游证：6-20位字母、数字或横杠
     *
     * @param MobileGuides
     * @return
     */
    public static boolean isMobileGuides(String MobileGuides) {
        if(isNullorEmpty(MobileGuides)) return false;

        Pattern p = Pattern
                .compile("^[A-Za-z0-9\\-]{6,20}$");
        Matcher m = p.matcher(MobileGuides.trim());
        return m.matches();
    }

    /**
     * 判断密码格式：6-12位的字母、数字或英文字符
     *
     * @param psd
     * @return
     */
    public static boolean isPassword(String psd) {
        if(isNullorEmpty(psd)) return false;

        Pattern p = Pattern
                .compile("^[A-Za-z0-9\\-]{6,12}$");
        Matcher m = p.matcher(psd.trim());
        return m.matches();
    }

    /**
     * 判断是否为中文
     *
     * @param name
     * @return
     */
    public static boolean isName(String name) {
        if(isNullorEmpty(name)) return false;

        Pattern p = Pattern
                .compile("^[u4e00-u9fa5]+$");
        Matcher m = p.matcher(name.trim());
        return m.matches();
    }


    /**
     * 判断是否是邮箱地址标准格式
     *
     * @param email
     * @return
     */
    public static boolean isEmailNO(String email) {

        String reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    //为银行卡添加空格
    public static String modifyBankCard(String cardNo) {
        if (cardNo == null)
            return "";
        return cardNo.replaceAll("([\\d]{4})", "$1 ");
    }

    /**
     * 判断是否是身份证标准格式
     *
     * @param idcard
     * @return
     */
    public static boolean isIdCard(String idcard) {
        String reg = "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9X])$)";
//        String reg = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(idcard);
        return m.matches();
    }

    public static boolean isTwCode(String taiWanCode) {
        String reg = "(\\d{10}\\[b|B]\\))";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(taiWanCode);
        return m.matches();
    }


    /**
     * 判断是否是港澳通行证
     *
     * @param str
     * @return
     */
    public static boolean isHKMacao(String str) {
        String reg = "([A-Z]\\d{8})";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是护照号吗
     */
    public static boolean isPassport(String passportNo) {
        String reg = "(P|p\\d{7})|(G|g\\d{8})";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(passportNo);

        return m.matches();
    }

    /**
     * 判断是否为字母
     */
    public static boolean isLatter(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将实体对象转化为Json字符串
     */
    public static String getJsonByObject(Object obj) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String submitJson = gson.toJson(obj);
        return submitJson;
    }

    /**
     * 判断是否是Json
     */
    public static boolean isJson(String jsonSource) {
        try {
            JsonElement jsonElement = new JsonParser().parse(jsonSource);
            if (jsonElement.isJsonObject()) {
                return true;
            } else {
                return false;
            }
        } catch (JsonParseException e) {
            return false;
        }
    }

    /**
     * 去除字Editable中的空格、回车、换行符、制表符
     */
    public static void replaceBlank(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            return;
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(editable);

        editable.replace(0, editable.length(), m.replaceAll(""));

    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断字符串是否合法搜索关键词（不包含空格、回车、换行符、制表符）
     */
    public static boolean isFilterString(String str) {
        if (str != null) {
            if (str.contains(" ")) return false;
            if (str.contains("\t")) return false;
            if (str.contains("\r")) return false;
            if (str.contains("\n")) return false;

            return true;
        }

        return true;
    }


    /**
     * 是否是有效的图片url
     */
    public static boolean isImageUriValid(String uri) {

        if (TextUtils.isEmpty(uri)) {
            return false;
        }

        String lowerCaseUrl = uri.toLowerCase();

        if (lowerCaseUrl.startsWith("http")) {
            return true;
        }

        if (lowerCaseUrl.startsWith("https")) {
            return true;
        }

        if (lowerCaseUrl.startsWith("content")) {
            return true;
        }

        if (lowerCaseUrl.startsWith("assets")) {
            return true;
        }

        if (lowerCaseUrl.startsWith("drawable")) {
            return true;
        }
        return false;
    }

    public static String subAddress(String formatAddress) {
        try {
            int index = formatAddress.indexOf("区");
            if (index == -1) {
                index = formatAddress.indexOf("县");
            }
            if (index == -1) {
                return formatAddress;
            }
            return formatAddress.substring(index + 1, formatAddress.length());
        } catch (Exception e) {

        }

        return formatAddress;
    }

    public static void setHtml(TextView tv,String content){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv.setText(Html.fromHtml(content));
        }
    }

    /**
     * String转换List
     *
     * @param listText
     *            :需要转换的文本
     * @return List<?>
     */
    public static ArrayList<String> stringToList(String listText) {
        if (listText == null || listText.equals("")) {
            return null;
        }
        listText = listText.substring(0);
        ArrayList<String> list = new ArrayList<String>();
        String[] text = listText.split(",");
        for (String str : text) {
            list.add(str);
        }
        return list;
    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式
     * @param startTime
     * @return
     */
    public static int getTimeCompareSize(String startTime){
        if(TextUtils.isEmpty(startTime))return 0;
        int i=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = new Date(System.currentTimeMillis());//结束时间
//            String time=getDateStr(date2,"yyyy-MM-dd");

            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                i= 1;
            }else if (date2.getTime()==date1.getTime()){
                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                i= 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  i;
    }

    public static String getDateStr(Date date,String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }



    /**
     * 中间4位使用*替换
     * @param phone
     * @return
     */
    public static String midleReplaceStar(String phone){
        String result=null;
        if (!TextUtils.isEmpty(phone)){
            if (phone.length()<7){
                result=phone;
            }else{
                String start = phone.substring(0,3);
                String end = phone.substring(phone.length()-4,phone.length());
                StringBuilder sb=new StringBuilder();
                sb.append(start).append("****").append(end);
                result=sb.toString();
            }
        }
        return result;
    }

    /**
     * 第二位使用*替换
     * @param name
     * @return
     */
    public static String nameReplaceStar(String name){
        String result=null;
        if (!TextUtils.isEmpty(name)){
            if (name.length()<2){
                result=name;
            }else{
                StringBuilder sb=new StringBuilder();
                String start = name.substring(0,1);
                String end = name.substring(1,name.length());
                sb.append(start);
                for (int i=0;i<end.length();i++){
                    sb.append("*");
                }
                result=sb.toString();
            }
        }
        return result;
    }



}
