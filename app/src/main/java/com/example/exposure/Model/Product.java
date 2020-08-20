package com.example.exposure.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Blob;

/**
 * Product POJO containing constructors, getters and setters
 */
public class Product {

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("name")
    @Expose
    private  String name;

    @SerializedName("description")
    @Expose
    private  String description;

    @SerializedName("dimentions")
    @Expose
    private  String dimentions;

    @SerializedName("price")
    @Expose
    private  String price;

    @SerializedName("QR_code")
    @Expose
    private Blob QR_code;

    @SerializedName("Businesses_business_id")
    @Expose
    private String Businesses_business_id;

    /**
     * Product Constructor with product id
     * @param product_id Product id
     * @param name Product name
     * @param description Product description
     * @param dimentions Product dimensions
     * @param price Product price
     * @param QR_code Product QR_code
     * @param Businesses_business_id Product business id - FK
     */
    public Product(String product_id, String name, String description, String dimentions, String price, Blob QR_code, String Businesses_business_id) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.dimentions = dimentions;
        this.price = price;
        this.QR_code = QR_code;
        this.Businesses_business_id = Businesses_business_id;
    }

    /**
     * Product Constructor without product id
     * @param name Product name
     * @param description Product description
     * @param dimentions Product dimensions
     * @param price Product price
     * @param QR_code Product QR_code
     * @param Businesses_business_id Product business id - FK
     */
    public Product( String name, String description, String dimentions, String price, Blob QR_code, String Businesses_business_id) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.dimentions = dimentions;
        this.price = price;
        this.QR_code = QR_code;
        this.Businesses_business_id = Businesses_business_id;
    }

    /**
     * Fetches product id
     * @return product_id product id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * sets product id
     * @param product_id
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * Fetches product name
     * @return name product name
     */
    public String getName() {
        return name;
    }

    /**
     * sets product name
     * @param name product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fetches product  description
     * @return description product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set product  description
     * @param description product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Fetches product  dimensions
     * @return dimentions product dimensions
     */
    public String getDimentions() {
        return dimentions;
    }

    /**
     * sets product  dimensions
     * @param dimentions product dimensions
     */
    public void setDimentions(String dimentions) {
        this.dimentions = dimentions;
    }

    /**
     * Fetches product price
     * @return price product price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets product price
     * @param price product price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Fetches product QR Code
     * @return QR_code product QR Code
     */
    public Blob getQR_code() {
        return QR_code;
    }

    /**
     * Sets product QR Code
     * @param QR_code product QR Code
     */
    public void setQR_code(Blob QR_code) {
        this.QR_code = QR_code;
    }

    /**
     * Fetches business id
     * @return Business_business_is business id
     */
    public String getBusiness_id() {
        return Businesses_business_id;
    }

    /**
     * Sets business id
     * @param business_id business id
     */
    public void setBusiness_id(String business_id) {
        this.Businesses_business_id = business_id;
    }
}
