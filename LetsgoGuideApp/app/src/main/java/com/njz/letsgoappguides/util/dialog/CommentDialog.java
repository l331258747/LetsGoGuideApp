package com.njz.letsgoappguides.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.njz.letsgoappguides.R;

/**
 * Created by LGQ
 * Time: 2019/3/20
 * Function:
 */

public class CommentDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater inflater;
    private TextView btn_remark_save, btn_remark_cancle;
    private EditText et_remark_context;
    private int num = 100;//备注说明限制的最大字数
    private CommentListener commentListener;

    public CommentDialog(@NonNull Context context, CommentListener commentListener) {
        super(context, R.style.LoadingDialog);
        this.mContext = context;
        this.commentListener = commentListener;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_dialog_comment, null);
        setContentView(layout);

        btn_remark_save = layout.findViewById(R.id.btn_remark_save);
        btn_remark_cancle = layout.findViewById(R.id.btn_remark_cancle);
        et_remark_context = layout.findViewById(R.id.et_remark_context);
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
                    Toast.makeText(mContext, "超出字数限制", Toast.LENGTH_SHORT).show();
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
        switch (v.getId()) {
            case R.id.btn_remark_save:
                String remark = et_remark_context.getText().toString();
                if (remark.equals("")) {
                    Toast.makeText(mContext, "内容回复不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    commentListener.onComment(CommentDialog.this,remark);
                }
                break;
            case R.id.btn_remark_cancle:
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        if (this != null && this.isShowing())
            super.dismiss();
    }

    public interface CommentListener {
        void onComment(CommentDialog dialog,String remark);
    }

}
