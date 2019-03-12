package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.editorView.CamaraRequestCode;
import com.njz.letsgoappguides.customview.widget.editorView.EditorLayout;
import com.njz.letsgoappguides.customview.widget.editorView.ServiceEditorLayout;
import com.njz.letsgoappguides.presenter.other.BatchUploadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadContract;
import com.njz.letsgoappguides.presenter.other.UpLoadPresenter;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import jp.wasabeef.richeditor.RichEditor;

public class PersonalStoryActivity extends BaseActivity implements UpLoadContract.View{

    @BindView(R.id.editorLayout)
    ServiceEditorLayout editor;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.tv_save)
    TextView tvSave;
    LoadingDialog loadingDialog;
    private Disposable disposable;
    private TackPicturesUtil tackPicUtil;
    private String storyImg;
    private String storyImgCompressPath;
    private String storyUrl="";

    private int story2head;
    UpLoadPresenter mPresenter;

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        editor.setPlaceholder(getPlaceholder());
        mPresenter = new UpLoadPresenter(context, this);

        tackPicUtil = new TackPicturesUtil(this);
        loadingDialog = new LoadingDialog(context);

        if(getIntent().getStringExtra(Constant.STORYINFO)!=null){
            String story=getIntent().getStringExtra(Constant.STORYINFO);
            editor.setHtml(story);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_story;
    }


    @OnClick({R.id.left_iv, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                finish();
                break;
            case R.id.tv_save:
                String myStory=editor.getEditor().getHtml();
                Intent intent = new Intent();
                intent.putExtra(Constant.STORYINFO, myStory); //放置要传出的数据
                activity.setResult(Constant.STORYID,intent);
                activity.finish(); //关闭活动
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CamaraRequestCode.CAMARA_GET_IMG) {
            if(data==null)return;
            if(data.getData()==null)return;
            String filePath = editor.insertImg(data.getData());
            storyImg = filePath;
            upFile3();
        }
    }

    //----富文本 start
    public void upFile3(){
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


    @Override
    public void upUploadSuccess(String datas) {
        if(story2head == 1){
            loadingDialog.dismiss();
            storyUrl=datas;
            showShortToast("上传成功");
            editor.getEditor().focusEditor();
            editor.getEditor().insertImage(storyUrl, "dachshund" + "\" style=\"width:96%");
            return;
        }

    }

    @Override
    public void upUploadFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }

    protected String getPlaceholder(){
        return "填写要求：" +
                "1、对自己的旅行体验、行业经验、兴趣爱好、职业特长等方面进行详细介绍，需满100字以上" +
                "2、描述的内容/图片需真实可靠，图片不可有水印" +
                "3、禁止留有微信、电话等联系方式" +
                "4、不允许出现“那就走认证、那就走官方认证、那就走官方推荐、那就走指定、xx地区唯一、那就走明星向导、那就走VIP向导”字样" +
                "5、图片不可相似重复，不可披露证件信息以免信息泄露" +
                "6、不得出现明星具体名字；" +
                "7、不得出现违反《广告法》和其它法律及平台相关规定的描述。";
    }
}
