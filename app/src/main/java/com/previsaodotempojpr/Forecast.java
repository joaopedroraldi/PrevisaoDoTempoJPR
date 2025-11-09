package com.previsaodotempojpr;

public class Forecast {
    private String date;
    private String description;
    private String maxTemp;
    private String minTemp;

    public Forecast(String date, String description, String maxTemp, String minTemp) {
        this.date = date;
        this.description = description;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }
}