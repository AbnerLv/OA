package com.lzb.oa.ui.complany;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.lzb.oa.R;
import com.lzb.oa.service.CompanyService;
import com.lzb.oa.ui.adapter.MeetingAdapter;

public class MeetingActivity extends Activity {

    private ListView meettingListView;
    private MeetingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.meeting_list);
    }


    private void init(){
        meettingListView = (ListView)this.findViewById(R.id.lv_meeting_info);
        adapter = new MeetingAdapter(getApplicationContext());
        CompanyService.getInstance();
    }
}
