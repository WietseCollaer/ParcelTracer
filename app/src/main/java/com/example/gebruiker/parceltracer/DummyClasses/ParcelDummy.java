package com.example.gebruiker.parceltracer.DummyClasses;

/**
 * Created by Gebruiker on 23/10/2017.
 */

public class ParcelDummy {
    private String name;
    private String status;

    public ParcelDummy() {
        new ParcelDummy("Samung S8", "In transfer");
    }

    public ParcelDummy(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
