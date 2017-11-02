package com.example.gebruiker.parceltracer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AftershipResource {
    /** Pages to show (Default: 1) */
    @SerializedName("page")
    @Expose(serialize = false, deserialize = false)
    private int page;

    /** Number of trackings each page contain (Default: 100) */
    @SerializedName("limit")
    @Expose(serialize = false, deserialize = false)
    private int limit;

    /** Total number of matched trackings (Max: 10.000) */
    @SerializedName("count")
    @Expose(serialize = false)
    private int count;

    /** Main keyword of the trackings */
    @SerializedName("keyword")
    @Expose(serialize = false)
    private String keyword;

    /** Unique code of each courier */
    @SerializedName("slug")
    @Expose(serialize = false, deserialize = false)
    private String slug;

    /** Origin countries of the trackings */
    @SerializedName("origin")
    @Expose(serialize = false, deserialize = false)
    private List<String> origins;

    /** Destination countries of the trackings */
    @SerializedName("destination")
    @Expose(serialize = false, deserialize = false)
    private List<String> destinations;

    /** Status of the trackings */
    @SerializedName("tag")
    @Expose(serialize = false, deserialize = false)
    private String tag;

    /** Custom fields of the trackings */
    @SerializedName("fields")
    @Expose(serialize = false, deserialize = false)
    private String fields;

    /** Earliest creation date of the trackings */
    @SerializedName("created_at_min")
    @Expose(serialize = false, deserialize = false)
    private String createdAtMin;

    /** Latest creation date of the trackings */
    @SerializedName("created_at_max")
    @Expose(serialize = false, deserialize = false)
    private String createdAtMax;

    /** Last update date of the trackings */
    @SerializedName("last_updated_at")
    @Expose(serialize = false, deserialize = false)
    private String lastUpdatedAt;

    /** List of Trackings */
    @SerializedName(value = "trackings")
    @Expose(serialize = false)
    private List<Tracking> trackings;

    /** Single Trackings */
    @SerializedName(value = "tracking")
    @Expose
    private Tracking tracking;

    /** List of Couriers */
    @SerializedName(value = "couriers")
    @Expose(serialize = false)
    private List<Courier> couriers;

    /** Single Courier */
    @SerializedName(value = "courier")
    @Expose(serialize = false)
    private Courier courier;

    public AftershipResource(Tracking tracking) {
        this.tracking = tracking;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getCreatedAtMin() {
        return createdAtMin;
    }

    public void setCreatedAtMin(String createdAtMin) {
        this.createdAtMin = createdAtMin;
    }

    public String getCreatedAtMax() {
        return createdAtMax;
    }

    public void setCreatedAtMax(String createdAtMax) {
        this.createdAtMax = createdAtMax;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public List<Tracking> getTrackings() {
        return trackings;
    }

    public void setTrackings(List<Tracking> trackings) {
        this.trackings = trackings;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}