package com.lzb.oa.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzb.oa.R;
import com.lzb.oa.service.SettingService;
import com.lzb.oa.service.handler.ModifyPasswordHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class ModifyPasswordActivity extends BaseActivity implements OnClickListener {


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

        SharedPreferences sp = getSharedPreferences("OAEmpInfo", MODE_PRIVATE);
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
                modifyPassword();
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

    private void modifyPassword(){
        String username = etChangePassUsername.getText().toString();
        String oldPassword = etChangePassOldPass.getText().toString();
        String newPassword1 = etChangePassNewPass.getText().toString();
        String newPassword2 = etChangePassConfirmPass.getText().toString();
        if (!newPassword1.equals(newPassword2)) {
            Toast.makeText(ModifyPasswordActivity.this, "两次输入的新密码不相同，请重新输入！",Toast.LENGTH_SHORT).show();
            etChangePassNewPass.getText().clear();
            etChangePassConfirmPass.getText().clear();
            etChangePassNewPass.requestFocus();
        } else {
            try {
                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("oldPassword", oldPassword);
                json.put("newPassword", newPassword2);
                SettingService.getInstance().modifyPassword(
                        ModifyPasswordActivity.this, json, new ModifyPasswordHandler() {
                            @Override
                            public void onSuccess(int code) {
                                if (code > 0) {
                                    dialog();
                                } else {
                                    Toast.makeText(getApplicationContext(), "修改失败，请重试！", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    @Override
    protected void onStop() {
        SettingService.getInstance().cancelPendingRequests();
        super.onStop();
    }

    /*
        * 弹出对话框
        */
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("密码已修改成功，请重新登录！");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(ModifyPasswordActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
