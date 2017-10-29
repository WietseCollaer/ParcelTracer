package com.example.gebruiker.parceltracer.presenter.utils;

import com.example.gebruiker.parceltracer.model.AftershipResource;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DataDeserializer implements JsonDeserializer<AftershipResource> {
    @Override
    public AftershipResource deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement resource = json.getAsJsonObject().get("data");
        return new Gson().fromJson(resource, AftershipResource.class);
    }
}