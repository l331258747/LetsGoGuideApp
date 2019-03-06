package com.njz.letsgoappguides.customview.widget.editorView;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
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

public class EditorLayout extends LinearLayout implements View.OnClickListener {
    TextView action_bold, action_italic, action_underline, action_heading4, action_insert_image;
    EditorView editor;


    Context context;

    public EditorLayout(Context context) {
        this(context,null);
    }

    public EditorLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EditorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.view_editor, this, true);

        editor = view.findViewById(R.id.editor);
        action_bold = view.findViewById(R.id.action_bold);
        action_italic = view.findViewById(R.id.action_italic);
        action_underline = view.findViewById(R.id.action_underline);
        action_heading4 = view.findViewById(R.id.action_heading4);
        action_insert_image = view.findViewById(R.id.action_insert_image);

        action_bold.setOnClickListener(this);
        action_italic.setOnClickListener(this);
        action_underline.setOnClickListener(this);
        action_heading4.setOnClickListener(this);
        action_insert_image.setOnClickListener(this);

    }

    public void setPlaceholder(String str) {
        editor.setPlaceholder(str);
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
            case R.id.action_bold:
                editor.setBold();
                break;
            case R.id.action_italic:
                editor.setItalic();
                break;
            case R.id.action_underline:
                editor.setUnderline();
                break;
            case R.id.action_heading4:
                editor.setHeading();
                break;
            case R.id.action_insert_image:
                editor.getInsetImage();
                break;

        }
    }
}
