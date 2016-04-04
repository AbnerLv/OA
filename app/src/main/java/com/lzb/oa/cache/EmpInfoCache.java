package com.lzb.oa.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class EmpInfoCache {
    private static final String TAG = "EmpInfoCache";
    public static void cacheEmpInfo(Context context, JSONObject resp) {
        SharedPreferences sp = context.getSharedPreferences("OAEmpInfo",
                context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString("emp_nickname", resp.getString("emp_nickname"));
            editor.putString("emp_name", resp.getString("emp_name"));
            editor.putString("emp_sex", resp.getString("emp_sex"));
            editor.putString("emp_age", resp.getString("emp_age"));
            editor.putString("emp_phone_no", resp.getString("emp_phone_no"));
            editor.putString("emp_email", resp.getString("emp_email"));
            editor.putString("emp_no", resp.getString("emp_no"));
            editor.putString("emp_department",
                    resp.getString("emp_department"));
            editor.putString("emp_position", resp.getString("emp_position"));
            editor.putString("emp_entry_date",
                    resp.getString("emp_entry_date"));
            editor.putString("emp_birthday", resp.getString("emp_birthday"));
            editor.putString("emp_nation", resp.getString("emp_nation"));
            editor.putString("emp_identify", resp.getString("emp_identify"));
            editor.putString("emp_city", resp.getString("emp_city"));
            editor.putString("emp_address", resp.getString("emp_address"));
            editor.putString("emp_password", resp.getString("emp_password"));
            editor.commit();
        } catch (Exception e) {
            Log.d("TAG", e.toString());
            System.out.println("缓存员工信息失败");
        }
    }

}
