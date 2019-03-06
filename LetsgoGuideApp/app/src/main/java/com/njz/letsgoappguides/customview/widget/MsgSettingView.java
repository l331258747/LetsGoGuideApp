package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;

/**
 * Created by LGQ
 * Time: 2018/11/22
 * Function:
 */

public class MsgSettingView extends LinearLayout {

    Context context;
    TextView tv_title;
    LinearLayout ll_application_remind,ll_phone_remind;
    ImageView iv_application_remind,iv_phone_remind;

    boolean isAppRemind;
    boolean isPhoneRemind;


    public MsgSettingView(Context context) {
        this(context,null);
    }

    public MsgSettingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MsgSettingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_msg_setting, this, true);
        this.context = context;

        tv_title = findViewById(R.id.tv_title);
        ll_application_remind = findViewById(R.id.ll_application_remind);
        ll_phone_remind = findViewById(R.id.ll_phone_remind);
        iv_application_remind = findViewById(R.id.iv_application_remind);
        iv_phone_remind = findViewById(R.id.iv_phone_remind);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MsgSettingView);
        if (attributes != null) {
            String titleText = attributes.getString(R.styleable.MsgSettingView_MsgSettingView_title);
            if (!TextUtils.isEmpty(titleText)) {
                tv_title.setText(titleText);
            }

            attributes.recycle();
        }

        ll_application_remind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioClickLisener == null) return;
                radioClickLisener.onApplicationClick();
            }
        });

        ll_phone_remind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioClickLisener == null) return;
                radioClickLisener.onPhoneClick();
            }
        });
    }

    public void init(boolean isAppOpen,boolean isPhoneOpen){
        isAppRemind = isAppOpen;
        isPhoneRemind = isPhoneOpen;
        setImageDrawable(iv_application_remind,isAppRemind);
        setImageDrawable(iv_phone_remind,isPhoneRemind);
    }

    public void setApplicationRemind(){
        isAppRemind = !isAppRemind;
        setImageDrawable(iv_application_remind,isAppRemind);
    }
    public void setPhoneRemind(){
        isPhoneRemind = !isPhoneRemind;
        setImageDrawable(iv_phone_remind,isPhoneRemind);
    }

    public boolean getApplicationRemind(){
        return isAppRemind;
    }
    public boolean getPhoneRemind(){
        return isPhoneRemind;
    }

    private void setImageDrawable(ImageView iv,boolean isOpen){
        iv.setImageDrawable(ContextCompat.getDrawable(context,isOpen?R.mipmap.msg_st:R.mipmap.msg_st_un));
    }

    RadioClickLisener radioClickLisener;
    public interface RadioClickLisener {
        void onApplicationClick();
        void onPhoneClick();
    }
    public void setBtnClickLisener(RadioClickLisener radioClickLisener){
        this.radioClickLisener = radioClickLisener;
    }

}
