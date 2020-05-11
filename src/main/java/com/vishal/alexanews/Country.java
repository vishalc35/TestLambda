package com.vishal.alexanews;

public enum  Country {

    US("us"),
    GB("gb");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
