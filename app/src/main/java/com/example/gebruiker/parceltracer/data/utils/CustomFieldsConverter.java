package com.example.gebruiker.parceltracer.data.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.converter.TypeConverter;

import java.util.Map;

@com.raizlabs.android.dbflow.annotation.TypeConverter
public class CustomFieldsConverter extends TypeConverter<String, Map> {
    private Gson gson = new Gson();

    @Override
    public String getDBValue(Map model) {
        return gson.toJson(model);
    }

    @Override
    public Map getModelValue(String data) {
        return gson.fromJson(data, new TypeToken<Map<String, String>>(){{}}.getType());
    }
}
