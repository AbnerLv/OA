package com.lzb.oa.service;

/**
 * Created by lvzhenbin on 2015/10/4.
 */
public class AuthService {

    public static String SMSAPPKEY = "addb908c50f4";
    public static String SMSAPPSECRET = "b3668eaf3ef4825e6a7653fd8d1799bc";

    public static AuthService instance;

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

}
