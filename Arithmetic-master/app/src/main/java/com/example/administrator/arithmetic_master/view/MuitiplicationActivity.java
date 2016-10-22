package com.example.administrator.arithmetic_master.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.arithmetic_master.R;

public class MuitiplicationActivity extends AppCompatActivity {

    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muitiplication);

        img_back = (ImageView) findViewById(R.id.mul_back);
        
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuitiplicationActivity.this.finish();
            }
        });
    }
}
