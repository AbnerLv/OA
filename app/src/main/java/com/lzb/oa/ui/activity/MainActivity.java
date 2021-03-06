package com.lzb.oa.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzb.oa.R;
import com.lzb.oa.ui.activity.BaseActivity;
import com.lzb.oa.ui.fragment.ComplanyManaFragment;
import com.lzb.oa.ui.fragment.CustomerInfoFragment;
import com.lzb.oa.ui.fragment.SettingFragment;
import com.lzb.oa.ui.fragment.TaskManaFragment;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * Created by lvzhenbin on 2015/9/23.
 */
public class MainActivity extends BaseActivity implements OnClickListener {

    private static final String TAG = "MainActivity";
    private CustomerInfoFragment customerInfoFragment; //用于展示消息的Fragment
    private ComplanyManaFragment complanyManaFragment;//用于展示联系人的Fragment
    private TaskManaFragment taskManaFragment; //用于展示动态的Fragment
    private SettingFragment settingFragment;  //用于展示设置的Fragment
    private View taskLayout;   //消息界面布局
    private View contactsLayout;  //联系人界面布局
    private View newsLayout;  //动态界面布局
    private View settingLayout;  //设置界面布局
    private ImageView messageImage;  //在Tab布局上显示消息图标的控件
    private ImageView contactsImage;  //在Tab布局上显示联系人图标的控件
    private ImageView newsImage;  //在Tab布局上显示动态图标的控件
    private ImageView settingImage;  //在Tab布局上显示设置图标的控件
    private TextView messageText; //在Tab布局上显示消息标题的控件
    private TextView contactsText;  //在Tab布局上显示联系人标题的控件
    private TextView newsText; //在Tab布局上显示动态标题的控件
    private TextView settingText;  //在Tab布局上显示设置标题的控件
    private FragmentManager fragmentManager;//用于对Fragment进行管理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        taskLayout = findViewById(R.id.task_layout);
        contactsLayout = findViewById(R.id.customer_layout);
        newsLayout = findViewById(R.id.company_layout);
        settingLayout = findViewById(R.id.setting_layout);
        messageImage = (ImageView) findViewById(R.id.task_image);
        contactsImage = (ImageView) findViewById(R.id.customer_image);
        newsImage = (ImageView) findViewById(R.id.company_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);
        messageText = (TextView) findViewById(R.id.task_text);
        contactsText = (TextView) findViewById(R.id.customer_text);
        newsText = (TextView) findViewById(R.id.company_text);
        settingText = (TextView) findViewById(R.id.setting_text);

        taskLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.task_layout:
            setTabSelection(0);
            break;
        case R.id.customer_layout:
            setTabSelection(1);
            break;
        case R.id.company_layout:
            setTabSelection(2);
            break;
        case R.id.setting_layout:
            setTabSelection(3);
            break;
        default:
            break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     * 
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        clearSelection();// 每次选中之前先清楚掉上次的选中状态
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);  // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index) {
        case 0:
            // 当点击了动态tab时，改变控件的图片和文字颜色
            messageImage.setImageResource(R.mipmap.main_selected);
            messageText.setTextColor(Color.parseColor("#11cd6e"));
            Log.d(TAG,"taskManaFragment");
            if (taskManaFragment == null) {
                // 如果taskManaFragment为空，则创建一个并添加到界面上
                taskManaFragment = new TaskManaFragment();
                transaction.add(R.id.content, taskManaFragment);
            } else {
                // 如果taskManaFragment不为空，则直接将它显示出来
                taskManaFragment.onResume();
                transaction.show(taskManaFragment);
            }
            break;

        case 1:
            // 当点击了消息tab时，改变控件的图片和文字颜色
            contactsImage.setImageResource(R.mipmap.customer_selected);
            contactsText.setTextColor(Color.parseColor("#11cd6e"));
            if (customerInfoFragment == null) {
                // 如果customerInfoFragment为空，则创建一个并添加到界面上
                customerInfoFragment = new CustomerInfoFragment();
                transaction.add(R.id.content, customerInfoFragment);
                transaction.show(customerInfoFragment);
            } else {
                // 如果customerInfoFragment不为空，则直接将它显示出来
                customerInfoFragment.onResume();
                transaction.show(customerInfoFragment);
            }

            break;
        case 2:
            // 当点击了联系人tab时，改变控件的图片和文字颜色
            newsImage.setImageResource(R.mipmap.company_selected);
            newsText.setTextColor(Color.parseColor("#11cd6e"));
            if (complanyManaFragment == null) {
                // 如果complanyManaFragment为空，则创建一个并添加到界面上
                complanyManaFragment = new ComplanyManaFragment();
                transaction.add(R.id.content, complanyManaFragment);
            } else {
                // 如果complanyManaFragment不为空，则直接将它显示出来
                complanyManaFragment.onResume();
                transaction.show(complanyManaFragment);
            }
            break;

        case 3:
            // 当点击了设置tab时，改变控件的图片和文字颜色
            settingImage.setImageResource(R.mipmap.setting_selected);
            settingText.setTextColor(Color.parseColor("#11cd6e"));
            if (settingFragment == null) {
                // 如果SettingFragment为空，则创建一个并添加到界面上
                settingFragment = new SettingFragment();
                transaction.add(R.id.content, settingFragment);
            } else {
                // 如果SettingFragment不为空，则直接将它显示出来
                settingFragment.onResume();
                transaction.show(settingFragment);
            }
            break;
            default:break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        messageImage.setImageResource(R.mipmap.main_unselected);
        messageText.setTextColor(Color.parseColor("#33475f"));
        contactsImage.setImageResource(R.mipmap.customer_unselected);
        contactsText.setTextColor(Color.parseColor("#33475f"));
        newsImage.setImageResource(R.mipmap.company_unselected);
        newsText.setTextColor(Color.parseColor("#33475f"));
        settingImage.setImageResource(R.mipmap.setting_unselected);
        settingText.setTextColor(Color.parseColor("#33475f"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     * 
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (customerInfoFragment != null) {
            transaction.hide(customerInfoFragment);
        }
        if (complanyManaFragment != null) {
            transaction.hide(complanyManaFragment);
        }
        if (taskManaFragment != null) {
            transaction.hide(taskManaFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

}
