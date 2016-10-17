package com.example.administrator.arithmetic_master.view;



import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;

public class CalculateActivity extends AppCompatActivity implements CalculateSettingDialog.NoticeDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        showNoticeDialog();
    }

    public void showNoticeDialog()
    {
        CalculateSettingDialog settingDialog=new CalculateSettingDialog();
        settingDialog.show(getSupportFragmentManager(),"CalculateSettingDialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        CalculateSettingDialog customDialog=(CalculateSettingDialog)dialog;
        Log.i("Output Grade", User.getInstance().currentExeGrade+"");
        Log.i("Output Num",User.getInstance().currentExeNum+"");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        // User touched the dialog's negative button

    }
}
