package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.model.mine.BatchUploadInfo;
import com.njz.letsgoappguides.model.mine.FeedBackInfo;
import com.njz.letsgoappguides.presenter.other.BatchUploadContract;
import com.njz.letsgoappguides.presenter.other.BatchUploadPresenter;
import com.njz.letsgoappguides.presenter.setting.FeedBackContract;
import com.njz.letsgoappguides.presenter.setting.FeedBackPresenter;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.TimeUtils;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.accessory.PhotoAdapter;
import com.njz.letsgoappguides.util.accessory.RecyclerItemClickListener;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class FeedbackActivity extends BaseActivity implements FeedBackContract.View, BatchUploadContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.feekback_et_id)
    EditText feekbackEtId;
    @BindView(R.id.feekback_et_contact)
    EditText feekbackEtContact;
    @BindView(R.id.recycler_view)
    RecyclerView mPhotoRecyclerView;
    FeedBackPresenter mFeedBackPresenter;
    BatchUploadPresenter mBatchUploadPresenter;
    private TackPicturesUtil tackPicUtil;
    LoadingDialog loadingDialog;
    private Disposable disposable;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> urlPhotos = new ArrayList<>();
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> upLoadPhotos2 = new ArrayList<>();
    private List<String> list=new ArrayList<>();
    String upUrls="";

    @Override
    protected void initView() {
        titleTv.setText("意见反馈");
        loadingDialog = new LoadingDialog(context);
        mFeedBackPresenter = new FeedBackPresenter(this, context);
        mBatchUploadPresenter = new BatchUploadPresenter(context,this);

        initAddPhoto();
        tackPicUtil = new TackPicturesUtil(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick(R.id.left_iv)
    public void leftBack() {
        if(feekbackEtId.getText().toString().trim().equals("")&&feekbackEtContact.getText().toString().trim().equals("")&&upUrls.equals("")){
            finish();
        }else{
            new AlertDialog.Builder(context).setTitle("您确定要放弃此次编辑？").setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    }).setNegativeButton("取消", null).show();
        }
    }

    @Override
    public void saveFeedbackSuccess(String str) {
        showShortToast("提交成功");
        finish();
    }

    @Override
    public void saveFeedbackFailed(String msg) {
        showShortToast(msg);
    }


    @OnClick(R.id.feedback_in_button)
    public void onViewClicked() {
        String content = feekbackEtId.getText().toString().trim();
        String contact = feekbackEtContact.getText().toString().trim();
        if (content.equals("")) {
            showShortToast("反馈信息不能为空！");
            return;
        } else if (contact.equals("")) {
            showShortToast("联系方式必填！");
            return;
        }
        FeedBackInfo infos=new FeedBackInfo();
        infos.setContent(content);
        infos.setMobile(contact);
        infos.setIdeaImgs(upUrls);
        infos.setOriginType(2);
        mFeedBackPresenter.saveFeedback(infos);
    }

    //-----------start 拍照-----------


    //------------附件图片
    private void initAddPhoto(){
        //------------附件
        photoAdapter = new PhotoAdapter(context, selectedPhotos);
        mPhotoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(PhotoAdapter.IMAGE_LINE, OrientationHelper.VERTICAL));
        mPhotoRecyclerView.setAdapter(photoAdapter);
        mPhotoRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(FeedbackActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(FeedbackActivity.this);
                        }
                    }
                }));
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
        if (resultCode == -1 &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
                photoAdapter.notifyDataSetChanged();
            }
            upUrls="";
            int a=0;
            for (int i=0;i<selectedPhotos.size();i++){
                if(selectedPhotos.get(i).startsWith("http")){
                    upUrls+=selectedPhotos.get(i).toString()+",";
                }else {
                    a++;
                    if(a==1){
                        upFile();
                    }
                }
            }
//            mPhotoRecyclerView.invalidateItemDecorations();

            initAddPhoto();//刷新数据
        }
    }

    //-----------start 拍照-----------



    public void upFile() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhotos) throws Exception {
                sendHead();
                disposable.dispose();
            }
        });
        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage2();
    }

    public void sendHead() {
        //构建要上传的文件
        mBatchUploadPresenter.batchUpload(upLoadPhotos2);
    }


    private void compressImage2() {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                upLoadPhotos2.clear();
                for (String path : selectedPhotos) {
                    list.clear();
                    if(path.startsWith("http")){
                        list.add(path);//添加网络图片，不需要上传
                    }else{
                        File file = new File(path);
                        if(!file.getName().startsWith("crop") || file.length()>1024*100) {
                            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
                            ImageUtils.getImage(path, savePath);
                            upLoadPhotos2.add(savePath);
                        }else{
                            upLoadPhotos2.add(path);
                        }
                    }
                }
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    //----------------end 拍照


    @Override
    public void batchUploadSuccess(BatchUploadInfo data) {
        loadingDialog.dismiss();
        if(data!=null){
            String[] url=data.getUrl();
            if(list.size()>0){
                for (int i=0;i<list.size();i++){
                    upUrls+=list.get(i)+",";
                }
            }
            for (int i=0;i<url.length;i++){
                upUrls+=url[i]+",";
            }
            selectedPhotos= StringUtils.stringToList(upUrls);
            photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void batchUploadFailed(String msg) {
        loadingDialog.dismiss();
    }
}
