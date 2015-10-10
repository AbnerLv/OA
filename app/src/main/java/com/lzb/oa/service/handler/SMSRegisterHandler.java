package com.lzb.oa.service.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.lzb.oa.ui.login_resgester.RegisterActivity;

import cn.smssdk.SMSSDK;

/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class SMSRegisterHandler extends Handler {

    private Context context;
    private EditText etPhoneNo;

    public SMSRegisterHandler(Context context, EditText etPhoneNo) {
        this.context = context;
        this.etPhoneNo = etPhoneNo;
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;
        Log.e("event", "event=" + event);
        Log.e("result", "result=" + result);
        Log.e("data", "data=" + data);

        if (result == SMSSDK.RESULT_COMPLETE) {
            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                Toast.makeText(context, "提交验证码成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivity(intent);

                RegisterActivity.startRegisterActivity(context,
                        etPhoneNo.getText().toString().trim());
            } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                Toast.makeText(context, "验证码已经发送", Toast.LENGTH_SHORT).show();
            } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                Toast.makeText(context, "获取国家列表成功", Toast.LENGTH_SHORT).show();
            }
        } else {
            ((Throwable) data).printStackTrace();
            Toast.makeText(context, "发送失败", Toast.LENGTH_LONG).show();
        }

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public EditText getEtPhoneNo() {
        return etPhoneNo;
    }

    public void setEtPhoneNo(EditText etPhoneNo) {
        this.etPhoneNo = etPhoneNo;
    }
}
