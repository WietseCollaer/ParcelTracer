package com.example.gebruiker.parceltracer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Courier {
    /** Unique code of courier */
    private String slug;

    /** Name of courier */
    private String name;

    /** Contact phone number of courier */
    private String phone;

    /** Other name of courier, if several they will be separated by commas */
    private String otherName;

    /** Website link of courier */
    private String url;

    /** Requirement fields for the courier */
    private List<String> requiredFields;

    /** Default constructor with all the fields of the class */
    public Courier(String url, String slug, String name, String phone, String otherName) {
        this.url = url;
        this.slug = slug;
        this.name = name;
        this.phone = phone;
        this.otherName = otherName;
    }

    /**
     * Constructor: creates a Courier from a JSONObject with the information of the Courier.
     *
     * @param courierJSON A JSONObject with information of the Courier
     * @throws JSONException
     */
    public Courier(JSONObject courierJSON) throws JSONException {
        this.url = courierJSON.isNull("web_url") ? "" : courierJSON.getString("web_url");
        this.slug = courierJSON.isNull("slug") ? "" : courierJSON.getString("slug");
        this.name = courierJSON.isNull("name") ? "" : courierJSON.getString("name");
        this.phone = courierJSON.isNull("phone") ? "" : courierJSON.getString("phone");
        this.otherName = courierJSON.isNull("other_name") ? "" : courierJSON.getString("other_name");
        JSONArray requiredFieldsArray = courierJSON.isNull("required_fields") ? null : courierJSON.getJSONArray("required_fields");
        if (requiredFieldsArray != null && requiredFieldsArray.length() != 0) {
            this.requiredFields = new ArrayList<>();
            for (int i = 0; i <requiredFieldsArray.length(); i++) {
                this.requiredFields.add(requiredFieldsArray.get(i).toString());
            }
        }
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void addRequiredField(String requiredField) {
        if (requiredFields == null) {
            requiredFields = new ArrayList<>();
        }
        requiredFields.add(requiredField);
    }

    public void deleteRequiredField(String requiredField) {
        if (requiredFields != null) {
            requiredFields.remove(requiredField);
        }
    }

    public void deleteRequiredFields() {
        requiredFields = null;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "slug='" + slug + "\'" +
                ", name='" + name + "\'" +
                ", phone='" + phone + "\'" +
                ", other_name='" + otherName + "\'" +
                ", web_url='" + url + "\'" +
                "}";
    }
}