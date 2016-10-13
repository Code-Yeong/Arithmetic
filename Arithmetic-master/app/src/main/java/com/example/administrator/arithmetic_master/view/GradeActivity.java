package com.example.administrator.arithmetic_master.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.arithmetic_master.R;

public class GradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

    }

    public void selectGrade(View v)
    {
        switch (v.getId())
        {
            case R.id.img_gradeone:
                Log.i("Grade","One");
                break;
            case R.id.img_gradetwo:
                Log.i("Grade","Two");
                break;
            case R.id.img_gradethree:
                Log.i("Grade","Three");
                break;
            case R.id.img_gradefour:
                Log.i("Grade","Four");
                break;
            case R.id.img_gradefive:
                Log.i("Grade","Five");
                break;
            case R.id.img_gradesix:
                Log.i("Grade","Six");
                break;
            default:
                break;
        }
    }

}
