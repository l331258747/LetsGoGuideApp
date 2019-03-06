package com.njz.letsgoappguides.ui.im;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.other.IMUserModel;
import com.njz.letsgoappguides.ui.im.cache.UserCacheManager;
import com.njz.letsgoappguides.util.log.LogUtil;

/**
 * Created by LGQ
 * Time: 2019/1/23
 * Function:
 */

public class MyEaseChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

//    private boolean isRobot;
    private boolean isRobot = true;

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

        if(UserCacheManager.notExistedOrExpired(toChatUsername)){
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

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
