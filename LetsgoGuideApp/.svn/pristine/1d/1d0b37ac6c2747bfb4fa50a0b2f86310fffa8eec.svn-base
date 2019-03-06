package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;

/**
 * Created by Administrator on 2018/11/5.
 */

public class LoginItemView extends LinearLayout{

    EditText et_input;
    ImageView login_item_iv;

    public LoginItemView(Context context) {
        this(context, null);
    }

    public LoginItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_login_item, this, true);

        et_input = (EditText) findViewById(R.id.login_item_et);
        login_item_iv = (ImageView) findViewById(R.id.login_item_iv);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.login_item);
        if (attributes != null) {

            int etViewlength = attributes.getInteger(R.styleable.login_item_login_item_max_length,20);
            if(etViewlength != 0){
                et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(etViewlength)});
            }

            String hint = attributes.getString(R.styleable.login_item_login_item_hint);
            if (!TextUtils.isEmpty(hint)) {
                et_input.setHint(hint);
            }

            int leftDrawable = attributes.getResourceId(R.styleable.login_item_login_item_right_drawable, -1);
            if (leftDrawable != -1) {
                login_item_iv.setVisibility(VISIBLE);
                login_item_iv.setImageDrawable(context.getResources().getDrawable(leftDrawable));
            }

            attributes.recycle();
        }
    }

    public void setEtInputType(int type){
        et_input.setInputType(type);
    }

    public String getEtContent(){
        return et_input.getText().toString();
    }

    public EditText getEtView(){
        return et_input;
    }

    public ImageView getLogin_item_iv() {
        return login_item_iv;
    }

    public void setLogin_item_iv(ImageView login_item_iv) {
        this.login_item_iv = login_item_iv;
    }
}
