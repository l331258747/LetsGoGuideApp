package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.MyRatingBar;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView3;
import com.njz.letsgoappguides.customview.widget.flowlayout.FlowLayout;
import com.njz.letsgoappguides.customview.widget.flowlayout.TagAdapter;
import com.njz.letsgoappguides.customview.widget.flowlayout.TagFlowLayout;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.mine.LabelItemModel;
import com.njz.letsgoappguides.model.mine.PersonalInfo;
import com.njz.letsgoappguides.presenter.mine.GetLUserinfoPresenter;
import com.njz.letsgoappguides.presenter.mine.GetUserinfoContract;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.banner.LocalImageHolderView;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.webview.LWebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresonalViewActivity extends BaseActivity implements GetUserinfoContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.image_auth_1)
    ImageView imageAuth1;
    @BindView(R.id.tv_auth_1)
    TextView tvAuth1;
    @BindView(R.id.image_auth_2)
    ImageView imageAuth2;
    @BindView(R.id.tv_auth_2)
    TextView tvAuth2;
    @BindView(R.id.image_auth_3)
    ImageView imageAuth3;
    @BindView(R.id.tv_auth_3)
    TextView tvAuth3;
    @BindView(R.id.tv_view_qianm)
    TextView tvViewQianm;
    @BindView(R.id.tv_date)
    TextView tvDate;
    /*    @BindView(R.id.tv_biaoqian_id)
        TextView tvBiaoqianId;*/

    GetLUserinfoPresenter mGetLUserinfoPresenter;
    int[] leftdraw = {R.mipmap.my_uncertified,
            R.mipmap.my_uncertified,
            R.mipmap.my_audit,
            R.mipmap.my_authen,
    };
    String[] str = {"未认证", "认证失败", "认证中", "已认证"};//{"未认证","认证失败",,"认证中","认证成功"};
    @BindView(R.id.my_rating_bar)
    MyRatingBar myRatingBar;
    @BindView(R.id.ll_biaoqian)
    LinearLayout llBiaoqian;
    @BindView(R.id.webview)
    LWebView webView;
    @BindView(R.id.view_empty)
    EmptyView3 view_empty;
    boolean isEnabled;

    StringBuffer sb;
    PersonalInfo personalInfo;

    @BindView(R.id.ll_auth_1)
    LinearLayout llAuth1;
    @BindView(R.id.ll_auth_2)
    LinearLayout llAuth2;
    @BindView(R.id.ll_auth_3)
    LinearLayout llAuth3;


    @Override
    protected void initView() {
        titleTv.setText("个人主页预览");
        isEnabled = getIntent().getBooleanExtra("ISENABLED", false);
        sb = new StringBuffer();
        if (isEnabled) {
            personalInfo = getIntent().getParcelableExtra("PERSOBALINFO");
            showInfo(personalInfo);
        }
        mGetLUserinfoPresenter = new GetLUserinfoPresenter(context, this);
        mGetLUserinfoPresenter.getUserinfo(true);

    }

    public void showInfo(PersonalInfo personalInfo) {
        if (personalInfo == null) return;
        String img = personalInfo.getImage();
        if (!img.equals("")) {
            ArrayList<String> list = StringUtils.stringToList(img);
            initBanner(list);
        } else {
            convenientBanner.setVisibility(View.GONE);
        }
        GlideUtil.LoadCircleImage(context, personalInfo.getBase64Img(), ivHead);
        tvName.setText(personalInfo.getName());
        tvViewQianm.setText(personalInfo.getIntroduce());

        if (TextUtils.isEmpty(personalInfo.getMyStory()) || personalInfo.getMyStory().equals("<br>")) {
            view_empty.setVisible(true);
            view_empty.setEmptyData(R.mipmap.empty_guide_story, "他很害羞哦，什么都没留下~");
        } else {
            webView.loadDataWithBaseURL(null, personalInfo.getMyStory(), "text/html", "utf-8", null);
            view_empty.setVisible(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_presonal_view;
    }

    @OnClick({R.id.left_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_iv:
                finish();
                break;
        }
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


    @Override
    public void getUserinfoSuccess(UserVo str) {
        Log.e("test", str.toString());
        if (null != str) {
            getAgeWork(str.getGuideAge(), str.getWorkYear());
            if (!isEnabled) {
                String img = str.getImage();
                if (!img.equals("")) {
                    ArrayList<String> list = StringUtils.stringToList(img);
                    initBanner(list);
                } else {
                    convenientBanner.setVisibility(View.GONE);
                }
                GlideUtil.LoadCircleImage(context, str.getUserImg(), ivHead);
                tvName.setText(str.getName());
                tvViewQianm.setText(str.getIntroduce());
                if (TextUtils.isEmpty(str.getMyStory()) || str.getMyStory().equals("<br>")) {
                    view_empty.setVisible(true);
                    view_empty.setEmptyData(R.mipmap.empty_guide_story, "他很害羞哦，什么都没留下~");
                } else {
                    webView.loadDataWithBaseURL(null, str.getMyStory(), "text/html", "utf-8", null);
                    view_empty.setVisible(false);
                }
                getLanguage(str.getLanguage());
            } else {
                getLanguage(personalInfo.getLanguage());
            }

            llAuth1.setVisibility(Integer.valueOf(str.getCardViable())==2 ? View.VISIBLE : View.GONE);
            llAuth2.setVisibility(Integer.valueOf(str.getGuideViable())==2 ? View.VISIBLE : View.GONE);
            llAuth3.setVisibility(Integer.valueOf(str.getDriveViable())==2 ? View.VISIBLE : View.GONE);

            ivSex.setImageDrawable(ContextCompat.getDrawable(context, str.getGender() == 2 ? R.mipmap.icon_girl : R.mipmap.icon_boy));
            myRatingBar.setRating((int) str.getGuideScoreint());
            //-----------start  标签-----------
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            List<LabelItemModel> lists = str.getLabelList();
            if (lists.size() > 0) {
                TagFlowLayout tagFlowLayout = new TagFlowLayout(context);
                tagFlowLayout.setLayoutParams(lp2);
                tagFlowLayout.setMaxSelectCount(-1);
                llBiaoqian.addView(tagFlowLayout);
                initFlow(tagFlowLayout, lists);
            }
        }
    }

    public void getLanguage(String language) {
        if (!language.equals("")) {
            String langage = language;
            ArrayList<String> langages = StringUtils.stringToList(langage);
            for (int i = 0; i < langages.size(); i++) {
                if (i == langages.size() - 1) {
                    sb.append(langages.get(i));
                } else {
                    sb.append(langages.get(i) + " | ");
                }
            }
        }
        tvDate.setText(sb.toString());
    }

    public void getAgeWork(String age, int workYear) {
        if (!age.equals("")) {
            sb.append(age + " | ");
        }
        if (workYear != 0) {
            sb.append("从业" + workYear + "年 | ");
        } else {
            sb.append("新人导游" + " | ");
        }
    }

    @Override
    public void getUserinfoFailed(String msg) {

    }

    //---------------start

    public void initFlow(final TagFlowLayout tagFlowLayout, final List<LabelItemModel> mVals) {
        initFlow(tagFlowLayout, mVals, false);
    }

    public void initFlow(final TagFlowLayout tagFlowLayout, final List<LabelItemModel> mVals, final boolean isCustom) {
        final LayoutInflater mInflater = LayoutInflater.from(activity);

        TagAdapter adapter1 = new TagAdapter<LabelItemModel>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, LabelItemModel s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_labe2l, tagFlowLayout, false);
                tv.setText(s.getName());
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
            }

            @Override
            public void unSelected(int position, View view) {
            }
        };
        tagFlowLayout.setAdapter(adapter1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    //-------------end
}
