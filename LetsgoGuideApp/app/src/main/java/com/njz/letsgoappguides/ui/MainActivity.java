package com.njz.letsgoappguides.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.ActivityCollect;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.MyViewPager;
import com.njz.letsgoappguides.model.message.NotifyMainModel;
import com.njz.letsgoappguides.presenter.message.NotifyMainContract;
import com.njz.letsgoappguides.presenter.message.NotifyMainPresenter;
import com.njz.letsgoappguides.ui.activites.home.OrderDetailActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderDetailRefundActivity;
import com.njz.letsgoappguides.ui.adapters.FragmentAdapter;
import com.njz.letsgoappguides.ui.fragments.HomeFragment;
import com.njz.letsgoappguides.ui.fragments.MessageFragment;
import com.njz.letsgoappguides.ui.fragments.MysettingFragment;
import com.njz.letsgoappguides.ui.fragments.serveFragment;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.NotifyEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements NotifyMainContract.View {

    @BindView(R.id.vp_main)
    MyViewPager vp_main;
    @BindView(R.id.rg_main)
    LinearLayout rg_main;
    List<BaseFragment> fragments = new ArrayList<>();
    FragmentAdapter adapter;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.rl_icon_1)
    RelativeLayout rlIcon1;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R.id.rl_icon_2)
    RelativeLayout rlIcon2;
    @BindView(R.id.iv_icon_3)
    ImageView ivIcon3;
    @BindView(R.id.rl_icon_3)
    RelativeLayout rlIcon3;
    @BindView(R.id.iv_icon_4)
    ImageView ivIcon4;
    @BindView(R.id.rl_icon_4)
    RelativeLayout rlIcon4;
    @BindView(R.id.tv_num)
    TextView tvNum;

    Disposable notifyDisposable;

    private NotifyMainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNewIntent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        context = this;

        fragments.add(new HomeFragment());
        fragments.add(new serveFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MysettingFragment());
        adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vp_main.setAdapter(adapter);
        vp_main.setNoScroll(true);
        vp_main.setOffscreenPageLimit(fragments.size());
        vp_main.setCurrentItem(0);
        vp_main.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragments.get(position).loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rlIcon1.setSelected(true);

        initDot();

    }

    private void initDot() {
        tvNum.setVisibility(View.GONE);
        //小红点处理
        notifyDisposable = RxBus2.getInstance().toObservable(NotifyEvent.class, new Consumer<NotifyEvent>() {
            @Override
            public void accept(NotifyEvent notifyEvent) throws Exception {
                tvNum.setVisibility(notifyEvent.isShowTips()?View.VISIBLE:View.GONE);
            }
        });
        mPresenter = new NotifyMainPresenter(context,this);
        mPresenter.msgPushGetSendMsgList();
    }

    @OnClick({R.id.rl_icon_1, R.id.rl_icon_2, R.id.rl_icon_3, R.id.rl_icon_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
        case R.id.rl_icon_1:
            setTabIndex(0);
            break;
        case R.id.rl_icon_2:
            setTabIndex(1);
            break;
        case R.id.rl_icon_3:
            setTabIndex(2);
            break;
        case R.id.rl_icon_4:
            setTabIndex(3);
            break;
    }
}


    public void setSelecteds(ImageView imageView1,ImageView imageView2,ImageView imageView3,ImageView imageView4){
        imageView1.setSelected(true);
        imageView2.setSelected(false);
        imageView3.setSelected(false);
        imageView4.setSelected(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(notifyDisposable !=null && !notifyDisposable.isDisposed())
            notifyDisposable.dispose();
    }

    @Override
    public void msgPushGetSendMsgListSuccess(List<NotifyMainModel> data) {
        if(data == null || data.size() == 0){
            RxBus2.getInstance().post(new NotifyEvent(false));
        }else{
            RxBus2.getInstance().post(new NotifyEvent(true));
        }
    }

    @Override
    public void msgPushGetSendMsgListFailed(String msg) {
        LogUtil.e(msg);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("onNewIntent");
        setIntent(intent);
        getNewIntent();
    }

    private void getNewIntent(){
        Intent intent = getIntent(); //use the data received here
        int key = intent.getIntExtra("key", -1);
        String skip = intent.getStringExtra("skip");

        LogUtil.e("key:" + key);
        LogUtil.e("skip:" + skip);

        setSkip(skip,key);
    }

    public void setSkip(final String skip, final int correlationId){
        if(correlationId == -1){
            LogUtil.e("不能进行跳转correlationId");
            return;
        }
        if(TextUtils.isEmpty(skip)){
            LogUtil.e("不能进行跳转skip 空");
            return;
        }
        Intent intent;
        switch (skip){
            case Constant.NOTIFY_SKIP_OD:
                intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("ORDER_ID",correlationId);
                startActivity(intent);
                break;
            case Constant.NOTIFY_SKIP_ORD:
                intent = new Intent(context, OrderDetailRefundActivity.class);
                intent.putExtra("ORDER_ID",correlationId);
                startActivity(intent);
                break;
            case Constant.NOTIFY_SKIP_SD:
                ActivityCollect.getAppCollect().finishAllNotHome();
                MainActivity activity = (MainActivity) ActivityCollect.getAppCollect().findActivity(MainActivity.class);
                if(activity!=null){
                    activity.setTabIndex(1);
                }
                break;
            default:
                LogUtil.e("不能进行跳转skip");
                break;
        }
    }

    public void setTabIndex(int i) {
        vp_main.setCurrentItem(i);
        switch (i) {
            case 0:
                setSelecteds(ivIcon1, ivIcon2, ivIcon3, ivIcon4);
                break;
            case 1:
                setSelecteds(ivIcon2, ivIcon1, ivIcon3, ivIcon4);
                break;
            case 2:
                setSelecteds(ivIcon3, ivIcon2, ivIcon1, ivIcon4);
                RxBus2.getInstance().post(new NotifyEvent(false));
                break;
            case 3:
                setSelecteds(ivIcon4, ivIcon2, ivIcon3, ivIcon1);
                break;
        }
    }
}
