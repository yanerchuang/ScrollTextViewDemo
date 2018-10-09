package com.ywj.scrolltextviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 滚动字幕
 * startScroll() 开始滚动
 */
public class ScrollTextView extends android.support.v7.widget.AppCompatTextView {

    private static final String TAG = "ScrollTextView";
    private String mText = "";
    private int mOffsetX = 0;
    private Rect  mRect = new Rect();

    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
    /**
     * 速度，负数左移，正数右移。
     */
    private int mSpeed = -3;
    /**
     * 一秒内更新次数
     */
    private int FPS = 30;
    private int width;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
    }

    public void startScroll() {

        if (mSpeed < 0) {
            //左移
        } else if (mSpeed > 0) {
            //右移
            mOffsetX = width - mRect.right;
        }

        executorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                //如果View能容下所有文字，直接返回
                if (mRect.right < width) {
                    Log.w(TAG, "content width is smaller than view width,it's enough to show,return.");
                    return;
                }

                if (mSpeed < 0) {
                    //左移
                    if (mOffsetX < -mRect.right) {
                        mOffsetX = width;
                    }
                } else if (mSpeed > 0) {
                    //右移
                    if (mOffsetX > width) {
                        mOffsetX = -mRect.right;
                    }
                }else {
                    Log.w(TAG, "mSpeed can't be zero.");
                    return;
                }
                mOffsetX += mSpeed;
                postInvalidate();

            }
        }, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mText = getText().toString();
        TextPaint textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        //获取文本区域大小，保存在mRect中。
        textPaint.getTextBounds(mText, 0, mText.length(), mRect);
        float mTextCenterVerticalToBaseLine = (-textPaint.ascent() + textPaint.descent()) / 2 - textPaint.descent();
        int width = getWidth();
        if (mRect.right < width) {
            canvas.drawText(mText, 0, getHeight() / 2 + mTextCenterVerticalToBaseLine, textPaint);
        } else {
            canvas.drawText(mText, mOffsetX, getHeight() / 2 + mTextCenterVerticalToBaseLine, textPaint);
        }
    }

    /**
     * 视图移除时销毁任务和定时器
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        Log.e(TAG, "onDetachedFromWindow");
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    /**
     * 设置速度，每次更新的像素偏移大小
     *
     * @param speed >0:向右；<0 向左
     */
    public void setSpeed(int speed) {
        this.mSpeed = speed;
    }

    /**
     * 设置每秒刷新次数
     *
     * @param FPS 每秒传输帧数(Frames Per Second)
     */
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

}
