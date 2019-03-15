package com.njz.letsgoappguides.ui.activites.service;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.PriceView;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView3;
import com.njz.letsgoappguides.model.server.ServiceDetailInfo;
import com.njz.letsgoappguides.model.server.ServicePreviewInfo;
import com.njz.letsgoappguides.presenter.server.ServerDetailContract;
import com.njz.letsgoappguides.presenter.server.ServerDetailPresenter;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.banner.LocalImageHolderView;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.webview.LWebView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicePreviewActivity extends BaseActivity implements ServerDetailContract.View {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    //    ServicePreviewInfo infos;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_destination)
    TextView tvDestination;
    @BindView(R.id.tv_destination2)
    TextView tvDestination2;
    @BindView(R.id.pv_price)
    PriceView pvPrice;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.webview)
    LWebView webview;
    @BindView(R.id.view_empty)
    EmptyView3 viewEmpty;
    @BindView(R.id.price_introduce_content)
    TextView priceIntroduceContent;
    @BindView(R.id.tv_refund_rule_30)
    TextView tvRefundRule30;
    @BindView(R.id.tv_refund_rule_50)
    TextView tvRefundRule50;
    @BindView(R.id.ll_down_parent)
    LinearLayout ll_down_parent;
    @BindView(R.id.tv_down_time)
    TextView tv_down_time;
    @BindView(R.id.tv_down_reason)
    TextView tv_down_reason;

    int service_id;
    ServerDetailPresenter mServerDetailPresenter;
    int previewId;

    @Override
    protected void initView() {
        titleTv.setText("服务详情预览");

        previewId = intent.getIntExtra("PREVIEWID", 0);
        switch (previewId) {
            case Constant.PREVIEWID:
                ServicePreviewInfo infos = intent.getParcelableExtra("ServicePreviewInfo");
                initDate(infos);
                break;
            case Constant.PREVIEWIDDETAIL:
                service_id = intent.getIntExtra("SERVICE_ID", 0);
                mServerDetailPresenter = new ServerDetailPresenter(context, this);
                mServerDetailPresenter.getServeDetail(service_id);
                break;
        }
    }

    public void initDate(ServicePreviewInfo infos) {
        if (infos == null) return;
        String img = infos.getTitleImg();
        if (!TextUtils.isEmpty(img)) {
            ArrayList<String> list = StringUtils.stringToList(img);
            initBanner(list);
        } else {
            convenientBanner.setVisibility(View.GONE);
        }
        tvTitle.setText(infos.getTitle());
        tvDestination.setText(infos.getAddress());
        if (!TextUtils.isEmpty(infos.getServeFeature())) {
            webview.loadDataWithBaseURL(null, infos.getServeFeature(), "text/html", "utf-8", null);
            viewEmpty.setVisible(false);
        } else {
            viewEmpty.setVisible(true);
            viewEmpty.setEmptyData(R.mipmap.empty_guide_story, "他很害羞哦，什么都没留下~");
        }

        if (!TextUtils.isEmpty(infos.getCostExplain()))
            priceIntroduceContent.setText(infos.getCostExplain());
        if (!TextUtils.isEmpty(infos.getRenegePriceThree())) {
            List<String> lists = getValue(infos.getRenegePriceThree(),"0.3");
            tvRefundRule30.setText(String.format(getResources().getString(R.string.refund_rule_30),
                    lists.get(0) + "-" + lists.get(1), getProportion(lists.get(2))));
        }
        if (!TextUtils.isEmpty(infos.getRenegePriceFive())) {
            List<String> lists = getValue(infos.getRenegePriceFive(),"0.5");
            tvRefundRule50.setText(String.format(getResources().getString(R.string.refund_rule_50),
                    lists.get(0) + "-" + lists.get(1), getProportion(lists.get(2))));
        }
        if (Constant.PREVIEWID == previewId) {
            tvScore.setText("已售：" + infos.getSaleNum());
        }
        pvPrice.setPrice(infos.getServePrice());

        if (infos.getStatus() == -1) {
            ll_down_parent.setVisibility(View.VISIBLE);
            tv_down_time.setText(infos.getForceDownDate());
            tv_down_reason.setText(infos.getForceDownReason());
        }
    }

    public String getProportion(String str){
        int value = (int) (Float.valueOf(str) * 100);
        return value + "";
    }

    public List<String> getValue(String str,String def) {
        String[] strs = str.split(",");
        List<String> lists = new ArrayList<>(Arrays.asList(strs));
        if (lists.size() != 3) {
            lists.add(def);
        }else{
            if(Float.valueOf(lists.get(2)) <= 0){
                lists.set(2,def);
            }
        }
        return lists;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_preview;
    }

    @OnClick({R.id.left_iv})
    public void bottonClick() {
        finish();
    }

    //----------banner start
    public void initBanner(List<String> homeBanners) {
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView(new LocalImageHolderView.BannerListener<String>() {

                    @Override
                    public void bannerListener(Context context, int position, String data, ImageView view) {
                        GlideUtil.LoadImage(context, data, view);
                    }
                });
            }
        }, homeBanners)
                .setPointViewVisible(true) //设置指示器是否可见
                .setPageIndicator(new int[]{R.drawable.oval_white_hollow, R.drawable.oval_theme_solid})//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向（左、中、右）
                .setManualPageable(true);//设置手动影响（设置了该项无法手动切换）设置为false后手点击后 停止轮播了
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(Constant.BANNER_RUNNING_TIME);
    }


    //----------banner end


    //-------------得到服务详情start------------
    @Override
    public void getServeDetailSuccess(ServiceDetailInfo datas) {
        Log.e("test", datas.getNjzGuideServeEntity().toString());
        if (datas == null) return;
        if (datas.getNjzGuideServeEntity() == null) return;
        tvScore.setText("已售：" + datas.getSaleNum());


        ServicePreviewInfo infos = datas.getNjzGuideServeEntity();
        initDate(infos);

    }

    @Override
    public void getServeDetailFailed(String msg) {

    }
    //-------------得到服务详情end------------

}
