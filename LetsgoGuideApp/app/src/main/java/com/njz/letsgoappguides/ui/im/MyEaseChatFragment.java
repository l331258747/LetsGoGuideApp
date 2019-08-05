package com.njz.letsgoappguides.ui.im;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.other.IMUserModel;
import com.njz.letsgoappguides.ui.im.cache.UserCacheManager;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.log.LogUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.SendServerEvent;

import org.w3c.dom.Text;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LGQ
 * Time: 2019/1/23
 * Function:
 */

public class MyEaseChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

    //    private boolean isRobot;
    private boolean isRobot = true;
    private Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void setUpView() {
        setChatFragmentHelper(this);
//        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
//            Map<String, RobotUser> robotMap = HxEaseuiHelper.getInstance().getRobotList();
//            if (robotMap != null && robotMap.containsKey(toChatUsername)) {
//                isRobot = true;
//            }
//        }

        if (UserCacheManager.notExistedOrExpired(toChatUsername)) {
            ResponseCallback listener = new ResponseCallback<IMUserModel>() {
                @Override
                public void onSuccess(IMUserModel datas) {
                    if (datas == null) return;
                    UserCacheManager.save(datas.getImId(), datas.getNickname(), datas.getImgUrl());
                    titleBar.setTitle(datas.getNickname());

                }

                @Override
                public void onFault(String errorMsg) {
                    LogUtil.e(errorMsg);
                }
            };
            MethodApi.getUserByIMUsername(toChatUsername, new ProgressSubscriber(listener));
        }

        if(disposable == null){
            disposable = RxBus2.getInstance().toObservable(SendServerEvent.class, new Consumer<SendServerEvent>() {
                @Override
                public void accept(SendServerEvent sendServerEvent) throws Exception {
                    sendServer(sendServerEvent);
                }
            });
        }

        super.setUpView();
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {
        if (isRobot) {
            //set message extension
            message.setAttribute("em_robot_message", isRobot);
        }
        //设置要发送扩展消息用户昵称
//        message.setAttribute(Constant.IM_NICKNAME, MySelfInfo.getInstance().getName());
//        message.setAttribute(Constant.IM_HEADIMG, MySelfInfo.getInstance().getUserImg());

        UserCacheManager.setMsgExt(message);
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {
        if(message.getType() == EMMessage.Type.TXT){
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) AppUtils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", ((EMTextMessageBody) message.getBody()).getMessage());
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            ToastUtil.showShortToast(AppUtils.getContext(),"复制成功");
        }
    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        if(itemId == ITEM_SEND_SERVER){
            startActivity(new Intent(AppUtils.getContext(),SelectServerActivity.class));
        }
        return false;
    }

    private void sendServer(SendServerEvent sendServerEvent) {
        //发送扩展消息
        EMMessage message = EMMessage.createTxtSendMessage(sendServerEvent.getServerName(),toChatUsername);
        //增加自己的属性
        message.setAttribute("is_server",true);
        message.setAttribute("server_img",sendServerEvent.getServerImg());
        message.setAttribute("server_area",sendServerEvent.getServerArea());
        message.setAttribute("server_id",sendServerEvent.getServerId());
        message.setAttribute("server_name",sendServerEvent.getServerName());
        message.setAttribute("server_price",sendServerEvent.getServerPrice());
        //发送扩展消息
        EMClient.getInstance().chatManager().sendMessage(message);
        messageList.refresh();//刷新消息数据
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
