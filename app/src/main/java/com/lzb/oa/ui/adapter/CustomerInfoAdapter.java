package com.lzb.oa.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lzb.oa.R;
import com.lzb.oa.ui.customer.CustomerDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/10. 获取客户的信息
 */
public class CustomerInfoAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<HashMap<String, Object>> listCustomerInfo;
    private Context context;

    public CustomerInfoAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setItemList(
            ArrayList<HashMap<String, Object>> listCustomerInfo) {
        this.listCustomerInfo = listCustomerInfo;
        Log.e("list2", this.listCustomerInfo.toString());
    }

    @Override
    public int getCount() {
        Log.e("list2", this.listCustomerInfo.size() + "");
        return listCustomerInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView,
            ViewGroup parent) {
        ViewHolder viewHolder;
        Log.e("size", listCustomerInfo.size() + "");
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.customer_list_item,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.tvCustomerName = (TextView) convertView
                    .findViewById(R.id.tv_customer_name);
            viewHolder.tvCustomerPhoneNo = (TextView) convertView
                    .findViewById(R.id.tv_customer_phone_no);
            viewHolder.tvCustomerDate = (TextView) convertView
                    .findViewById(R.id.tv_customer_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (listCustomerInfo.size() > 0) {
            viewHolder.tvCustomerName.setText(listCustomerInfo.get(position)
                    .get("roomer_name").toString());
            viewHolder.tvCustomerPhoneNo.setText(listCustomerInfo.get(position)
                    .get("roomer_phone_no").toString());
            viewHolder.tvCustomerDate.setText(listCustomerInfo.get(position)
                    .get("roomer_date").toString());

            /**
             * 点击查看明细
             */
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击查看明细", Toast.LENGTH_SHORT)
                            .show();
                    String roomerNo = listCustomerInfo.get(position)
                            .get("roomer_no").toString();
                    CustomerDetailActivity.startCustomerDetailActivity(context,
                            roomerNo);
                }
            });
        } else {
            Toast.makeText(context, "暂时没有客户信息", Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // 辅助类，优化ListView性能
    private class ViewHolder {
        TextView tvCustomerName;
        TextView tvCustomerPhoneNo;
        TextView tvCustomerDate;
    }

}
