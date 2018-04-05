package com.shopping.wallmart.myapplication.com.wallmart.serverresponse;

import com.google.gson.annotations.SerializedName;

import java.util.jar.Attributes;

/**
 * Created by aruna on 21/03/18.
 */

public class ProductResponse {



    @SerializedName("recsPerPage")
    private String mrecsPerPage;

    @SerializedName("totalNumRecs")
    private String mtotalNumRecs;

    @SerializedName("firstRecNum")
    private String mfirstRecNum;

    @SerializedName("lastRecNum")
    private String mlastRecNum;

    @SerializedName("records")
    private Records[] mRecorsList;

    public String getMrecsPerPage() {
        return mrecsPerPage;
    }

    public String getMtotalNumRecs() {
        return mtotalNumRecs;
    }

    public String isMfirstRecNum() {
        return mfirstRecNum;
    }

    public String getMlastRecNum() {
        return mlastRecNum;
    }

    public Records[] getmRecorsList() {
        return mRecorsList;
    }

    public class Records{
        public Attributes getMattributes() {
            return mattributes;
        }

        @SerializedName("attributes")
        private Attributes mattributes;
    }

    public class Attributes{
        public String getmBrand() {
            return mBrand;
        }

        public ProductInfo[] getmRecords() {
            return mRecords;
        }

        @SerializedName("Brand")
        private String mBrand;

        @SerializedName("records")
        private ProductInfo[] mRecords;

    }

    public class ProductInfo{
        public String getProductId() {
            return productId;
        }

        public String getmProductUrl() {
            return mProductUrl;
        }

        public String getProductDisplayName() {
            return productDisplayName;
        }

        @SerializedName("product.id")
        private String productId;

        @SerializedName("product.image.url")
        private String mProductUrl;

        @SerializedName("product.displayName")
        private String productDisplayName;

        public String getFinalPrice() {
            return finalPrice;
        }

        public String getInventoryTotal() {
            return inventoryTotal;
        }

        public String getProductTag() {
            return productTag;
        }

        public String getInventoryUsed() {
            return inventoryUsed;
        }

        @SerializedName("sku.finalPrice")
        private  String finalPrice;

        @SerializedName("inventoryTotal")
        private String inventoryTotal;

        @SerializedName("product.tag")
        private String productTag;

        @SerializedName("inventoryUsed")
        private String inventoryUsed;
    }

}
