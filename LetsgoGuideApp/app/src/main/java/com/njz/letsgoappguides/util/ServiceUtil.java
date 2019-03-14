package com.njz.letsgoappguides.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.NjzGuideServeFormatDtosBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/14.
 */

public class ServiceUtil {

    public static boolean getPriceCheck(String price, Context contexts) {
        String context="";
        String contest1="";
        String contest2="";
        if(price.contains(".")){
            context=price.substring(price.indexOf(".")+1,price.length());
            contest2=price.substring(price.indexOf("."),price.length());
            contest1=price.substring(0,price.indexOf("."));
        }
        if(price.equals("")){
            Toast.makeText(contexts,"请输入价格",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(price.equals("0")){
            Toast.makeText(contexts,"价格不能为0",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!price.contains(".")&&price.length()>6){
            Toast.makeText(contexts,"价格不能大于六位",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(context.length()>2){
            Toast.makeText(contexts,"价格只能输入两位小数点",Toast.LENGTH_SHORT).show();
            return false;
        }else if(contest1.length()>6){
            Toast.makeText(contexts,"价格整数不能大于六位",Toast.LENGTH_SHORT).show();
            return false;
        }else if(contest2.length()==1){
            Toast.makeText(contexts,"价格格式不正确",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public static boolean getEndPrice(String price, Context contexts) {
        String context="";
        String contest1="";
        String contest2="";
        if(price.contains(".")){
            context=price.substring(price.indexOf(".")+1,price.length());
            contest2=price.substring(price.indexOf("."),price.length());
            contest1=price.substring(0,price.indexOf("."));
        }
        if(price.equals("")){
            Toast.makeText(contexts,"请输入最终报价",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(price.equals("0")){
            Toast.makeText(contexts,"最终报价不能为0",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!price.contains(".")&&price.length()>6){
            Toast.makeText(contexts,"最终报价不能大于六位",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(context.length()>2){
            Toast.makeText(contexts,"最终报价只能输入两位小数点",Toast.LENGTH_SHORT).show();
            return false;
        }else if(contest1.length()>6){
            Toast.makeText(contexts,"最终报价整数不能大于六位",Toast.LENGTH_SHORT).show();
            return false;
        }else if(contest2.length()==1){
            Toast.makeText(contexts,"最终报价格式不正确",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    //违约金参数设置
    public static void strSplitNum(String numStr, TextView text1, EditText text2, EditText text3){
        if(TextUtils.isEmpty(numStr))return;
        String[] threes=numStr.split(",");
        if(threes.length>=2) {
            text1.setText(threes[0]);
            text2.setText(threes[1]);
        }
        if(threes.length == 3){
            text3.setText(threes[2]);
        }else{
            text3.setText("0");
        }
    }

    public static float getMin(List<NjzGuideServeFormatDtosBean> in, String dic){
        float minprice=0;
        List<NjzGuideServeFormatDtosBean> newin=new ArrayList<>();
        newin.clear();
        for (int i=0;i<in.size();i++){
            if(in.get(i).getNjzGuideServeFormatDic().equals(dic)){
                newin.add(in.get(i));
            }
        }
        for(int j=0;j<newin.size();j++){
            minprice=newin.get(0).getServeDefaultPrice();
            if(newin.get(j).getServeDefaultPrice()<minprice){
                minprice=newin.get(j).getServeDefaultPrice();
            }
        }
        return minprice;
    }

    //得到服务最低价格
    public static float getMinPrices(List<NjzGuideServeFormatDtosBean> serveFormatList, int serverTypeId ){
        float minprice=0;
//        for (int i=0;i<serveFormatList.size();i++){
            switch (serverTypeId){
                case 1://向导陪游
                    minprice=getMin(serveFormatList,"xdpy_yy");
                    break;
                case 2://包车接送
                    minprice=getMin(serveFormatList,"jsjz_cx");
                    break;
                case 3://私人订制
//                                    getMinPrice(in,i,"srdz_");
                    break;
                case 4://特色体验
                    minprice=getMin(serveFormatList,"tsty_tc");
                    break;
                case 5://代订酒店
                    minprice=getMin(serveFormatList,"ddjd_tc");
                    break;
                case 6://代订门票
                    minprice=getMin(serveFormatList,"ddmp_tc");
                    break;
//            }
        }
        return minprice;
    }
}
