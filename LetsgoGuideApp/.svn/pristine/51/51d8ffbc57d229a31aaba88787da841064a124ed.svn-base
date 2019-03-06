package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.util.AppUtils;

import java.util.Set;


public class TextServiceView extends LinearLayout {


    LinearLayout ll_service_addcartype;
    TextView text_name;


    public TextServiceView(Context context) {
        this(context, null);
    }

    public TextServiceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextServiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_service_text, this, true);
        ll_service_addcartype=findViewById(R.id.ll_service_addcartype);
        text_name=findViewById(R.id.text_name);

    }


    public String getContent() {
        return text_name.getText().toString();
    }

    public void setContent(String content) {
        text_name.setText(content);
    }

    public void setOnclick(OnClickListener lister){
        ll_service_addcartype.setOnClickListener(lister);

    }

}
