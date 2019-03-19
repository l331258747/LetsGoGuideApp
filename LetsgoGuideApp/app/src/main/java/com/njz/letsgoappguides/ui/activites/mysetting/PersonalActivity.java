package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.other.MoreImgAdapter;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.editorView.CamaraRequestCode;
import com.njz.letsgoappguides.customview.widget.editorView.EditorLayout;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.mine.BatchUploadInfo;
import com.njz.letsgoappguides.model.mine.LabelItemModel;
import com.njz.letsgoappguides.model.mine.MyInfoData;
import com.njz.letsgoappguides.model.mine.PersonalInfo;
import com.njz.letsgoappguides.presenter.mine.GetLanguageContract;
import com.njz.letsgoappguides.presenter.mine.GetLanguagePresenter;
import com.njz.letsgoappguides.presenter.mine.ModifyInfoContract;
import com.njz.letsgoappguides.presenter.mine.ModifyInfoPresenter;
import com.njz.letsgoappguides.presenter.other.BatchUploadContract;
import com.njz.letsgoappguides.presenter.other.BatchUploadPresenter;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.GsonUtil;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.accessory.PhotoAdapter;
import com.njz.letsgoappguides.util.accessory.RecyclerItemClickListener;
import com.njz.letsgoappguides.util.cilpView.ClipPop;
import com.njz.letsgoappguides.util.cilpView.FileUtil;
import com.njz.letsgoappguides.util.dialog.LanguageDialog;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import jp.wasabeef.richeditor.RichEditor;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


/**
 * 个人资料
 */
