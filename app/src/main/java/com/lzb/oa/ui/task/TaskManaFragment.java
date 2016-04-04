package com.lzb.oa.ui.task;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.entity.TaskInfo;
import com.lzb.oa.service.TaskManaService;
import com.lzb.oa.service.handler.GetTaskInfosHandler;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzhenbin on 2016/3/1.
 */
public class TaskManaFragment extends Fragment {

    private static final String TAG = "TaskManaFragment";
    private TaskManaAdapter taskManaAdapter = null;
    private PullToRefreshListView mListView;
    private final List<TaskInfo> mTaskInfoList = new ArrayList<>();
    private String empNo = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View taskView = inflater.inflate(R.layout.task_mana_layout,
                container, false);
        mListView = (PullToRefreshListView)taskView.findViewById(R.id.lv_task_info);
        return taskView;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
        initContentListView();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        init();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }


    @Override
    public void onStop() {
        super.onStop();
        TaskManaService.getInstance().cancelPendingRequests();
    }

    /**
     * 初始化
     */
    private void init() {
        Log.d(TAG,"init");

        SharedPreferences sp = getActivity().getSharedPreferences(
                Constant.EMPINFO, getActivity().MODE_PRIVATE);
        empNo = sp.getString("emp_no", null);

    }

    private void initContentListView(){
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                requestData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                requestData();
            }
        });
        mListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "数据加载完毕", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void requestData() {
        TaskManaService.getInstance().getTaskInfos(getActivity(), new GetTaskInfosHandler() {
            @Override
            public void onSuccess(String taskInfos) {
                Gson mGson = new Gson();
                List<TaskInfo> tempTaskInfo = mGson.fromJson(taskInfos, new TypeToken<List<TaskInfo>>() {
                }.getType());
                mTaskInfoList.addAll(tempTaskInfo);
                taskManaAdapter = new TaskManaAdapter(getActivity());
                taskManaAdapter.setTaskInfos(mTaskInfoList);
                mListView.setAdapter(taskManaAdapter);
            }
        });
    }


}
