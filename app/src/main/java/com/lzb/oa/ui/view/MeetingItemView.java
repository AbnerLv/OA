package com.lzb.oa.ui.view;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.entity.MeetingEntity;
import com.lzb.oa.ui.complany.MeetingDetailActivity;


/**
 * Created by lzb on 2016/5/23.
 */
public class MeetingItemView {

    public static View getInstance(final Context context, View meetingView,final MeetingEntity meeting) {
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
        meetingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MeetingDetailActivity.class);
                intent.putExtra("meeting_theme", meeting.getTheme());
                intent.putExtra("meeting_time", meeting.getTime());
                intent.putExtra("meeting_end_time", meeting.getEndTime());
                intent.putExtra("meeting_content", meeting.getContent());
                intent.putExtra("meeting_address", meeting.getAddress());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return meetingView;
    }
}
