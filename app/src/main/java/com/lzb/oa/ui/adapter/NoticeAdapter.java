package com.lzb.oa.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lzb.oa.entity.NoticeEntity;
import com.lzb.oa.ui.view.NoticeItemView;

import java.util.List;

/**
 * Created by lzb on 2016/5/24.
 */


public class NoticeAdapter extends BaseAdapter {
    private static final String TAG = "NoticeAdapter";
    private List<NoticeEntity> notices;

    private Context mContext;

    public NoticeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setNotices(List<NoticeEntity> notices){
        this.notices = notices;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.e(TAG,"NoticeAdapter "+notices.size()+"");
        return notices.size();
    }

    @Override
    public Object getItem(int position) {
        return notices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notices.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView,
            ViewGroup parent) {
       return NoticeItemView.getInstance(mContext, convertView, notices.get(position));
    }
}
