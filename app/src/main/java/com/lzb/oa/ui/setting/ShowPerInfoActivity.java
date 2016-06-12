package com.lzb.oa.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;

public class ShowPerInfoActivity extends BaseActivity implements OnClickListener {


    private TextView tvPerInfoNickname;
    private TextView tvPerInfoName;
    private TextView tvPerInfoSex;
    private TextView tvPerInfoAge;
    private TextView tvPerInfoPhoneNum;
    private TextView tvPerInfoEmail;
    private TextView tvPerInfoEmpNo;
    private TextView tvPerInfoDepartment;
    private TextView tvPerInfoPosition;
    private TextView tvPerInfoEntryDate;
    private TextView tvPerInfoBirthday;
    private TextView tvPerInfoNation;
    private TextView tvPerInfoCity;
    private TextView tvPerInfoAddress;


    public static void startSettingPerInfo(Context context) {
        Intent intent = new Intent(context, ShowPerInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.setting_per_info);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPerInfo();
    }

    private void init() {

        tvPerInfoNickname = (TextView) findViewById(R.id.tv_per_info_nickname);
        tvPerInfoName = (TextView) findViewById(R.id.tv_per_info_name);
        tvPerInfoSex = (TextView) findViewById(R.id.tv_per_info_sex);
        tvPerInfoAge = (TextView) findViewById(R.id.tv_per_info_age);
        tvPerInfoPhoneNum = (TextView) findViewById(R.id.tv_per_info_phone_num);
        tvPerInfoEmail = (TextView) findViewById(R.id.tv_per_info_email);

        tvPerInfoEmpNo = (TextView) findViewById(R.id.tv_per_info_emp_no);
        tvPerInfoDepartment = (TextView) findViewById(R.id.tv_per_info_department);
        tvPerInfoPosition = (TextView) findViewById(R.id.tv_per_info_position);
        tvPerInfoEntryDate = (TextView) findViewById(R.id.tv_per_info_entry_date);

        tvPerInfoBirthday = (TextView) findViewById(R.id.tv_per_info_birthday);
        tvPerInfoNation = (TextView) findViewById(R.id.tv_per_info_nation);
        tvPerInfoCity = (TextView) findViewById(R.id.tv_per_info_city);
        tvPerInfoAddress = (TextView) findViewById(R.id.tv_per_info_address);

        tvPerInfoPhoneNum.setOnClickListener(this);
        tvPerInfoEmail.setOnClickListener(this);

    }

    private void getPerInfo() {
        SharedPreferences sp = getSharedPreferences("OAEmpInfo", 0);
        tvPerInfoNickname.setText(sp.getString("emp_nickname", null));
        tvPerInfoName.setText(sp.getString("emp_name", null));
        tvPerInfoSex.setText(sp.getString("emp_sex", null));
        tvPerInfoAge.setText(sp.getString("emp_age", null));
        tvPerInfoPhoneNum.setText(sp.getString("emp_phone_no", null));
        tvPerInfoEmail.setText(sp.getString("emp_email", null));

        tvPerInfoEmpNo.setText(sp.getString("emp_no", null));
        tvPerInfoDepartment.setText(sp.getString("emp_department", null));
        tvPerInfoPosition.setText(sp.getString("emp_position", null));
        tvPerInfoEntryDate.setText(sp.getString("emp_entry_date", null));

        tvPerInfoBirthday.setText(sp.getString("emp_birthday", null));
        tvPerInfoNation.setText(sp.getString("emp_nation", null));
        tvPerInfoCity.setText(sp.getString("emp_city", null));
        tvPerInfoAddress.setText(sp.getString("emp_address", null));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        // 打开拨号界面
        case R.id.tv_per_info_phone_num:
            Uri uri = Uri.parse("tel:"
                    + tvPerInfoPhoneNum.getText().toString().trim());
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
            break;
        // 打开邮件界面
        case R.id.tv_per_info_email:
            Uri uri1 = Uri.parse("mailto:"
                    + tvPerInfoEmail.getText().toString().trim());
            Intent it = new Intent(Intent.ACTION_SENDTO, uri1);
            startActivity(it);
            break;

        default:
            break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.per_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ActionBar点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.per_info_edit:
            EditPerInfoActivity.startEditPerInfo(ShowPerInfoActivity.this);
            break;
        case R.id.home:
            finish();
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