public class
PersonalActivity extends BaseActivity implements View.OnClickListener, UpLoadContract.View, GetLanguageContract.View, BatchUploadContract.View, ModifyInfoContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    //    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.personal_iv_more)
    ImageView personalIvMore;
    @BindView(R.id.personal_tvCheck)
    TextView personalTvCheck;
    @BindView(R.id.rl_personal)
    RelativeLayout rlPersonal;
    @BindView(R.id.personal_iv_moreb)
    ImageView personalIvMoreb;
    @BindView(R.id.personal_tvCheckb)
    TextView personalTvCheckb;
    @BindView(R.id.rl_personal_language)
    RelativeLayout rlPersonalLanguage;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    //    @BindView(R.id.personal_et_qianm)
    EditText personalEtQianm;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mPhotoRecyclerView;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.tit_story)
    TextView titStory;
    @BindView(R.id.ll_title_img_tip)
    LinearLayout ll_title_img_tip;

    LoadingDialog loadingDialog;
    private String headpath;// 头像地址
    private String headCompressPath;
    private Disposable disposable;
    String upUrls;
    String headUrl = "";
    String image;


    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> upLoadPhotos = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    UpLoadPresenter mPresenter;
    GetLanguagePresenter languagePresenter;
    BatchUploadPresenter mBatchUploadPresenter;
    ModifyInfoPresenter mModifyInfoPresenter;

    LanguageDialog dialog;
    List<GuideMacroEntityList> data;
    boolean isEnabled = false;

    String myStory;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        titleTv.setText("编辑资料");

        personalEtQianm = $(R.id.personal_et_qianm);
        etName = $(R.id.et_name);

        rlPersonal.setOnClickListener(this);
        rlPersonalLanguage.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnSubmit.setEnabled(isEnabled);

        if (MySelfInfo.getInstance().isLogin()) {
            etName.setText(MySelfInfo.getInstance().getName());
            myStory = MySelfInfo.getInstance().getMyStory();
            if (myStory.toString().trim().equals("") || myStory.toString().trim().equals("<br>")) {
                titStory.setText("请点击编辑");
            } else {
                titStory.setText("已编辑");
            }
            personalEtQianm.setText(MySelfInfo.getInstance().getIntroduce());
            GlideUtil.LoadCircleImage(context, MySelfInfo.getInstance().getUserImg(), ivHead);
            image = MySelfInfo.getInstance().getImage();
            if (!image.equals("")) {
                selectedPhotos = StringUtils.stringToList(image);
            }
            personalTvCheckb.setText(getLanguage());
        }
        personalEtQianm.addTextChangedListener(textWatcher);

        initAddPhoto();

        loadingDialog = new LoadingDialog(context);

        mPresenter = new UpLoadPresenter(context, this);
        languagePresenter = new GetLanguagePresenter(context, this);
        mBatchUploadPresenter = new BatchUploadPresenter(context, this);
        mModifyInfoPresenter = new ModifyInfoPresenter(context, this);
    }


    @OnClick({R.id.left_iv, R.id.title_yulan, R.id.rl_story})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                if (isEnabled) {
                    new AlertDialog.Builder(context).setTitle("您确定要放弃此次编辑？").setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    finish();
                                }
                            }).setNegativeButton("取消", null).show();
                } else {
                    finish();
                }
                break;
            case R.id.title_yulan:
                PersonalInfo personalInfo = new PersonalInfo();
                if (upUrls != null) {
                    personalInfo.setImage(upUrls);
                } else {
                    personalInfo.setImage(MySelfInfo.getInstance().getImage());
                }
                if (!headUrl.equals("")) {
                    personalInfo.setBase64Img(headUrl);
                } else {
                    personalInfo.setBase64Img(MySelfInfo.getInstance().getUserImg());
                }
                personalInfo.setIntroduce(personalEtQianm.getText().toString());
                personalInfo.setLanguage(personalTvCheckb.getText().toString());
                personalInfo.setMyStory(myStory);
                personalInfo.setName(etName.getText().toString());
                Intent intent1 = new Intent(context, PresonalViewActivity.class);
                intent1.putExtra("PERSOBALINFO", personalInfo);
                intent1.putExtra("ISENABLED", isEnabled);
                startActivity(intent1);
                break;
            case R.id.rl_story:
                Intent intent = new Intent(context, PersonalStoryActivity.class);
                intent.putExtra(Constant.STORYINFO, myStory);
                startActivityForResult(intent, Constant.STORYID);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        personalTvCheck.setText(getLable());
    }

    private String getLable() {
        StringBuffer lables = new StringBuffer("");
        List<LabelItemModel> ss2 = MySelfInfo.getInstance().getLabels();
        for (LabelItemModel item : ss2) {
            lables.append(item.getName() + ",");
        }
        List<String> ss = GsonUtil.convertJson2Array(MySelfInfo.getInstance().getFreeLabels());
        for (String str : ss) {
            lables.append(str + ",");
        }
        String str = lables.toString();
        str = str.endsWith(",") ? str.substring(0, str.length() - 1) : str;
        return str;
    }

    private String getLanguage() {
        StringBuffer languages = new StringBuffer("");
        List<GuideMacroEntityList> ss2 = MySelfInfo.getInstance().getGuideMacroEntityList();
        for (GuideMacroEntityList item : ss2) {
            languages.append(item.getName() + ",");
        }
        String str = languages.toString();
        str = str.endsWith(",") ? str.substring(0, str.length() - 1) : str;
        return str;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                String introduce = personalEtQianm.getText().toString();
                MyInfoData infoData = new MyInfoData();
                if (dialog != null) {
                    data = dialog.getData();
                    infoData.setGuideMacroEntityList(data);
                }
                infoData.setName(etName.getText().toString());
                if (!LoginUtil.verifyNames(etName.getText().toString()))
                    return;
                if (!LoginUtil.verifyLanguages(data))
                    return;
                infoData.setBase64Img(headUrl);
                infoData.setIntroduce(introduce);
                infoData.setMyStory(myStory);
                if (upUrls != null) {
                    infoData.setImage(upUrls);
                }

                mModifyInfoPresenter.userUpdate(infoData);
                break;
            case R.id.rl_personal:
                startActivity(new Intent(context, LabelActivity.class));
                break;
            case R.id.rl_personal_language:
                languagePresenter.userGetLanguage();
                personalTvCheckb.addTextChangedListener(textWatcher2);
                break;
        }
    }


    int type  = 1;
    @OnClick(R.id.iv_head)
    public void onViewClicked() {
        type = 2;
        uploadHeadImage();
    }

    /**
     * 获取图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.STORYID: //返回的请求码111
                if (null == data) return;
                //我的故事信息
                if (data.getExtras().getString(Constant.STORYINFO) != null) {
                    myStory = data.getExtras().getString(Constant.STORYINFO);
                    if (myStory.trim().equals("") || myStory.equals("<br>")) {
                        titStory.setText("请点击编辑");
                    } else {
                        titStory.setText("已编辑");
                    }
                    if (!myStory.equals(MySelfInfo.getInstance().getMyStory())) {
                        updateBack();
                    }
                }
                break;
        }
        switch (requestCode) {
            case ClipPop.REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    clipPop.gotoClipActivity(Uri.fromFile(clipPop.getTempFile()),type);
                }
                break;
            case ClipPop.REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    clipPop.gotoClipActivity(uri,type);
                }
                break;
            case ClipPop.REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String path = FileUtil.getRealFilePathFromUri(getApplicationContext(), uri);
                    if (path == null)
                        return;

                    if (type == 1) {
                        selectedPhotos.add(path);

                        upUrls = "";
                        int a = 0;
                        for (int i = 0; i < selectedPhotos.size(); i++) {
                            if (selectedPhotos.get(i).startsWith("http")) {
                                upUrls += selectedPhotos.get(i).toString() + ",";
                            } else {
                                a++;
                                if (a == 1) {
                                    updateBack();
                                    upFile2();
                                }
                            }
                        }
                        if (!image.equals(upUrls)) {
                            updateBack();
                        }
                        initAddPhoto();
                    }else if(type == 2){
                        headpath = path;
                        upFile();
                        updateBack();
                    }
                }
                break;
        }
    }

    //-----------start 上传头像-----------

    public void upFile() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhotos) throws Exception {
                sendHead();
                disposable.dispose();
            }
        });

        loadingDialog.showDialog("上传头像...");
        loadingDialog.setCancelable(false);
        compressImage();
    }

    public void sendHead() {
        //构建要上传的文件
        File file = new File(headCompressPath);
        mPresenter.upUpload(file);

    }

    private void compressImage() {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                File file = new File(headpath);
                String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
                ImageUtils.getImage(headpath, savePath);
                headCompressPath = savePath;
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    private void setHeadImg(String path) {
        GlideUtil.LoadCircleImage(context, path, ivHead);
    }

    @Override
    public void upUploadSuccess(String datas) {
        loadingDialog.dismiss();
        headUrl = datas;
        showShortToast("头像上传成功");
        setHeadImg(headpath);
    }

    @Override
    public void upUploadFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }
    //-------------end 上传头像-------------

    //------------上传多张图片  start---------------
    MoreImgAdapter moreImgAdapter;
    ClipPop clipPop;

    private void initAddPhoto() {
        if (selectedPhotos.size() == 0) {
            ll_title_img_tip.setVisibility(View.VISIBLE);
        } else {
            ll_title_img_tip.setVisibility(View.GONE);
        }
        //------------附件
        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false);
        mPhotoRecyclerView.setLayoutManager(linearLayoutManager);
        moreImgAdapter = new MoreImgAdapter(context,selectedPhotos);
        mPhotoRecyclerView.setAdapter(moreImgAdapter);
        mPhotoRecyclerView.setNestedScrollingEnabled(false);

        moreImgAdapter.onClickLisenter(new MoreImgAdapter.OnClickListener() {
            @Override
            public void onClick() {
                type = 1;
                uploadHeadImage();
            }

            @Override
            public void onDelect(int position) {
                selectedPhotos.remove(position);
                initAddPhoto();
                updateBack();
            }
        });
    }

    private void uploadHeadImage() {
        if(clipPop == null){
            clipPop = new ClipPop(context);
        }
        clipPop.showPop(LayoutInflater.from(activity).inflate(R.layout.activity_add_services, null));
    }



    public void upFile2() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhoto) throws Exception {
                //构建要上传的文件
                mBatchUploadPresenter.batchUpload(upLoadPhotos);
                disposable.dispose();
            }
        });

        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage2();
    }

    private void compressImage2() {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                upLoadPhotos.clear();
                for (String path : selectedPhotos) {
                    list.clear();
                    if (path.startsWith("http")) {
                        list.add(path);//添加网络图片，不需要上传
                    } else {
                        File file = new File(path);
                        if (!file.getName().startsWith("crop") || file.length() > 1024 * 100) {
                            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
                            ImageUtils.getImage(path, savePath);
                            upLoadPhotos.add(savePath);
                        } else {
                            upLoadPhotos.add(path);
                        }
                    }
                }
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    @Override
    public void batchUploadSuccess(BatchUploadInfo data) {
        loadingDialog.dismiss();
        if (data != null) {
            String[] url = data.getUrl();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    upUrls += list.get(i) + ",";
                }
            }
            for (int i = 0; i < url.length; i++) {
                upUrls += url[i] + ",";
            }
            selectedPhotos = StringUtils.stringToList(upUrls);
            moreImgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void batchUploadFailed(String msg) {
        loadingDialog.dismiss();
    }
    //------------上传多张图片  end


    @Override
    public void userGetLanguageSuccess(List<GuideMacroEntityList> str) {
        if (str == null) return;
        String text = personalTvCheckb.getText().toString();
        ArrayList<String> list = new ArrayList<>();
        if (!text.equals("")) {
            list = StringUtils.stringToList(text);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(str.get(i).getName())) {
                str.get(i).setSelect(true);
            } else {
                str.get(i).setSelect(false);
            }
        }
        dialog = new LanguageDialog(activity, personalTvCheckb);
        dialog.setData(str);
        dialog.show();
    }

    @Override
    public void userGetLanguageFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void userUpdateSuccess(String datas) {
        showShortToast("修改成功");
        if (dialog != null) {
            data = dialog.getData();
            MySelfInfo.getInstance().setGuideMacroEntityList(data);
        }
        MySelfInfo.getInstance().setMyStory(myStory);
        MySelfInfo.getInstance().setIntroduce(personalEtQianm.getText().toString());
        MySelfInfo.getInstance().setName(etName.getText().toString());
        if (!headUrl.equals("")) {
            MySelfInfo.getInstance().setUserImg(headUrl);
        }
        if (upUrls != null) {
            MySelfInfo.getInstance().setImage(upUrls);
        }
        finish();
    }

    @Override
    public void userUpdateFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getCheckLanguageSuccess(List<GuideMacroEntityList> str) {

    }

    @Override
    public void getCheckLanguageFailed(String msg) {

    }

    public void updateBack() {//设置按钮可点击并且改变背景颜色
        isEnabled = true;
        btnSubmit.setEnabled(isEnabled);
        btnSubmit.setTextColor(getResources().getColor(R.color.white));
        btnSubmit.setBackgroundResource(R.drawable.login_btn_style);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateBack();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!getLanguage().equals(personalTvCheckb.getText().toString())) {
                updateBack();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
