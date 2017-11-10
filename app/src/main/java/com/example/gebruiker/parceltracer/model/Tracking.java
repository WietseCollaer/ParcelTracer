package com.example.gebruiker.parceltracer.model;

import com.example.gebruiker.parceltracer.data.local.database.Database;
import com.example.gebruiker.parceltracer.data.utils.CheckpointListConverter;
import com.example.gebruiker.parceltracer.data.utils.CustomFieldsConverter;
import com.example.gebruiker.parceltracer.data.utils.StringListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;
import java.util.Map;

@Table(database = Database.class)
public class Tracking extends BaseModel {
    /** Identifier of the tracking in the Aftership system */
    @Column
    @PrimaryKey
    @SerializedName("id")
    @Expose(serialize = false)
    private String id;

    /** Date and time of the tracking created */
    @SerializedName("created_at")
    @Expose(serialize = false)
    private String createdAt;

    /** Date and time of the tracking last updated */
    @SerializedName("updated_at")
    @Expose(serialize = false)
    private String updatedAt;

    /** Date and time of the tracking last updated */
    @SerializedName("last_updated_at")
    @Expose(serialize = false, deserialize = false)
    private String lastUpdatedAt;

    /** Tracking number of a shipment. Duplicate tracking numbers,
     * or tracking number with invalid tracking number format will
     * not be accepted.
     */
    @Column
    @SerializedName("tracking_number")
    @Expose
    private String trackingNumber;

    /** Unique code of each courier. If you do not specify a slug,
     * Aftership will automatically detect the courier based on the
     * tracking number format and your selected couriers.
     */
    @Column
    @SerializedName("slug")
    @Expose
    private String slug;

    /** Whether or not Aftership will continue tracking the shipments.
     * Value is 'false' when status is 'Delivered' or 'Expired'.
     */
    @Column
    @SerializedName("active")
    @Expose(serialize = false)
    private boolean active;

    /** Tracking to create push notification */
    @Column(typeConverter = StringListConverter.class)
    @SerializedName("android")
    @Expose(serialize = false)
    private List android;

    /** Custom fields that accept any TEXT STRING */
    @SerializedName("custom_fields")
    @Expose
    private Map<String, String> customFields;

    /** Customer name of the tracking */
    @Column
    @SerializedName("customer_name")
    @Expose(serialize = false)
    private String customerName;

    /** Number of days until delivery */
    @SerializedName("delivery_time")
    @Expose(serialize = false)
    private int deliveryTime;

    /** Country ISO Alpha-3 (three letters) to specify the destination of the shipment.
     * If you use postal service to send international shipments, Aftership will automatically
     * get tracking results at the destination courier as well (e.g. USPS for USA).
     */
    @Column
    @SerializedName("destination_country_iso3")
    @Expose
    private String destinationCountryISO3;

    /** Email address(es) to receive email notifications, if several they will be separated by commas */
    @Column(typeConverter = StringListConverter.class)
    @SerializedName("emails")
    @Expose
    private List emails;

    /** Expected delivery date (if any) */
    @Column
    @SerializedName("expected_delivery")
    @Expose(serialize = false)
    private String expectedDelivery;

    /** Tracking to create push notification */
    @SerializedName("ios")
    @Expose(serialize = false)
    private List<String> ios;

    /** remarks on the tracking */
    @Column
    @SerializedName("note")
    @Expose(serialize = false)
    private String note;

    /** Text field for order ID */
    @SerializedName("order_id")
    @Expose
    private String orderID;

    /** Text field for order path */
    @SerializedName("order_id_path")
    @Expose
    private String orderIDPath;

    /** Country ISO Alpha-3 (three letters) to specify the origin country of the tracking */
    @Column
    @SerializedName("origin_country_iso3")
    @Expose
    private String originCountryISO3;

    /** Number of packages under the tracking */
    @Column
    @SerializedName("shipment_package_count")
    @Expose(serialize = false)
    private int shipmentPackageCount;

    /** Date when the shipment has been collected by the carrier */
    @Column
    @SerializedName("shipment_pickup_date")
    @Expose(serialize = false)
    private String shipmentPickupDate;

    /** Date when the shipment has been delivered */
    @Column
    @SerializedName("shipment_delivery_date")
    @Expose(serialize = false)
    private String shipmentDeliveryDate;

    /** Shipment type provided by carrier (if any) */
    @Column
    @SerializedName("shipment_type")
    @Expose(serialize = false)
    private String shipmentType;

    /** Shipment weight provided by carrier (if any) */
    @Column
    @SerializedName("shipment_weight")
    @Expose(serialize = false)
    private String shipmentWeight;

    /** Shipment weight unit provided by carrier (if any) */
    @Column
    @SerializedName("shipment_weight_unit")
    @Expose(serialize = false)
    private String shipmentWeightUnit;

    /** Signed by information for delivered shipment (if any) */
    @Column
    @SerializedName("signed_by")
    @Expose(serialize = false)
    private String signedBy;

    /** Phone number(s) to receive sms notifications, if several they will be separated by commas.
     * Enter country code before each phone number.
     */
    @Column(typeConverter = StringListConverter.class)
    @SerializedName("smses")
    @Expose
    private List smses;

    /** Source of how this tracking is added */
    @Column
    @SerializedName("source")
    @Expose(serialize = false)
    private String source;

