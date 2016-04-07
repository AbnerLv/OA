package com.lzb.oa.ui.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.utils.DateTimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class EditPerInfoActivity extends BaseActivity implements OnClickListener,
        OnCheckedChangeListener {


    private final static String EDIT_PER_INFO_URL = Constant.URL
            + "edit_per_info.json";

    private EditText etPerInfoNickname;
    private EditText etPerInfoName;
    private RadioGroup radioPerInfoSex;
    private RadioButton radioPerInfoMale;
    private RadioButton radioPerInfoFemale;
    private RadioButton radioPerInfoSecret;
    private EditText etPerInfoAge;
    private EditText etPerInfoPhoneNo;
    private EditText etPerInfoEmail;
    private TextView etPerInfoEmpNo;
    private TextView etPerInfoDepartment;
    private TextView etPerInfoPosition;
    private TextView etPerInfoEntryDate;
    private EditText etPerInfoBirthday;
    private EditText etPerInfoNation;
    private EditText etPerInfoCity;
    private EditText etPerInfoAddress;
    private Button btnPerInfoSubmit;

    private Calendar calendar = Calendar.getInstance();
    private String sex = null;

    private RequestQueue mQueueEdit = null;

    public static void startEditPerInfo(Context context) {
        Intent intent = new Intent(context, EditPerInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.edit_per_info);

        init();
    }

    private void init() {
        etPerInfoNickname = (EditText) findViewById(R.id.et_per_info_nickname);
        etPerInfoName = (EditText) findViewById(R.id.et_per_info_name);
        radioPerInfoSex = (RadioGroup) findViewById(R.id.radio_per_info_sex);
        radioPerInfoMale = (RadioButton) findViewById(R.id.radio_per_info_male);
        radioPerInfoFemale = (RadioButton) findViewById(R.id.radio_per_info_female);
        radioPerInfoSecret = (RadioButton) findViewById(R.id.radio_per_info_secret);
        etPerInfoAge = (EditText) findViewById(R.id.et_per_info_age);
        etPerInfoPhoneNo = (EditText) findViewById(R.id.et_per_info_phone_no);
        etPerInfoEmail = (EditText) findViewById(R.id.et_per_info_email);
        etPerInfoEmpNo = (TextView) findViewById(R.id.et_per_info_emp_no);
        etPerInfoDepartment = (TextView) findViewById(R.id.et_per_info_department);
        etPerInfoPosition = (TextView) findViewById(R.id.et_per_info_position);
        etPerInfoEntryDate = (TextView) findViewById(R.id.et_per_info_entry_date);
        etPerInfoBirthday = (EditText) findViewById(R.id.et_per_info_birthday);
        etPerInfoNation = (EditText) findViewById(R.id.et_per_info_nation);
        etPerInfoCity = (EditText) findViewById(R.id.et_per_info_city);
        etPerInfoAddress = (EditText) findViewById(R.id.et_per_info_address);
        btnPerInfoSubmit = (Button) findViewById(R.id.btn_edit_per_info_submit);

        setPerInfo();

        radioPerInfoSex.setOnCheckedChangeListener(this);
        etPerInfoBirthday.setOnClickListener(this);
        btnPerInfoSubmit.setOnClickListener(this);
    }

    private void setPerInfo() {
        SharedPreferences sp = getSharedPreferences("OAEmpInfo", 0);
        etPerInfoNickname.setText(sp.getString("emp_nickname", null));
        etPerInfoName.setText(sp.getString("emp_name", null));
        Toast.makeText(getApplicationContext(), sp.getString("emp_name", null),
                Toast.LENGTH_LONG).show();
        if (radioPerInfoMale.getText().toString().trim()
                .equals(sp.getString("emp_sex", null))) {
            radioPerInfoMale.setChecked(true);
        } else if (radioPerInfoFemale.getText().toString().trim()
                .equals(sp.getString("emp_sex", null))) {
            radioPerInfoFemale.setChecked(true);
        } else {
            radioPerInfoSecret.setChecked(true);
        }

        etPerInfoAge.setText(sp.getString("emp_age", null));
        etPerInfoPhoneNo.setText(sp.getString("emp_phone_no", null));
        etPerInfoEmail.setText(sp.getString("emp_email", null));

        etPerInfoEmpNo.setText(sp.getString("emp_no", null));
        etPerInfoDepartment.setText(sp.getString("emp_department", null));
        etPerInfoPosition.setText(sp.getString("emp_position", null));
        etPerInfoEntryDate.setText(sp.getString("emp_entry_date", null));

        etPerInfoBirthday.setText(sp.getString("emp_birthday", null));
        etPerInfoNation.setText(sp.getString("emp_nation", null));
        etPerInfoCity.setText(sp.getString("emp_city", null));
        etPerInfoAddress.setText(sp.getString("emp_address", null));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.et_per_info_birthday:
            DateTimePickerDialog.dateDialogAll(EditPerInfoActivity.this, calendar,
                    etPerInfoBirthday);
            break;

        case R.id.btn_edit_per_info_submit:
            try {
                JSONObject json = new JSONObject();
                json.put("nickname", etPerInfoNickname.getText().toString()
                        .trim());
                json.put("name", etPerInfoName.getText().toString().trim());
                json.put("sex", sex);
                json.put("age", etPerInfoAge.getText().toString().trim());
                json.put("phoneNo", etPerInfoPhoneNo.getText().toString()
                        .trim());
                json.put("email", etPerInfoEmail.getText().toString().trim());
                json.put("empNo", etPerInfoEmpNo.getText().toString().trim());
                json.put("birthday", etPerInfoBirthday.getText().toString()
                        .trim());
                json.put("nation", etPerInfoNation.getText().toString().trim());
                json.put("city", etPerInfoCity.getText().toString().trim());
                json.put("address", etPerInfoAddress.getText().toString()
                        .trim());

                mQueueEdit = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        EDIT_PER_INFO_URL, json, new Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int success = Integer.parseInt(response
                                            .getString("success"));
                                    if (success == 1) {
                                        dialog();
                                    } else {
                                        Toast.makeText(EditPerInfoActivity.this,
                                                "修改个人信息失败，请耐心等待5秒后再次尝试",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                mQueueEdit.add(jsonObjectRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            break;

        default:
            break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.home:
            finish();
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radio_per_info_male) {
            // 把mRadio1的内容传到mTextView1
            sex = radioPerInfoMale.getText().toString();
        } else if (checkedId == R.id.radio_per_info_female) {
            // 把mRadio2的内容传到mTextView1
            sex = radioPerInfoFemale.getText().toString();
        } else {
            sex = radioPerInfoSecret.getText().toString();
        }
    }

    /**
     * 弹出对话框
     */
    protected void dialog() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setMessage("个人信息已成功修改,对话框2秒后自动关闭！");
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                builder.dismiss();
                t.cancel();
                EditPerInfoActivity.this.finish();
            }
        }, 2000);
    }
}
