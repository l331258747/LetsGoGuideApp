package com.njz.letsgoappguides.util.cilpView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.njz.letsgoappguides.R;

/**
 * 头像上传裁剪框
 */
public class ClipView extends View {
    private Paint paint = new Paint();
    //画裁剪区域边框的画笔
    private Paint borderPaint = new Paint();
    //裁剪框水平方向间距
    private float mHorizontalPadding;
    //裁剪框边框宽度
    private int clipBorderWidth;
    //裁剪圆框的半径
    private int clipRadiusWidth;
    //裁剪框矩形宽度
    private int clipWidth;
    //裁剪框类别，（圆形、矩形），默认为圆形
    private ClipType clipType = ClipType.RECTANGLE;
    private Xfermode xfermode;
    private Xfermode xfermode2;

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //去锯齿
        paint.setAntiAlias(true);
        borderPaint.setStyle(Style.STROKE);
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStrokeWidth(clipBorderWidth);
        borderPaint.setAntiAlias(true);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        xfermode2 = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        //通过Xfermode的DST_OUT来产生中间的透明裁剪区域，一定要另起一个Layer（层）
        canvas.saveLayer(0, 0, this.getWidth(), this.getHeight(), null, LAYER_FLAGS);
        //设置背景

        //绘制圆形裁剪框
        if (clipType == ClipType.HEADIMG) {
            paint.setXfermode(xfermode2);
            Bitmap mp = BitmapFactory.decodeResource(getResources(), R.mipmap.clip_head);
            canvas.drawBitmap(mp,this.getWidth() / 2 - mp.getWidth() / 2, this.getHeight() / 2 - mp.getHeight() / 2, paint);
        }else if (clipType == ClipType.RECTANGLE) { //绘制矩形裁剪框
            canvas.drawColor(Color.parseColor("#a8000000"));
            paint.setXfermode(xfermode);
            clipWidth = this.getWidth() * 8 / 15;
            canvas.drawRect(mHorizontalPadding, this.getHeight() / 2 - clipWidth / 2,
                    this.getWidth() - mHorizontalPadding, this.getHeight() / 2 + clipWidth / 2, paint);
            canvas.drawRect(mHorizontalPadding, this.getHeight() / 2 - clipWidth / 2,
                    this.getWidth() - mHorizontalPadding, this.getHeight() / 2 + clipWidth / 2, borderPaint);
        }
        canvas.restore();
    }

    /**
     * 获取裁剪区域的Rect
     *
     * @return
     */
    public Rect getClipRect() {
        if(clipType == ClipType.HEADIMG){
            Rect rect = new Rect();
            //宽度的一半 - 圆的半径
            rect.left = (this.getWidth() / 2 - clipRadiusWidth);
            //宽度的一半 + 圆的半径
            rect.right = (this.getWidth() / 2 + clipRadiusWidth);
            //高度的一半 - 圆的半径
            rect.top = (this.getHeight() / 2 - clipRadiusWidth);
            //高度的一半 + 圆的半径
            rect.bottom = (this.getHeight() / 2 + clipRadiusWidth);
            return rect;
        }

        Rect rect = new Rect();
        //宽度的一半 - 圆的半径
        rect.left = 0;
        //宽度的一半 + 圆的半径
        rect.right = this.getWidth();
        //高度的一半 - 圆的半径
        rect.top = getHeight() /2 - getWidth() * 8 /15 /2;
        //高度的一半 + 圆的半径
        rect.bottom = getHeight() /2 + getWidth() * 8 /15 /2;
        return rect;
    }

    /**
     * 设置裁剪框边框宽度
     *
     * @param clipBorderWidth
     */
    public void setClipBorderWidth(int clipBorderWidth) {
        this.clipBorderWidth = clipBorderWidth;
        borderPaint.setStrokeWidth(clipBorderWidth);
        invalidate();
    }

    /**
     * 设置裁剪框水平间距
     *
     * @param mHorizontalPadding
     */
    public void setmHorizontalPadding(float mHorizontalPadding) {
        this.mHorizontalPadding = mHorizontalPadding;
        this.clipRadiusWidth = (int) (getScreenWidth(getContext()) - 2 * mHorizontalPadding) / 2;
        this.clipWidth = clipRadiusWidth * 2;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /**
     * 设置裁剪框类别
     *
     * @param clipType
     */
    public void setClipType(ClipType clipType) {
        this.clipType = clipType;
    }

    /**
     * 裁剪框类别，圆形、矩形
     */
    public enum ClipType {
        RECTANGLE,HEADIMG
    }
}
