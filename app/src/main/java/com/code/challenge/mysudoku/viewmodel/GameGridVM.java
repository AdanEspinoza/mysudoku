package com.code.challenge.mysudoku.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.code.challenge.mysudoku.R;
import com.code.challenge.mysudoku.model.SudokuValidator;
import com.code.challenge.mysudoku.view.board.Cell;

/**
 * Created by adanesp on 5/31/2019
 */
public class GameGridVM {
    private Cell[][] Sudoku = new Cell[9][9];

    private Context context;

    public GameGridVM(Context context ){
        this.context = context;
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y] = new Cell(context);
            }
        }
    }

    public void setGrid( int[][] grid, boolean isTacoBellChallenge){
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y].setInitValue(grid[x][y]);
                if( grid[x][y] != 0 && !isTacoBellChallenge){
                    Sudoku[x][y].setNotModifiable();
                }
            }
        }
    }

    public Cell[][] getGrid(){
        return Sudoku;
    }

    public Cell getItem(int x , int y ){
        return Sudoku[x][y];
    }

    public Cell getItem(int position ){
        int x = position % 9;
        int y = position / 9;

        return Sudoku[x][y];
    }

    public void setItem( int x , int y , int number ){
        Sudoku[x][y].setValue(number);
    }

    public void checkGame(){
        int [][] sudokuGrid = new int[9][9];
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++ ){
                sudokuGrid[x][y] = getItem(x,y).getValue();
            }
        }

        if( SudokuValidator.getInstance().checkSudoku(sudokuGrid)){
            Toast.makeText(context, R.string.solved, Toast.LENGTH_LONG).show();
        }
    }

    public void validateGame(){
        int [][] sudGrid = new int[9][9];
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++ ){
                sudGrid[x][y] = getItem(x,y).getValue();
            }
        }

        if( SudokuValidator.getInstance().validateSudoku(sudGrid) ){
            Toast.makeText(context, R.string.valid, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }
}
