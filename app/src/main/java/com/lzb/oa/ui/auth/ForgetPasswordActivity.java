package com.lzb.oa.ui.auth;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.service.UserService;
import com.lzb.oa.service.handler.ForgetPasswordHandler;
import com.lzb.oa.ui.activity.UserLoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends BaseActivity implements OnClickListener {

    private EditText etBackEmpNo;
    private EditText etBackPhoneNo;
    private EditText etBackIdentify;
    private Button btnBackSubmit;
    private Button btnBackReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.back_password);
        init();
    }

    private void init() {
        etBackEmpNo = (EditText) findViewById(R.id.et_back_emp_no);
        etBackPhoneNo = (EditText) findViewById(R.id.et_back_phone_no);
        etBackIdentify = (EditText) findViewById(R.id.et_back_identity);
        btnBackSubmit = (Button) findViewById(R.id.btn_back_submit);
        btnBackReset = (Button) findViewById(R.id.btn_back_reset);

        btnBackSubmit.setOnClickListener(this);
        btnBackReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back_submit:
                if (chkEdit()) {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("empNo", etBackEmpNo.getText().toString());
                        json.put("phoneNo", etBackPhoneNo.getText().toString());
                        json.put("identify", etBackIdentify.getText().toString());

                        UserService.getInstance().forgetPassword(getApplicationContext(),
                                json, new ForgetPasswordHandler() {
                                    @Override
                                    public void success(String password) {
                                        dialog(password);
                                    }
                                });
                    } catch (JSONException e) {
                        Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                    }

                }

                break;

            case R.id.btn_back_reset:
                etBackEmpNo.getText().clear();
                etBackPhoneNo.getText().clear();
                etBackIdentify.getText().clear();
                break;

            default:
                break;
        }
    }

    private boolean chkEdit() {
        String empNo = etBackEmpNo.getText().toString();
        String phone = etBackPhoneNo.getText().toString();
        String identify = etBackIdentify.getText().toString();
        if (empNo == null || "".equals(empNo)) {
            Toast.makeText(this, "员工号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phone == null || "".equals(phone)) {
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (identify == null || "".equals(identify)) {
            Toast.makeText(this, "身份证号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 弹出对话框
     * 用户名
     *
     * @param password 密码
     */
    protected void dialog(String password) {
        Builder builder = new Builder(this);
        builder.setMessage("您的登陆密码为：" + password);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(ForgetPasswordActivity.this,
                        UserLoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
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
        super.onStop();
        UserService.getInstance().cancelPendingRequests();
    }
}
