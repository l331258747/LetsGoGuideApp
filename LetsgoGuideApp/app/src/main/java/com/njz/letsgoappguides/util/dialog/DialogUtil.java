package com.njz.letsgoappguides.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.ui.activites.LoginActivity;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.log.LogUtil;


public class DialogUtil {

    private static DialogUtil instance = null;

    public static DialogUtil getInstance() {
        if (instance == null) {
            instance = new DialogUtil();
        }
        return instance;
    }

    private DialogUtil() {
    }


    public AlertDialog getDefaultDialog(Context context, String title, String content, String positiveName, final DialogUtil.DialogCallBack callBack) {
        AlertDialog alterDialog = new AlertDialog.Builder(context)
                .setMessage(content)
                .setPositiveButton(positiveName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBack == null){
                            dialog.cancel();
                        }else{
                            callBack.exectEvent(dialog);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

        return alterDialog;
    }

    public AlertDialog getDefaultDialog(Context context, String content) {
        return this.getDefaultDialog(context,"提示",content,"确定",null);
    }

    public AlertDialog getDefaultDialog(Context context, String content,final DialogUtil.DialogCallBack callBack) {
        return this.getDefaultDialog(context,"提示",content,"确定",callBack);
    }

    public AlertDialog getDefaultDialog(Context context, String content,String positiveName,final DialogUtil.DialogCallBack callBack) {
        return this.getDefaultDialog(context,"提示",content,positiveName,callBack);
    }
    public AlertDialog getEditDialog(Context context ,final DialogUtil.DialogEditCallBack callBack) {

        View dialogView = View.inflate(context, R.layout.dialog_edit, null);
        final EditText et = dialogView.findViewById(R.id.et_input);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        et.setMaxLines(1);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et.setHint("请输入4个字以内的标签");
        AlertDialog alterDialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBack == null){
                            dialog.cancel();
                        }else{
                            callBack.exectEvent(dialog,et.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

        return alterDialog;
    }

    public AlertDialog getEditDialog(Context context ,final DialogUtil.DialogEditCallBack callBack,int maxLength,String content) {

        View dialogView = View.inflate(context, R.layout.dialog_edit, null);
        final EditText et = dialogView.findViewById(R.id.et_input);
        et.setText(content);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        et.setMaxLines(1);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        AlertDialog alterDialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBack == null){
                            dialog.cancel();
                        }else{
                            callBack.exectEvent(dialog,et.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

        return alterDialog;
    }

    public interface DialogCallBack {
        void exectEvent(DialogInterface alterDialog);
    }

    public interface DialogEditCallBack {
        void exectEvent(DialogInterface alterDialog,String str);
    }

    //联系客服对话框
    public void showCustomerMobileDialog(final Context context){
        showMobileDialog(context,"400-1177-169","暂无客服联系方式");

    }

    public void showGuideMobileDialog(final Context context,final String mobil){
        showMobileDialog(context,mobil,"暂无游客联系方式");
    }
    public void showGuideMobileDialog(final Context context,final String mobil,
                                      int orderId,int serveId,int guideId){
        showMobileDialog(context,mobil,"暂无游客联系方式");

        if(!TextUtils.isEmpty(mobil)){
            ResponseCallback listener = new ResponseCallback<String>() {
                @Override
                public void onSuccess(String datas) {
                    LogUtil.e(datas);
                }

                @Override
                public void onFault(String errorMsg) {
                    LogUtil.e(errorMsg);
                }
            };
            MethodApi.wiretapping(orderId, serveId,guideId,new ProgressSubscriber(listener, null, false));
        }
    }

    public void showMobileDialog(final Context context,final String mobile,String error){
        if(TextUtils.isEmpty(mobile)){
            ToastUtil.showShortToast(context,error);
            return;
        }
        DialogUtil.getInstance().getDefaultDialog(context, "提示", mobile, "呼叫", new DialogUtil.DialogCallBack() {
            @Override
            public void exectEvent(DialogInterface alterDialog) {
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                context.startActivity(dialIntent);
                alterDialog.dismiss();
            }
        }).show();
    }

}
