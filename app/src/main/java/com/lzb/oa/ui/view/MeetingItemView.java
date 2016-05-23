package com.lzb.oa.ui.view;

import android.content.Context;

import android.view.View;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.entity.MeetingEntity;


/**
 * Created by lzb on 2016/5/23.
 */
public class MeetingItemView {

    public static View getInstance(final Context context, View meetingView,MeetingEntity meeting) {
        if (meetingView == null) {
            meetingView = View.inflate(context, R.layout.meeting_list_item, null);
        }

        TextView meetingThemeView = ViewHolder.findViewById(meetingView, R.id.tv_meeting_theme);
        meetingThemeView.setText(meeting.getTheme());
        TextView meetingTimeView = ViewHolder
                .findViewById(meetingView, R.id.tv_meeting_time);
        meetingTimeView.setText(meeting.getTime());
        TextView meetingConntentView = ViewHolder
                .findViewById(meetingView, R.id.tv_meeting_content);
        meetingConntentView.setText(meeting.getContent());
        TextView meetingAddressView = ViewHolder
                .findViewById(meetingView, R.id.tv_meeting_address);
        meetingAddressView.setText(meeting.getAddress());
        return meetingView;
    }
}
