package com.example.pirates_game;

import android.app.ActivityOptions;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Big_card extends AppCompatActivity {

    private ImageView big_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        big_im = findViewById(R.id.imageView_big);

        int img_link = getIntent().getExtras().getInt("id_image");
        big_im.setImageResource(img_link);
        big_im.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_big: {
                    onBackPressed();
                    break;
                }
            }
        }
    };
}
