package com.lzb.oa.ui.task;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lzb.oa.R;
import com.lzb.oa.service.TaskManaService;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

public class TaskManaFragment extends Fragment {

    public TaskManaAdapter taskManaAdapter = null;
    private ListView lvTaskInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.task_mana_layout,
                container, false);
        return newsLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        TaskManaService.getInstance().getTaskInfo(getActivity(),
                taskManaAdapter, lvTaskInfo);
    }

    /**
     * 初始化
     */
    private void init() {
        lvTaskInfo = (ListView) getActivity().findViewById(R.id.lv_task_info);
        taskManaAdapter = new TaskManaAdapter(getActivity());
    }

}
