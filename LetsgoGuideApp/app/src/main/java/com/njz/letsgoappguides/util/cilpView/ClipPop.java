package com.njz.letsgoappguides.util.cilpView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.njz.letsgoappguides.BuildConfig;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.other.ClipImageActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.io.File;

/**
 * Created by LGQ
 * Time: 2019/3/19
 * Function:
 */

public class ClipPop extends PopupWindow {
    /**
     * IMAGE_CACHE_PATH 图片缓存目录
     */
    public static final String IMAGE_CACHE_PATH = com.njz.letsgoappguides.util.FileUtil.getFolder(Constant.IMAGE_PATH).getAbsolutePath();

    //请求相机
    public static final int REQUEST_CAPTURE = 130;
    //请求相册
    public static final int REQUEST_PICK = 131;
    //请求截图
    public static final int REQUEST_CROP_PHOTO = 132;
    private File tempFile;

    PopupWindow popupWindow;
    WindowManager.LayoutParams params;
    Activity activity;
    Context context;

    public ClipPop(Context context) {
        super(context);
        this.activity = (Activity) context;
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);

        //popupWindow在弹窗的时候背景半透明
        params = ((Activity)context).getWindow().getAttributes();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                activity.getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCamera();
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPhoto();
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void showPop(View parent){
        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        params.alpha = 0.5f;
        activity.getWindow().setAttributes(params);
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    public File getTempFile(){
        return tempFile;
    }
    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
//        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        tempFile = com.njz.letsgoappguides.util.FileUtil.createDownloadFile(IMAGE_CACHE_PATH + System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(activity, activity.getApplicationInfo().packageName + ".provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        activity.startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri,int type) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(activity, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        activity.startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(activity, ClipImageActivity.class);
        intent.setData(uri);
        activity.startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
