package com.example.administrator.arithmetic_master.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;

public class GradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        Log.i("info", "UserInfo:" + User.getInstance().toString());
    }

    public void selectGrade(View v) {
        User u = User.getInstance();
        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
        switch (v.getId()) {
            case R.id.img_gradeone:
                Log.i("Grade", "One");
                u.setUserGrade(1);
                break;
            case R.id.img_gradetwo:
                Log.i("Grade", "Two");
                u.setUserGrade(2);
                break;
            case R.id.img_gradethree:
                Log.i("Grade", "Three");
                u.setUserGrade(3);
                break;
            case R.id.img_gradefour:
                Log.i("Grade", "Four");
                u.setUserGrade(4);
                break;
            case R.id.img_gradefive:
                Log.i("Grade", "Five");
                u.setUserGrade(5);
                break;
            case R.id.img_gradesix:
                Log.i("Grade", "Six");
                u.setUserGrade(6);
                break;
            default:
                u.setUserGrade(0);
                break;
        }
        new HttpUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //处理请求信息
            }
        }, "userAction_setUserGrade.action?id=" + u.getUserId() + "&grade=" + u.getUserGrade()).start();
        startActivity(intent);
        finish();
    }
}
