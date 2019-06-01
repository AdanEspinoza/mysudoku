package com.code.challenge.mysudoku.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.code.challenge.mysudoku.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEasy, btnMedium, btnHard, btnTB;
    public static final String GAME_MODE = "game_mode";
    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";
    public static final String TB = "tb_challenge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEasy = (findViewById(R.id.btnEasy));
        btnMedium = findViewById(R.id.btnMedium);
        btnHard = findViewById(R.id.btnHard);
        btnTB = findViewById(R.id.btnTacoBell);

        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);
        btnTB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, GameActivity.class);

        switch (v.getId()){
            case R.id.btnEasy:
                intent.putExtra(GAME_MODE, EASY);
                break;
            case R.id.btnMedium: intent.putExtra(GAME_MODE, MEDIUM);
                break;
            case R.id.btnHard: intent.putExtra(GAME_MODE, HARD);
                break;
            case R.id.btnTacoBell: intent.putExtra(GAME_MODE, TB);
                break;
        }

        startActivity(intent);
    }
}
