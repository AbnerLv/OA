package com.lzb.oa.ui.task;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzb.oa.MainActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.TaskManaService;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvzhenbin
 * @date 2015-10-12
 */
public class TaskManaFragment extends Fragment {

    private static final String TAG = "TaskManaFragment";
    public TaskManaAdapter taskManaAdapter = null;
    // private ListView lvTaskInfo;
    private PullToRefreshListView mPullToRefreshListView;
    private String empNo = null;
    private boolean is_divPage;  //是否分页
    private List<String> oneTotal = new ArrayList<String>();// 用来存放一页数据
    private List<String> total = new ArrayList<String>();//用来存放获取的所有数据
    private static int pageNo = 1;//设置pageNo的初始化值为1，即默认获取的是第一页的数据。


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.task_mana_layout,
                container, false);
        return newsLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 初始化
     */
    private void init() {
       // lvTaskInfo = (ListView) getActivity().findViewById(R.id.lv_task_info);

        initContentListView();
        SharedPreferences sp = getActivity().getSharedPreferences(
                Constant.EMPINFO, getActivity().MODE_PRIVATE);
        empNo = sp.getString("emp_no", null);

    }

    private void initContentListView(){
        mPullToRefreshListView = (PullToRefreshListView)getActivity().findViewById(R.id.lv_task_info);

        //初始化控件
       // mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH); //下拉和上拉都会执行onRefresh()中的方法了
        taskManaAdapter = new TaskManaAdapter(getActivity());
        requestData(pageNo);
        /*
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e(TAG, "onPullDownToRefresh");
                requestData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e(TAG, "onPullDownToRefresh");
                //这里写下拉刷新的任务
            }
        });*/

        /*
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getActivity(), "正在获取更多数据...", Toast.LENGTH_SHORT).show();
                requestData(pageNo++);

            }
        });
        */

        mPullToRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <=4) {
                    Toast.makeText(getActivity(), "正在获取更多数据...",Toast.LENGTH_SHORT).show();
                    requestData(pageNo++);
                }else if (pageNo >4) {
                    /**
                     * 如果pageNo>4则表示，服务端没有更多的数据可供加载了。
                     */
                    Toast.makeText(getActivity(), "没有更多数据啦...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                is_divPage = (firstVisibleItem + visibleItemCount == totalItemCount);
            }
        });


    }

    private void requestData(int pageNo) {
        TaskManaService.getInstance().getTaskInfo(getActivity(),
                taskManaAdapter, mPullToRefreshListView, pageNo);
    }

}
