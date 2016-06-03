package com.lzb.oa.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.commons.Period;
import com.lzb.oa.entity.TaskInfoEntity;
import com.lzb.oa.ui.task.TaskDetailActivity;

/**
 * Created by lvzhenbin on 2016/4/2.
 */
public class TaskInfoItemView {

    public static View getInstance(final Context context, View taskInfoView, final TaskInfoEntity taskInfo) {
        if (taskInfoView == null) {
            taskInfoView = View.inflate(context, R.layout.task_mana_item, null);
        }

        TextView taskRentView = ViewHolder
                .findViewById(taskInfoView, R.id.tv_task_rent);
        if (Integer.parseInt(taskInfo.getRoomer_rent()) == 0) {
            taskRentView.setText("租房");
        } else {
            taskRentView.setText("买房");
        }

        TextView taskDateView = ViewHolder
                .findViewById(taskInfoView, R.id.tv_task_date);
        taskDateView.setText("看房日期：" + taskInfo.getRoomer_date() + "  "
                + Period.getString(Integer.parseInt(taskInfo.getRoomer_period())));

        TextView taskAddressView = ViewHolder
                .findViewById(taskInfoView, R.id.tv_task_address);
        taskAddressView.setText("房子地址： "+taskInfo.getHouse_address());

        TextView taskFlagView = ViewHolder
                .findViewById(taskInfoView, R.id.tv_task_flag);
        if ("".equals(taskInfo.getRoomer_emp_no())) {
            taskFlagView.setText("可领取");
            taskFlagView.setBackgroundColor(ContextCompat.getColor(
                    context, R.color.calendar_green));
        } else {
            taskFlagView.setText("任务已被" + taskInfo.getRoomer_emp_no() + "领取，点击查看详情");
            taskFlagView.setBackgroundColor(ContextCompat.getColor(
                   context, R.color.light_red));
        }

        taskInfoView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TaskDetailActivity.class);
                intent.putExtra("task_data", taskInfo);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return taskInfoView;
    }
}
