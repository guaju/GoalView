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
    private int lineStartX,lineStartY;
    private int lineStopX;
    private int lineStopY;


    public GoalLine(Context context) {
        super(context);
        //测量初始化
        initMeasure();
        //初始化画笔
        initPaint();
    }



    private void initMeasure() {
        screenWidth = ScreenUtil.getScreenWidth(getContext());
        goalLineWidth = (int) (screenWidth * 0.8);
        //给线的高度20dp，决定了线的高度
        goallineHeight = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineHeight);
        //线距离左边的距离
        goalLineLeftSep = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineLeftSep);
        //线距离顶部的距离
        goalLineTopSep = getContext().getResources().getDimensionPixelSize(R.dimen.goalLineTopSep);
        //文字笔的尺寸
        textPaintSize = getContext().getResources().getDimensionPixelSize(R.dimen.goalTextSize);
        //设置橙色线的粗度
        orangeLineWidth = 0;
        float screenMetricDgree = ScreenUtil.getScreenMetricDgree(getContext());
        Log.e("screenMetricDgree", "initMeasure: "+screenMetricDgree );
    }

    private void initPaint() {
        //画笔初始化

        //灰色线的画笔
        greyLinePaint = new Paint();
        greyLinePaint.setAntiAlias(true);
        //设置线两端的样式：圆形样式
        greyLinePaint.setStrokeCap(Paint.Cap.ROUND);
        greyLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greyLinePaint.setColor(getContext().getResources().getColor(R.color.goalLineGrey));
        greyLinePaint.setStrokeWidth(goallineHeight);
        //设置橙色画笔
        orangeLinePaint = new Paint();
        orangeLinePaint.setAntiAlias(true);
        orangeLinePaint.setStrokeCap(Paint.Cap.ROUND);
        orangeLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        orangeLinePaint.setColor(getContext().getResources().getColor(R.color.goalLineOrange));
        orangeLinePaint.setStrokeWidth(goallineHeight);
        //设置橙色文字画笔
        orangeTextPaint = new TextPaint();
        orangeTextPaint.setColor(getContext().getResources().getColor(R.color.goalLineOrange));
        orangeTextPaint.setTextSize(textPaintSize);
        //设置黑色文字画笔
        blackTextPaint = new TextPaint();
        blackTextPaint.setColor(getContext().getResources().getColor(R.color.black));
        blackTextPaint.setTextSize(textPaintSize);
        //设置灰色文字画笔
        greyTextPaint = new TextPaint();
        greyTextPaint.setColor(getContext().getResources().getColor(R.color.goalLineGrey));
        greyTextPaint.setTextSize(textPaintSize);
        //设置圆圈的画笔
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //先给的透明
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
    //为了决定控件的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到布局或者代码给本控件设置的margin值
        getMarginValues();
        //拿到父容器传给我的宽  测量模式AT_MOST   EXCACTLY    UN_SPECIFED
        width = MeasureSpec.getSize(widthMeasureSpec);   //不管宽了
        //把高低定死  = 顶部margin + 自定义的线距离顶部的距离 +线的粗度 + 底部margin+
        height = topMargin + goalLineTopSep + goallineHeight + bottomMargin + orderDrawable.getMinimumHeight();
        //调用系统的setMeasuredDimension
        setMeasuredDimension(width, height);
        Log.e("GUAJU1", "onMeasure: " + width + "--" + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        getMarginValues();
//        Rect rect = new Rect(goalLineLeftSep, goalLineTopSep, goalLineWidth, goallineHeight);
        Log.e("GUAJU1", "ondraw: " + leftMargin + "--" + rightMargin);
        //绘制最底部一层的灰色线  ：开始坐标是 自定义左间距+布局或者代码给的左margin
        canvas.drawLine(goalLineLeftSep + leftMargin
                ,   goalLineTopSep + topMargin
                ,  screenWidth - goalLineLeftSep - leftMargin - rightMargin
                , goalLineTopSep + topMargin,
                greyLinePaint);
        //橙色线
        canvas.drawLine(goalLineLeftSep + leftMargin ,
                goalLineTopSep + topMargin,
                goalLineLeftSep + leftMargin+orangeLineWidth, //橙色线的结束x坐标 orangeLineWidth
                goalLineTopSep + topMargin,
                orangeLinePaint
        );

        //绘制业绩及预约圆点
        int radius = (int) (goallineHeight / 2.2);   //定义圆点半径
        canvas.drawCircle(goalLineLeftSep + leftMargin + yejiDistance  , goalLineTopSep+ topMargin, radius, circlePaint);
        canvas.drawCircle(goalLineLeftSep + leftMargin + orderDistance , goalLineTopSep+ topMargin, radius, circlePaint);

        yejiDrawable = getContext().getResources().getDrawable(R.drawable.completed);

        float drawableCaculateWidth = yejiDrawable.getMinimumWidth() * 26.0f / 34.0f;
        yejiDrawable.setBounds((int) (goalLineLeftSep + leftMargin + yejiDistance - drawableCaculateWidth)
                , goalLineTopSep+ topMargin+ goallineHeight/2
                , (int) (goalLineLeftSep + leftMargin + yejiDistance + (yejiDrawable.getMinimumWidth()- drawableCaculateWidth))
                , goalLineTopSep+ topMargin+ goallineHeight/2+yejiDrawable.getMinimumHeight());
        
        yejiDrawable.draw(canvas);

        orderDrawable.setBounds(goalLineLeftSep + leftMargin + orderDistance - (int) drawableCaculateWidth
                , goalLineTopSep+ topMargin+ goallineHeight/2
                , goalLineLeftSep + leftMargin + orderDistance + (int) (orderDrawable.getMinimumWidth() - drawableCaculateWidth)
                , goalLineTopSep+ topMargin+ goallineHeight/2+ orderDrawable.getMinimumHeight());
        orderDrawable.draw(canvas);



        //绘制四角文字
        canvas.drawText(yejiStr, 0, textPaintSize, orangeTextPaint);
        canvas.drawText(orderStr, width - blackTextPaint.measureText(orderStr+" "), textPaintSize, blackTextPaint);
        canvas.drawText(startStr, 0, height - textPaintSize, greyTextPaint);
        canvas.drawText(goalStr, width - greyTextPaint.measureText(goalStr+" "), height - textPaintSize, greyTextPaint);

        invalidate();
    }

    public void setGoal(float goal, float maxTarget) {

        if (goal<0){
            goal=0;
        }if (maxTarget<0){
            maxTarget=0;
        }
        if (goal>maxTarget){
            goal=maxTarget;
        }
        this.goal=goal;
        this.maxTarget=maxTarget;

        float v = goal / maxTarget;
        int orangeIncrease = (int) ((screenWidth -3*leftMargin-3*goalLineLeftSep-rightMargin) * v);
        orangeLineWidth = orangeIncrease;
        Log.e("GUAJU", "setGoal: " + orangeIncrease);
        if (orangeLineWidth > 0) {
            invalidate();
        }
    }

    public void setYejiNum(float yejiNum) {
        if (yejiNum<0){
           yejiNum=0;
        }
        if (yejiNum>maxTarget){
            yejiNum=maxTarget;
        }
        this.yejiNum=yejiNum;

        float v = yejiNum / maxTarget;
        yejiDistance = (int) ((screenWidth -3*leftMargin-3*goalLineLeftSep-rightMargin) * v);
        circlePaint.setColor(getContext().getResources().getColor(R.color.white));


        invalidate();

    }

    public void setOrderNum(float orderNum) {
        if (orderNum<0){
            orderNum=0;
        }if (orderNum>maxTarget){
            orderNum=maxTarget;
        }
        this.orderNum = orderNum;
        float v = orderNum / maxTarget;
        orderDistance = (int) ((screenWidth -3*leftMargin-3*goalLineLeftSep-rightMargin) * v);
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
