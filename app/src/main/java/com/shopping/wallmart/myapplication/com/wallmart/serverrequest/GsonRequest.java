package com.shopping.wallmart.myapplication.com.wallmart.serverrequest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by aruna on 21/03/18.
 */

public class GsonRequest<T> extends Request<T>
{
    /* Default charset for GSON request. */
    protected static final String PROTOCOL_CHARSET = "utf-8";


    protected final Gson mGSON = new Gson();

    protected final Class<T> mResponseClazz;
    protected final Response.Listener<T> mListner;

    private String mRequestBody;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param listener response listener
     * @param errorListener error response listener
     */
    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener)
    {
        super(Method.GET, url, errorListener);
        mResponseClazz = clazz;
        mListner = listener;
    }

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     * @param listener response listener
     * @param errorListener error response listener
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener)
    {
        super(Method.GET, url, errorListener);
        mResponseClazz = clazz;
        mListner = listener;
    }

    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param method URL Request method
     * @param url URL of the request to make
     * @param responseBody Request response body
     * @param clazz Relevant class object, for Gson's reflection
     * @param listener response listener
     * @param errorListener error response listener
     */
    public GsonRequest(int method, String url, String responseBody,
                       Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener)
    {
        super(method, url, errorListener);
        mResponseClazz = clazz;
        mListner = listener;
        mRequestBody = responseBody;
    }

    /**
     * Make  request and return a parsed object from JSON.
     *
     * @param method URL Request method
     * @param url URL of the request to make
     * @param responseBody Request response body
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     * @param listener response listener
     * @param errorListener error response listener
     */
    public GsonRequest(int method,String url,String responseBody, Class<T> clazz,
                       Map<String, String> headers, Response.Listener<T> listener,
                       Response.ErrorListener errorListener)
    {
        super(method, url, errorListener);
        mResponseClazz = clazz;

        mListner = listener;
        mRequestBody = responseBody;
    }



    @Override
    protected void deliverResponse(T response)
    {
        mListner.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));


            return Response.success(mGSON.fromJson(json, mResponseClazz), HttpHeaderParser.parseCacheHeaders(response));

        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e));
        }
    }


    @Override
    public byte[] getBody()
    {
        try
        {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        }
        catch (UnsupportedEncodingException uee)
        {

            return null;
        }
    }
}

