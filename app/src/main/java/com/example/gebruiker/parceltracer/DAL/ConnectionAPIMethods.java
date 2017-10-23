package com.example.gebruiker.parceltracer.DAL;

public enum ConnectionAPIMethods {
    getAllTrackings(0), getTrackingById(1), getTrackingByTrackingNumber(2);

    private final int numberMethod;

    ConnectionAPIMethods(int numberMethod){
        this.numberMethod = numberMethod;
    }

    public int getNumberMethod(){
        return numberMethod;
    }
}