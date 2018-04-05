package com.shopping.wallmart.myapplication;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shopping.wallmart.myapplication.com.wallmart.models.ProductModel;
import com.shopping.wallmart.myapplication.com.wallmart.serverrequest.CustomVolleyRequest;

import java.util.ArrayList;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by aruna on 21/03/18.
 */

public class ProductGridViewAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
    private Context mContext;
    private ArrayList<ProductModel> mProductList;
    private ArrayList<String> names;
    LayoutInflater mInflater;

    public ProductGridViewAdapter(Context mContext, ArrayList<ProductModel> mProductList){
        this.mContext = mContext;
        this.mProductList = mProductList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return mProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = mInflater.inflate(R.layout.product_gridview, viewGroup, false);
        NetworkImageView networkImageView = view.findViewById(R.id.image);


        imageLoader = CustomVolleyRequest.getInstance(mContext).getmImageLoader();
        imageLoader.get(mProductList.get(i).productImageUrl, ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher_round, android.R.drawable.ic_dialog_alert));

        networkImageView.setImageUrl(mProductList.get(i).productImageUrl, imageLoader);
        TextView description = (TextView) view.findViewById(R.id.description);
        TextView price = (TextView) view.findViewById(R.id.price);
        description.setText(mProductList.get(i).productDisplayName);
        price.setText(mProductList.get(i).skuFinalPrice);
        Button tag = (Button) view.findViewById(R.id.info);
        if(TextUtils.isEmpty(mProductList.get(i).productTag)){
            tag.setVisibility(View.INVISIBLE);
        }else{
            tag.setText(mProductList.get(i).productTag);
            if(mProductList.get(i).productTag.compareToIgnoreCase("new") == 0){
                tag.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));

            }else{
                tag.setBackgroundColor(mContext.getResources().getColor(R.color.colorblue));
            }
        }

        networkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent product = new Intent(mContext, ProductDeatils.class);
                ProductModel prod = new ProductModel();
                prod.setProductImageUrl(mProductList.get(i).productImageUrl);
                prod.setProductCategoryId(mProductList.get(i).productCategoryId);
                prod.setProductId(mProductList.get(i).productId);
                prod.setProductDisplayName(mProductList.get(i).productDisplayName);
                prod.setSkuFinalPrice(mProductList.get(i).skuFinalPrice);


                product.putExtra("TEST", prod);

                product.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(product);

            }
        });





        return view;
    }


}
