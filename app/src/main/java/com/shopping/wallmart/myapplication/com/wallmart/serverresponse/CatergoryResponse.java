package com.shopping.wallmart.myapplication.com.wallmart.serverresponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aruna on 21/03/18.
 */

public class CatergoryResponse {

    @SerializedName("displayName")
    private String mDisplayName;

    @SerializedName("contents")
    private Catergory[] mCategoryList;



    public String getmDisplayName() {
        return mDisplayName;
    }

    public void setmDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public Catergory[] getmCategoryList() {
        return mCategoryList;
    }

    public void setmCategoryList(Catergory[] mCategoryList) {
        this.mCategoryList = mCategoryList;
    }




    public class Catergory
    {

        @SerializedName("id")
        private String mCategoryId;

        @SerializedName("displayName")
        private String mCategoryName;

        @SerializedName("active")
        private boolean mCategoryStatus;


        public String getmCategoryId() {
            return mCategoryId;
        }

        public void setmCategoryId(String mCategoryId) {
            this.mCategoryId = mCategoryId;
        }

        public String getmCategoryName() {
            return mCategoryName;
        }

        public void setmCategoryName(String mCategoryName) {
            this.mCategoryName = mCategoryName;
        }

        public boolean getmCategoryStatus() {
            return mCategoryStatus;
        }

        public void setmCategoryStatus(boolean mCategoryStatus) {
            this.mCategoryStatus = mCategoryStatus;
        }



    }

}
