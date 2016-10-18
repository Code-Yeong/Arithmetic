package com.example.administrator.arithmetic_master.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;

public class SignupActivity extends AppCompatActivity {


    private static final String TAG = "SignupActivity";

    EditText _nameText;
    //EditText _emailText;
    EditText _passwordText;
    Button _signupButton;
    TextView _loginLink;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _nameText=(EditText)findViewById(R.id.input_name);
        //_emailText=(EditText)findViewById(R.id.input_email);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _signupButton=(Button)findViewById(R.id.btn_signup);
        _loginLink=(TextView)findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("创建账户中...");
        progressDialog.show();

        final String loginname = _nameText.getText().toString();
        final String password = _passwordText.getText().toString();

        new HttpUtil(new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj.toString().equals("0x00")) {
                    onSignupFailed();
                } else {
                    onSignupSuccess(loginname,password);
                }
                progressDialog.dismiss();
            }
        },"userAction_regist.action?loginname="+loginname+"&password="+password).start();
    }


    public void onSignupSuccess(String loginname,String password) {
        _signupButton.setEnabled(true);
        Intent intent=new Intent();
        intent.putExtra("loginname",loginname);
        intent.putExtra("password",password);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "The loginname is already exist", Toast.LENGTH_SHORT).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 2) {
            _nameText.setError("至少两个字符");
            valid = false;
        } else {
            _nameText.setError(null);
        }

/*        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }*/

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("至少4-10个字母或数字");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
