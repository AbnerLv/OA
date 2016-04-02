package com.lzb.oa.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lzb.oa.entity.TaskInfo;
import com.lzb.oa.ui.view.TaskInfoItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/9.
 */


public class TaskManaAdapter extends BaseAdapter {
    private static final String TAG = "TaskManaAdapter";
    private List<TaskInfo> mTaskInfos;

    private Context mContext;

    public TaskManaAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setTaskInfos(List<TaskInfo> taskList){
        this.mTaskInfos = taskList;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        Log.e(TAG,"mTaskInfos "+mTaskInfos.size()+"");
        return mTaskInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTaskInfos.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView,
            ViewGroup parent) {
       return TaskInfoItemView.getInstance(mContext,convertView,mTaskInfos.get(position));
    }
}
