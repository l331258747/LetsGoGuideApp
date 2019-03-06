package com.njz.letsgoappguides.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.NotifyItemView;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView;
import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.message.NotifyMainModel;
import com.njz.letsgoappguides.model.other.IMUserModel;
import com.njz.letsgoappguides.presenter.message.NotifyMainContract;
import com.njz.letsgoappguides.presenter.message.NotifyMainPresenter;
import com.njz.letsgoappguides.ui.activites.message.NotifySettingActivity;
import com.njz.letsgoappguides.ui.activites.message.SystemMsgActivity;
import com.njz.letsgoappguides.ui.im.ChatActivity;
import com.njz.letsgoappguides.ui.im.ChatHelp;
import com.njz.letsgoappguides.ui.im.cache.UserCacheManager;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.NotifyEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment implements NotifyMainContract.View {

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.view_notify_message)
    NotifyItemView viewNotifyMessage;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.view_empty)
    EmptyView viewEmpty;
    @BindView(R.id.list)
    EaseConversationList conversationListView;

    private boolean isLoad;
    private NotifyMainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

        titleView.getRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,NotifySettingActivity.class));
            }
        });

        initSwipeLayout();

        mPresenter = new NotifyMainPresenter(context,this);

        setUpView();
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    //初始化SwipeLayout
    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_theme));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoad) return;
                getData();
            }
        });
    }

    public void getData(){
        swipeRefreshLayout.setRefreshing(true);
        isLoad = true;
        mPresenter.msgPushGetSendMsgList();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);//比oncreate先执行
        if (isVisibleToUser && isPrepared && !isLoad) {
            getData();
            refresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getUserVisibleHint() && isPrepared){
            refresh();
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void msgPushGetSendMsgListSuccess(List<NotifyMainModel> data) {
        swipeRefreshLayout.setRefreshing(false);
        isLoad = false;

        boolean hasItem = false;
        for (NotifyMainModel model : data){
            if(TextUtils.equals(model.getMsgBroad(), Constant.NOTIFY_TYPE_SYSTEM_MSG)){
                setNofityItem(viewNotifyMessage,model);
                hasItem = true;
            }
        }
        if(!hasItem){
            setNotifyItemEmpty(viewNotifyMessage);
        }

        viewEmpty.setVisible(false);

        refresh();
    }

    @Override
    public void msgPushGetSendMsgListFailed(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        showShortToast(msg);
        isLoad = false;

        if(msg.startsWith("-")){
            viewEmpty.setVisible(true);
            viewEmpty.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
            viewEmpty.setBtnClickLisener(new EmptyClickLisener() {
                @Override
                public void onClick() {
                    getData();
                }
            });
        }
    }

    public void setNotifyItemEmpty(NotifyItemView item){
        item.setContent("暂无新消息");
        item.getViewNum().setVisibility(View.GONE);
        item.setTime("");
    }

    public void setNofityItem(NotifyItemView item,NotifyMainModel model){
        if(model.getContent() != null){
            item.setContent(model.getContent().getAlert());
        }
        item.setTime(model.getStartTimeTwo());
        if(model.getUnReadNum() < 1){
            item.getViewNum().setVisibility(View.GONE);
        }else{
            item.getViewNum().setVisibility(View.VISIBLE);
            item.setNum(model.getUnReadNum());
        }
    }


    @OnClick({R.id.view_notify_message})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.view_notify_message:
                intent = new Intent(context,SystemMsgActivity.class);
                startActivity(intent);
                setNotifyItemEmpty(viewNotifyMessage);
                break;
        }
    }


    //-----------------------------------------环信

    private ChatHelp chatHelp;
    protected boolean isConflict;

    protected Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    LogUtil.e("im 0");
                    break;
                case 1:
                    LogUtil.e("im 1");
                    break;
                case ChatHelp.MSG_REFRESH: {
                    if(chatHelp == null) return;
                    chatHelp.refresh();
                    break;
                }
                default:
                    break;
            }
        }
    };

    protected void setUpView() {
        chatHelp = new ChatHelp(conversationListView);
        chatHelp.setLoadCallback(new ChatHelp.loadCallBack() {
            @Override
            public void loadCallback(List<EMConversation> list) {
                for (EMConversation item : list) {
                    if(UserCacheManager.notExistedOrExpired(item.conversationId())){
                        ResponseCallback listener = new ResponseCallback<IMUserModel>() {
                            @Override
                            public void onSuccess(IMUserModel datas) {
                                if (datas == null) return;
                                UserCacheManager.save(datas.getImId(), datas.getNickname(), datas.getImgUrl());
                            }

                            @Override
                            public void onFault(String errorMsg) {
                                LogUtil.e(errorMsg);
                            }
                        };
                        MethodApi.getUserByIMUsername(item.conversationId(), new ProgressSubscriber(listener));
                    }
                }
            }
        });

        chatHelp.setConversationListItemClickListener(
                new ChatHelp.EaseConversationListItemClickListener() {
                    @Override
                    public void onListItemClicked(EMConversation conversation) {
                        startActivity(new Intent(context, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
                    }
                }, new EMConnectionListener() {
                    @Override
                    public void onDisconnected(int error) {
                        if (chatHelp.isError(error)) {
                            isConflict = true;
                        } else {
                            handler.sendEmptyMessage(0);
                        }
                    }

                    @Override
                    public void onConnected() {
                        handler.sendEmptyMessage(1);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(chatHelp != null)
            chatHelp.removeConnectionListener();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }

    public void refresh() {
        if(isConflict) return;
        if(chatHelp == null) return;

        if (!handler.hasMessages(ChatHelp.MSG_REFRESH)) {
            handler.sendEmptyMessage(ChatHelp.MSG_REFRESH);
        }
    }


    //--------------提醒
    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            if (getUserVisibleHint() && !isConflict) {
                chatHelp.refresh();
            }else  {
                RxBus2.getInstance().post(new NotifyEvent(true));
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {
        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {
        }


        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };



}