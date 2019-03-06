package com.njz.letsgoappguides.ui.activites.mysetting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.MineEditView;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.authentication.DriveTypeInfo;
import com.njz.letsgoappguides.presenter.mine.CarAuthenContract;
import com.njz.letsgoappguides.presenter.mine.CarAuthenPresenter;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenContract;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenPresenter;
import com.njz.letsgoappguides.presenter.other.DriveTypeContract;
import com.njz.letsgoappguides.presenter.other.DriveTypePresenter;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.ui.activites.mine.BigImageActivity;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.accessory.PhotoAdapter;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.other.DateViewUtils;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;
import com.wildma.idcardcamera.camera.CameraActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


/**
 * 车导认证
 */
public class CarAuthenticationActivity extends BaseActivity implements View.OnClickListener, QueryAuthenContract.View,UpLoadContract.View,CarAuthenContract.View,DriveTypeContract.View {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.carauth_iv_driver)
    ImageView carauthIvDriver;
    @BindView(R.id.carauth_iv_driver2)
    ImageView carauthIvDriver2;
    @BindView(R.id.ll_auth_id)
    LinearLayout llAuthId;
    @BindView(R.id.auth_include_nopass)
    RelativeLayout authIncludeNopass;
    @BindView(R.id.auth_include_passing)
    RelativeLayout authIncludePassing;
    @BindView(R.id.tv_text_reason)
    TextView tvTextReason;
    @BindView(R.id.met_carauth_id)
    MineEditView metCarauthId;
    @BindView(R.id.met_carauth)
    MineEditView metCarauth;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.met_carauth_startdate)
    MineEditView metCarauthStartdate;
    @BindView(R.id.met_carauth_enddate)
    MineEditView metCarauthEnddate;
    @BindView(R.id.bu_auth_to)
    TextView buAutTo;
    @BindView(R.id.reset_button)
    Button resetButton;

    private TackPicturesUtil tackPicUtil;
    public int isCard=0;
    public static final int CARD = 10;
    public static final int CARD_POSTIV = 11;

    List<DriveTypeInfo> mDriveTypeInfo;
    List<String> list;

    QueryAuthenPresenter mQueryAuthenPresenter;
    CarAuthenPresenter mCarAuthenPresenter;
    UpLoadPresenter mUpLoadPresenter;
    DriveTypePresenter mDriveTypePresenter;
    private String headpath="";// 手持身份证
    private String headpath2="";// 身份证正面

    private String url="";// 驾驶证照片
    private String url2="";// 驾驶证副业照片
    private String headCompressPath;
    private Disposable disposable;
    LoadingDialog loadingDialog;

    List<String> listHeadPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication_car;
    }

    @Override
    protected void initView() {
        titleTv.setText("驾驶证认证");
        tackPicUtil = new TackPicturesUtil(this);
        loadingDialog = new LoadingDialog(context);
        getPicPermission(context);
        mUpLoadPresenter = new UpLoadPresenter(context,this);
        mQueryAuthenPresenter = new QueryAuthenPresenter(this, context);
        mCarAuthenPresenter = new CarAuthenPresenter(this, context);
        mDriveTypePresenter = new DriveTypePresenter(context,this);
        initLayout();
        buAutTo.setOnClickListener(this);
    }

    public void initLayout() {//{"未认证","认证失败",,"认证中","认证成功"};
        list=new ArrayList<>();
        mDriveTypeInfo=new ArrayList<DriveTypeInfo>() ;
        metCarauth.setFocusables(false, new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                AppUtils.HideKeyboard(metCarauth);
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(context,
                        new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                if(list.size()>0){
                                    metCarauth.setContent(list.get(options1));
                                    tvValue.setText(mDriveTypeInfo.get(options1).getId()+"");
                                }
                            }
                        })
                        .build();
                pvOptions.setPicker(list, null, null);
                pvOptions.show();
            }
        });
        metCarauthStartdate.setFocusables(false, new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                DateViewUtils.initDate(metCarauthStartdate,context);
            }
        });
        metCarauthEnddate.setFocusables(false,new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                DateViewUtils.initDate(metCarauthEnddate,context);
            }
        });
        if (MySelfInfo.getInstance().isLogin()) {
            if (""!=MySelfInfo.getInstance().getDriveViable() ){
                int driveViable=Integer.parseInt(MySelfInfo.getInstance().getDriveViable());
//                int driveViable=-1;
                switch (driveViable) {
                    case 0:
                        titleTv.setText("驾驶证认证-认证失败");
                        llAuthId.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.VISIBLE);
                        carauthIvDriver.setOnClickListener(this);
                        carauthIvDriver2.setOnClickListener(this);
                        resetButton.setOnClickListener(this);
                        mDriveTypePresenter.getDriveType();
                        tvTextReason.setText(MySelfInfo.getInstance().getDriveViableNoPassCause());
                        break;
                    case -1:
                        titleTv.setText("驾驶证认证");
                        llAuthId.setVisibility(View.VISIBLE);
                        carauthIvDriver.setOnClickListener(this);
                        carauthIvDriver2.setOnClickListener(this);
                        mDriveTypePresenter.getDriveType();
                        break;
                    case 1:
                        titleTv.setText("驾驶证认证-正在审核中");
                        llAuthId.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        titleTv.setText("驾驶证认证信息修改");
                        mDriveTypePresenter.getDriveType();
                        mQueryAuthenPresenter.queryAuthen();
                        llAuthId.setVisibility(View.VISIBLE);
                        buAutTo.setVisibility(View.VISIBLE);
//                        metCarauthId.setEnableds(false);//不可点击
//                        metCarauth.setEnableds(false);
//                        metCarauthStartdate.setEnableds(false);
//                        metCarauthEnddate.setEnableds(false);
                        carauthIvDriver.setOnClickListener(this);
                        carauthIvDriver2.setOnClickListener(this);
                        break;
                }
            }
        }
    }


    @OnClick(R.id.left_iv)
    public void leftBack() {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                mQueryAuthenPresenter.queryAuthen();
                llAuthId.setVisibility(View.VISIBLE);
                authIncludeNopass.setVisibility(View.GONE);
                break;
            case R.id.carauth_iv_driver:
                isCard=CARD;
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_BACK);
//                CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_BACK);
                break;
            case R.id.carauth_iv_driver2:
                isCard=CARD_POSTIV;
