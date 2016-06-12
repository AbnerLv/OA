package com.lzb.oa.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.service.UserService;
import com.lzb.oa.service.handler.RegisterHandler;
import com.lzb.oa.ui.activity.UserLoginActivity;
import com.lzb.oa.utils.CheckNullUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户注册
 * Created by lvzhenbin on 2015/10/23.
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {

    private final static String TAG = "RegisterActivity";

    private EditText etRegisterEmpNickname;
    private EditText etRegisterEmpNo;
    private EditText etRegisterEmpName;
    private EditText etRegisterPass;
    private EditText etRegisterPassConfirm;
    private Button btnRegisterSubmit;
    private Button btnRegisterReset;


    public static void startRegisterActivity(Context context, String phoneNo) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("empPhoneNo", phoneNo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_register);
        Log.e(TAG,"RegisterActiv");
        init();
    }

    private void init() {
        etRegisterEmpNickname = (EditText) findViewById(R.id.et_register_emp_nickname);
        etRegisterEmpNo = (EditText) findViewById(R.id.et_register_emp_no);
        etRegisterEmpName = (EditText) findViewById(R.id.et_register_emp_name);
        etRegisterPass = (EditText) findViewById(R.id.et_register_pass);
        etRegisterPassConfirm = (EditText) findViewById(R.id.et_register_pass_confirm);
        btnRegisterSubmit = (Button) findViewById(R.id.btn_register_submit);
        btnRegisterReset = (Button) findViewById(R.id.btn_register_reset);
        btnRegisterSubmit.setOnClickListener(this);
        btnRegisterReset.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.btn_register_submit:

            if (CheckNullUtil.checkIsNull(etRegisterEmpNickname)) {
                Toast.makeText(RegisterActivity.this, "昵称不能为空",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (CheckNullUtil.checkIsNull(etRegisterEmpNo)) {
                Toast.makeText(RegisterActivity.this, "员工号不能为空",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (CheckNullUtil.checkIsNull(etRegisterEmpName)) {
                Toast.makeText(RegisterActivity.this, "员工姓名不能为空",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (CheckNullUtil.checkIsNull(etRegisterPass)) {
                Toast.makeText(RegisterActivity.this, "密码不能为空",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (CheckNullUtil.checkIsNull(etRegisterPassConfirm)) {
                Toast.makeText(RegisterActivity.this, "确认密码不能为空",
                        Toast.LENGTH_SHORT).show();
                break;
            } else if (!etRegisterPass.getText().toString().trim()
                    .equals(etRegisterPassConfirm.getText().toString().trim())) {
                Toast.makeText(RegisterActivity.this, "两次输入的密码不一致，请重新输入",
                        Toast.LENGTH_SHORT).show();
                etRegisterPass.getText().clear();
                etRegisterPassConfirm.getText().clear();
                break;
            } else {
                try {
                    JSONObject json = new JSONObject();
                    Intent intent = getIntent();
                    json.put("empPhoneNo", intent.getStringExtra("empPhoneNo"));
                    json.put("empNickname", etRegisterEmpNickname.getText()
                            .toString().trim());
                    json.put("empNo", etRegisterEmpNo.getText().toString()
                            .trim());
                    json.put("empName", etRegisterEmpName.getText().toString()
                            .trim());
                    json.put("pass", etRegisterPass.getText().toString().trim());
                    empRegister(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            break;

        case R.id.btn_register_reset:
            etRegisterEmpNickname.getText().clear();
            etRegisterEmpNo.getText().clear();
            etRegisterPass.getText().clear();
            etRegisterPassConfirm.getText().clear();
            etRegisterEmpNickname.requestFocus();
            break;
        }
    }

    /**
     * 注册
     * 
     * @param json
     */
    private void empRegister(final JSONObject json) {
        UserService.getInstance().register(getApplicationContext(), json, new RegisterHandler() {
            @Override
            public void register(int success) {
                try {
                    String nickname = json.getString("empNickname");
                    String password = json.getString("pass");
                    Toast.makeText(RegisterActivity.this, "注册成功",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                    intent.putExtra("username", nickname);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        UserService.getInstance().cancelPendingRequests();
    }
}
