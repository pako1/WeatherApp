package com.example.kaelxin.weatherapp;

public class Weather {

    private String icon;
    private String weatherConditions;
    private String timeData;
    private String maxTemp;
    private String minTemp;
    private String currentTemp;
    private String windSpeed;
    private String nameLocation;

    public Weather(String icon, String weatherConditions, String timeData, String maxTemp, String minTemp, String currentTemp, String windSpeed, String nameLocation) {
        this.icon = icon;
        this.weatherConditions = weatherConditions;
        this.timeData = timeData;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.currentTemp = currentTemp;
        this.windSpeed = windSpeed;
        this.nameLocation = nameLocation;
    }

    public String getIcon() {
        return icon;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public String getTimeData() {
        return timeData;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getNameLocation() {
        return nameLocation;
    }
}
