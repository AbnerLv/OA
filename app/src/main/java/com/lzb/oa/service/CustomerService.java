package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.response.ErrorResponse;
import com.lzb.oa.ui.adapter.CustomerInfoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class CustomerService {

    public static CustomerService instance;

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    /**
     * 获取客户信息
     * 
     * @param context
     *            getActivity()
     * @param customerInfoAdapter
     *            客户信息适配器
     * @param lvCustomerInfo
     *            显示客户信息列表的ListViewa
     */
    public void getCustomerInfo(Context context,
            final CustomerInfoAdapter customerInfoAdapter,
            final ListView lvCustomerInfo) {
        String CUSTOMER_INFO_URL = Constant.URL + "customer_info.json";
        final ArrayList<HashMap<String, Object>> listCustomerInfo = new ArrayList<>();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                CUSTOMER_INFO_URL, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("jsonArray", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = new JSONObject(response
                                        .get(i).toString());
                                HashMap<String, Object> map = new HashMap<>();
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
                        customerInfoAdapter.setItemList(listCustomerInfo);
                        customerInfoAdapter.notifyDataSetChanged();
                        lvCustomerInfo.setAdapter(customerInfoAdapter);
                    }
                }, new ErrorResponse(context));
        mQueue.add(jsonArrayRequest);
    }

}
