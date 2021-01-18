package com.example.pirates_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class Start_screen extends AppCompatActivity {
    Chip chip_or;
    Chip chip_gr;
    Chip chip_red;
    Chip chip_bl;
    Chip chip_yel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        chip_or = findViewById(R.id.chip_or);
        chip_gr = findViewById(R.id.chip_gr);
        chip_red = findViewById(R.id.chip_red);
        chip_bl = findViewById(R.id.chip_bl);
        chip_yel = findViewById(R.id.chip_yel);


        chip_or.setOnClickListener(onClickListener);
        chip_gr.setOnClickListener(onClickListener);
        chip_red.setOnClickListener(onClickListener);
        chip_bl.setOnClickListener(onClickListener);
        chip_yel.setOnClickListener(onClickListener);

    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chip_or: {
                    Intent intent = new Intent(Start_screen.this, MainActivity.class);
                    intent.putExtra("color", "orange");
                    startActivity(intent);
                    break;
                }
                case R.id.chip_gr: {
                    Intent intent = new Intent(Start_screen.this, MainActivity.class);
                    intent.putExtra("color", "green");
                    startActivity(intent);
                    break;
                }
                case R.id.chip_red: {
                    Intent intent = new Intent(Start_screen.this, MainActivity.class);
                    intent.putExtra("color", "red");
                    startActivity(intent);
                    break;
                }
                case R.id.chip_bl: {
                    Intent intent = new Intent(Start_screen.this, MainActivity.class);
                    intent.putExtra("color", "blue");
                    startActivity(intent);
                    break;
                }
                case R.id.chip_yel: {
                    Intent intent = new Intent(Start_screen.this, MainActivity.class);
                    intent.putExtra("color", "yellow");
                    startActivity(intent);
                    break;
                }
            }
        }
    };
}
