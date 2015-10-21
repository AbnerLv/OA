package com.lzb.oa.ui.auth;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class BackPassword extends BaseActivity implements OnClickListener {

    private final static String BACK_PASSWORD_URL = Constant.URL
            + "back_password.json";
    private EditText etBackEmpNo;
    private EditText etBackPhoneNo;
    private EditText etBackIdentify;
    private Button btnBackSubmit;
    private Button btnBackReset;

    private RequestQueue mQueue = null;

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
            JSONObject json = null;
            if (chkEdit()) {
                json = new JSONObject();
                try {
                    json.put("empNo", etBackEmpNo.getText().toString());
                    json.put("phoneNo", etBackPhoneNo.getText().toString());
                    json.put("identify", etBackIdentify.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Method.POST, BACK_PASSWORD_URL, json,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("TAG", response.toString());
                                Toast.makeText(BackPassword.this,
                                        response.toString(), Toast.LENGTH_SHORT)
                                        .show();
                                try {
                                    dialog(response.get("emp_password")
                                            .toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("TAG", error.getMessage(), error);
                                Toast.makeText(
                                        BackPassword.this,
                                        "网络连接出错，请检查网络状况！"
                                                + etBackEmpNo.getText()
                                                        .toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                mQueue.add(jsonObjectRequest);
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
        if (etBackEmpNo.getText().toString() == null
                || "".equals(etBackEmpNo.getText().toString())) {
            etBackEmpNo.requestFocus();
            Toast.makeText(this, "员工号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etBackPhoneNo.getText().toString() == null
                || "".equals(etBackPhoneNo.getText().toString())) {
            etBackPhoneNo.requestFocus();
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etBackIdentify.getText().toString() == null
                || "".equals(etBackIdentify.getText().toString())) {
            etBackIdentify.requestFocus();
            Toast.makeText(this, "身份证号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 弹出对话框
     *
     * 用户名
     * 
     * @param password
     *            密码
     */
    protected void dialog(String password) {
        Builder builder = new Builder(this);
        builder.setMessage("您的登陆密码为：" + password);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(BackPassword.this,
                        LoginActivity.class);
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
}