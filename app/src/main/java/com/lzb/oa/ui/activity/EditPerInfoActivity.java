package com.lzb.oa.ui.activity;

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

import com.google.gson.Gson;
import com.lzb.oa.R;
import com.lzb.oa.entity.EmpEntity;
import com.lzb.oa.service.UserService;
import com.lzb.oa.service.handler.ModifyPerInfoHandler;
import com.lzb.oa.utils.DateTimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class EditPerInfoActivity extends BaseActivity implements OnClickListener,
        OnCheckedChangeListener {

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
    private String empNo = null;

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
        empNo = sp.getString("emp_no", null);

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
                modifyPerInfo();
                break;

            default:
                break;
        }

    }

    private void modifyPerInfo() {
        try {
            String emp_nickname = etPerInfoNickname.getText().toString();
            String emp_name = etPerInfoName.getText().toString();
            String emp_age = etPerInfoAge.getText().toString();
            String emp_phone_no = etPerInfoPhoneNo.getText().toString();
            String emp_email = etPerInfoEmail.getText().toString().trim();
            String emp_birthday = etPerInfoBirthday.getText().toString();
            String emp_nation = etPerInfoNation.getText().toString().trim();
            String emp_city = etPerInfoCity.getText().toString().trim();
            String emp_address = etPerInfoAddress.getText().toString();
            EmpEntity empEntity = new EmpEntity(emp_nickname, emp_name,
                    sex, emp_age, emp_phone_no, emp_email, emp_birthday, emp_nation,
                    emp_city, emp_address, empNo);
            Gson gson = new Gson();
            String gJosn = gson.toJson(empEntity);
            JSONObject json = new JSONObject(gJosn);
            UserService.getInstance().modifyPerInfo(getApplicationContext(), json, new ModifyPerInfoHandler() {
                @Override
                public void onSuccess(int code) {
                    if (code > 0) {
                        dialog();
                    } else {
                        Toast.makeText(EditPerInfoActivity.this,
                                "修改个人信息失败，请耐心等待5秒后再次尝试",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage() + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
