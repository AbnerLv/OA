package com.lzb.oa.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.lzb.oa.R;
import com.lzb.oa.entity.TaskInfoEntity;
import com.lzb.oa.service.TaskManaService;
import com.lzb.oa.service.handler.GetTaskInfosHandler;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import java.util.List;

/**
 * Created by lvzhenbin on 2016/3/1.
 */
public class TaskManaFragment extends Fragment {

    private static final String TAG = "TaskManaFragment";
    private TaskManaAdapter taskManaAdapter = null;
    private ListView mListView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View taskView = inflater.inflate(R.layout.task_mana_layout,container, false);
        mListView = (ListView)taskView.findViewById(R.id.lv_task_info);
        return taskView;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();

    }



    @Override
    public void onStop() {
        super.onStop();
        TaskManaService.getInstance().cancelPendingRequests();
    }


    public void requestData() {
        TaskManaService.getInstance().getTaskInfos(getActivity(), new GetTaskInfosHandler() {
            @Override
            public void onSuccess(List<TaskInfoEntity> taskEntity) {
                if(taskEntity != null){
                    taskManaAdapter = new TaskManaAdapter(getActivity());
                    taskManaAdapter.setTaskInfos(taskEntity);
                    mListView.setAdapter(taskManaAdapter);
                }else{
                    Toast.makeText(getActivity(),"任务获取失败，等会再试！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
