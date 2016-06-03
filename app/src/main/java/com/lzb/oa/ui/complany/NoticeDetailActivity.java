package com.lzb.oa.ui.complany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.lzb.oa.R;

public class NoticeDetailActivity extends Activity {

    private TextView tvTheme;
    private TextView tvTime;
    private TextView tvContent;
    private TextView tvEmpNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.notice_detail);
        init();
    }

    private void init(){
        tvTheme = (TextView)this.findViewById(R.id.tv_notice_detail_theme);
        tvTime = (TextView)this.findViewById(R.id.tv_notice_detail_time);
        tvContent = (TextView)this.findViewById(R.id.tv_notice_detail_content);
        tvEmpNo = (TextView)this.findViewById(R.id.tv_notice_detail_empno);

        Intent intent = getIntent();
        String notice_theme = intent.getStringExtra("notice_theme");
        String notice_time = intent.getStringExtra("notice_time");
        String notice_content = intent.getStringExtra("notice_content");
        String notice_empno = intent.getStringExtra("notice_empno");

        tvTheme.setText(notice_theme+"");
        tvTime.setText(notice_time+"");
        tvContent.setText(notice_content+"");
        tvEmpNo.setText(notice_empno+"");
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
