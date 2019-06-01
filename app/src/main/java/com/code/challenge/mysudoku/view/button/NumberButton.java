package com.code.challenge.mysudoku.view.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.code.challenge.mysudoku.model.SudokuEngine;

/**
 * Created by adanesp on 5/31/2019
 */
public class NumberButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    private int number;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SudokuEngine.getInstance().setNumber(number);
    }

    public void setNumber(int number){
        this.number = number;
    }
}
