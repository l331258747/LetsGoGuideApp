package com.njz.letsgoappguides.ui.activites.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.util.banner.LocalImageHolderView;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class BigImageActivity extends BaseActivity {

    public static final String BANNER_INDEX = "BANNER_INDEX";
    public static final String BANNER_IMGS = "BANNER_IMGS";
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;

    int index;
    List<String> imgs;

    public static void startActivity(Activity activity, int index, List<String> imgs){
        Intent intent = new Intent(activity, BigImageActivity.class);
        intent.putExtra(BANNER_INDEX,index);
        intent.putStringArrayListExtra(BANNER_IMGS, (ArrayList<String>) imgs);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha_out, R.anim.alpha_in);// 淡出淡入动画效果
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_image;
    }

    @Override
    public void initView() {
        index = intent.getIntExtra(BANNER_INDEX,0);
        imgs = intent.getStringArrayListExtra(BANNER_IMGS);
        if(imgs == null){
            return;
        }
        initBanner(imgs);
    }


    public void initBanner(List<String> datas) {
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView(new LocalImageHolderView.BannerListener<String>() {

                    @Override
                    public void bannerListener(Context context, int position, String data, ImageView view) {
                        GlideUtil.LoadImageFitCenter(context, data, view);
                    }
                }, 1);
            }
        }, datas)
                .setPointViewVisible(true) //设置指示器是否可见
                .setPageIndicator(new int[]{R.drawable.oval_white_hollow, R.drawable.oval_theme_solid})//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向（左、中、右）
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        finish();
                        overridePendingTransition(R.anim.alpha_out, R.anim.alpha_in);// 淡出淡入动画效果
                    }
                }) //设置点击监听事件
                .setManualPageable(true);//设置手动影响（设置了该项无法手动切换）

        convenientBanner.setcurrentitem(index);
//        convenientBanner.setCanLoop(false);
    }
}
