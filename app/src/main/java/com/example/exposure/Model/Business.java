package com.example.exposure.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Business POJO containing constructors, getters and setters
 */
public class Business {

    @SerializedName("business_id")
    @Expose
    private String business_id;

    @SerializedName("name")
    @Expose
    private  String name;

    @SerializedName("contact_name")
    @Expose
    private  String contact_name;

    @SerializedName("contact_phone")
    @Expose
    private  String contact_phone;

    @SerializedName("contact_email")
    @Expose
    private  String contact_email;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    /**
     * Business constructor with business id
     * @param business_id business id
     * @param name business name
     * @param contact_name business contact name
     * @param contact_phone business contact phone
     * @param contact_email business contact email
     * @param category_id category id - FK
     */
    public Business(String business_id, String name, String contact_name, String contact_phone, String contact_email, String category_id) {
        this.business_id = business_id;
        this.name = name;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.category_id = category_id;
    }

    /**
     * Business constructor without business id
     * @param name business name
     * @param contact_name business contact name
     * @param contact_phone business contact phone
     * @param contact_email business contact email
     * @param category_id category id - FK
     */
    public Business(String name, String contact_name, String contact_phone, String contact_email, String category_id) {
        this.business_id = business_id;
        this.name = name;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.category_id = category_id;
    }

    /**
     * Fetches business id
     * @return business_id business id
     */
    public String getBusiness_id() {
        return business_id;
    }

    /**
     * Sets business id
     * @param business_id business id
     */
    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    /**
     * Fetches business name
     * @return name business name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets business name
     * @param name business name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fetches business name
     * @return contact_name business contact name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * Sets business contact name
     * @param contact_name business contact name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Fetches business  contact phone
     * @return contact_phone business  contact phone
     */
    public String getContact_phone() {
        return contact_phone;
    }

    /**
     * Sets business contact phone
     * @param contact_phone business contact phone
     */
    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    /**
     * Fetches business contact email
     * @return contact_email business contact email
     */
    public String getContact_email() {
        return contact_email;
    }

    /**
     * Sets business contact email
     * @param contact_email business contact email
     */
    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * Fetches business category id
     * @return category_id business category id
     */
    public String getCategory_id() {
        return category_id;
    }

    /**
     * Fetches business category id
     * @param category_id business category id
     */
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
