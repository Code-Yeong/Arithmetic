package com.example.administrator.arithmetic_master.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.arithmetic_master.R;

import java.io.IOException;

public class MathTipsActivity extends AppCompatActivity {

    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_tips);

        img_back = (ImageView) findViewById(R.id.tip_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MathTipsActivity.this.finish();
            }
        });
    }
}
