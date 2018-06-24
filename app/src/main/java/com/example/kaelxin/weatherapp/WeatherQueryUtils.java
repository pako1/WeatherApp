package com.example.kaelxin.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public final class WeatherQueryUtils {

    /*
    https://openweathermap.org/current
    ftiaxneis http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22
    opou 8a pairneis ta dedomena apo mia polh kai mono kai apo ayth 8a emfanizeis kapoia sigkekrimena pragmata !!
    https://www.androidcentral.com/best-weather-apps-android
    akolou8eis ayto to pragma.
    */


    private WeatherQueryUtils() {
    }

    public static Weather fetchData(String response) {

        URL url = createUrl(response);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fetchJson(jsonResponse);
    }

    private static Weather fetchJson(String jsonResponse) {

        Weather weather = null;

        try {
            JSONObject jsonObjectResponse = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObjectResponse.getJSONArray("weather");
            String weatherCondition = "";
            String weatherIcon = "";
            String formatedTime = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonWeatherObject = jsonArray.getJSONObject(i);
                weatherCondition = jsonWeatherObject.getString("main");
                weatherIcon = jsonWeatherObject.getString("icon");
            }
            JSONObject mainObject = jsonObjectResponse.getJSONObject("main");
            String currentTemp = mainObject.getString("temp");
            String minTemp = mainObject.getString("temp_min");
            String maxTemp = mainObject.getString("temp_max");
            JSONObject windObject = jsonObjectResponse.getJSONObject("wind");
            String windspeed = windObject.getString("speed");
            String location = jsonObjectResponse.getString("name");
            String time = jsonObjectResponse.getString("dt");
            if (time != null) {
                Date convertedTime = new java.util.Date(Integer.parseInt(time) * 1000L);
                SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                formatedTime = simpleDateFormat.format(convertedTime);
            }

            weather = new Weather(weatherIcon, weatherCondition, formatedTime, maxTemp, minTemp, currentTemp, windspeed, location);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weather;
    }

    private static String makeHttpConnection(URL url) throws IOException {

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1500);
            httpURLConnection.setReadTimeout(1500);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromInput(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return jsonResponse;
    }

    private static String readFromInput(InputStream inputStream) throws IOException {

        StringBuilder builder = new StringBuilder();

        if (inputStream != null) {
            //byte → string
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            // reads the data
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }

        }
        return builder.toString();
    }

    private static URL createUrl(String response) {

        URL url = null;

        try {
            url = new URL(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }

}
