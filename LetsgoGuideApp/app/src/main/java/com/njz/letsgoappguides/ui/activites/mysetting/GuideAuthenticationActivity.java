package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.MineEditView;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.GuideValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.presenter.mine.GuideAuthenContract;
import com.njz.letsgoappguides.presenter.mine.GuideAuthenPresenter;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenContract;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenPresenter;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.other.DateViewUtils;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;
import com.wildma.idcardcamera.camera.CameraActivity;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 导游资格认证
 */
public class GuideAuthenticationActivity extends BaseActivity implements View.OnClickListener, QueryAuthenContract.View,UpLoadContract.View,GuideAuthenContract.View {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.guideauth_ivimg)
    ImageView guideauthIvimg;
    @BindView(R.id.ll_auth_id)
    LinearLayout llAuthId;
    @BindView(R.id.auth_include_nopass)
    RelativeLayout authIncludeNopass;
    @BindView(R.id.tv_text_reason)
    TextView tvTextReason;
    @BindView(R.id.auth_include_passing)
    RelativeLayout authIncludePassing;
    @BindView(R.id.met_guideauth_id)
    MineEditView metGuideauth;
    @BindView(R.id.met_guideauth_workdate)
    MineEditView metGuideauthWorkdate;
    @BindView(R.id.met_guideauth_startdate)
    MineEditView metGuideauthStartdate;
    @BindView(R.id.met_guideauth_enddate)
    MineEditView metGuideauthEnddate;
    @BindView(R.id.bu_auth_to)
    TextView buAutTo;
    @BindView(R.id.reset_button)
    Button resetButton;

    private TackPicturesUtil tackPicUtil;

    QueryAuthenPresenter mQueryAuthenPresenter;
    GuideAuthenPresenter mGuideAuthenPresenter;
    UpLoadPresenter mUpLoadPresenter;
    private String headpath;// 头像地址
    private String headCompressPath;
    private Disposable disposable;
    LoadingDialog loadingDialog;
    String url="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication_guide;
    }

    @Override
    protected void initView() {

        titleTv.setText("导游资格认证");
        metGuideauth.setKeyListeners(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                String dataID = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890-";
                char[] data = dataID.toCharArray();
                return data;
            }
        });

        tackPicUtil=new TackPicturesUtil(this);
        getPicPermission(context);
        mQueryAuthenPresenter = new QueryAuthenPresenter(this, context);
        mGuideAuthenPresenter = new GuideAuthenPresenter(this, context);
        mUpLoadPresenter = new UpLoadPresenter(context,this);
        initLayout();
        loadingDialog = new LoadingDialog(context);
        buAutTo.setOnClickListener(this);
    }

    public void initLayout() {//{"未认证","认证失败",,"认证中","认证成功"};
        metGuideauthWorkdate.setFocusables(false, new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                DateViewUtils.initDate(metGuideauthWorkdate,context);
            }
        });
        metGuideauthStartdate.setFocusables(false, new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                DateViewUtils.initDate(metGuideauthStartdate,context);
            }
        });
        metGuideauthEnddate.setFocusables(false,new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                DateViewUtils.initDate(metGuideauthEnddate,context);
            }
        });
        if (MySelfInfo.getInstance().isLogin()) {
            if (""!=MySelfInfo.getInstance().getCardViable() ){
                int guideViable=Integer.parseInt(MySelfInfo.getInstance().getGuideViable());
//                int guideViable=-1;
                switch (guideViable) {
                    case 0:
                        titleTv.setText("导游资格认证-认证失败");
                        llAuthId.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.VISIBLE);
                        resetButton.setOnClickListener(this);
                        guideauthIvimg.setOnClickListener(this);
                        tvTextReason.setText(MySelfInfo.getInstance().getGuideViableNoPassCause());
                        break;
                    case -1:
                        titleTv.setText("导游资格认证");
                        llAuthId.setVisibility(View.VISIBLE);
                        guideauthIvimg.setOnClickListener(this);
                        break;
                    case 1:
                        titleTv.setText("导游资格认证-正在审核中");
                        llAuthId.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        titleTv.setText("导游资格认证信息修改");
                        llAuthId.setVisibility(View.VISIBLE);
                        mQueryAuthenPresenter.queryAuthen();
                        buAutTo.setVisibility(View.VISIBLE);
//                        metGuideauth.setEnableds(false);
//                        metGuideauthWorkdate.setEnableds(false);//不可点击
//                        metGuideauthStartdate.setEnableds(false);
//                        metGuideauthEnddate.setEnableds(false);
                        guideauthIvimg.setOnClickListener(this);
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
            case R.id.guideauth_ivimg:
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_BACK);
//                CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_BACK);
                break;
            case R.id.bu_auth_to:
                toauth();
                break;
        }
    }

    public void toauth(){
        if (metGuideauth.getContent() .equals("")) {
            showShortToast("请输入导游证号");
        } else if (metGuideauth.getContent() .length()<6||metGuideauth.getContent() .length()>20) {
            showShortToast("请输入6-20位的导游证号");
        } else if (metGuideauthWorkdate.getContent() .equals("")) {
            showShortToast("请选择从业时间");
        } else if(StringUtils.getTimeCompareSize(metGuideauthWorkdate.getContent())!=3){
            showShortToast("从业时间不能大于当前时间");
        }else if (metGuideauthStartdate.getContent() .equals("")) {
            showShortToast("请选择证件生效时间");
        } else if(StringUtils.getTimeCompareSize(metGuideauthStartdate.getContent())!=3){
            showShortToast("证件生效时间不能大于当前时间");
        }else if (metGuideauthEnddate.getContent() .equals("")) {
            showShortToast("请选择证件失效时间");
        } else if(StringUtils.getTimeCompareSize(metGuideauthEnddate.getContent())==3){
            showShortToast("证件失效时间不能小于当前时间");
        }else if (url .equals(""))  {
            showShortToast("请上传导游证照片");
        } else {
            GuideValidInfo mGuideValidInfo=new GuideValidInfo();
            mGuideValidInfo.setGuideCardNum(metGuideauth.getContent());
            mGuideValidInfo.setStartTime(metGuideauthWorkdate.getContent());
            mGuideValidInfo.setGuideValidStartTime(metGuideauthStartdate.getContent());
            mGuideValidInfo.setGuideValidEndTime(metGuideauthEnddate.getContent());
            mGuideValidInfo.setGuidePhoto(url);
            mGuideValidInfo.setType(1);
            mGuideAuthenPresenter.toAuthInfo(mGuideValidInfo);
        }
    }

    @Override
    public void queryAuthenSuccess(AuthenInfo infos) {
        AuthenInfo mAuthenInfo=infos;
        if(mAuthenInfo!=null&&!mAuthenInfo.equals("")){
            metGuideauth.setContent(mAuthenInfo.getGuideCardNum());
            metGuideauthWorkdate.setContent(mAuthenInfo.getStartTime());
            metGuideauthStartdate.setContent(mAuthenInfo.getGuideValidStartTime());
            metGuideauthEnddate.setContent(mAuthenInfo.getGuideValidEndTime());
            GlideUtil.LoadImage(context, mAuthenInfo.getGuidePhoto(), guideauthIvimg);
            url=mAuthenInfo.getGuidePhoto();
        }
    }

    @Override
    public void queryAuthenFailed(String msg) {

    }

    @Override
    public void toAuthInfoSuccess(UserVo data) {
        showShortToast("提交成功");
        MySelfInfo.getInstance().setGuideViable(data.getGuideViable());
        finish();
    }

    @Override
    public void toAuthInfoFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void upUploadSuccess(String urls) {
        loadingDialog.dismiss();
        if(urls!=null){
            url=urls;
            GlideUtil.LoadImage(context, urls, guideauthIvimg);
        }
    }

    @Override
    public void upUploadFailed(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
                //获取图片路径，显示图片
                final String path = CameraActivity.getImagePath(data);
                if (!TextUtils.isEmpty(path)) {
                    headpath = path;
                    upFile(headpath);//上传图片
                }
            }
            switch (requestCode) {
                case TackPicturesUtil.CHOOSE_PIC:
                case TackPicturesUtil.TACK_PIC:
                case TackPicturesUtil.CROP_PIC:
                    String path = tackPicUtil.getPicture(requestCode, resultCode, data, false);
                    if (path == null)
                        return;
                    headpath = path;
                    upFile(headpath);//上传图片
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


