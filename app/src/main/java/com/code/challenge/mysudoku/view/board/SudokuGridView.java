package com.code.challenge.mysudoku.view.board;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.code.challenge.mysudoku.R;
import com.code.challenge.mysudoku.model.SudokuEngine;
import com.code.challenge.mysudoku.viewmodel.GameGridVM;

/**
 * Created by adanesp on 5/31/2019
 * This class handles the UI for my custom gridview
 */
public class SudokuGridView extends GridView{

    private final Context context;
    private final GameGridVM mGameGridVM;

    public SudokuGridView(final Context context , AttributeSet attrs) {
        super(context,attrs);

        this.context = context;
        mGameGridVM = new GameGridVM(context, null);

        SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);

        setAdapter(gridViewAdapter);

        setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = position % 9;
                int y = position / 9;

                SudokuEngine.getInstance().setSelectedPosition(x, y);
                Cell[][] Sudoku = mGameGridVM.getGrid();
                Sudoku[x][y].setPressed(true);
                Sudoku[x][y].setBackgroundColor(context.getResources().getColor(R.color.color_teal_200));
                //gridViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    class SudokuGridViewAdapter extends BaseAdapter {

        private Context context;

        public SudokuGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 81;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           /* int x = position % 9;
            int y = position / 9;
            SudokuCell[][] Sudoku = mGameGridVM.getGrid();
            if(Sudoku[x][y].isPressed()){
                Sudoku[x][y].setBackgroundColor(context.getResources().getColor(R.color.color_teal_200));
            }else {
                Sudoku[x][y].setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            }*/
            return SudokuEngine.getInstance().getGrid().getItem(position);
        }
    }
}