//                CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_FRONT);
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_FRONT);
                break;
            case R.id.bu_auth_to:
                toauth();
                break;
        }
    }

    public void toauth(){
        if(metCarauthId.getContent().equals("")){
            showShortToast("请输入档案编号");
        }else if(metCarauthId.getContent().length()!=12){
            showShortToast("请输入12位驾驶证档案证号");
        }else if(metCarauth.getContent().equals("")){
            showShortToast("请选择准驾车型");
        }else if(metCarauthStartdate.getContent().equals("")){
            showShortToast("请选择证件生效时间");
        } else if(StringUtils.getTimeCompareSize(metCarauthStartdate.getContent())!=3){
            showShortToast("证件生效时间不能大于当前时间");
        }else if(metCarauthEnddate.getContent().equals("")){
            showShortToast("请选择证件失效时间");
        }else if(StringUtils.getTimeCompareSize(metCarauthEnddate.getContent())==3){
            showShortToast("证件失效时间必须大于当前时间");
        }else if (url .equals("")) {
            showShortToast("请上传驾驶证");
        }else if (url2 .equals("")) {
            showShortToast("请上传驾驶证副页");
        }else{
            DriveValidInfo mDriveValidInfo=new DriveValidInfo();
            mDriveValidInfo.setDriveNumber(metCarauthId.getContent());
            mDriveValidInfo.setDriveType(Integer.parseInt(tvValue.getText().toString()));
            mDriveValidInfo.setDriveValidStartTime(metCarauthStartdate.getContent());
            mDriveValidInfo.setDriveValidEndTime(metCarauthEnddate.getContent());
            mDriveValidInfo.setDriveImg(url);
            mDriveValidInfo.setDriveImgBelow(url2);
            mDriveValidInfo.setType(2);
            mCarAuthenPresenter.toAuthInfo(mDriveValidInfo);
        }
    }

    @Override
    public void queryAuthenSuccess(AuthenInfo infos) {
        AuthenInfo mAuthenInfo=infos;
        if(mAuthenInfo!=null&&!mAuthenInfo.equals("")){
            metCarauthId.setContent(mAuthenInfo.getDriveNumber());
            metCarauth.setContent(mAuthenInfo.getDriveTypeName());
            metCarauthStartdate.setContent(mAuthenInfo.getGuideValidStartTime());
            metCarauthEnddate.setContent(mAuthenInfo.getGuideValidEndTime());
            GlideUtil.LoadImage(context, mAuthenInfo.getDriveImg(), carauthIvDriver);
            GlideUtil.LoadImage(context, mAuthenInfo.getDriveImgBelow(), carauthIvDriver2);
            url=mAuthenInfo.getDriveImg();
            url2=mAuthenInfo.getDriveImgBelow();
            tvValue.setText(""+mAuthenInfo.getDriveType());

//            listHeadPath=new ArrayList<>();
//            listHeadPath.add(mAuthenInfo.getDriveImg());
//            Log.e("test",listHeadPath.size()+"__________"+listHeadPath.toString()+"____________"+mAuthenInfo.getDriveImg());
//            carauthIvDriver.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    BigImageActivity.startActivity((Activity) context, 0, listHeadPath);
//                }
//            });
        }
    }

    @Override
    public void queryAuthenFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void toAuthInfoSuccess(UserVo data) {
        showShortToast("提交成功");
        MySelfInfo.getInstance().setDriveViable(data.getDriveViable());
        finish();
    }

    @Override
    public void toAuthInfoFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void upUploadSuccess(String urls) {
        loadingDialog.dismiss();
        if (isCard==CARD){
            url=urls;
            GlideUtil.LoadImage(context, urls, carauthIvDriver);
        }else if (isCard==CARD_POSTIV){
            url2=urls;
            GlideUtil.LoadImage(context, urls, carauthIvDriver2);
        }
    }

    @Override
    public void upUploadFailed(String msg) {
        loadingDialog.dismiss();
    }


    @Override
    public void getDriveTypeSuccess(List<DriveTypeInfo> lists) {
        if(lists!=null){
            mDriveTypeInfo=lists;
            for (int i=0;i<lists.size();i++){
                String name=lists.get(i).getName();
                int id=lists.get(i).getId();
                list.add(name);
            }
        }
    }

    @Override
    public void getDriveTypeFailed(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            //获取图片路径，显示图片
            final String path = CameraActivity.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (isCard==CARD){
                    headpath = path;
                    upFile(headpath);//上传图片
                }else if (isCard==CARD_POSTIV){
                    headpath2 = path;
                    upFile(headpath2);//上传图片
                }
            }
        }
        switch (requestCode) {
            case TackPicturesUtil.CHOOSE_PIC:
            case TackPicturesUtil.TACK_PIC:
            case TackPicturesUtil.CROP_PIC:
                String path = tackPicUtil.getPicture(requestCode, resultCode, data, false);
                if (path == null)
                    return;
                if (isCard==CARD){
                    headpath = path;
                    upFile(headpath);//上传图片
                }else if (isCard==CARD_POSTIV){
                    headpath2 = path;
                    upFile(headpath2);//上传图片
                }
                break;
            default:
                break;
        }
    }

    private void compressImage(final String aheadpath) {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                File file = new File(aheadpath);
                String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
                ImageUtils.getImage(aheadpath, savePath);
                headCompressPath = savePath;
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    public void upFile(String aheadpath) {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhotos) throws Exception {
                sendHead();
                disposable.dispose();
            }
        });
        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage(aheadpath);
    }

    public void sendHead() {
        //构建要上传的文件
        File file = new File(headCompressPath);
        mUpLoadPresenter.upUpload(file);
    }

    //拍照，存储权限
    public void getPicPermission(Context context) {
        tackPicUtil.checkPermission(context);
    }
}


