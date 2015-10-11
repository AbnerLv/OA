package com.lzb.oa.ui.customer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lzb.oa.R;
import com.lzb.oa.service.CustomerService;
import com.lzb.oa.ui.adapter.CustomerInfoAdapter;

public class CustomerInfoFragment extends Fragment {


    private ListView lvCustomerInfo;
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
        CustomerService.getInstance().getCustomerInfo(getActivity(),
                customerInfoAdapter, lvCustomerInfo);
    }

    // 初始化ListView控件
    private void init() {
        lvCustomerInfo = (ListView) getActivity().findViewById(
                R.id.lv_customer_info);
        customerInfoAdapter = new CustomerInfoAdapter(getActivity());
    }


}
