package com.shopping.wallmart.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shopping.wallmart.myapplication.com.wallmart.models.ProductModel;
import com.shopping.wallmart.myapplication.com.wallmart.serverrequest.CustomVolleyRequest;

public class ProductDeatils extends AppCompatActivity {

    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatils);
        Intent intent = getIntent();
        ProductModel productModel = (ProductModel) intent.getSerializableExtra("TEST");

        NetworkImageView networkImageView = findViewById(R.id.prodImage);
        imageLoader = CustomVolleyRequest.getInstance(getApplicationContext()).getmImageLoader();
        imageLoader.get(productModel.productImageUrl, ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher_round, android.R.drawable.ic_dialog_alert));

        networkImageView.setImageUrl(productModel.productImageUrl, imageLoader);

        TextView prodDesc = findViewById(R.id.prodDescription);
        prodDesc.setText(productModel.productDisplayName);
        TextView prodPrice = findViewById(R.id.prodPrice);
        prodPrice.setText(productModel.skuFinalPrice);



    }

}
