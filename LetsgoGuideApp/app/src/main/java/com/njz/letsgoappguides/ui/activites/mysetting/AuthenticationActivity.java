package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.MineEditView;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.ToAuthenInfo;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.presenter.mine.IdentityAuthenContract;
import com.njz.letsgoappguides.presenter.mine.IdentityAuthenPresenter;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenContract;
import com.njz.letsgoappguides.presenter.mine.QueryAuthenPresenter;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.presenter.setting.UpdataPsdPresenter;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;
import com.wildma.idcardcamera.camera.CameraActivity;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 实名认证
 */
public class AuthenticationActivity extends BaseActivity implements View.OnClickListener, QueryAuthenContract.View,UpLoadContract.View ,IdentityAuthenContract.View{


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.auth_id_img)
    ImageView authIdImg;
    @BindView(R.id.auth_iv_zheng)
    ImageView authIvZheng;
    @BindView(R.id.auth_iv_back)
    ImageView authIvBack;
    @BindView(R.id.ll_auth_id)
    LinearLayout llAuthId;
    @BindView(R.id.auth_include_nopass)
    RelativeLayout authIncludeNopass;
    @BindView(R.id.tv_text_reason)
    TextView tvTextReason;
    @BindView(R.id.auth_include_passing)
    RelativeLayout authIncludePassing;
    @BindView(R.id.met_auth_name)
    MineEditView metAuthName;
    @BindView(R.id.met_auth_card)
    MineEditView metAuthCard;
    @BindView(R.id.met_auth_sex)
    MineEditView metAuthSex;
    @BindView(R.id.met_auth_bir)
    MineEditView metAuthBir;
    @BindView(R.id.bu_auth_to)
    TextView buAutTo;
    @BindView(R.id.tv_tips)
    TextView tv_tips;
    @BindView(R.id.reset_button)
    Button resetButton;

    private TackPicturesUtil tackPicUtil;
    public int isCard=0;
    public static final int CARD = 10;
    public static final int CARD_POSTIV = 11;
    public static final int CARD_BACK = 12;

    QueryAuthenPresenter mQueryAuthenPresenter;
    IdentityAuthenPresenter mIdentityAuthenPresenter;
    UpLoadPresenter mUpLoadPresenter;
    private String headpath;// 手持身份证
    private String headpath2;// 身份证正面
    private String headpath3;// 身份证背面

    private String url="";// 手持身份证
    private String url2="";// 身份证正面
    private String url3="";// 身份证背面
    private String headCompressPath;
    private Disposable disposable;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void initView() {
        titleTv.setText("实名认证");
        metAuthCard.setKeyListeners(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                String dataID = "xX1234567890";
                char[] data = dataID.toCharArray();
                return data;
            }
        });

        tackPicUtil = new TackPicturesUtil(this);
        loadingDialog = new LoadingDialog(context);
        getPicPermission(context);

        mQueryAuthenPresenter = new QueryAuthenPresenter(this, context);
        mIdentityAuthenPresenter=new IdentityAuthenPresenter(this,context);
        mUpLoadPresenter = new UpLoadPresenter(context,this);
        initLayout();

        metAuthCard.addTextChangedListeners(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!LoginUtil.verifyIdCard2(metAuthCard.getContent()))
                    return;
                if (metAuthCard.getContent().length()==18){
                    String cardNum=metAuthCard.getContent();
//                    String birthday = cardNum.substring(6, 14);
                    String year=cardNum.substring(6, 10);
                    String month=cardNum.substring(10, 12);
                    String dat=cardNum.substring(12, 14);
                    metAuthBir.setContent(year+"-"+month+"-"+dat);
                    String id17 = cardNum.substring(16, 17);
                    if (Integer.parseInt(id17) % 2 != 0) {
                        metAuthSex.setContent("男");
                    } else {
                        metAuthSex.setContent("女");
                    }
                }else{
                    metAuthSex.setContent("");
                    metAuthBir.setContent("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buAutTo.setOnClickListener(this);
    }

    public void initLayout() {//{"未认证","认证失败",,"认证中","认证成功"};
        metAuthSex.setFocusables(false);
        metAuthBir.setFocusables(false);
        if (MySelfInfo.getInstance().isLogin()) {
            if (""!=MySelfInfo.getInstance().getCardViable() ){
                int cardViable=Integer.parseInt(MySelfInfo.getInstance().getCardViable());
//                int cardViable=0;
                switch (cardViable) {
                    case 0:
                        titleTv.setText("实名认证-认证失败");
                        authIdImg.setOnClickListener(this);
                        authIvZheng.setOnClickListener(this);
                        authIvBack.setOnClickListener(this);
                        resetButton.setOnClickListener(this);
                        llAuthId.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.VISIBLE);
                        tvTextReason.setText(MySelfInfo.getInstance().getCardViableNoPassCause());
                        break;
                    case -1:
                        titleTv.setText("实名认证");
                        llAuthId.setVisibility(View.VISIBLE);
                        authIdImg.setOnClickListener(this);
                        authIvZheng.setOnClickListener(this);
                        authIvBack.setOnClickListener(this);
                        break;
                    case 1:
                        titleTv.setText("实名认证-正在审核中");
                        llAuthId.setVisibility(View.GONE);
                        authIncludeNopass.setVisibility(View.GONE);
                        authIncludePassing.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        titleTv.setText("实名认证信息修改");
                        mQueryAuthenPresenter.queryAuthen();
                        llAuthId.setVisibility(View.VISIBLE);
                        buAutTo.setVisibility(View.VISIBLE);
                        tv_tips.setText("");
//                        metAuthName.setEnableds(false);//不可点击
//                        metAuthCard.setEnableds(false);
                        authIdImg.setOnClickListener(this);//不可点击
                        authIvZheng.setOnClickListener(this);
                        authIvBack.setOnClickListener(this);
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
                llAuthId.setVisibility(View.VISIBLE);
                authIncludeNopass.setVisibility(View.GONE);
                mQueryAuthenPresenter.queryAuthen();
                break;
            case R.id.auth_id_img:
                isCard=CARD;
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_PERSON);
                break;
            case R.id.auth_iv_zheng:
                isCard=CARD_POSTIV;
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_FRONT);
                break;
            case R.id.auth_iv_back:
                isCard=CARD_BACK;
                tackPicUtil.showDialog(context,CameraActivity.TYPE_IDCARD_BACK);
                break;
            case R.id.bu_auth_to:
                toauth();
                break;
        }
    }

    public void toauth(){
        if (!LoginUtil.verifyNames(metAuthName.getContent())){
        }else if (!LoginUtil.verifyIdCard(metAuthCard.getContent())) {
        }else if(url.equals("")){
            showShortToast("请上传手持身份证照片");
        }else if(url2.equals("")){
            showShortToast("请上传身份证正面照片");
        }else if(url3.equals("")){
            showShortToast("请上传身份证背面照片");
        }else{
            ToAuthenInfo data = new ToAuthenInfo();
            data.setName(metAuthName.getContent());
            data.setIdCardNum(metAuthCard.getContent());
            data.setGender(metAuthSex.getContent().equals("女")?2:1);
            data.setBirthTime(metAuthBir.getContent());
            data.setCardPhoto(url);
            data.setCardPhotoAbove(url2);
            data.setCardPhotoBelow(url3);
            data.setType(0);
            mIdentityAuthenPresenter.toAuthInfo(data);
        }
    }

    @Override
    public void queryAuthenSuccess(AuthenInfo infos) {
        AuthenInfo mAuthenInfo=infos;
        if(mAuthenInfo!=null&&!mAuthenInfo.equals("")){
            metAuthName.setContent(mAuthenInfo.getName());
            metAuthCard.setContent(mAuthenInfo.getIdCardNum());
            metAuthSex.setContent(mAuthenInfo.getGender()== 2?"女":"男");
            metAuthBir.setContent(mAuthenInfo.getBirthTime());
            GlideUtil.LoadImage(context, mAuthenInfo.getCardPhoto(), authIdImg);
            GlideUtil.LoadImage(context, mAuthenInfo.getCardPhotoAbove(), authIvZheng);
            GlideUtil.LoadImage(context, mAuthenInfo.getCardPhotoBelow(), authIvBack);
            url=mAuthenInfo.getCardPhoto();
            url2=mAuthenInfo.getCardPhotoAbove();
            url3=mAuthenInfo.getCardPhotoBelow();
        }
    }

    @Override
    public void queryAuthenFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void toAuthInfoSuccess(UserVo str) {
        showShortToast("提交成功");
        MySelfInfo.getInstance().setCardViable(str.getCardViable());
        MySelfInfo.getInstance().setName(str.getName());
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
            GlideUtil.LoadImage(context, urls, authIdImg);
        }else if (isCard==CARD_POSTIV){
            url2=urls;
            GlideUtil.LoadImage(context, urls, authIvZheng);
        }else if(isCard==CARD_BACK){
            url3=urls;
            GlideUtil.LoadImage(context, urls, authIvBack);
        }
    }

    @Override
    public void upUploadFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拍照照片返回
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
                }else if(isCard==CARD_BACK) {
                    headpath3 = path;
                    upFile(headpath3);//上传图片
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
                }else if(isCard==CARD_BACK){
                    headpath3 = path;
                    upFile(headpath3);//上传图片
                }
                break;
            default:
                break;
        }
    }

    //拍照，存储权限
    public void getPicPermission(Context context) {
        tackPicUtil.checkPermission(context);
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

}


