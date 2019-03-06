package com.njz.letsgoappguides.customview.widget.editorView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by LGQ
 * Time: 2018/11/26
 * Function:
 */

public class EditorView extends RichEditor {
    Context context;

    public EditorView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public EditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

        //初始化编辑高度
//        setEditorHeight(200);
        //初始化字体大小
        setEditorFontSize(14);
        //初始化字体颜色
        setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);

        //初始化内边距
//        setPadding(10, 10, 10, 10);
        //设置编辑框背景，可以是网络图片
        //设置默认显示语句
        setPlaceholder("Insert text here...");
        //设置编辑器是否可用
        setInputEnabled(true);

    }

    public void setPlaceholder(String str){
        super.setPlaceholder(str);
    }

    //正常文字
    public void setEditorFontSize(int px) {
        super.setEditorFontSize(px);
        super.setEditorFontColor(Color.BLACK);
    }

    //加粗
    public void setBold() {
        super.setBold();
    }

    //斜体
    public void setItalic() {
        super.setItalic();
    }

    //下划线
    public void setUnderline() {
        super.setUnderline();
    }

    //标题
    public void setHeading() {
        super.setHeading(4);
    }

    //插入图片
    public void getInsetImage() {
        //alt在h5中的作用是图片的失效后的替换文本，同样也是鼠标放上去显示的文本，还可以告诉搜索引擎这个图片是什么，用来加关键字
//        mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
//        "dachshund" + "\" style=\"max-width:100%");
        getImage();
    }

    private void getImage() {
        TakePhotoUtils.getGalleryImg((Activity) context, CamaraRequestCode.CAMARA_GET_IMG);//相册选择图片
    }

    public String insertImg(Uri bitmapUri) {
        /**
         * 下面是对图片进行压缩处理---并且统一复制到sdcard的takephoto文件夹
         */
        String filePath = TakePhotoUtils.getRealFilePathByUri(context, bitmapUri);//图片的真实路径
        try {
            filePath = TakePhotoUtils.saveFile(context, BitmapFactory.decodeFile(filePath), filePath, 20);//压缩图片得到真实路径，imgQuality为图片的质量，按100制，默认图片质量20%（即压缩80%），现在主流手机使用20%最佳---平均下来150k左右
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * imageview对大图显示效果不好，这里对原图进行压缩
         */
//        Bitmap images = BitmapFactory.decodeFile(filePath, TakePhotoUtils.getOptions(filePath, 4));//压缩图片的大小，按4倍来压缩

        return filePath;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

}
