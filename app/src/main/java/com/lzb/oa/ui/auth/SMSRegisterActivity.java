package com.lzb.oa.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.service.handler.SMSRegisterHandler;
import com.lzb.oa.utils.SMSVerifyUtil;

import cn.smssdk.SMSSDK;

public class SMSRegisterActivity extends BaseActivity implements
        OnClickListener {

    public String phString;
    public String code;
    private Spinner spDepartment;
    private EditText etPhoneNo;
    private EditText etVerifycode;
    private Button btnGetcode;
    private Button btnNext;
    private Drawable bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sms_register);

        init();

        SMSVerifyUtil.initSMS(this, new SMSRegisterHandler(
                SMSRegisterActivity.this, etPhoneNo));
    }

    private void init() {
        spDepartment = (Spinner) findViewById(R.id.sp_department);
        etPhoneNo = (EditText) findViewById(R.id.et_phone_no);
        etVerifycode = (EditText) findViewById(R.id.et_verifycode);
        btnGetcode = (Button) findViewById(R.id.btn_getcode);
        btnNext = (Button) findViewById(R.id.btn_next);
        bg = btnGetcode.getBackground(); // 获取button背景样式

        btnGetcode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.btn_getcode:// 获取验证码
            phString = etPhoneNo.getText().toString();
            if (!TextUtils.isEmpty(phString)) {
                code = SMSVerifyUtil.subStrCountry(spDepartment
                        .getSelectedItem().toString());
                SMSVerifyUtil.getCode(code, etPhoneNo, btnGetcode, bg);
                Log.e("phStrig", phString);
            } else {
                Toast.makeText(this, "电话不能为空", Toast.LENGTH_LONG).show();
            }

            break;
        case R.id.btn_next:// 校验验证码
            if (!TextUtils.isEmpty(etVerifycode.getText().toString())) {
                SMSVerifyUtil.submitCode(code, phString, etVerifycode.getText()
                        .toString());
            } else {
                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_LONG).show();
            }
            break;
        default:
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler(); // 必须有，否则会造成内存泄露
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
