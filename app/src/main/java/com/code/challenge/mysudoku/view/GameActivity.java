package com.code.challenge.mysudoku.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.code.challenge.mysudoku.R;
import com.code.challenge.mysudoku.model.SudokuEngine;

public class GameActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        String gameMode = MainActivity.TB;

        if (extras != null) {
            gameMode = extras.getString(MainActivity.GAME_MODE);
        }

        switch (gameMode){
            case MainActivity.EASY:
            case MainActivity.MEDIUM:
            case MainActivity.HARD:
                SudokuEngine.getInstance().createGrid(this, gameMode);
                break;
            case MainActivity.TB:
                SudokuEngine.getInstance().createGridFromFile(this);
                break;
            default:
                    SudokuEngine.getInstance().createGridFromFile(this);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.progressTitle);
        alertDialog.setMessage(R.string.progressQuestion);
        // Add the buttons
        alertDialog.setPositiveButton(R.string.progressNO, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        alertDialog.setNegativeButton(R.string.progressYES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameActivity.super.onBackPressed();
            }
        });
        alertDialog.show();
    }
}