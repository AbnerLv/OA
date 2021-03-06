package com.lzb.oa.ui.adapter;

import android.view.View;

/**
 * Adapter 接口 . 列表必须实现此接口 .
 * Created by Administrator on 2016/6/8.
 */

public interface HeaderAdapter {

    public static final int PINNED_HEADER_GONE = 0;
    public static final int PINNED_HEADER_VISIBLE = 1;
    public static final int PINNED_HEADER_PUSHED_UP = 2;

    /**
     * 获取 Header 的状态
     * @param groupPosition
     * @param childPosition
     * @return PINNED_HEADER_GONE,PINNED_HEADER_VISIBLE,PINNED_HEADER_PUSHED_UP 其中之一
     */
    int getHeaderState(int groupPosition, int childPosition);

    /**
     * 配置 Header, 让 Header 知道显示的内容
     * @param header
     * @param groupPosition
     * @param childPosition
     * @param alpha
     */
    void configureHeader(View header, int groupPosition, int childPosition, int alpha);

    /**
     * 设置组按下的状态
     * @param groupPosition
     * @param status
     */
    void setGroupClickStatus(int groupPosition, int status);

    /**
     * 获取组按下的状态
     * @param groupPosition
     * @return
     */
    int getGroupClickStatus(int groupPosition);
}
