package com.shopping.wallmart.myapplication.com.wallmart.serverrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by aruna on 21/03/18.
 */

public class NetworkManager
{
    private static NetworkManager mInstance;
    private Context mContext;
    private static RequestQueue mRequestQueue;

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    /*
     * Private constructor method
     * @param context
     */
    private NetworkManager(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * This method is used to get singleton class object
     * @param context  Application Context
     * @return NetworkManager Singleton Object
     */
    public static synchronized NetworkManager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), CACHE_SIZE);
            try {
                Network network = new BasicNetwork(new HurlStack(null, null));
                mRequestQueue = new RequestQueue(cache, network);
                mRequestQueue.start();
            }
            catch(Exception e)
            {
                Log.e(NetworkManager.class.getSimpleName(),e.getLocalizedMessage());
            }
        }
        return mRequestQueue;
    }
}
