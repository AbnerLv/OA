package com.lzb.oa.ui.complany;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzb.oa.R;
import com.lzb.oa.entity.NoticeEntity;
import com.lzb.oa.service.CompanyService;
import com.lzb.oa.service.handler.GetNoticeInfosHandler;
import com.lzb.oa.ui.adapter.NoticeAdapter;


import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends Activity {


    private ListView noticeListView;
    private final List<NoticeEntity> mNoticeInfoList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.notice_list);
        init();
    }


    private void init(){
        noticeListView = (ListView)this.findViewById(R.id.lv_notice_info);
        CompanyService.getInstance().getNoticeInfo(getApplicationContext(), new GetNoticeInfosHandler() {
            @Override
            public void onSuccess(String noticeInfos) {
                Gson mGson = new Gson();
                List<NoticeEntity> tempNoticeInfo = mGson.fromJson(noticeInfos, new TypeToken<List<NoticeEntity>>() {
                }.getType());
                mNoticeInfoList.addAll(tempNoticeInfo);
                noticeAdapter = new NoticeAdapter(getApplicationContext());
                noticeAdapter.setNotices(mNoticeInfoList);
                noticeListView.setAdapter(noticeAdapter);
            }
        });

    }
}
