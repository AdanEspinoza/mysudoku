package com.code.challenge.mysudoku.view.button;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.code.challenge.mysudoku.R;

/**
 * Created by adanesp on 5/31/2019
 */
public class ButtonGridView extends GridView {

    public ButtonGridView(Context context , AttributeSet attrs ){
        super(context , attrs);

        ButtonsGridViewAdapter gridViewAdapter = new ButtonsGridViewAdapter(context);

        setAdapter(gridViewAdapter);
    }

    class ButtonsGridViewAdapter extends BaseAdapter {

        private Context context;

        public ButtonsGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 11;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if( v == null ){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(R.layout.button, parent , false);

                NumberButton btn;
                btn = (NumberButton)v;
                btn.setTextSize(25);
                btn.setId(position);

                if( position < 9 ) {
                    btn.setText(String.valueOf(position + 1));
                    btn.setNumber(position + 1);

                }else if(position == 10) {
                    btn.setText(R.string.validate);
                    btn.setNumber(-1);
                }else {
                    btn.setText(R.string.delete);
                    btn.setNumber(0);
                }
                return btn;
            }

            return v;
        }

    }
}
