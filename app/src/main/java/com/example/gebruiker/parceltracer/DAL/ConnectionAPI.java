package com.example.gebruiker.parceltracer.DAL;

import android.os.AsyncTask;

import com.example.gebruiker.parceltracer.exception.AftershipAPIException;
import com.example.gebruiker.parceltracer.model.Tracking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionAPI extends AsyncTask<Void,Void,ConnectionAPI> {
    private final String API_VERSION = "v4";
    private final String API_BASE_URL = "https://api.aftership.com/";
    private final String API_KEY = "104b6506-bd37-4f5b-ab69-22f107fe4802";

    /** method call */
    private ConnectionAPIMethods method;

    /** exception */
    private Exception exception;

    /** Parameters */
    private String id;
    private String slug;
    private String trackingNumber;

    /** returns */
    private Tracking trackingReturn;
    private List<Tracking> trackingsReturn;

    /**
     * Default constructor
     *
     * @param method Action method (getAllTrackings, getTrackingById, ...)
     **/
    public ConnectionAPI(ConnectionAPIMethods method) {
        this.method = method;
    }

    /**
     * Constructor for getTrackingById
     *
     * @param method Action method (getAllTrackings, getTrackingById, ...)
     * @param id ID of the tracking
     **/
    public ConnectionAPI(ConnectionAPIMethods method, String id) {
        this(method);

        this.id = id;

        if(method != ConnectionAPIMethods.getTrackingById) {
            exception = new AftershipAPIException("The constructor can only be called with " +
                    "ConnectionAPIMethods.getTrackingById");
        }
    }

    /**
     * Constructor for getTrackingByTrackingNumber
     *
     * @param method Action method (getAllTrackings, getTrackingById, ...)
     * @param slug Identifier of the courier
     * @param trackingNumber Tracking number of the package
     **/
    public ConnectionAPI(ConnectionAPIMethods method, String slug, String trackingNumber) {
        this(method);

        this.slug = slug;
        this.trackingNumber = trackingNumber;

        if(method != ConnectionAPIMethods.getTrackingById) {
            exception = new AftershipAPIException("The constructor can only be called with " +
                    "ConnectionAPIMethods.getTrackingById");
        }
    }

    public List<Tracking> getAllTrackings()
            throws AftershipAPIException,IOException,ParseException,JSONException {
        JSONObject trackingJSON;
        List<Tracking> trackings = new ArrayList<>();

        String response = request("GET", "/trackings/", null);
        JSONArray trackingArray = new JSONArray(response);

        for (int i = 0; i < trackingArray.length(); i++) {
            trackingJSON = trackingArray.getJSONObject(i);
            trackings.add(new Tracking(trackingJSON));
        }

        return trackings;
    }

    public Tracking getTrackingById(String id)
            throws AftershipAPIException,IOException,ParseException,JSONException {
        String response = request("GET", "/trackings/" + id, null);
        JSONObject trackingJSON = new JSONObject(response);

        return new Tracking(trackingJSON);
    }

    public Tracking getTrackingByTrackingNumber(String slug, String trackingNumber)
            throws AftershipAPIException,IOException,ParseException,JSONException {
        String response = request("GET", "/trackings/" + slug + "/" + trackingNumber, null);
        JSONObject trackingJSON = new JSONObject(response);

        return new Tracking(trackingJSON);
    }

    @Override
    protected ConnectionAPI doInBackground(Void... params) {
        if(exception == null) {
            try {
                switch (method.getNumberMethod()) {
                    case 0:
                        trackingsReturn = getAllTrackings();
                        break;
                    case 1:
                        trackingReturn = getTrackingById(id);
                        break;
                    case 2:
                        trackingReturn = getTrackingByTrackingNumber(slug, trackingNumber);
                        break;
                }
            } catch (Exception e) {
                exception = e;
            }
        }
        return this;
    }

    public Object getReturn() {
        switch (method.getNumberMethod()) {
            case 0:
                return trackingsReturn;
            case 1:
                return trackingReturn;
            case 2:
                return trackingReturn;
            default:
                return null;
        }
    }

    /**
     * makes a request to the HTTP API of Aftership
     *
     * @param method Method of the request: GET, POST, PUT, DELETE
     * @param query url of the request
     * @param body JSONObject with the body of the request,
     *             if the request doesn't need body "GET/DELETE",
     *             the body would be null
     * @return  A JSONObject with the response of the request
     * @throws  AftershipAPIException  If the request response an error
     * @throws  java.io.IOException If there is a problem with the connection
     * @throws  java.text.ParseException If the response can not be parsed to JSONObject
     **/
    public String request(String method, String query, JSONObject body)
            throws AftershipAPIException,IOException,ParseException,JSONException{
        HttpURLConnection connection;
        BufferedReader reader = null;
        OutputStreamWriter writer = null;

        URL url = new URL(new URL(API_BASE_URL), API_VERSION + query);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setReadTimeout(10000);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("aftership-api-key", API_KEY);

        if (body != null) {
            connection.setDoOutput(true);
        }

        connection.connect();

        if (body != null) {
            writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body.toString());
            writer.flush();
        }

        InputStream inputStream = connection.getInputStream();
        StringBuffer buffer = new StringBuffer();

        if (inputStream == null) {
            return null;
        }

        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
            return null;
        }

        return buffer.toString();
    }
}