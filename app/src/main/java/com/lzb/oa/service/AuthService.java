package com.lzb.oa.service;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by lvzhenbin on 2015/10/4.
 */
public class AuthService {

    public static AuthService instance;

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public void register(final Context context, final JSONObject json) {

    }

}
