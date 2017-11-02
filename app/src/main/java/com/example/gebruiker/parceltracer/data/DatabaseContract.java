package com.example.gebruiker.parceltracer.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String CONTENT_AUTHORITY = "com.example.gebruiker.parceltracer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TRACKING = "tracking";
    public static final String PATH_CHECKPOINT = "checkpoint";

    public static final class TrackingEntry implements BaseColumns {
        public static final String CONTENT_URI_STRING = "content://" + CONTENT_AUTHORITY + "/" + PATH_TRACKING;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACKING;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACKING;

        public static final String TABLE_NAME = "tracking";

        /** Title of the tracking */
        public static final String COLUMN_TITLE = "title";

        /** Tracking ID as returned by the API */
        public static final String COLUMN_TRACKING_ID = "tracking_id";

        /** Tracking number of a shipment (provided by the courier) */
        public static final String COLUMN_TRACKING_NUMBER = "tracking_number";

        /** Identifier of the courier (based on the tracking) */
        public static final String COLUMN_SLUG = "slug";

        /** Customer name */
        public static final String COLUMN_CUSTOMER = "customer";

        /** Current status of tracking */
        public static final String COLUMN_TAG = "tag";

        /** Category of the shipment */
        public static final String COLUMN_CATEGORY = "category";

        /** Remarks on the tracking */
        public static final String COLUMN_NOTE = "note";

        /** Country ISO Alpha-3 (three letters) to specify the origin of the shipment */
        public static final String COLUMN_ORIGIN_ISO3 = "origin_iso3";

        /** Country ISO Alpha-3 (three letters) to specify the destination of the shipment */
        public static final String COLUMN_DESTINATION_ISO3 = "destination_iso3";

        /** Expected delivery date of the shipment */
        public static final String COLUMN_EXPECTED_DELIVERY = "expected_delivery";

        /** Date when the shipment has been delivered */
        public static final String COLUMN_DELIVERY_DATE = "delivery_date";

        /** Signed by information for delivered shipment */
        public static final String COLUMN_SIGNED_BY = "signed_by";

        public static Uri buildTrackingUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class CheckpointEntry implements BaseColumns {
        public static final String CONTENT_URI_STRING = "content://" + CONTENT_AUTHORITY + "/" + PATH_CHECKPOINT;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CHECKPOINT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CHECKPOINT;

        public static final String TABLE_NAME = "checkpoint";

        /** Date and time of the checkpoint (provided by courier) */
        public static final String COLUMN_DATE = "date";

        /** Identifier of the courier (based on the checkpoint) */
        public static final String COLUMN_SLUG = "slug";

        /** Checkpoint message */
        public static final String COLUMN_MESSAGE = "message";

        /** Status of the checkpoint */
        public static final String COLUMN_TAG = "tag";

        /** City name of the checkpoint */
        public static final String COLUMN_CITY = "city";

        /** Postal code of the checkpoint */
        public static final String COLUMN_ZIP = "zip";

        /** State name of the checkpoint */
        public static final String COLUMN_STATE = "state";

        /** Country name of the checkpoint */
        public static final String COLUMN_COUNTRY = "country";

        /** Country ISO Alpha-3 (three letters) of the checkpoint */
        public static final String COLUMN_COUNTRY_ISO3 = "country_iso3";

        /** Tracking foreign key */
        public static final String COLUMN_TRACKING_KEY = "tracking_key";

        public static Uri buildCheckpointUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}