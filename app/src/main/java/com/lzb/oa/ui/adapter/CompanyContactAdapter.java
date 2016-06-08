package com.lzb.oa.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.entity.EmpEntity;
import com.lzb.oa.ui.view.CompanyContactListView;

import java.util.ArrayList;


public class CompanyContactAdapter extends  BaseExpandableListAdapter implements HeaderAdapter {
	private static final String TAG = "CompanyContactAdapter";
	private ArrayList<EmpEntity>[] childrenData;
	private ArrayList<String> groupData;
	private Context context;
	private CompanyContactListView listView;
	private LayoutInflater inflater;

	public CompanyContactAdapter(ArrayList<EmpEntity>[] childrenData,ArrayList<String> groupData
			,Context context,CompanyContactListView listView){
		this.groupData = groupData;
		this.childrenData = childrenData;
		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
		Log.d(TAG,"CompanyContactAdapter Constoner");
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childrenData[groupPosition].get(childPosition).getEmp_name();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = createChildrenView();
		}
		TextView text = (TextView)view.findViewById(R.id.childto);
		text.setText(childrenData[groupPosition].get(childPosition).getEmp_name());
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childrenData[groupPosition].size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = createGroupView();
		}

		ImageView iv = (ImageView)view.findViewById(R.id.groupIcon);

		if (isExpanded) {
			iv.setImageResource(R.mipmap.contacts_browser_down);
		}
		else{
			iv.setImageResource(R.mipmap.contacts_browser_right);
		}

		TextView text = (TextView)view.findViewById(R.id.groupto);
		text.setText(groupData.get(groupPosition));
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	private View createChildrenView() {
		return inflater.inflate(R.layout.company_contact_child, null);
	}

	private View createGroupView() {
		return inflater.inflate(R.layout.company_contact_group, null);
	}

	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition,
								int childPosition, int alpha) {
		String groupData =  this.groupData.get(groupPosition);
		((TextView) header.findViewById(R.id.groupto)).setText(groupData);

	}

	private SparseIntArray groupStatusMap = new SparseIntArray();

	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.keyAt(groupPosition)>=0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}
}
