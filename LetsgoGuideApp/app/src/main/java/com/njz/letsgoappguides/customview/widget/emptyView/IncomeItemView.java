package com.njz.letsgoappguides.customview.widget.emptyView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;

/**
 * Created by LGQ
 * Time: 2018/10/11
 * Function:
 */

public class IncomeItemView extends LinearLayout {

    Context context;
    ImageView iv_empty_img;
    TextView tv_empty_content;
    LinearLayout rl_empty_parent;

    public IncomeItemView(Context context) {
        this(context, null);
    }

    public IncomeItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IncomeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_empty4, this, true);

        this.context = context;

        iv_empty_img = findViewById(R.id.iv_empty_img);
        tv_empty_content = findViewById(R.id.tv_empty_content);
        rl_empty_parent = findViewById(R.id.rl_empty_parent);

    }

    public void setVisible(boolean visible) {
        this.setVisibility(visible ? VISIBLE : GONE);
    }


    public void setEmptyBackground(int colorId) {
        rl_empty_parent.setBackgroundResource(colorId);
    }

    private void setImg(int resourceId) {
        iv_empty_img.setImageDrawable(ContextCompat.getDrawable(context, resourceId));
    }

    private void setContent(String str) {
        tv_empty_content.setText(str);
    }


    EmptyClickLisener btnClickLisener;

    public void setBtnClickLisener(EmptyClickLisener btnClickLisener) {
        this.btnClickLisener = btnClickLisener;
    }
}
