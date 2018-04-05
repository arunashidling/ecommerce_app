package com.shopping.wallmart.myapplication.com.wallmart.serverrequest;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.shopping.wallmart.myapplication.com.wallmart.serverresponse.CatergoryResponse;
import com.shopping.wallmart.myapplication.com.wallmart.serverresponse.ProductResponse;

/**
 * Created by aruna on 21/03/18.
 */

public class ServerRequest {
    public ServerRequest(){

    }
    public void getCategoryDetails (Context mContext, String url, Response.Listener<CatergoryResponse> listener,
                                    Response.ErrorListener errorListener)
    {
        NetworkManager mNetworkManager = NetworkManager.getInstance(mContext);

        GsonRequest registerRequest = new GsonRequest<CatergoryResponse>(url,
                CatergoryResponse.class, null, listener, errorListener);
        registerRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mNetworkManager.getRequestQueue().add(registerRequest);

    }


    public void getProductDetails (Context mContext, String url, Response.Listener<ProductResponse> listener,
                                    Response.ErrorListener errorListener)
    {
        NetworkManager mNetworkManager = NetworkManager.getInstance(mContext);

        GsonRequest registerRequest = new GsonRequest<ProductResponse>(url,
                ProductResponse.class, null, listener, errorListener);
        registerRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mNetworkManager.getRequestQueue().add(registerRequest);

    }
}
