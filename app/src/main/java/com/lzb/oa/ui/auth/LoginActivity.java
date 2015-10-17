package com.lzb.oa.ui.auth;

import android.content.Context;
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
import com.lzb.oa.R;
import com.lzb.oa.service.AuthService;
import com.lzb.oa.utils.ActivityCollector;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private EditText tvUsername;
    private EditText tvPassword;
    private Button btn_login;
    private TextView tvBackpass;
    private TextView tvNewuser;
    private SharedPreferences sp;

    public static void startLoginActivity(Context context, String username,
            String password) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }

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
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
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
        case R.id.btn_login:
            if (tvUsername.getText() == null
                    || "".equals(tvUsername.getText().toString().trim())) {
                Toast.makeText(LoginActivity.this, "用户名不能为空！",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (tvPassword.getText() == null
                    || "".equals(tvPassword.getText().toString().trim())) {
                Toast.makeText(LoginActivity.this, "密码不能为空！",
                        Toast.LENGTH_SHORT).show();
                break;
            } else {
                String userName = tvUsername.getText().toString().trim();
                String passWord = tvPassword.getText().toString().trim();
                JSONObject json = new JSONObject();
                try {
                    json.put("UserName", userName);
                    json.put("PassWord", passWord);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("emp_nickname", userName);
                editor.putString("emp_password", passWord);
                editor.commit();

                AuthService.getInstance().checkLogin(json, LoginActivity.this);
            }

            break;

        // 找回密码
        case R.id.tv_backpass:
            Intent intent = new Intent(LoginActivity.this, BackPassword.class);
            startActivity(intent);
            break;

        // 注册点击事件
        case R.id.tv_newuser:
            Intent intent1 = new Intent(LoginActivity.this,
                    SMSRegisterActivity.class);
            startActivity(intent1);
            break;
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

}
