package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;


public class MineEditView extends LinearLayout {

    TextView tv_title;
    EditText tv_content;
    ImageView iv_right_img;

    public MineEditView(Context context) {
        this(context, null);
    }

    public MineEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_mine_edit, this, true);

        tv_title = (TextView) findViewById(R.id.tv_title_per);
        tv_content =  (EditText) findViewById(R.id.tv_info_per);
        iv_right_img = (ImageView)  findViewById(R.id.iv_right_imgs);


        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.home_edit_view);
        if (attributes != null) {
            String titleText = attributes.getString(R.styleable.home_edit_view_per_title);
            if (!TextUtils.isEmpty(titleText)) {
                tv_title.setText(titleText);
            }

            String contentText = attributes.getString(R.styleable.home_edit_view_per_content);
            if (!TextUtils.isEmpty(contentText)) {
                tv_content.setText(contentText);
            }

            String contentHint = attributes.getString(R.styleable.home_edit_view_per_hint);
            if(!TextUtils.isEmpty(contentHint)){
                tv_content.setHint(contentHint);
            }

            int rightDrawable = attributes.getResourceId(R.styleable.home_edit_view_per_right_drawable, -1);
            if (rightDrawable != -1) {
                iv_right_img.setVisibility(VISIBLE);
                iv_right_img.setImageDrawable(ContextCompat.getDrawable(context,rightDrawable));
            }
            attributes.recycle();
        }
    }

    public void setEtInputType(int type){
        tv_content.setInputType(type);
    }

    public void setContent(String content){
        tv_content.setTextColor(getResources().getColor(R.color.color_text));
        tv_content.setText(content);
    }

    public String getContent(){
        return tv_content.getText().toString();
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public String getTitle(){
        return tv_title.getText().toString();
    }

    public void setrightDrawable(Context context,int rightDrawable){
        iv_right_img.setImageDrawable(ContextCompat.getDrawable(context,rightDrawable));
    }


    public void setEnableds(boolean isonclick){
        tv_content.setEnabled(isonclick);
    }

    public void setFocusables(boolean isFocusable,View.OnClickListener lister){//设置可点击但是不可编辑
        tv_content.setFocusable(isFocusable);
        tv_content.setFocusableInTouchMode(isFocusable);
        tv_content.setOnClickListener(lister);
    }

    public void setFocusables(boolean isFocusable){//设置可点击但是不可编辑
        tv_content.setFocusable(isFocusable);
        tv_content.setFocusableInTouchMode(isFocusable);
    }


    public void addTextChangedListeners(TextWatcher mTextWatcher){
        tv_content.addTextChangedListener(mTextWatcher);
    }

    public void setKeyListeners(KeyListener lister){
        tv_content.setKeyListener(lister);
    }

}
