package com.lzb.oa.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lzb.oa.entity.MeetingEntity;
import com.lzb.oa.ui.view.MeetingItemView;

import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/9.
 */


public class MeetingAdapter extends BaseAdapter {
    private static final String TAG = "TaskManaAdapter";
    private List<MeetingEntity> meetings;

    private Context mContext;

    public MeetingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMeetings(List<MeetingEntity> meetings){
        this.meetings = meetings;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.e(TAG,"mTaskInfos "+meetings.size()+"");
        return meetings.size();
    }

    @Override
    public Object getItem(int position) {
        return meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return meetings.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView,
            ViewGroup parent) {
       return MeetingItemView.getInstance(mContext, convertView, meetings.get(position));
    }
}
