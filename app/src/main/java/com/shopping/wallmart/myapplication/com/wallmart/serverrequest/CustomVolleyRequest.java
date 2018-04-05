package com.shopping.wallmart.myapplication.com.wallmart.serverrequest;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by aruna on 21/03/18.
 */

public class CustomVolleyRequest {
    private static CustomVolleyRequest mCustomVolleyRequest;
    private static Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private CustomVolleyRequest(Context mContext){
        this.mContext = mContext;
        this.mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized CustomVolleyRequest getInstance(Context mContext){
        if(mCustomVolleyRequest == null){
            mCustomVolleyRequest = new CustomVolleyRequest(mContext);
        }
        return mCustomVolleyRequest;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10*1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();

        }
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }
}
