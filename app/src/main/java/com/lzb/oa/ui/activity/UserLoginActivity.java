package com.lzb.oa.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.MainActivity;
import com.lzb.oa.R;
import com.lzb.oa.service.UserService;
import com.lzb.oa.service.handler.CheckLoginHandler;
import com.lzb.oa.ui.auth.ForgetPasswordActivity;
import com.lzb.oa.ui.auth.SMSRegisterActivity;
import com.lzb.oa.utils.ActivityCollector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class UserLoginActivity extends BaseActivity implements OnClickListener {

    private EditText tvUsername;
    private EditText tvPassword;
    private Button btnLogin;
    private TextView tvBackpass;
    private TextView tvNewuser;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_login);

        init();

        // 获取缓存的用户名，密码
        sp = getSharedPreferences("OAEmpInfo", MODE_PRIVATE);
        if (sp != null) {
            String username = sp.getString("emp_nickname", null);
            String password = sp.getString("emp_password", null);
            tvUsername.setText(username);
            tvPassword.setText(password);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        tvUsername = (EditText) findViewById(R.id.username);
        tvPassword = (EditText) findViewById(R.id.password);
        tvBackpass = (TextView) findViewById(R.id.tv_backpass);
        tvNewuser = (TextView) findViewById(R.id.tv_newuser);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        tvBackpass.setOnClickListener(this);
        tvNewuser.setOnClickListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if (username != null && password != null) {
            tvUsername.setText(username);
            tvPassword.setText(password);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login: login();break;

            // 找回密码
            case R.id.tv_backpass:
                Intent intent = new Intent(UserLoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;

            // 注册点击事件
            case R.id.tv_newuser:
                Intent intent1 = new Intent(UserLoginActivity.this,
                        SMSRegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void login() {
        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();
        if (username == null || "".equals(username)) {
            Toast.makeText(UserLoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (password == null || "".equals(password)) {
            Toast.makeText(UserLoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject json = new JSONObject();
            try {
                json.put("username", username);
                json.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            UserService.getInstance().checkLogin(getApplicationContext(), json, new CheckLoginHandler() {
                @Override
                public void onSuccess(String password) {
                    if (password == null || "0".equals(password)) {
                        Toast.makeText(getApplicationContext(), "输入的用户名或密码有错",Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog();
                    }
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监听返回键
            ActivityCollector.finishAll();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 登陆进程框
     */
    private void progressDialog() {
        final ProgressDialog proDialog = new ProgressDialog(this);
        proDialog.setTitle("验证中");
        proDialog.setMessage("正在登陆，请稍后…");
        proDialog.setIndeterminate(true);
        proDialog.setCancelable(false);
        proDialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                proDialog.dismiss();
                t.cancel();
                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }


}
