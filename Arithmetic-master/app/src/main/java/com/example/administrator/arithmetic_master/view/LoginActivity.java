package com.example.administrator.arithmetic_master.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;
import com.example.administrator.arithmetic_master.utils.AutoLogin;
import com.example.administrator.arithmetic_master.utils.CreateJson;
import com.example.administrator.arithmetic_master.utils.DisplayMsg;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private boolean isAuto = false;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        _loginButton = (Button) findViewById(R.id.btn_login);

        String loginInfo = AutoLogin.read(this);
        if (loginInfo.length() < 5) {
            isAuto = false;
        } else {
            isAuto = true;
            showLoginInfo(loginInfo);//从文件中读取用户名和密码并自动填写
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void showLoginInfo(String info) {
        try {
            JSONObject o = new JSONObject(info);
            _emailText.setText(o.getString("loginname"));
            _passwordText.setText(o.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        Log.d(TAG, "Login");
        AutoLogin.save(this, _emailText.getText().toString().trim(), _passwordText.getText().toString().trim());//保存账号和密码
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登陆中...");
        progressDialog.show();

        final String loginname = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        new HttpUtil(new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj.toString().equals("0x01") || msg.obj.toString().equals("0x00")) {
                    onLoginFailed();
                } else {
                    AutoLogin.save(LoginActivity.this, loginname, password);//保存账号和密码
                    parseUserInfo(msg.obj.toString());
                    onLoginSuccess();
                }
                progressDialog.dismiss();
            }
        }, "userAction_login.action?loginname=" + loginname + "&password=" + password).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                //获取注册的账号和密码并显示在界面输入框中
                _emailText.setText(data.getStringExtra("loginname"));
                _passwordText.setText(data.getStringExtra("password"));
                login();//注册成功并返回登陆
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        if (User.getInstance().getUserGrade() == 0) {
            Intent intent = new Intent(getApplicationContext(), GradeActivity.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, MainPageActivity.class));
        }
        finish();
    }

    public void onLoginFailed() {
        DisplayMsg.Show(this, "登录失败");
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError("请输入用户名");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("至少4-10个字母或数字");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    public void parseUserInfo(String info) {
        try {
            JSONObject j = new JSONObject(info);
            User u = User.getInstance();
            u.setUserFlag(Integer.parseInt(j.getString("flag")));
            u.setUserGrade(Integer.parseInt(j.getString("grade")));
            u.setUserId(j.getString("id"));
            u.setUserLoginName(j.getString("loginname"));
            u.setUserName(j.getString("name"));
            u.setSex(j.getString("sex"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
