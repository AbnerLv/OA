package com.lzb.oa.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lzb.oa.R;
import com.lzb.oa.ui.activity.CompanyContactActivity;
import com.lzb.oa.ui.activity.MeetingActivity;
import com.lzb.oa.ui.activity.NoticeActivity;

public class ComplanyManaFragment extends Fragment implements OnClickListener {

    private LinearLayout complanyMeeting;
    private LinearLayout complanyContacts;
    private LinearLayout complanyNotices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.complany_mana_layout,
                container, false);
        return contactsLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    // 初始化控件
    private void init() {
        complanyMeeting = (LinearLayout) getActivity().findViewById(
                R.id.layout_complany_meeting);
        complanyContacts = (LinearLayout) getActivity().findViewById(
                R.id.layout_complany_contacts);
        complanyNotices = (LinearLayout) getActivity().findViewById(
                R.id.layout_complany_noticts);

        complanyMeeting.setOnClickListener(this);
        complanyContacts.setOnClickListener(this);
        complanyNotices.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.layout_complany_meeting:
            Intent intent = new Intent(getActivity(),MeetingActivity.class);
            getActivity().startActivity(intent);
            break;

        case R.id.layout_complany_contacts:
            Intent intentCompanyContac = new Intent(getActivity(),CompanyContactActivity.class);
            getActivity().startActivity(intentCompanyContac);
            break;

        case R.id.layout_complany_noticts:
            Intent intentNoticts = new Intent(getActivity(),NoticeActivity.class);
            getActivity().startActivity(intentNoticts);
            break;

        default:
            break;
        }
    }
}
