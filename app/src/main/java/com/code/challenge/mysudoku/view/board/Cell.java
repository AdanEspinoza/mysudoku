package com.code.challenge.mysudoku.view.board;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * Created by adanesp on 5/31/2019
 */
public class Cell extends BaseCell {

    private Paint mPaint;
    private int xPos, yPos;

    public Cell(Context context, int xPos, int yPos){
        super(context);
        this.xPos = xPos;
        this.yPos = yPos;
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNumber(canvas);
        drawLines(canvas);
    }

    private void drawNumber(Canvas canvas){
        if(modifiable) {
            mPaint.setColor(Color.BLUE);
        }else {
            mPaint.setColor(Color.BLACK);
        }
        mPaint.setTextSize(80);
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        mPaint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

        if( getValue() != 0 ){
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2	, mPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        int cellPos = (xPos % 3) + (3 * (yPos % 3));
        switch (cellPos) {
            case 0:
            case 3:
            case 6:
                //draw left border
                canvas.drawLine(0,0, 0, getHeight(), mPaint);
            break;
        }

        switch (cellPos) {
            case 0:
            case 1:
            case 2:
                //draw top border
                canvas.drawLine(0,0, getWidth(), 0, mPaint);
                break;
        }

        switch (cellPos) {
            case 2:
            case 5:
            case 8:
                //draw right border
                canvas.drawLine(getWidth(),0, getWidth(), getHeight(), mPaint);
                break;
        }

        switch (cellPos) {
            case 6:
            case 7:
            case 8:
                //draw bottom border
                canvas.drawLine(0,getHeight(), getWidth(), getHeight(), mPaint);
                break;
        }
    }
}
