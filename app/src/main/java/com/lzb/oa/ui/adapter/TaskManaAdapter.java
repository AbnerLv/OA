package com.lzb.oa.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.commons.Period;
import com.lzb.oa.ui.task.TaskDetail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/9.
 */

@SuppressLint("InflateParams")
public class TaskManaAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<HashMap<String, Object>> listTask;

    private Context context;

    public TaskManaAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setItemList(ArrayList<HashMap<String, Object>> listTask) {
        this.listTask = listTask;
    }

    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView,
            ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.task_mana_item,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.tvTaskRent = (TextView) convertView
                    .findViewById(R.id.tv_task_rent);
            viewHolder.tvTaskDate = (TextView) convertView
                    .findViewById(R.id.tv_task_date);
            viewHolder.tvTaskAddress = (TextView) convertView
                    .findViewById(R.id.tv_task_address);
            viewHolder.tvTaskFlag = (TextView) convertView
                    .findViewById(R.id.tv_task_flag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String roomer_rent = listTask.get(position).get("roomer_rent")
                .toString().trim();
        final String roomer_date = listTask.get(position).get("roomer_date")
                .toString().trim();
        final String roomer_period = listTask.get(position).get("roomer_period")
                .toString().trim();
        final String house_address = listTask.get(position).get("house_address")
                .toString().trim();
        final String roomer_emp_no = listTask.get(position).get("roomer_emp_no")
                .toString().trim();

        Log.e("rent", roomer_rent);

        if (Integer.parseInt(roomer_rent) == 0) {
            viewHolder.tvTaskRent.setText("租房");
        } else {
            viewHolder.tvTaskRent.setText("买房");
        }

        viewHolder.tvTaskDate.setText("看房日期：" + roomer_date + "  "
                + Period.getString(Integer.parseInt(roomer_period)));
        viewHolder.tvTaskAddress.setText("房子地址： " + house_address);

        if ("".equals(roomer_emp_no)) {
            viewHolder.tvTaskFlag.setText("可领取");
        } else {
            viewHolder.tvTaskFlag.setText("任务已被" + roomer_emp_no + "领取，点击查看详情");
            viewHolder.tvTaskFlag.setBackgroundColor(
                    getContext().getResources().getColor(R.color.gray_bg));
        }

        // 给ListView的Item点击事件
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String roomerNo = listTask.get(position).get("roomer_no")
                        .toString().trim();
                String roomerName = listTask.get(position).get("roomer_name")
                        .toString().trim();
                String roomerSex = listTask.get(position).get("roomer_sex")
                        .toString().trim();
                String roomerPhoneNo = listTask.get(position)
                        .get("roomer_phone_no").toString().trim();
                String roomerHouseNo = listTask.get(position)
                        .get("roomer_house_no").toString().trim();
                String roomerComplete = listTask.get(position)
                        .get("roomer_complete").toString().trim();
                String roomerHouseCity = listTask.get(position)
                        .get("house_city").toString().trim();
                TaskDetail.startTaskDetail(context, roomerNo, roomerName,
                        roomerSex, roomerPhoneNo, roomerHouseNo, roomer_date,
                        roomer_period, roomer_rent, roomerComplete,
                        roomer_emp_no, roomerHouseCity, house_address);

            }
        });
        return convertView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    final class ViewHolder {
        TextView tvTaskRent;
        TextView tvTaskDate;
        TextView tvTaskAddress;
        TextView tvTaskFlag;
    }
}
