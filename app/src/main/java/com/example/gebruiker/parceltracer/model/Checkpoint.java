package com.example.gebruiker.parceltracer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import java.util.List;

import com.example.gebruiker.parceltracer.data.DatabaseContract.CheckpointEntry;

@StorIOSQLiteType(table = CheckpointEntry.TABLE_NAME)
@StorIOContentResolverType(uri = CheckpointEntry.CONTENT_URI_STRING)
public class Checkpoint {
    /** Unique code of each courier */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_SLUG)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_SLUG)
    @SerializedName("slug")
    @Expose
    private String slug;

    /** City name of the checkpoint (if any) */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_CITY)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_CITY)
    @SerializedName("city")
    @Expose
    private String city;

    /** Date and time of the tracking created */
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /** Location info (city - country) */
    @SerializedName("location")
    @Expose(serialize = false, deserialize = false)
    private String location;

    /** Country name of the checkpoint, may also contain other location info */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_COUNTRY)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_COUNTRY)
    @SerializedName("country_name")
    @Expose
    private String countryName;

    /** Checkpoint message */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_MESSAGE)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_MESSAGE)
    @SerializedName("message")
    @Expose
    private String message;

    /** Country ISO Alpha-3 (three letters) of the checkpoint */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_COUNTRY_ISO3)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_COUNTRY_ISO3)
    @SerializedName("country_iso3")
    @Expose
    private String countryISO3;

    /** Status of the checkpoint */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_TAG)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_TAG)
    @SerializedName("tag")
    @Expose
    private String tag;

    /** Status of the checkpoint */
    @SerializedName("subtag")
    @Expose(serialize = false, deserialize = false)
    private String subtag;

    /** Date and time of the checkpoint, provided by courier. Value may be:
     * Empty String,
     * YYYY-MM-DD,
     * YYYY-MM-DDTHH:MM:SS, or
     * YYYY-MM-DDTHH:MM:SS+TIMEZONE
     */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_DATE)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_DATE)
    @SerializedName("checkpoint_time")
    @Expose
    private String checkpointTime;

    /** Location coordinates */
    @SerializedName("coordinates")
    @Expose
    private List<String> coordinates;

    /** State name of the checkpoint (if any) */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_STATE)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_STATE)
    @SerializedName("state")
    @Expose
    private String state;

    /** ZIP Code of the checkpoint (if any) */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_ZIP)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_ZIP)
    @SerializedName("zip")
    @Expose
    private String zip;

    /** Tracking id */
    @StorIOSQLiteColumn(name = CheckpointEntry.COLUMN_TRACKING_KEY)
    @StorIOContentResolverColumn(name = CheckpointEntry.COLUMN_TRACKING_KEY)
    private String trackingKey;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountryISO3() {
        return countryISO3;
    }

    public void setCountryISO3(String countryISO3) {
        this.countryISO3 = countryISO3;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSubtag() {
        return subtag;
    }

    public void setSubtag(String subtag) {
        this.subtag = subtag;
    }

    public String getCheckpointTime() {
        return checkpointTime;
    }

    public void setCheckpointTime(String checkpointTime) {
        this.checkpointTime = checkpointTime;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTrackingKey() { return trackingKey; }

    public void setTrackingKey(String trackingKey) { this.trackingKey = trackingKey; }
}