package com.code.challenge.mysudoku.model;

import android.content.Context;
import android.util.Log;

import com.code.challenge.mysudoku.viewmodel.GameGridVM;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adanesp on 5/31/2019
 */
public class SudokuEngine {
    private static SudokuEngine instance;

    private GameGridVM grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private SudokuEngine(){}

    public static SudokuEngine getInstance(){
        if( instance == null ){
            instance = new SudokuEngine();
        }
        return instance;
    }

    /**
     * Method to generate a random sudoku, then display it on GameGridVM
     * @param context
     * @param gameMode
     */
    public void createGrid( Context context, String gameMode ){
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku, gameMode);
        grid = new GameGridVM(context);
        grid.setGrid(Sudoku, false);
    }

    /**
     * Method to read the sudoku from json file
     * @param context
     */
    public void createGridFromFile(Context context) {
        List<List<String>> inputList = readJson(context);
        int [][] sudokuFromFile = convertInputListToArray(inputList);
        grid = new GameGridVM(context);
        grid.setGrid(sudokuFromFile, true);
    }

    public GameGridVM getGrid(){
        return grid;
    }

    public void setSelectedPosition( int x , int y ){
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber( int number ){
        if(number == -1) {
            grid.validateGame();
            return;
        }
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        grid.checkGame();
    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("input.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private List<List<String>> readJson(Context context){
        List<String> rowList = new ArrayList<>();
        List<List<String>> inputList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("board");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();

            for (int i = 0; i < m_jArry.length(); i++) {
                String row = m_jArry.getString(i);
                row = row.replaceAll("[\\\"\\[\\]]", "");
                row = row.replace(".", "0");
                rowList = Arrays.asList(row.trim().split(","));
                inputList.add(rowList);
                Log.d("Row as list:", rowList.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Row as list:", inputList.toString());
        return inputList;
    }

    private int[][] convertInputListToArray(List<List<String>> inputList) {
        int [][] sudokuFromFile = new int[9][9];
        for(int i=0; i< inputList.size(); i++){
            List<String> row = inputList.get(i);
            for(int j=0; j<row.size(); j++){
                try {
                    String value = row.get(j);
                    sudokuFromFile[j][i] = Integer.valueOf(value);
                }catch (NumberFormatException e){
                    sudokuFromFile[j][i] = 0;
                }
            }
        }

        return sudokuFromFile;
    }

    private void printSudoku(int Sudoku[][]) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(Sudoku[y][x] + "|");
            }
            System.out.println();
        }
    }
}
