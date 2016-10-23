package com.example.administrator.arithmetic_master.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.arithmetic_master.R;

public class PersonalActivity extends AppCompatActivity {

    private ImageView back;
    private ImageView personInfomation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        back=(ImageView) findViewById(R.id.personal_back);
        personInfomation=(ImageView)findViewById(R.id.personal_information);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