    /** Current status of tracking */
    @Column
    @SerializedName("tag")
    @Expose(serialize = false)
    private String tag;

    /** Status of the tracking */
    @SerializedName("subtag")
    @Expose(serialize = false, deserialize = false)
    private String subtag;

    /** Title of the tracking (Default value: tracking number) */
    @Column
    @SerializedName("title")
    @Expose
    private String title;

    /** Number of attempts Aftership tracks at courier's system */
    @Column
    @SerializedName("tracked_count")
    @Expose(serialize = false)
    private int trackedCount;

    /** When last mile tracking is supported */
    @Column
    @SerializedName("last_mile_tracking_supported")
    @Expose(serialize = false)
    private String lastMileTrackingSupported;

    /** Unique token */
    @SerializedName("unique_token")
    @Expose(serialize = false)
    private String uniqueToken;

    /** All checkpoints of this tracking */
    @SerializedName("checkpoints")
    @Expose(serialize = false)
    private List<Checkpoint> checkpoints;

    /** Tracking account number */
    @SerializedName("tracking_account_number")
    @Expose(serialize = false)
    private String trackingAccountNumber;

    /** Tracking destination country */
    @SerializedName("tracking_destination_country")
    @Expose(serialize = false)
    private String trackingDestinationCountry;

    /** Tracking key */
    @SerializedName("tracking_key")
    @Expose(serialize = false)
    private String trackingKey;

    /** Tracking postal code */
    @SerializedName("tracking_postal_code")
    @Expose(serialize = false)
    private String trackingPostalCode;

    /** Tracking ship date */
    @SerializedName("tracking_ship_date")
    @Expose(serialize = false)
    private String trackingShipDate;

    public static final String CATEGORY_KEY = "category";

    public Tracking() {}

    public Tracking(String title, String slug, String trackingNumber, Map<String,String> customFields) {
        this.title = title;
        this.slug = slug;
        this.trackingNumber = trackingNumber;
        this.customFields = customFields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getAndroid() {
        return android;
    }

    public void setAndroid(List<String> android) {
        this.android = android;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDestinationCountryISO3() {
        return destinationCountryISO3;
    }

    public void setDestinationCountryISO3(String destinationCountryISO3) {
        this.destinationCountryISO3 = destinationCountryISO3;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(String expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public List<String> getIos() {
        return ios;
    }

    public void setIos(List<String> ios) {
        this.ios = ios;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderIDPath() {
        return orderIDPath;
    }

    public void setOrderIDPath(String orderIDPath) {
        this.orderIDPath = orderIDPath;
    }

    public String getOriginCountryISO3() {
        return originCountryISO3;
    }

    public void setOriginCountryISO3(String originCountryISO3) {
        this.originCountryISO3 = originCountryISO3;
    }

    public int getShipmentPackageCount() {
        return shipmentPackageCount;
    }

    public void setShipmentPackageCount(int shipmentPackageCount) {
        this.shipmentPackageCount = shipmentPackageCount;
    }

    public String getShipmentPickupDate() {
        return shipmentPickupDate;
    }

    public void setShipmentPickupDate(String shipmentPickupDate) {
        this.shipmentPickupDate = shipmentPickupDate;
    }

    public String getShipmentDeliveryDate() {
        return shipmentDeliveryDate;
    }

    public void setShipmentDeliveryDate(String shipmentDeliveryDate) {
        this.shipmentDeliveryDate = shipmentDeliveryDate;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getShipmentWeight() {
        return shipmentWeight;
    }

    public void setShipmentWeight(String shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }

    public String getShipmentWeightUnit() {
        return shipmentWeightUnit;
    }

    public void setShipmentWeightUnit(String shipmentWeightUnit) {
        this.shipmentWeightUnit = shipmentWeightUnit;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public List<String> getSmses() {
        return smses;
    }

    public void setSmses(List<String> smses) {
        this.smses = smses;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrackedCount() {
        return trackedCount;
    }

    public void setTrackedCount(int trackedCount) {
        this.trackedCount = trackedCount;
    }

    public String getLastMileTrackingSupported() {
        return lastMileTrackingSupported;
    }

    public void setLastMileTrackingSupported(String lastMileTrackingSupported) {
        this.lastMileTrackingSupported = lastMileTrackingSupported;
    }

    public String getUniqueToken() {
        return uniqueToken;
    }

    public void setUniqueToken(String uniqueToken) {
        this.uniqueToken = uniqueToken;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String getTrackingAccountNumber() {
        return trackingAccountNumber;
    }

    public void setTrackingAccountNumber(String trackingAccountNumber) {
        this.trackingAccountNumber = trackingAccountNumber;
    }

    public String getTrackingDestinationCountry() {
        return trackingDestinationCountry;
    }

    public void setTrackingDestinationCountry(String trackingDestinationCountry) {
        this.trackingDestinationCountry = trackingDestinationCountry;
    }

    public String getTrackingKey() {
        return trackingKey;
    }

    public void setTrackingKey(String trackingKey) {
        this.trackingKey = trackingKey;
    }

    public String getTrackingPostalCode() {
        return trackingPostalCode;
    }

    public void setTrackingPostalCode(String trackingPostalCode) {
        this.trackingPostalCode = trackingPostalCode;
    }

    public String getTrackingShipDate() {
        return trackingShipDate;
    }

    public void setTrackingShipDate(String trackingShipDate) {
        this.trackingShipDate = trackingShipDate;
    }
}