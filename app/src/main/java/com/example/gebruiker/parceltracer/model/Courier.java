package com.example.gebruiker.parceltracer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Courier {
    /** Unique code of courier */
    @SerializedName("slug")
    @Expose
    private String slug;

    /** Name of courier */
    @SerializedName("name")
    @Expose
    private String name;

    /** Contact phone number of courier */
    @SerializedName("phone")
    @Expose
    private String phone;

    /** Other name of courier, if several they will be separated by commas */
    @SerializedName("other_name")
    @Expose
    private String otherName;

    /** Website link of courier */
    @SerializedName("web_url")
    @Expose
    private String url;

    /** Requirement fields for the courier */
    @SerializedName("required_fields")
    @Expose
    private List<String> requiredFields;

    /** Optional fields for the courier */
    @SerializedName("optional_fields")
    @Expose
    private List<String> optionalFields;

    /** Default constructor with all the fields of the class */
    public Courier(String url, String slug, String name, String phone, String otherName) {
        this.url = url;
        this.slug = slug;
        this.name = name;
        this.phone = phone;
        this.otherName = otherName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public List<String> getOptionalFields() {
        return optionalFields;
    }

    public void setOptionalFields(List<String> optionalFields) {
        this.optionalFields = optionalFields;
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