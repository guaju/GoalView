package com.guaju.goalview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guaju.goalview.R;

/**
 * Created by guaju on 2018/4/18.
 */

public class GoalLine extends View {

    private int screenWidth;
    private int goalLineWidth;
    private float goal;
    private int goallineHeight, goalLineLeftSep, goalLineTopSep;
    private Paint greyLinePaint, orangeLinePaint, circlePaint;
    private int orangeLineWidth;
    private float maxTarget;
    private float yejiNum, orderNum;
    private int yejiDistance;
    private int orderDistance;
    private Canvas canvas;
    private Drawable yejiDrawable;
    private Drawable orderDrawable;
    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;
    private int textPaintSize;
    private TextPaint orangeTextPaint, blackTextPaint, greyTextPaint;
    private String yejiStr = "";
    private String orderStr = "";
    private String startStr = "";
    private String goalStr = "";
    private int width;
    private int height;


    public GoalLine(Context context) {
        super(context);
        initMeasure();
        initPaint();
    }

    private void initMeasure() {
        screenWidth = ScreenUtil.getScreenWidth(getContext());
        goalLineWidth = (int) (screenWidth * 0.8);
        goallineHeight = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineHeight);
        goalLineLeftSep = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineLeftSep);
        goalLineTopSep = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineTopSep);
        textPaintSize = getContext().getResources().getDimensionPixelSize(R.dimen.goalTextSize);
        orangeLineWidth = goalLineLeftSep;

    }

    private void initPaint() {
        greyLinePaint = new Paint();
        greyLinePaint.setAntiAlias(true);
        greyLinePaint.setStrokeCap(Paint.Cap.ROUND);
        greyLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greyLinePaint.setColor(getContext().getResources().getColor(R.color.goalLineGrey));
        greyLinePaint.setStrokeWidth(goallineHeight);

        orangeLinePaint = new Paint();
        orangeLinePaint.setAntiAlias(true);
        orangeLinePaint.setStrokeCap(Paint.Cap.ROUND);
        orangeLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        orangeLinePaint.setColor(getContext().getResources().getColor(R.color.goalLineOrange));
        orangeLinePaint.setStrokeWidth(goallineHeight);

        orangeTextPaint = new TextPaint();
        orangeTextPaint.setColor(getContext().getResources().getColor(R.color.goalLineOrange));
        orangeTextPaint.setTextSize(textPaintSize);

        blackTextPaint = new TextPaint();
        blackTextPaint.setColor(getContext().getResources().getColor(R.color.black));
        blackTextPaint.setTextSize(textPaintSize);

        greyTextPaint = new TextPaint();
        greyTextPaint.setColor(getContext().getResources().getColor(R.color.goalLineGrey));
        greyTextPaint.setTextSize(textPaintSize);


        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setColor(getContext().getResources().getColor(R.color.transparent));

    }

    public GoalLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initMeasure();
        initPaint();
    }

    public GoalLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getMarginValues();
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = topMargin + goalLineTopSep + goallineHeight + bottomMargin + orderDrawable.getMinimumHeight();
        setMeasuredDimension(width, height);
        Log.e("GUAJU1", "onMeasure: " + width + "--" + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        getMarginValues();
//        Rect rect = new Rect(goalLineLeftSep, goalLineTopSep, goalLineWidth, goallineHeight);
        Log.e("GUAJU1", "ondraw: " + leftMargin + "--" + rightMargin);
        canvas.drawLine(goalLineLeftSep + leftMargin
                , goalLineTopSep + topMargin
                , screenWidth - goalLineLeftSep - leftMargin - rightMargin
                , goalLineTopSep + topMargin,
                greyLinePaint);
        canvas.drawLine(goalLineLeftSep + getLeftPaddingOffset(),
                goalLineTopSep + topMargin,
                orangeLineWidth,
                goalLineTopSep + topMargin,
                orangeLinePaint
        );

        //绘制业绩及预约圆点
        int radius = (int) (goallineHeight / 2.2);
        canvas.drawCircle(goalLineLeftSep + yejiDistance - radius, (goalLineTopSep + goallineHeight) / 2 + topMargin, radius, circlePaint);
        canvas.drawCircle(goalLineLeftSep + orderDistance - radius, (goalLineTopSep + goallineHeight) / 2 + topMargin, radius, circlePaint);

        yejiDrawable = getContext().getResources().getDrawable(R.drawable.completed);

        float drawableCaculateWidth = yejiDrawable.getMinimumWidth() * 26.0f / 34.0f;
        yejiDrawable.setBounds(goalLineLeftSep + yejiDistance - (int) drawableCaculateWidth
                , goalLineTopSep / 2 + goallineHeight + topMargin
                , goalLineLeftSep + yejiDistance + (int) (yejiDrawable.getMinimumWidth() - drawableCaculateWidth)
                , goalLineTopSep / 2 + goallineHeight + yejiDrawable.getMinimumHeight() + topMargin);
        yejiDrawable.draw(canvas);

        orderDrawable.setBounds(goalLineLeftSep + orderDistance - (int) drawableCaculateWidth
                , goalLineTopSep / 2 + goallineHeight + topMargin
                , goalLineLeftSep + orderDistance + (int) (orderDrawable.getMinimumWidth() - drawableCaculateWidth)
                , goalLineTopSep / 2 + goallineHeight + orderDrawable.getMinimumHeight() + topMargin);
        orderDrawable.draw(canvas);

        //绘制四角文字
        canvas.drawText(yejiStr, 0, textPaintSize, orangeTextPaint);
        canvas.drawText(orderStr, width - blackTextPaint.measureText(orderStr+" "), textPaintSize, blackTextPaint);
        canvas.drawText(startStr, 0, height - textPaintSize, greyTextPaint);
        canvas.drawText(goalStr, width - greyTextPaint.measureText(goalStr+" "), height - textPaintSize, greyTextPaint);

        invalidate();
    }

    public void setGoal(float goal, float maxTarget) {
        this.goal = goal;
        this.maxTarget = maxTarget;
        float v = goal / maxTarget;
        int orangeIncrease = (int) ((screenWidth - 2 * goalLineLeftSep) * v);
        orangeLineWidth = orangeIncrease;
        Log.e("GUAJU", "setGoal: " + orangeIncrease);
        if (orangeLineWidth > 0) {
            invalidate();
        }
    }

    public void setYejiNum(float yejiNum) {
        this.yejiNum = yejiNum;
        float v = yejiNum / maxTarget;
        yejiDistance = (int) ((screenWidth - 2 * goalLineLeftSep) * v);
        circlePaint.setColor(getContext().getResources().getColor(R.color.white));


        invalidate();

    }

    public void setOrderNum(float orderNum) {
        this.orderNum = orderNum;
        float v = orderNum / maxTarget;
        orderDistance = (int) ((screenWidth - 2 * goalLineLeftSep) * v);
        circlePaint.setColor(getContext().getResources().getColor(R.color.white));
        orderDrawable = getContext().getResources().getDrawable(R.drawable.order);
        invalidate();
    }

    public void getMarginValues() {

        if (getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) getLayoutParams();
            leftMargin = ll.leftMargin;
            rightMargin = ll.rightMargin;
            topMargin = ll.topMargin;
            bottomMargin = ll.bottomMargin;

        } else if (getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams ll = (RelativeLayout.LayoutParams) getLayoutParams();
            leftMargin = ll.leftMargin;
            rightMargin = ll.rightMargin;
            topMargin = ll.topMargin;
            bottomMargin = ll.bottomMargin;
        }


        Log.e("GUAJU1", "initMeasure: " + leftMargin + "--" + rightMargin);

    }

    public void setStrValues(String yejiStr, String orderStr, String startStr, String goalStr) {
        this.yejiStr = yejiStr;
        this.orderStr = orderStr;
        this.startStr = startStr;
        this.goalStr = goalStr;
        invalidate();
    }

}
