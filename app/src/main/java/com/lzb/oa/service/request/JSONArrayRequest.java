package com.lzb.oa.service.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class JSONArrayRequest extends JsonRequest<JSONArray> {

    /**
     * 重写JsonArrayRequest 类
     * 
     * @param method
     * @param url
     * @param jsonRequest
     * @param listener
     * @param errorListener
     */
    public JSONArrayRequest(int method, String url, JSONObject jsonRequest,
            Response.Listener<JSONArray> listener,
            Response.ErrorListener errorListener) {
        super(method, url,
                (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(
            NetworkResponse response) {
        try {
            String je = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            return Response.success(new JSONArray(je),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        } catch (JSONException var4) {
            return Response.error(new ParseError(var4));
        }
    }
}
