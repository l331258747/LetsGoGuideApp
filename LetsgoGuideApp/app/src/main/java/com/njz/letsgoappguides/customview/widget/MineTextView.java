package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;


public class MineTextView extends LinearLayout {

    TextView tv_title;
    TextView tv_content;
    EditText et_text;
    TextView text_tv_backcolor;
    ImageView bottom_view1;
    ImageView text_iv_moreb;
    RelativeLayout rl_id;

    public MineTextView(Context context) {
        this(context, null);
    }

    public MineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_mine_text3, this, true);

        tv_title = (TextView) findViewById(R.id.text_iv);
        tv_content =  (TextView) findViewById(R.id.text_tvCheckb);
        et_text =   findViewById(R.id.et_text);
        text_tv_backcolor =   findViewById(R.id.text_tv_backcolor);
        bottom_view1 =   findViewById(R.id.bottom_view1);
        text_iv_moreb =   findViewById(R.id.text_iv_moreb);
        rl_id =   findViewById(R.id.rl_id);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.setting_item);
        if (attributes != null) {
            //标题
            String titleText = attributes.getString(R.styleable.setting_item_titles);
            if (!TextUtils.isEmpty(titleText)) {
                tv_title.setText(titleText);
            }

            //右边标题
            String contentText = attributes.getString(R.styleable.setting_item_rightTitles);
            if (!TextUtils.isEmpty(contentText)) {
                tv_content.setVisibility(VISIBLE);
                tv_content.setText(contentText);
            }

            //中间编辑框
            String editText = attributes.getString(R.styleable.setting_item_center_edit);
            if (!TextUtils.isEmpty(editText)) {
                et_text.setVisibility(VISIBLE);
                et_text.setText(editText);
            }

            //编辑框hint
            String contentHint = attributes.getString(R.styleable.setting_item_center_edit_hint);
            if(!TextUtils.isEmpty(contentHint)){
                et_text.setVisibility(VISIBLE);
                et_text.setHint(contentHint);
            }

            //右边按钮
            boolean rightbutton = attributes.getBoolean(R.styleable.setting_item_right_button_visible,false);
            if (rightbutton) {
                text_tv_backcolor.setVisibility(VISIBLE);
            }
            //右边箭头号
            boolean rightimg = attributes.getBoolean(R.styleable.setting_item_right_img_visible,false);
            if (rightimg) {
                text_iv_moreb.setVisibility(VISIBLE);
            }
            //线条
            boolean bottomtimg = attributes.getBoolean(R.styleable.setting_item_bottom_lines_visible,false);
            if (bottomtimg) {
                bottom_view1.setVisibility(VISIBLE);
            }
            attributes.recycle();
        }
    }

    public void setContent(String content){
        tv_content.setText(content);
    }

    public String getContent(){
        return tv_content.getText().toString();
    }

    public void setEditContent(String content){
        et_text.setText(content);
    }

    public EditText getEditText(){
        return et_text;
    }

    public String getEditContent(){
        return et_text.getText().toString();
    }

    public void setWidths(int dps){
        et_text.setWidth(dps);
    }

    public void setFocusables(boolean isFocusable,View.OnClickListener lister){//设置可点击但是不可编辑
        et_text.setFocusable(isFocusable);
        et_text.setFocusableInTouchMode(isFocusable);
        rl_id.setOnClickListener(lister);
        et_text.setOnClickListener(lister);
    }



    public void setInputTypes(int type){
        et_text.setInputType(type);
    }
}
