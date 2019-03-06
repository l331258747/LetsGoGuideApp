package com.njz.letsgoappguides.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.presenter.home.AddOrderRemarkContract;
import com.njz.letsgoappguides.presenter.home.AddOrderRemarkPresenter;

/**
 * Created by Administrator on 2019/2/21.
 */

public class RemarkDialog extends Dialog implements View.OnClickListener,AddOrderRemarkContract.View{

    private Context mContext;
    private LayoutInflater inflater;
    private TextView btn_remark_save,btn_remark_cancle;
    private EditText et_remark_context;
    private int orderId;
    private AddOrderRemarkPresenter presenter;
    private int num = 500;//备注说明限制的最大字数
    LinearLayout ll_order_remarks;
    LinearLayout ll_order_remarks_edit;
    TextView tv_order_remarks_context;
    String remark;


    public RemarkDialog(@NonNull Context context,
                        int orderId,
                        LinearLayout ll_order_remarks,
                        LinearLayout ll_order_remarks_edit ,
                        TextView tv_order_remarks_context) {
        super(context, R.style.LoadingDialog);
        this.mContext = context;
        this.orderId=orderId;
        this.ll_order_remarks=ll_order_remarks;
        this.ll_order_remarks_edit=ll_order_remarks_edit;
        this.tv_order_remarks_context=tv_order_remarks_context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_dialog_remark, null);
        setContentView(layout);

        btn_remark_save=layout.findViewById(R.id.btn_remark_save);
        btn_remark_cancle=layout.findViewById(R.id.btn_remark_cancle);
        et_remark_context=layout.findViewById(R.id.et_remark_context);
        et_remark_context.setText(tv_order_remarks_context.getText().toString());
        btn_remark_save.setOnClickListener(this);
        btn_remark_cancle.setOnClickListener(this);
        et_remark_context.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_remark_context.getText();
                int len = editable.length();
                if (len > num) {
                    Toast.makeText(mContext,"超出字数限制",Toast.LENGTH_SHORT).show();
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, num);
                    et_remark_context.setText(newStr);
                    editable = et_remark_context.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_remark_save:
                remark=et_remark_context.getText().toString();
                if(remark.equals("")){
                    Toast.makeText(mContext,"请输入备注说明",Toast.LENGTH_SHORT).show();
                }else{
                    presenter=new AddOrderRemarkPresenter(mContext,this);
                    presenter.addOrderNote(orderId,remark);
                }
                break;
            case R.id.btn_remark_cancle:
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        if(this!=null && this.isShowing())
            super.dismiss();
    }

    @Override
    public void addOrderNoteSuccess(String str) {
        ll_order_remarks.setVisibility(View.GONE);
        ll_order_remarks_edit.setVisibility(View.VISIBLE);
        tv_order_remarks_context.setText(remark);
        dismiss();
        Toast.makeText(mContext,"保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderNoteFailed(String msg) {
        dismiss();
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
