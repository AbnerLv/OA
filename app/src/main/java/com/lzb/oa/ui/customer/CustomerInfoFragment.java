package com.lzb.oa.ui.customer;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.ui.adapter.CustomerInfoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerInfoFragment extends Fragment {

    private final static String CUSTOMER_INFO_URL = Constant.URL
            + "customer_info.json";

    private ListView lvCustomerInfo;
    private RequestQueue mQueue = null;
    private CustomerInfoAdapter customerInfoAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.customer_layout,
                container, false);
        return messageLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        getCustomerInfo();
    }

    // 初始化ListView控件
    private void init() {
        lvCustomerInfo = (ListView) getActivity().findViewById(
                R.id.lv_customer_info);
        customerInfoAdapter = new CustomerInfoAdapter(getActivity());
    }

    // 从服务器获取信息
    private void getCustomerInfo() {
        final ArrayList<HashMap<String, Object>> listCustomerInfo = new ArrayList<HashMap<String, Object>>();
        mQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                CUSTOMER_INFO_URL, new Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("jsonArray", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = new JSONObject(response
                                        .get(i).toString());

                                HashMap<String, Object> map = new HashMap<String, Object>();
                                map.put("roomer_no", json.get("roomer_no"));
                                map.put("roomer_name", json.get("roomer_name"));
                                map.put("roomer_phone_no",
                                        json.get("roomer_phone_no"));
                                map.put("roomer_date", json.get("roomer_date"));
                                listCustomerInfo.add(map);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // 将list集合中的数据传入到自定义的adapter中
                        customerInfoAdapter.setItemList(listCustomerInfo);
                        customerInfoAdapter.notifyDataSetChanged();
                        lvCustomerInfo.setAdapter(customerInfoAdapter);
                    }
                }, new ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        mQueue.add(jsonArrayRequest);
    }

}
