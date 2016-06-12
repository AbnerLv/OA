package com.lzb.oa.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.entity.TaskInfoEntity;
import com.lzb.oa.service.SettingService;
import com.lzb.oa.service.handler.GetHaveTasksHandler;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import java.util.List;

public class HaveTaskActivity extends BaseActivity {


    private ListView lvHaveTaskInfo;
    private TaskManaAdapter taskManaAdapter;

    public static void startSettingTaskInfo(Context context, String emp_no) {
        Intent intent = new Intent(context, HaveTaskActivity.class);
        intent.putExtra("emp_no", emp_no);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.have_task_layout);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        lvHaveTaskInfo = (ListView) findViewById(R.id.lv_have_task_info);
        requestData();
    }

    private void requestData(){
        Intent intent = getIntent();
        String empNo = intent.getStringExtra("emp_no");
        SettingService.getInstance().getHaveTasks(getApplicationContext(), empNo, new GetHaveTasksHandler() {
            @Override
            public void success(List<TaskInfoEntity> entitys) {
                if (entitys != null && entitys.size() != 0) {
                    taskManaAdapter = new TaskManaAdapter(getApplicationContext());
                    taskManaAdapter.setTaskInfos(entitys);
                    lvHaveTaskInfo.setAdapter(taskManaAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "没有任务", Toast.LENGTH_LONG);
                }
            }
        });
    }



    @Override
    public void onStop() {
        super.onStop();
        SettingService.getInstance().cancelPendingRequests();
    }

}
