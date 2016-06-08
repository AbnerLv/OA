package com.lzb.oa.ui.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.service.SettingService;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswdActivity extends BaseActivity implements OnClickListener {


    private EditText etChangePassUsername;
    private EditText etChangePassOldPass;
    private EditText etChangePassNewPass;
    private EditText etChangePassConfirmPass;
    private Button btnChangePassSubmit;
    private Button btnChangePassReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.setting_change_pass);
        init();
    }

    private void init() {
        etChangePassUsername = (EditText) findViewById(R.id.et_chang_pass_username);
        etChangePassOldPass = (EditText) findViewById(R.id.et_chang_pass_old_pass);
        etChangePassNewPass = (EditText) findViewById(R.id.et_chang_pass_new_pass);
        etChangePassConfirmPass = (EditText) findViewById(R.id.et_chang_pass_confirm_pass);
        btnChangePassSubmit = (Button) findViewById(R.id.btn_change_pass_submit);
        btnChangePassReset = (Button) findViewById(R.id.btn_change_pass_reset);

        SharedPreferences sp = getSharedPreferences("OAEmpInfo",
                MODE_PRIVATE);
        String username = sp.getString("emp_nickname", null);

        if (!(username == null || "".equals(username))) {
            etChangePassUsername.setText(username);
            etChangePassOldPass.requestFocus();
        }

        btnChangePassSubmit.setOnClickListener(this);
        btnChangePassReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.btn_change_pass_submit:
            final String username = etChangePassUsername.getText().toString()
                    .trim();
            final String password = etChangePassNewPass.getText().toString()
                    .trim();
            if (!password.equals(etChangePassConfirmPass.getText().toString()
                    .trim())) {
                Toast.makeText(ChangePasswdActivity.this, "两次输入的密码不正确，请重新输入！",
                        Toast.LENGTH_SHORT).show();
                etChangePassNewPass.getText().clear();
                etChangePassConfirmPass.getText().clear();
                etChangePassNewPass.requestFocus();
                break;
            }
            try {
                JSONObject json = new JSONObject();
                json.put("userName", username);
                json.put("oldPass", etChangePassOldPass.getText().toString()
                        .trim());
                json.put("newPass", password);
                SettingService.getInstance().modifyPassword(
                        ChangePasswdActivity.this, json, username, password);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            break;

        case R.id.btn_change_pass_reset:
            etChangePassOldPass.getText().clear();
            etChangePassNewPass.getText().clear();
            etChangePassConfirmPass.getText().clear();
            etChangePassOldPass.requestFocus();
            break;

        default:
            break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
