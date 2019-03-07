package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.customview.widget.flowlayout.FlowLayout;
import com.njz.letsgoappguides.customview.widget.flowlayout.TagFlowLayout;
import com.njz.letsgoappguides.util.AppUtils;

import java.util.Set;

import butterknife.BindView;


public class LinearServiceView extends LinearLayout {


    View view;
    TextView tvTitle1,group_id;
    EditText etEditid1;
    ImageView ivImage1;
    RelativeLayout rlId1;
    ImageView bottomView1;
    TextView tvTitle2;
    EditText etEditid2;
    TextView tvText2;
    RelativeLayout rlId2;
    ImageView service_lin_delete;


    private LinearServiceView.OnSelectListener mOnSelectListener;
    private LinearServiceView.OnTagClickListener mOnTagClickListener;

    public interface OnSelectListener {
        void onSelected(Set<Integer> selectPosSet);
    }

    public interface OnTagClickListener {
        boolean onTagClick(View view, int position, LinearServiceView parent);
    }


    public void setOnSelectListener(LinearServiceView.OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }


    public void setOnTagClickListener(LinearServiceView.OnTagClickListener onTagClickListener) {
        mOnTagClickListener = onTagClickListener;
    }
    public LinearServiceView(Context context) {
        this(context, null);
    }

    public LinearServiceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearServiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.view_service_lin, this, true);
        tvTitle1=findViewById(R.id.tv_title1);
        etEditid1=findViewById(R.id.et_editid1);
        ivImage1=findViewById(R.id.iv_image1);
        rlId1=findViewById(R.id.rl_id1);
        bottomView1=findViewById(R.id.bottom_view1);
        tvTitle2=findViewById(R.id.tv_title2);
        etEditid2=findViewById(R.id.et_editid2);
        tvText2=findViewById(R.id.tv_text2);
        rlId2=findViewById(R.id.rl_id2);
        group_id=findViewById(R.id.group_id);
        service_lin_delete=findViewById(R.id.service_lin_delete);

        etEditid1.setInputType(InputType.TYPE_CLASS_TEXT);
        etEditid2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public void setTitleContent(String content) {
        tvTitle1.setText(content);
    }

    public String getTitleContent() {
        return tvTitle1.getText().toString();
    }

    public void setIdContent(String content) {
        group_id.setText(""+content);
    }

    public String getIdContent() {
        return group_id.getText().toString();
    }

    public void setTitle2Content(String content) {
        String str = "";
        if(content.endsWith("yy")){
            str = "价格";
        }else if(content.endsWith("cx")){
            str = "用车价格";
        }else if(content.endsWith("tc")){
            str = "套餐价格";
        }
        tvTitle2.setText(str);
    }

    public String getTitle2Content() {
        return tvTitle2.getText().toString();
    }

    public void setEditHint1(String hint) {
        String str = "";
        if(hint.endsWith("yy")){
            str = "请选择服务语言";
        }else if(hint.endsWith("cx")){
            str = "请输入车辆类型，如：经济型5座车";
        }else if(hint.endsWith("tc")){
            if(hint.startsWith("tsty")){
                str = "请输入名称，如：海棠湾飞行体验";
            }else if(hint.startsWith("ddjd")){
                str = "请输入名称，如：经济型酒店";
            }else if(hint.startsWith("ddmp")){
                str = "请输入名称，如：天门山A线";
            }else{
                str = "请输入名称，如：中文向导服务";
            }
        }
        etEditid1.setHint(str);
    }


    public void setEditHint2(String hint) {
        String str = "";
        if(hint.endsWith("yy")){
            str = "请合理设置价格";
        }else if(hint.endsWith("cx")){
            str = "请合理设置用车价格";
        }else if(hint.endsWith("tc")){
            str = "请合理设置套餐价格";
        }
        etEditid2.setHint(str);
    }

    public String getHint() {
        return etEditid1.getHint().toString();
    }
    public String getHint2() {
        return etEditid2.getHint().toString();
    }

    public String getContent() {
        return etEditid1.getText().toString();
    }

    public void setContent(String content) {
        etEditid1.setText(content);
    }
    public String getContent2() {
        return etEditid2.getText().toString();
    }

    public void setContent2(String content) {
        etEditid2.setText(content);
    }

    public void setOnclick(boolean isFocusable,View.OnClickListener lister){//设置可点击但是不可编辑
        etEditid1.setFocusable(isFocusable);
        etEditid1.setFocusableInTouchMode(isFocusable);
        etEditid1.setOnClickListener(lister);
        ivImage1.setOnClickListener(lister);

    }

    public void setOnclick2(boolean isFocusable,View.OnClickListener lister){//设置可点击但是不可编辑
        etEditid2.setFocusable(isFocusable);
        etEditid2.setFocusableInTouchMode(isFocusable);
        etEditid2.setOnClickListener(lister);
    }

    public void setOnclickDelete(View.OnClickListener listener){
        service_lin_delete.setOnClickListener(listener);
    }


    public void setButtonOnListener(View.OnClickListener listener){
        tvText2.setOnClickListener(listener);
    }

    public void setDeleteBtnVisibility(int visibility){
        service_lin_delete.setVisibility(visibility);
    }

    public void setBtnVisibility(int visibility){
        ivImage1.setVisibility(visibility);
    }

}
