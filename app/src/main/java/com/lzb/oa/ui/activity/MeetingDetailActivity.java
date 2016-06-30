package com.lzb.oa.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.lzb.oa.R;

public class MeetingDetailActivity extends Activity {

    private TextView tvTheme;
    private TextView tvTime;
    private TextView tvContent;
    private TextView tvAddress;
    private TextView tvEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.company_meeting_detail);
        init();
    }

    private void init(){
        tvTheme = (TextView)this.findViewById(R.id.tv_meeting_detail_theme);
        tvTime = (TextView)this.findViewById(R.id.tv_meeting_detail_start_time);
        tvContent = (TextView)this.findViewById(R.id.tv_meeting_detail_content);
        tvAddress = (TextView)this.findViewById(R.id.tv_meeting_detail_address);
        tvEndTime = (TextView)this.findViewById(R.id.tv_meeting_detail_end_time);

        Intent intent = getIntent();
        String meeting_theme = intent.getStringExtra("meeting_theme");
        String meeting_time = intent.getStringExtra("meeting_time");
        String meeting_end_time = intent.getStringExtra("meeting_end_time");
        String meeting_content = intent.getStringExtra("meeting_content");
        String meeting_address = intent.getStringExtra("meeting_address");

        tvTheme.setText(meeting_theme+"");
        tvTime.setText(meeting_time+"");
        tvContent.setText(meeting_content+"");
        tvAddress.setText(meeting_address+"");
        tvEndTime.setText(meeting_end_time+"");
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
