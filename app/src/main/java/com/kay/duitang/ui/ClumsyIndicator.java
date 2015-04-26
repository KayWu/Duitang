package com.kay.duitang.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.kay.duitang.R;

/**
 * Created by Kay on 15/4/26.
 */
public class ClumsyIndicator extends View {

    // 设置个数
    private int mCount;

    // 当前选择
    private int selectedItem = 0;

    // 圆形半径
    private int mRadius = 4;

    // 选中圆形半径
    private int mSelectedRadius = 6;

    // 圆形之间的间隔
    private int mSpace = 25;

    // 画笔
    private Paint mPaint;

    public ClumsyIndicator(Context context) {
        this(context, null);
    }

    public ClumsyIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClumsyIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.light_grey));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setViewPager(ViewPager pager) {
        mCount = pager.getAdapter().getCount();
        invalidate();
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft() + getPaddingRight() + mSpace * mCount;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = getPaddingTop() + getPaddingBottom() + mSelectedRadius * 2;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = getHeight() / 2f;
        float x = mSpace / 2f;
        for (int i = 0; i < mCount; i++) {
            if (i != selectedItem) {
                canvas.drawCircle(x, y, mRadius, mPaint );
            } else {
                canvas.drawCircle(x, y, mSelectedRadius, mPaint );
            }
            x += mSpace;
        }
    }

}
