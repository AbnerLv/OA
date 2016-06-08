package com.lzb.oa.ui.complany;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzb.oa.R;
import com.lzb.oa.entity.EmpEntity;
import com.lzb.oa.service.CompanyService;
import com.lzb.oa.service.handler.GetAllContactersHandler;
import com.lzb.oa.service.handler.GetDepartmentsHandler;
import com.lzb.oa.ui.adapter.CompanyContactAdapter;
import com.lzb.oa.ui.view.CompanyContactListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 公司的通讯录
 */
public class CompanyContactActivity extends Activity{
	private static final String TAG = "CompanyContactActivity";
	private CompanyContactListView explistview;
	private ArrayList<String> departmentData = null;
	private int expandFlag = -1;//控制列表的展开  
	private CompanyContactAdapter adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_contact_layout);
		initView();
		requestData();
	}
	
	/**
	 * 初始化VIEW
	 */
	private void initView() {
		explistview = (CompanyContactListView)findViewById(R.id.explistview);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {



		//设置单个分组展开
		//explistview.setOnGroupClickListener(new GroupClickListener());
	}

	private void requestData(){
		Log.d(TAG,"requestData---");
		CompanyService.getInstance().getDepartments(getApplicationContext(), new GetDepartmentsHandler() {
			@Override
			public void onSuccess(String departments) {
				Gson gson = new Gson();
				departmentData = gson.fromJson(departments,new TypeToken<List<String>>() {
				}.getType());
			}
		});

		CompanyService.getInstance().getAllContacters(getApplicationContext(), new GetAllContactersHandler() {
			@Override
			public void onSuccess(String contacters) {
				Log.i(TAG,contacters.toString());
				Gson gson = new Gson();
				List<EmpEntity> empEntities = gson.fromJson(contacters,new TypeToken<List<EmpEntity>>() {
				}.getType());
				Log.i(TAG,"empEntities = "+ empEntities.size()+"");
				ArrayList<EmpEntity>[] contacterList = new ArrayList[departmentData.size()];
				Log.i(TAG,empEntities.toString());
				for(int i = 0; i < departmentData.size(); i++){
					contacterList[i] = new ArrayList<>();
					for(EmpEntity entity : empEntities){
						if((departmentData.get(i)).equals(entity.getEmp_department())){
							contacterList[i].add(entity);
							Log.i(TAG,"emp_name = " + entity.getEmp_name());
						}
					}
				}
				//设置悬浮头部VIEW
				explistview.setHeaderView(getLayoutInflater().inflate(R.layout.company_contact_group_head,
						explistview, false));
				adapter = new CompanyContactAdapter(contacterList, departmentData, getApplicationContext(),explistview);
				explistview.setAdapter(adapter);
			}
		});
	}
	
	class GroupClickListener implements OnGroupClickListener{
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
			if (expandFlag == -1) {
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			} else if (expandFlag == groupPosition) {
				explistview.collapseGroup(expandFlag);
				expandFlag = -1;
			} else {
				explistview.collapseGroup(expandFlag);
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			}
			return true;
		}
	}
}
