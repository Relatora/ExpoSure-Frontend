package com.example.exposure.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Expo Event POJO containing constructors, getters and setters
 */

public class ExpoEvent {

    @SerializedName("event_id")
    @Expose
    private String event_id;

    @SerializedName("name")
    @Expose
    private  String name;

    @SerializedName("location")
    @Expose
    private  String location;

    @SerializedName("contact_name")
    @Expose
    private  String contact_name;

    @SerializedName("contact_phone")
    @Expose
    private  String contact_phone;

    @SerializedName("contact_email")
    @Expose
    private  String contact_email;

    @SerializedName("photo")
    @Expose
    private  String photo;

    @SerializedName("date")
    @Expose
    private String date;


    /**
     * Events Constructor with id
     * @param event_id Event Id
     * @param name Event Name
     * @param location  Event Location
     * @param contact_name Event contact name
     * @param contact_phone Event contact phone
     * @param contact_email Event contact email
     * @param date Event date
     * @param photo Event photo
     */
    public ExpoEvent(String event_id, String name, String location, String contact_name,
                     String contact_phone, String contact_email, String date, String photo) {
        this.event_id = event_id;
        this.name = name;
        this.location = location;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.date = date;
        this.photo = photo;
    }

    /**
     * Events Constructor without id
     * @param name Event Name
     * @param location  Event Location
     * @param contact_name Event contact name
     * @param contact_phone Event contact phone
     * @param contact_email Event contact email
     * @param date Event date
     * @param photo Event photo
     */
    public ExpoEvent(String name, String location, String contact_name,
                     String contact_phone, String contact_email, String date, String photo) {
        this.event_id = "";
        this.name = name;
        this.location = location;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.date = date;
        this.photo = photo;
    }

    /**
     *  Fetches ExpoEvent Id
     * @return event_id  ExpoEvent Event Id
     */
    public String getevent_id() {
        return event_id;
    }

    /**
     * Sets ExpoEvent id
     * @param event_id ExpoEvent Event id
     */
    public void setevent_id(String event_id) {
        this.event_id = event_id;
    }

    /**
     * Fetches ExpoEvent name
     * @return name ExpoEvent name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets ExpoEvent name
     * @param name ExpoEvent name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fetches ExpoEvent location
     * @return location ExpoEvent location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets ExpoEvent location
     * @param location ExpoEvent location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets ExpoEvent contact name
     * @return contact_name ExpoEvent contact name
     */
    public String getcontact_name() {
        return contact_name;
    }

    /**
     * Sets ExpoEvent contact name
     * @param contact_name ExpoEvent contact name
     */
    public void setcontact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Fetches ExpoEvent contact phone
     * @return contact_phone ExpoEvent contact phone
     */
    public String getcontact_phone() {
        return contact_phone;
    }

    /**
     * Sets ExpoEvent contact phone
     * @param contact_phone ExpoEvent contact phone
     */
    public void setcontact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    /**
     * Fetches ExpoEvent contact email
     * @return contact_email ExpoEvent contact email
     */
    public String getcontact_email() {
        return contact_email;
    }

    /**
     * Sets event contact email
     * @param contact_email ExpoEvent contact email
     */
    public void setcontact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * Fetches ExpoEvent date
     * @return date ExpoEvent date
     */
    public String getdate() {
        return date;
    }

    /**
     * Sets ExpoEvent date
     * @param date ExpoEvent date
     */
    public void setdate(String date) {
        this.date = date;
    }

    /**
     * Fetches ExpoEvent  photo
     * @return photo ExpoEvent photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets ExpoEvent photo
     * @param photo ExpoEvent  photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
