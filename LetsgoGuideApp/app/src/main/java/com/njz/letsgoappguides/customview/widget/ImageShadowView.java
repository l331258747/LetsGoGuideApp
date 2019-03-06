package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.njz.letsgoappguides.R;

/**
 * Created by Administrator on 2018/12/26.
 */

public class ImageShadowView extends AppCompatImageView{

    private Paint mPaint;

    public ImageShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        BlurMaskFilter bf = new BlurMaskFilter(20,BlurMaskFilter.Blur.INNER);
        mPaint.setColor(getContext().getResources().getColor(R.color.gray_999));
        mPaint.setMaskFilter(bf);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.guide_icon);
        canvas.drawBitmap(mBitmap.extractAlpha(mPaint, null), 20,20, mPaint);
        canvas.drawBitmap(mBitmap,0,0,null);
    }
}
