package com.lzb.oa.service.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;


/**
 * Created by lvzhenbin on 2016/4/2.
 */
public class GsonRequest<T> extends Request<T> {
    private final Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;

    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }


    public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError());
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


}
