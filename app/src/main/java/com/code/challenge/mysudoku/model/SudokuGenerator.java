package com.code.challenge.mysudoku.model;

import com.code.challenge.mysudoku.view.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by adanesp on 5/31/2019
 */
public class SudokuGenerator {
    private static SudokuGenerator instance;

    private ArrayList<ArrayList<Integer>> availableList = new ArrayList<ArrayList<Integer>>();

    private Random rand = new Random();

    private SudokuGenerator(){}

    public static SudokuGenerator getInstance(){
        if( instance == null ){
            instance = new SudokuGenerator();
        }
        return instance;
    }

    /**
     * Generate a random list of numbers depending of the available numbers in a row
     * @return
     */
    public int[][] generateGrid(){
        int[][] Sudoku = new int[9][9];
        int currPosition = 0;

        while( currPosition < 81 ){
            if( currPosition == 0 ){
                clearGrid(Sudoku);
            }
            if( availableList.get(currPosition).size() != 0 ){
                int i = rand.nextInt(availableList.get(currPosition).size());
                int number = availableList.get(currPosition).get(i);

                if( !checkConflict(Sudoku, currPosition , number)){
                    int xPos = currPosition % 9;
                    int yPos = currPosition / 9;

                    Sudoku[xPos][yPos] = number;
                    availableList.get(currPosition).remove(i);
                    currPosition++;
                }else{
                    availableList.get(currPosition).remove(i);
                }

            }else{
                for( int i = 1 ; i <= 9 ; i++ ){
                    availableList.get(currPosition).add(i);
                }
                currPosition--;
            }
        }
        return Sudoku;
    }

    /**
     * Method to remove elements to start playing
     * This is random
     * @param Sudoku
     * @param gameMode
     * @return
     */
    public int[][] removeElements( int[][] Sudoku, String gameMode){
        int i = 0;
        int difficulty = 0;

        switch (gameMode){
            case MainActivity.EASY:
                difficulty = 25;
                break;
            case MainActivity.MEDIUM:
                difficulty = 35;
                break;
            case MainActivity.HARD:
                difficulty = 45;
                break;
            default:
                difficulty = 25;
                break;
        }

        while( i < difficulty ){
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if( Sudoku[x][y] != 0 ){
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;

    }

    private void clearGrid(int [][] Sudoku){
        availableList.clear();

        for( int y =  0; y < 9 ; y++ ){
            for( int x = 0 ; x < 9 ; x++ ){
                Sudoku[x][y] = -1;
            }
        }

        for( int x = 0 ; x < 81 ; x++ ){
            availableList.add(new ArrayList<Integer>());
            for( int i = 1 ; i <= 9 ; i++){
                availableList.get(x).add(i);
            }
        }
    }

    private boolean checkConflict( int[][] Sudoku , int currentPos , final int number){
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if( checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number) ){
            return true;
        }

        return false;
    }

    /**
     * Return true if there is a conflict
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    private boolean checkHorizontalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int x = xPos - 1; x >= 0 ; x-- ){
            if( number == Sudoku[x][yPos]){
                return true;
            }
        }

        return false;
    }

    private boolean checkVerticalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int y = yPos - 1; y >= 0 ; y-- ){
            if( number == Sudoku[xPos][y] ){
                return true;
            }
        }

        return false;
    }

    private boolean checkRegionConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for( int x = xRegion * 3 ; x < xRegion * 3 + 3 ; x++ ){
            for( int y = yRegion * 3 ; y < yRegion * 3 + 3 ; y++ ){
                if( ( x != xPos || y != yPos ) && number == Sudoku[x][y] ){
                    return true;
                }
            }
        }

        return false;
    }
}