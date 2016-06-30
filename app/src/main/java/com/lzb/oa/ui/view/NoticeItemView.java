package com.lzb.oa.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.entity.NoticeEntity;
import com.lzb.oa.ui.activity.NoticeDetailActivity;


/**
 * Created by lzb on 2016/5/24.
 */
public class NoticeItemView {

    public static View getInstance(final Context context, View noticeView,final NoticeEntity notice) {
        if (noticeView == null) {
            noticeView = View.inflate(context, R.layout.company_notice_list_item, null);
        }

        TextView noticeThemeView = ViewHolder.findViewById(noticeView, R.id.tv_notice_theme);
        noticeThemeView.setText(notice.getTheme());
        TextView noticeTimeView = ViewHolder
                .findViewById(noticeView, R.id.tv_notice_time);
        noticeTimeView.setText(notice.getTime());
        TextView noticeConntentView = ViewHolder
                .findViewById(noticeView, R.id.tv_notice_content);
        noticeConntentView.setText(notice.getContent());
        noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("notice_theme", notice.getTheme());
                intent.putExtra("notice_time", notice.getTime());
                intent.putExtra("notice_content", notice.getContent());
                intent.putExtra("notice_empno", notice.getEmpNo());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return noticeView;
    }
}
