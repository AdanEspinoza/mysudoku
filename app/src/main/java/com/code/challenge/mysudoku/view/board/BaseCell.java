package com.code.challenge.mysudoku.view.board;

import android.content.Context;
import android.view.View;

/**
 * Created by adanesp on 5/31/2019
 */
public class BaseCell extends View {


    private int value;
    public boolean modifiable = true;
    private boolean isPressed = false;

    public BaseCell(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable(){
        modifiable = false;
    }

    public void setInitValue(int value){
        this.value = value;
        invalidate();
    }

    public void setValue( int value ){
        if( modifiable ){
            this.value = value;
        }

        invalidate();
    }

    public int getValue(){
        return value;
    }

    @Override
    public boolean isPressed() {
        return isPressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
