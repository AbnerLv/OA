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
import com.lzb.oa.ui.task.TaskDetail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
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
            viewHolder.tvTaskPeriod = (TextView) convertView
                    .findViewById(R.id.tv_task_period);
            viewHolder.tvTaskAddress = (TextView) convertView
                    .findViewById(R.id.tv_task_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (Integer.parseInt(
                listTask.get(position).get("roomer_rent").toString()) == 0) {
            viewHolder.tvTaskRent.setText("租房");
        } else {
            viewHolder.tvTaskRent.setText("买房");
        }
        viewHolder.tvTaskDate.setText(
                "看房日期：" + listTask.get(position).get("roomer_date").toString());
        Log.e("rent", listTask.get(position).get("roomer_rent").toString());
        switch (Integer.parseInt(
                listTask.get(position).get("roomer_period").toString())) {
        case 1:
            viewHolder.tvTaskPeriod.setHint("9:30~11:30");
            break;

        case 2:
            viewHolder.tvTaskPeriod.setHint("13:30~15:30");
            break;

        case 3:
            viewHolder.tvTaskPeriod.setHint("15:30~17:30");
            break;

        case 4:
            viewHolder.tvTaskPeriod.setHint("18:30~20:30");
            break;

        default:
            break;
        }
        viewHolder.tvTaskAddress.setText(
                listTask.get(position).get("house_address").toString());
                /*
                 * if ("".equals(listTask.get(position).get("roomer_emp_no"))){
                 * viewHolder.tvTaskFlag.setText("可领取"); } else {
                 * viewHolder.tvTaskFlag.setText("任务已被" +
                 * listTask.get(position).get("roomer_emp_no").toString().trim()
                 * + "领取，点击查看详情"); //
                 * viewHolder.tvTaskFlag.setBackgroundColor(R.color.gray_bg); }
                 */

        // 给ListView的Item点击事件
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String roomerNo = listTask.get(position).get("roomer_no")
                        .toString();
                String roomerName = listTask.get(position).get("roomer_name")
                        .toString();
                String roomerSex = listTask.get(position).get("roomer_sex")
                        .toString();
                String roomerPhoneNo = listTask.get(position)
                        .get("roomer_phone_no").toString();
                String roomerHouseNo = listTask.get(position)
                        .get("roomer_house_no").toString();
                String roomerDate = listTask.get(position).get("roomer_date")
                        .toString();
                String roomerPeriod = listTask.get(position)
                        .get("roomer_period").toString();
                String roomerRent = listTask.get(position).get("roomer_rent")
                        .toString();
                String roomerComplete = listTask.get(position)
                        .get("roomer_complete").toString();
                // String roomerEmpNo = listTask.get(position)
                // .get("roomer_emp_no").toString();
                String roomerHouseCity = listTask.get(position)
                        .get("house_city").toString();
                String roomerHouseAddress = listTask.get(position)
                        .get("house_address").toString();
                TaskDetail.startTaskDetail(context, roomerNo, roomerName,
                        roomerSex, roomerPhoneNo, roomerHouseNo, roomerDate,
                        roomerPeriod, roomerRent, roomerComplete, null,
                        roomerHouseCity, roomerHouseAddress);

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
        TextView tvTaskPeriod;
        TextView tvTaskAddress;
        TextView tvTaskFlag;
    }
}
