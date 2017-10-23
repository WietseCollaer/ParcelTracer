package com.example.gebruiker.parceltracer.model;

import com.example.gebruiker.parceltracer.exception.AftershipAPIException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tracking {
    /** Identifier of the tracking in the Aftership system */
    private String id;

    /** Tracking number of a shipment. Duplicate tracking numbers,
     * or tracking number with invalid tracking number format will
     * not be accepted.
     */
    private String trackingNumber;

    /** Unique code of each courier. If you do not specify a slug,
     * Aftership will automatically detect the courier based on the
     * tracking number format and your selected couriers.
     */
    private String slug;

    /** Email address(es) to receive email notifications, if several they will be separated by commas */
    private List<String> emails;

    /** Phone number(s) to receive sms notifications, if several they will be separated by commas.
     * Enter country code before each phone number.
     */
    private List<String> smses;

    /** Title of the tracking (Default value: tracking number) */
    private String title;

    /** Customer name of the tracking */
    private String customerName;

    /** Country ISO Alpha-3 (three letters) to specify the destination of the shipment.
     * If you use postal service to send international shipments, Aftership will automatically
     * get tracking results at the destination courier as well (e.g. USPS for USA).
     */
    private ISO3Country destinationCountryISO3;

    /** Country ISO Alpha-3 (three letters) to specify the origin country of the tracking */
    private ISO3Country originCountryISO3;

    /** Text field for order ID */
    private String orderID;

    /** Text field for order path */
    private String orderIDPath;

    /** Custom fields that accept any TEXT STRING */
    private Map<String, String> customFields;

    /** Fields informed by Aftership API */
    /** Date and time of the tracking created */
    private Date createdAt;

    /** Date and time of the tracking last updated */
    private Date updatedAt;

    /** Whether or not Aftership will continue tracking the shipments.
     * Value is 'false' when status is 'Delivered' or 'Expired'.
     */
    private boolean active;

    /** Expected delivery date (if any) */
    private String expectedDelivery;

    /** Number of packages under the tracking */
    private int shipmentPackageCount;

    /** Shipment type provided by carrier (if any) */
    private String shipmentType;

    /** Signed by information for delivered shipment (if any) */
    private String signedBy;

    /** Source of how this tracking is added */
    private String source;

    /** Current status of tracking */
    private StatusTag tag;

    /** Number of attempts Aftership tracks at courier's system */
    private int trackedCount;

    /** All checkpoints of this tracking */
    private List<Checkpoint> checkpoints;

    /** Unique token */
    private String uniqueToken;

    /** Tracking account number */
    private String trackingAccountNumber;

    /** Tracking postal code */
    private String trackingPostalCode;

    /** Tracking ship date */
    private String trackingShipDate;

    /** Tracking to create push notification */
    private List<String> android;

    public Tracking(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        this.title = trackingNumber;
    }

    /**
     * Constructor: creates a Tracking from a JSONObject with the information of the Tracking.
     *
     * @param trackingJSON A JSONObject with information of the Tracking
     * @throws JSONException
     * @throws AftershipAPIException
     * @throws ParseException
     */
    public Tracking(JSONObject trackingJSON) throws JSONException, AftershipAPIException, ParseException {
        /** fields that can be updated by the user */
        id = trackingJSON.isNull("id") ? "" : trackingJSON.getString("id");
        trackingNumber = trackingJSON.isNull("tracking_number") ? "" : trackingJSON.getString("tracking_number");
        slug = trackingJSON.isNull("slug") ? "" : trackingJSON.getString("slug");
        title = trackingJSON.isNull("title") ? "" : trackingJSON.getString("title");
        customerName = trackingJSON.isNull("customer_name") ? "" : trackingJSON.getString("customer_name");
        destinationCountryISO3 = trackingJSON.isNull("destination_country_iso3") ? null : ISO3Country.valueOf(trackingJSON.getString("destination_country_iso3"));
        orderID = trackingJSON.isNull("order_id") ? "" : trackingJSON.getString("order_id");
        orderIDPath = trackingJSON.isNull("order_id_path") ? "" : trackingJSON.getString("order_id_path");

        trackingAccountNumber = trackingJSON.isNull("tracking_account_number") ? "" : trackingJSON.getString("tracking_account_number");
        trackingPostalCode = trackingJSON.isNull("tracking_postal_code") ? "" : trackingJSON.getString("tracking_postal_code");
        trackingShipDate = trackingJSON.isNull("tracking_ship_date") ? "" : trackingJSON.getString("tracking_ship_date");

        JSONArray smsesArray = trackingJSON.isNull("smses") ? null : trackingJSON.getJSONArray("smses");
        if (smsesArray != null && smsesArray.length() != 0) {
            smses = new ArrayList<>();
            for (int i = 0; i < smsesArray.length(); i++) {
                smses.add(smsesArray.get(i).toString());
            }
        }

        JSONArray emailsArray = trackingJSON.isNull("emails") ? null : trackingJSON.getJSONArray("emails");
        if (emailsArray != null && emailsArray.length() != 0) {
            emails = new ArrayList<>();
            for (int i = 0; i < emailsArray.length(); i++) {
                emails.add(emailsArray.get(i).toString());
            }
        }

        JSONObject customFieldsJSON = trackingJSON.isNull("custom_fields") ? null : trackingJSON.getJSONObject("custom_fields");
        if (customFieldsJSON != null) {
            customFields = new HashMap<>();
            Iterator<?> keys = customFieldsJSON.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                customFields.put(key, customFieldsJSON.getString(key));
            }
        }

        /** fields that can't be updated by the user (only retrieve) */
        createdAt = trackingJSON.isNull("created_at") ? null : DateMethods.getDate(trackingJSON.getString("created_at"));
        updatedAt = trackingJSON.isNull("updated_at") ? null : DateMethods.getDate(trackingJSON.getString("updated_at"));
        expectedDelivery = trackingJSON.isNull("expected_delivery") ? "" : trackingJSON.getString("expected_delivery");

        active = !trackingJSON.isNull("active") && trackingJSON.getBoolean("active");
        originCountryISO3 = trackingJSON.isNull("origin_country_iso3") ? null : ISO3Country.valueOf(trackingJSON.getString("origin_country_iso3"));
        shipmentPackageCount = trackingJSON.isNull("shipment_package_count") ? 0 : trackingJSON.getInt("shipment_package_count");
        shipmentType = trackingJSON.isNull("shipment_type") ? "" : trackingJSON.getString("shipment_type");
        signedBy = trackingJSON.isNull("signed_by") ? "" : trackingJSON.getString("signed_by");
        source = trackingJSON.isNull("source") ? "" : trackingJSON.getString("source");
        tag = trackingJSON.isNull("tag") ? null : StatusTag.valueOf(trackingJSON.getString("tag"));
        trackedCount = trackingJSON.isNull("tracked_count") ? 0 : trackingJSON.getInt("tracked_count");
        uniqueToken = trackingJSON.isNull("unique_token") ? "" : trackingJSON.getString("unique_token");

        /** android */
        JSONArray androidArray = trackingJSON.isNull("android") ? null : trackingJSON.getJSONArray("android");
        if (androidArray != null && androidArray.length() != 0) {
            android = new ArrayList<>();
            for (int i = 0; i < androidArray.length(); i++) {
                android.add(androidArray.get(i).toString());
            }
        }

        /** checkpoints */
        JSONArray checkpointsArray = trackingJSON.isNull("checkpoints") ? null : trackingJSON.getJSONArray("checkpoints");
        if (checkpointsArray != null && checkpointsArray.length() != 0) {
            checkpoints = new ArrayList<>();
            for (int i = 0; i < checkpointsArray.length(); i++) {
                checkpoints.add(new Checkpoint((JSONObject) checkpointsArray.get(i)));
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setAndroid(List<String> android) {
        this.android = android;
    }

    public List<String> getAndroid() {
        return android;
    }

    public void addAndroid(String android) {
        if (this.android == null) {
            this.android = new ArrayList<>();
        }
        this.android.add(android);
    }

    public void deleteAndroid(String android) {
        if (this.android != null) {
            this.android.remove(android);
        }
    }

    public void deleteAndroid() {
        android = null;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void addEmail(String email) {
        if (emails == null) {
            emails = new ArrayList<>();
        }
        emails.add(email);
    }

    public void deleteEmails(String email) {
        if (emails != null) {
            emails.remove(email);
        }
    }

    public void deleteEmails() {
        emails = null;
    }

    public void setSmses(List<String> smses) {
        this.smses = smses;
    }

    public List<String> getSmses() {
        return smses;
    }

    public void addSms(String sms) {
        if (smses == null) {
            smses = new ArrayList<>();
        }
        smses.add(sms);
    }

    public void deleteSms(String sms) {
        if (smses != null) {
            smses.remove(sms);
        }
    }

    public void deleteSmses() {
        smses = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setDestinationCountryISO3(ISO3Country destinationCountryISO3) {
        this.destinationCountryISO3 = destinationCountryISO3;
    }

    public ISO3Country getDestinationCountryISO3() {
        return destinationCountryISO3;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderIDPath(String orderIDPath) {
        this.orderIDPath = orderIDPath;
    }

    public String getOrderIDPath() {
        return orderIDPath;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void addCustomField(String field, String value) {
        if (customFields == null) {
            customFields = new HashMap<>();
        }
        customFields.put(field, value);
    }

    public void deleteCustomField(String field) {
        if (customFields != null) {
            customFields.remove(field);
        }
    }

    public void deleteCustomFields() {
        customFields = null;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtString() {
        return DateMethods.toString(createdAt);
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedAtString() {
        return DateMethods.toString(updatedAt);
    }

    public boolean isActive() {
        return active;
    }

    public String getExpectedDelivery() {
        return expectedDelivery;
    }

    public ISO3Country getOriginCountryISO3() {
        return originCountryISO3;
    }

    public int getShipmentPackageCount() {
        return shipmentPackageCount;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public String getSource() {
        return source;
    }

    public StatusTag getTag() {
        return tag;
    }

    public int getTrackedCount() {
        return trackedCount;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setUniqueToken(String uniqueToken) {
        this.uniqueToken = uniqueToken;
    }

    public String getUniqueToken() {
        return uniqueToken;
    }

    public void setTrackingAccountNumber(String trackingAccountNumber) {
        this.trackingAccountNumber = trackingAccountNumber;
    }

    public String getTrackingAccountNumber() {
        return trackingAccountNumber;
    }

    public void setTrackingPostalCode(String trackingPostalCode) {
        this.trackingPostalCode = trackingPostalCode;
    }

    public String getTrackingPostalCode() {
        return trackingPostalCode;
    }

    public void setTrackingShipDate(String trackingShipDate) {
        this.trackingShipDate = trackingShipDate;
    }

    public String getTrackingShipDate() {
        return trackingShipDate;
    }

    public JSONObject generateJSON() throws JSONException {
        JSONObject globalJSON = new JSONObject();
        JSONObject trackingJSON = new JSONObject();
        JSONObject customFieldsJSON;

        trackingJSON.put("tracking_number", trackingNumber);
        if (slug != null) trackingJSON.put("slug", slug);
        if (title != null) trackingJSON.put("title", title);
        if (emails != null) {
            JSONArray emailsJSON = new JSONArray(emails);
            trackingJSON.put("emails", emailsJSON);
        }
        if (smses != null) {
            JSONArray smsesJSON = new JSONArray(smses);
            trackingJSON.put("smses", smsesJSON);
        }
        if (customerName != null) trackingJSON.put("customer_name", customerName);
        if (destinationCountryISO3 != null)
            trackingJSON.put("destination_country_iso3", destinationCountryISO3.toString());
        if (orderID != null) trackingJSON.put("order_id", orderID);
        if (orderIDPath != null) trackingJSON.put("order_id_path", orderIDPath);

        if (trackingAccountNumber != null) trackingJSON.put("tracking_account_number", trackingAccountNumber);
        if (trackingPostalCode != null) trackingJSON.put("tracking_postal_code", trackingPostalCode);
        if (trackingShipDate != null) trackingJSON.put("tracking_ship_date", trackingShipDate);

        if (customFields != null) {
            customFieldsJSON = new JSONObject();

            for (Map.Entry<String, String> entry : customFields.entrySet()) {
                customFieldsJSON.put(entry.getKey(), entry.getValue());
            }
            trackingJSON.put("custom_fields", customFieldsJSON);
        }
        globalJSON.put("tracking", trackingJSON);

        if (android != null) {
            JSONArray androidJSON = new JSONArray(android);
            trackingJSON.put("android", androidJSON);
        }

        return globalJSON;
    }

    public JSONObject generatePutJSON() throws JSONException {
        JSONObject globalJSON = new JSONObject();
        JSONObject trackingJSON = new JSONObject();
        JSONObject customFieldsJSON;

        if (title != null) trackingJSON.put("title", title);
        if (emails != null) {
            JSONArray emailsJSON = new JSONArray(emails);
            trackingJSON.put("emails", emailsJSON);
        }
        if (smses != null) {
            JSONArray smsesJSON = new JSONArray(smses);
            trackingJSON.put("smses", smsesJSON);
        }
        if (customerName != null) trackingJSON.put("customer_name", customerName);
        if (orderID != null) trackingJSON.put("order_id", orderID);
        if (orderIDPath != null) trackingJSON.put("order_id_path", orderIDPath);
        if (customFields != null) {
            customFieldsJSON = new JSONObject();

            for (Map.Entry<String, String> entry : customFields.entrySet()) {
                customFieldsJSON.put(entry.getKey(), entry.getValue());
            }
            trackingJSON.put("custom_fields", customFieldsJSON);
        }
        globalJSON.put("tracking", trackingJSON);

        if (android != null) {
            JSONArray androidJSON = new JSONArray(android);
            trackingJSON.put("android", androidJSON);
        }

        return globalJSON;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "id='" + id + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", slug='" + slug + '\'' +
                ", emails=" + emails +
                ", smses=" + smses +
                ", title='" + title + '\'' +
                ", customerName='" + customerName + '\'' +
                ", destinationCountryISO3=" + destinationCountryISO3 +
                ", originCountryISO3=" + originCountryISO3 +
                ", orderID='" + orderID + '\'' +
                ", orderIDPath='" + orderIDPath + '\'' +
                ", customFields=" + customFields +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", active=" + active +
                ", expectedDelivery='" + expectedDelivery + '\'' +
                ", shipmentPackageCount=" + shipmentPackageCount +
                ", shipmentType='" + shipmentType + '\'' +
                ", signedBy='" + signedBy + '\'' +
                ", source='" + source + '\'' +
                ", tag=" + tag +
                ", trackedCount=" + trackedCount +
                ", checkpoints=" + checkpoints +
                ", uniqueToken='" + uniqueToken + '\'' +
                ", trackingAccountNumber='" + trackingAccountNumber + '\'' +
                ", trackingPostalCode='" + trackingPostalCode + '\'' +
                ", trackingShipDate='" + trackingShipDate + '\'' +
                ", android=" + android +
                '}';
    }
}