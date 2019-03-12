package com.njz.letsgoappguides.ui.activites.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.editorView.CamaraRequestCode;
import com.njz.letsgoappguides.customview.widget.editorView.ServiceEditorLayout;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;

import java.io.File;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 服务特色
 */
public class ServicesFeatureActivity extends BaseActivity implements UpLoadContract.View {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.editorLayout)
    ServiceEditorLayout editor;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String storyImg;
    private String storyImgCompressPath;
    private String storyUrl = "";

    private int story2head;
    LoadingDialog loadingDialog;
    UpLoadPresenter mPresenter;
    private Disposable disposable;

    @Override
    protected void initView() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        switch (getIntent().getIntExtra("ID", 0)) {
            case 101://特色服务
                titleTv.setText("服务特色说明");
                editor.setPlaceholder(getPlaceholder());
                if (getIntent().getStringExtra("myFeature") != null) {
                    String myFeature = getIntent().getStringExtra("myFeature");
                    editor.setHtml(myFeature);
                }
                break;
            case 103://行程设计
                titleTv.setText("行程设计");
                editor.setPlaceholder(getPlaceholder2());
                if (getIntent().getStringExtra("myDesingn") != null) {
                    String myDesingn = getIntent().getStringExtra("myDesingn");
                    editor.setHtml(myDesingn);
                }
                break;
        }

        loadingDialog = new LoadingDialog(context);
        mPresenter = new UpLoadPresenter(context, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_services_feature;
    }


    //-----------上传图片接口start-----------
    @Override
    public void upUploadSuccess(String datas) {
        if (story2head == 1) {
            loadingDialog.dismiss();
            storyUrl = datas;
            showShortToast("上传成功");
            editor.getEditor().focusEditor();
            editor.getEditor().insertImage(storyUrl, "dachshund" + "\" style=\"width:96%;");
            return;
        }
    }

    @Override
    public void upUploadFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }
    //-----------上传图片接口end-----------


    //----富文本 start
    public void upFile3() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhotos) throws Exception {
                sendStoryImg();
                disposable.dispose();
            }
        });

        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage3();
    }

    public void sendStoryImg() {
        //构建要上传的文件
        File file = new File(storyImgCompressPath);
        story2head = 1;
        mPresenter.upUpload(file);

    }

    private void compressImage3() {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
//                File file = new File(storyImg);
//                String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
//                ImageUtils.getImage(storyImg, savePath);
                storyImgCompressPath = storyImg;
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    //----富文本 end


    /**
     * 获取图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CamaraRequestCode.CAMARA_GET_IMG) {
            if (null == data) return;
            if (data.getData() == null) return;
            String filePath = editor.insertImg(data.getData());
            storyImg = filePath;
            upFile3();
        }
    }

    @OnClick({R.id.left_iv})
    public void bottonClick() {
        finish();
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        switch (getIntent().getIntExtra("ID", 0)) {
            case 101://特色服务
                if (editor.getEditor().getHtml() == null || editor.getEditor().getHtml().equals("")) {
                    showShortToast("请对您提供的服务进行详细说明");
                } else {
                    String myFeature = editor.getEditor().getHtml();
                    LogUtil.e(editor.getEditor().getHtml());
                    Intent intent = new Intent();
                    intent.putExtra("myFeature", myFeature); //放置要传出的数据
                    activity.setResult(101, intent);
                    activity.finish(); //关闭活动
                }
                break;
            case 103://行程设计
                if (editor.getEditor().getHtml() == null || editor.getEditor().getHtml().equals("")) {
                    showShortToast("请根据游客提供的需求单对整个行程进行设计");
                } else {
                    String myDesingn = editor.getEditor().getHtml();
                    Intent intent = new Intent();
                    intent.putExtra("myDesingn", myDesingn); //放置要传出的数据
                    activity.setResult(103, intent);
                    activity.finish(); //关闭活动
                }
                break;
        }
    }

    protected String getPlaceholder2(){
        return "数量要求：" +
                "1、至少2段文字描述" +
                "2、每段文字，字数在40-500字之间" +
                "质量要求：" +
                "1、描述必须真实可靠并与线下提供服务内容一致，且无错别字；" +
                "2、建议包括：" +
                "     ☆根据游客行程安排对每天的行程/玩法进行详细介绍" +
                "     ☆对行程中的景点、门票购买、取票等信息进行介绍" +
                "     ☆对行程中的住宿环境、地点、是否含早餐等信息介绍" +
                "     ☆对行程中的吃饭环境、地点及食谱相关信息介绍" +
                "     ☆对行程中的车辆信息介绍（提供用车服务时）" +
                "     ☆出游的提示信息/注意事项" +
                "3、禁止留有微信、电话等联系方式；" +
                "4、不得出现明星具体名字；" +
                "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
    }

    protected String getPlaceholder() {
        switch (getIntent().getIntExtra("serverTypeId", 0)) {
            case Constant.SERVER_TYPE_GUIDE_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字" +
                        "2、建议包括：" +
                        "     ☆服务亮点介绍" +
                        "     ☆行程/玩法介绍" +
                        "     ☆服务时长和服务时段" +
                        "     ☆车辆信息介绍（提供用车服务时）" +
                        "     ☆出游的提示信息" +
                        "3、禁止留有微信、电话等联系方式" +
                        "4、不得出现明星具体名字" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述";
            case Constant.SERVER_TYPE_FEATURE_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字；" +
                        "2、建议包括：" +
                        "     ☆服务亮点介绍" +
                        "     ☆特色/行程/玩法介绍" +
                        "     ☆场地/景点介绍" +
                        "     ☆服务时长和服务时段" +
                        "     ☆车辆信息介绍（提供用车服务时）" +
                        "     ☆出游的提示信息/注意事项" +
                        "3、禁止留有微信、电话等联系方式；" +
                        "4、不得出现明星具体名字；" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
            case Constant.SERVER_TYPE_CUSTOM_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字；" +
                        "2、建议包括：" +
                        "     ☆服务亮点介绍" +
                        "     ☆特色/行程/玩法介绍" +
                        "     ☆景点介绍及门票购买、取票介绍" +
                        "     ☆住宿环境、地点、是否含早餐等信息介绍" +
                        "     ☆吃饭环境、地点及食谱相关信息介绍" +
                        "     ☆车辆信息介绍（提供用车服务时）" +
                        "     ☆出游的提示信息/注意事项" +
                        "3、禁止留有微信、电话等联系方式；" +
                        "4、不得出现明星具体名字；" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
            case Constant.SERVER_TYPE_HOTEL_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字" +
                        "2、建议包括：" +
                        "     ☆酒店/民宿的场景（内景、外景均可）介绍" +
                        "     ☆室内设施介绍" +
                        "     ☆是否含早餐" +
                        "     ☆地点以及使用方式" +
                        "     ☆注意事项" +
                        "3、禁止留有微信、电话等联系方式；" +
                        "4、不得出现明星具体名字；" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
            case Constant.SERVER_TYPE_TICKET_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字" +
                        "2、建议包括：" +
                        "     ☆门票包含的内容介绍" +
                        "     ☆门票涉及的景点/演出介绍" +
                        "     ☆地点以及使用方式" +
                        "     ☆注意事项" +
                        "3、禁止留有微信、电话等联系方式；" +
                        "4、不得出现明星具体名字；" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
            case Constant.SERVER_TYPE_CAR_ID:
                return "数量要求：" +
                        "1、至少2段文字描述" +
                        "2、每段文字，字数在40-500字之间" +
                        "质量要求：" +
                        "1、描述必须与服务标题匹配且无错别字；" +
                        "2、建议包括：" +
                        "     ☆服务亮点介绍" +
                        "     ☆车辆信息介绍：包含车辆照片、车型、座位数以及品牌等信息" +
                        "     ☆行程路线介绍" +
                        "     ☆服务时长和服务时段" +
                        "     ☆注意事项" +
                        "3、禁止留有微信、电话等联系方式；" +
                        "4、不得出现明星具体名字；" +
                        "5、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
        }
        return "请对您提供的服务信息进行详细说明";
    }
}
