package com.example.gebruiker.parceltracer.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Checkpoint {
    /** Date and time of the tracking created */
    private String createdAt;

    /** Date and time of the checkpoint, provided by courier. Value may be:
     * Empty String,
     * YYYY-MM-DD,
     * YYYY-MM-DDTHH:MM:SS, or
     * YYYY-MM-DDTHH:MM:SS+TIMEZONE
     */
    private String checkpointTime;

    /** City name of the checkpoint (if any) */
    private String city;

    /** ZIP Code of the checkpoint (if any) */
    private String zip;

    /** State name of the checkpoint (if any) */
    private String state;

    /** Country ISO Alpha-3 (three letters) of the checkpoint */
    private ISO3Country countryISO3;

    /** Country name of the checkpoint, may also contain other location info */
    private String countryName;

    /** Checkpoint message */
    private String message;

    /** Status of the checkpoint */
    private String tag;

    /**
     * Constructor: creates a Checkpoint from a JSONObject with the information of the Checkpoint.
     *
     * @param checkpointJSON A JSONObject with information of the Checkpoint
     * @throws JSONException
     */
    public Checkpoint(JSONObject checkpointJSON) throws JSONException {
        createdAt = checkpointJSON.isNull("created_at") ? "" : checkpointJSON.getString("created_at");
        checkpointTime = checkpointJSON.isNull("checkpoint_time") ? "" : checkpointJSON.getString("checkpoint_time");
        city = checkpointJSON.isNull("city") ? "" : checkpointJSON.getString("city");
        zip = checkpointJSON.isNull("zip") ? "" : checkpointJSON.getString("zip");
        state = checkpointJSON.isNull("state") ? "" : checkpointJSON.getString("state");
        countryISO3 = checkpointJSON.isNull("country_iso3") ? null : ISO3Country.valueOf(checkpointJSON.getString("country_iso3"));
        countryName = checkpointJSON.isNull("country_name") ? "" : checkpointJSON.getString("country_name");
        message = checkpointJSON.isNull("message") ? "" : checkpointJSON.getString("message");
        tag = checkpointJSON.isNull("tag") ? "" : checkpointJSON.getString("tag");
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCheckpointTime(String checkpointTime) {
        this.checkpointTime = checkpointTime;
    }

    public String getCheckpointTime() {
        return checkpointTime;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCountryISO3(ISO3Country countryISO3) {
        this.countryISO3 = countryISO3;
    }

    public ISO3Country getCountryISO3() {
        return countryISO3;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "createdAt='" + createdAt + '\'' +
                ", checkpointTime='" + checkpointTime + '\'' +
                ", city='" + city + '\'' +
                ", countryISO3=" + countryISO3 +
                ", countryName='" + countryName + '\'' +
                ", message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", tag='" + tag + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}