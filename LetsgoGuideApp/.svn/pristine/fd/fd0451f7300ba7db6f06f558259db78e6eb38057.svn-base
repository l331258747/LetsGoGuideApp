package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.njz.letsgoappguides.R;
/**
 * Created by Administrator on 2018/11/5.
 */

public class RegisterItemView extends LinearLayout{

    EditText et_input;
    TextView btn_next;
    TextView register_item_tv;

    public RegisterItemView(Context context) {
        this(context, null);
    }

    public RegisterItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegisterItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_register_item, this, true);

        et_input = (EditText) findViewById(R.id.register_item_et);
        btn_next = (TextView) findViewById(R.id.register_item_btn);
        register_item_tv = (TextView) findViewById(R.id.register_item_tv);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.register_item);
        if (attributes != null) {

            int etViewlength = attributes.getInteger(R.styleable.register_item_register_item_max_length,20);
            if(etViewlength != 0){
                et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(etViewlength)});
            }

            String hint = attributes.getString(R.styleable.register_item_register_item_hint);
            if (!TextUtils.isEmpty(hint)) {
                et_input.setHint(hint);
            }

            int leftDrawable = attributes.getResourceId(R.styleable.register_item_register_item_right_drawable, -1);
            if (leftDrawable != -1) {
                btn_next.setVisibility(VISIBLE);
            }


            String rightText = attributes.getString(R.styleable.register_item_register_item_right_text);
            if (!TextUtils.isEmpty(hint)) {
                register_item_tv.setVisibility(VISIBLE);
                register_item_tv.setText(rightText);
            }

            attributes.recycle();
        }
    }

    public void setEtInputType(int type){
        et_input.setInputType(type);
    }

    public void setEtContent(String phone){
        et_input.setText(phone);
    }

    public String getEtContent(){
        return et_input.getText().toString();
    }

    public EditText getEtView(){
        return et_input;
    }

    public TextView getRightText(){
        return register_item_tv;
    }

    public void setOnClickLisener(OnClickListener listener){
        register_item_tv.setOnClickListener(listener);
    }

}
