package com.example.gebruiker.parceltracer.data.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.converter.TypeConverter;

import java.util.List;

@com.raizlabs.android.dbflow.annotation.TypeConverter
public class StringListConverter extends TypeConverter<String, List> {
    private Gson gson = new Gson();

    @Override
    public String getDBValue(List model) {
        return gson.toJson(model);
    }

    @Override
    public List getModelValue(String data) {
        return gson.fromJson(data, new TypeToken<List<String>>(){{}}.getType());
    }
}
