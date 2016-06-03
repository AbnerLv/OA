package com.lzb.oa.ui.complany;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzb.oa.R;
import com.lzb.oa.entity.MeetingEntity;
import com.lzb.oa.service.CompanyService;
import com.lzb.oa.service.handler.GetMeetingInfosHandler;
import com.lzb.oa.ui.adapter.MeetingAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeetingActivity extends Activity {

    private ListView meettingListView;
    private MeetingAdapter adapter;
    private final List<MeetingEntity> mMeetingInfoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.meeting_list);
        init();
    }


    private void init(){
        meettingListView = (ListView)this.findViewById(R.id.lv_meeting_info);

        CompanyService.getInstance().getMeetingInfo(getApplicationContext(), new GetMeetingInfosHandler() {
            @Override
            public void onSuccess(String meettingInfos) {
                Gson mGson = new Gson();
                List<MeetingEntity> tempMeetingInfo = mGson.fromJson(meettingInfos, new TypeToken<List<MeetingEntity>>() {
                }.getType());
                mMeetingInfoList.addAll(tempMeetingInfo);
                adapter = new MeetingAdapter(getApplicationContext());
                adapter.setMeetings(mMeetingInfoList);
                meettingListView.setAdapter(adapter);
            }
        });
    }
}
