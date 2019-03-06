package com.njz.letsgoappguides.customview.widget.editorView;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;

import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by LGQ
 * Time: 2018/11/26
 * Function:
 */

public class ServiceEditorLayout extends LinearLayout implements View.OnClickListener {
    TextView action_heading4, action_insert_image,action_text;
    EditorView editor;


    Context context;

    public ServiceEditorLayout(Context context) {
        this(context,null);
    }

    public ServiceEditorLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ServiceEditorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.view_editor_service, this, true);

        editor = view.findViewById(R.id.editor);
        action_insert_image = view.findViewById(R.id.action_insert_image);
        action_heading4 = view.findViewById(R.id.action_heading4);
        action_text = view.findViewById(R.id.action_text);

        action_heading4.setOnClickListener(this);
        action_insert_image.setOnClickListener(this);
        action_text.setOnClickListener(this);

    }

    public void setPlaceholder(String str) {
        editor.setPlaceholder(str);
    }

    public void setInputEnabled(boolean is) {
        editor.setInputEnabled(is);
    }

    public String insertImg(Uri bitmapUri) {
        return editor.insertImg(bitmapUri);
    }

    public void setHtml(String html) {
        editor.setHtml(html);
    }

    public RichEditor getEditor() {
        return editor;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_heading4:
                editor.setHeading();
                break;
            case R.id.action_text:
                editor.setEditorFontSize(14);
                break;
            case R.id.action_insert_image:
                editor.getInsetImage();
                break;

        }
    }
}
