package com.shopping.wallmart.myapplication.com.wallmart.models;

import com.shopping.wallmart.myapplication.com.wallmart.serverrequest.API;
import com.shopping.wallmart.myapplication.com.wallmart.serverresponse.ProductResponse;

import java.io.Serializable;

/**
 * Created by aruna on 21/03/18.
 */

public class ProductModel implements Serializable {


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getSkuDisplayName() {
        return skuDisplayName;
    }

    public void setSkuDisplayName(String skuDisplayName) {
        this.skuDisplayName = skuDisplayName;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getSkuFinalPrice() {
        return skuFinalPrice;
    }

    public void setSkuFinalPrice(String skuFinalPrice) {
        this.skuFinalPrice = skuFinalPrice;
    }

    public String getSkulastPrice() {
        return skulastPrice;
    }

    public void setSkulastPrice(String skulastPrice) {
        this.skulastPrice = skulastPrice;
    }

    public String getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(String maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }

    public String getPriceStrikeOff() {
        return priceStrikeOff;
    }

    public void setPriceStrikeOff(String priceStrikeOff) {
        this.priceStrikeOff = priceStrikeOff;
    }

    public String getInventoryTotal() {
        return inventoryTotal;
    }

    public void setInventoryTotal(String inventoryTotal) {
        this.inventoryTotal = inventoryTotal;
    }

    public String getInventoryUsed() {
        return inventoryUsed;
    }

    public void setInventoryUsed(String inventoryUsed) {
        this.inventoryUsed = inventoryUsed;
    }

    public String productId,
        productCategoryId,
        productImageUrl,
        skuDisplayName,
        productDisplayName,
        productDescription,
        skuFinalPrice,
        skulastPrice,
        maxQuantity,
        productTag,
        priceStrikeOff,
        inventoryTotal,
        inventoryUsed;

  public ProductModel(ProductResponse.ProductInfo productInfo){
      this.productId = productInfo.getProductId();
      this.productImageUrl = API.MAIN_URL + String.format(API.IMAGE, productId);
      this.productDisplayName = productInfo.getProductDisplayName();
      this.skuFinalPrice = "$ " + productInfo.getFinalPrice();
      this.inventoryTotal = productInfo.getInventoryTotal();

      this.productTag = productInfo.getProductTag();


  }

    public ProductModel(){

    }


}
