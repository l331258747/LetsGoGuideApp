package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class TitleView extends RelativeLayout {

    RelativeLayout titleLlayout;
    ImageView leftIv,rightIv;
    TextView titleTv,rightTv;
    View line;
    Context context;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);

        titleLlayout = (RelativeLayout) findViewById(R.id.title_layout);
        line = (View) findViewById(R.id.line);
        leftIv = (ImageView) findViewById(R.id.left_iv);
        rightIv =  (ImageView) findViewById(R.id.right_iv);
        titleTv =  (TextView) findViewById(R.id.title_tv);
        rightTv = (TextView)  findViewById(R.id.right_tv);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        if (attributes != null) {
            String titleText = attributes.getString(R.styleable.TitleView_TitleView_title);
            if (!TextUtils.isEmpty(titleText)) {
                titleTv.setText(titleText);
            }

            int leftDrawable = attributes.getResourceId(R.styleable.TitleView_TitleView_left_img,-1);
            if (leftDrawable != -1) {
                leftIv.setVisibility(VISIBLE);
                leftIv.setImageDrawable(ContextCompat.getDrawable(context,leftDrawable));
            }

            String rightText = attributes.getString(R.styleable.TitleView_TitleView_right_text);
            if (!TextUtils.isEmpty(rightText)) {
                rightTv.setText(rightText);
                rightTv.setVisibility(VISIBLE);
            }

            int rightDrawable = attributes.getResourceId(R.styleable.TitleView_TitleView_right_img,-1);
            if (rightDrawable != -1) {
                rightIv.setVisibility(VISIBLE);
                rightIv.setImageDrawable(ContextCompat.getDrawable(context,rightDrawable));
            }

            boolean titleVisible = attributes.getBoolean(R.styleable.TitleView_TitleView_visible,false);
            if (titleVisible) {
                titleLlayout.setBackgroundResource(R.color.transparent);
            }
            boolean titleLineVisible = attributes.getBoolean(R.styleable.TitleView_TitleView_line_visible,false);
            if (titleLineVisible) {
                line.setVisibility(GONE);
            }

            attributes.recycle();
        }
    }

    public void setTitle(String title){
        titleTv.setText(title);
    }

    public void setRightTv(String rightText){
        rightTv.setText(rightText);
        rightTv.setVisibility(VISIBLE);
    }

    public void setRightIv(int resourceId){
        rightIv.setImageDrawable(ContextCompat.getDrawable(context,resourceId));
        rightTv.setVisibility(VISIBLE);
    }

    public ImageView getLeftIv(){
        return leftIv;
    }
    public ImageView getRightIv(){
        return rightIv;
    }
    public TextView getTitleTv(){
        return titleTv;
    }
    public TextView getRightTv(){
        return rightTv;
    }

}
