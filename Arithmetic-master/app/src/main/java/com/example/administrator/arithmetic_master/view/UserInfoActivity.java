package com.example.administrator.arithmetic_master.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;

public class UserInfoActivity extends AppCompatActivity {

    private EditText info_username;
    private RadioGroup info_sex;
    private EditText info_phone;
    private EditText info_email;
    private EditText info_address;
    private Button info_saveinfo;

    private  RadioButton radioButtonBoy;
    private RadioButton getRadioButtonGirl;

    private String sex="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        info_username=(EditText)findViewById(R.id.info_username);
        info_sex=(RadioGroup)findViewById(R.id.info_sex);
        info_phone=(EditText)findViewById(R.id.info_phone_number);
        info_email=(EditText)findViewById(R.id.info_email);
        info_address=(EditText)findViewById(R.id.info_address);
        info_saveinfo=(Button)findViewById(R.id.info_saveinfo);
        radioButtonBoy=(RadioButton)findViewById(R.id.info_boy);
        getRadioButtonGirl=(RadioButton)findViewById(R.id.info_girl);

        info_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.info_boy)
                {
                    sex="0";
                }
                else {
                    sex="1";
                }
            }
        });

        info_saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取用户修改的信息
                String username=info_username.getText().toString();
                String phone=info_phone.getText().toString();
                String email=info_email.getText().toString();
                String address=info_address.getText().toString();

                User.getInstance().setUserName(username);
                User.getInstance().setSex(sex);
                User.getInstance().setPhone(phone);
                User.getInstance().setEmail(email);
                User.getInstance().setAddress(address);

                //将修改发送到服务端
                String url="userAction_updateUser.action?userid="+User.getInstance().getUserId()+"&name="+username+"&sex="+sex+"&phone="+phone+"&email="+email+"&address="+address;
                new HttpUtil(new Handler()
                {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                },url).start();

                //更新界面信息
                UpdateInfo();
            }
        });

        //显示界面信息
        UpdateInfo();
    }

    private void UpdateInfo()
    {
        info_username.setText(User.getInstance().getUserName());
        if(User.getInstance().getSex().equals("0"))
        {
            radioButtonBoy.setChecked(true);
            getRadioButtonGirl.setChecked(false);
        }
        else
        {
            radioButtonBoy.setChecked(false);
            getRadioButtonGirl.setChecked(true);
        }
        info_phone.setText(User.getInstance().getPhone());
        info_email.setText(User.getInstance().getEmail());
        info_address.setText(User.getInstance().getAddress());
    }
}
