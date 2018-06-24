package com.example.kaelxin.weatherapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class WeatherLoader extends AsyncTaskLoader<Weather> {

    private String stringQuery;

    WeatherLoader(Context context, String stringQuery) {
        super(context);
        this.stringQuery = stringQuery;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Weather loadInBackground() {

        return WeatherQueryUtils.fetchData(stringQuery);

    }
}
