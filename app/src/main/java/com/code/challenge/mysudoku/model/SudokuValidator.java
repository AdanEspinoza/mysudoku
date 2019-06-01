package com.code.challenge.mysudoku.model;

import java.util.HashSet;

/**
 * Created by adanesp on 5/31/2019
 */
public class SudokuValidator {
    private static SudokuValidator instance;

    private SudokuValidator(){}

    public static SudokuValidator getInstance(){
        if( instance == null ){
            instance = new SudokuValidator();
        }
        return instance;
    }

    /**
     * Methods to determin if the game was won
     * @param Sudoku
     * @return
     */
    public boolean checkSudoku( int[][] Sudoku){
        return (checkHorizontal(Sudoku) || checkVertical(Sudoku) || checkBoxes(Sudoku));
    }

    private boolean checkHorizontal(int[][] Sudoku) {
        for( int y = 0 ; y < 9 ; y++ ){
            for( int xPos = 0 ; xPos < 9 ; xPos++ ){

                if( Sudoku[xPos][y] == 0 ){
                    return false;
                }
                for( int x = xPos + 1 ; x < 9 ; x++ ){
                    if( Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0 ){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkVertical(int[][] Sudoku) {
        for( int x = 0 ; x < 9 ; x++ ){
            for( int yPos = 0 ; yPos < 9 ; yPos++ ){

                if( Sudoku[x][yPos] == 0 ){
                    return false;
                }
                for( int y = yPos + 1 ; y < 9 ; y++ ){
                    if( Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0 ){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkBoxes(int[][] Sudoku) {
        for( int xRegion = 0; xRegion < 3; xRegion++ ){
            for( int yRegion = 0; yRegion < 3 ; yRegion++ ){
                if( !checkBox(Sudoku, xRegion, yRegion) ){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBox(int[][] Sudoku , int xRegion , int yRegion){
        for( int xPos = xRegion * 3; xPos < xRegion * 3 + 3 ; xPos++ ){
            for( int yPos = yRegion * 3 ; yPos < yRegion * 3 + 3 ; yPos++ ){
                for( int x = xPos ; x < xRegion * 3 + 3 ; x++ ){
                    for( int y = yPos ; y < yRegion * 3 + 3 ; y++ ){
                        if( (( x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y] ) || Sudoku[x][y] == 0 ){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * Method to determin if the current sudoku is valid or not
     * @param Sudoku
     * @return
     */
    public boolean validateSudoku( int[][] Sudoku){
        // checking rows and columns
        HashSet row, col;
        for (int i = 0; i < 9; i++) {
            row = new HashSet();
            col = new HashSet();
            for (int j = 0; j < 9; j++) {
                if(!row.add(Sudoku[i][j]) && Sudoku[i][j] != 0) return false;
                if(!col.add(Sudoku[j][i]) && Sudoku[j][i] != 0) return false;
            }
        }
        // checking 9 boxes
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                row = new HashSet();
                for (int i = x; i < x + 3; i++) {
                    for (int j = y; j < y + 3; j++) {
                        if(!row.add(Sudoku[i][j]) && Sudoku[i][j] != 0) return false;
                    }
                }
            }
        }
        return true;
    }
}

