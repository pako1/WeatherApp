package com.example.kaelxin.weatherapp;


import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Weather> {

    @BindView(R.id.name_location)
    TextView nameLocation;
    @BindView(R.id.time_data)
    TextView currentTime;
    @BindView(R.id.icon)
    ImageView weatherIcon;
    @BindView(R.id.weather_condition)
    TextView weatherCondition;
    @BindView(R.id.curTemp)
    TextView currentTemp;
    @BindView(R.id.maxTemp)
    TextView maximumT;
    @BindView(R.id.minTemp)
    TextView minimumT;
    @BindView(R.id.wind_speed)
    TextView windSpeedo;

    private static final String OWM_REQUEST_URL = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=df7701517816c75216755ecb08cec37b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<Weather> onCreateLoader(int id, Bundle args) {
        return new WeatherLoader(this, OWM_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<Weather> loader, Weather weatherData) {

        if (weatherData != null) {
            nameLocation.setText(weatherData.getNameLocation());
            currentTime.setText(weatherData.getTimeData());
            currentTemp.setText(weatherData.getCurrentTemp());
            weatherCondition.setText(weatherData.getWeatherConditions());
            maximumT.setText(weatherData.getMaxTemp());
            minimumT.setText(weatherData.getMinTemp());
            windSpeedo.setText(weatherData.getWindSpeed());
            Picasso.get().load(weatherData.getIcon()).into(weatherIcon);
        }

    }

    @Override
    public void onLoaderReset(Loader<Weather> loader) {
        nameLocation.setText("");
        currentTime.setText("");
        currentTemp.setText("");
        weatherCondition.setText("");
        maximumT.setText("");
        minimumT.setText("");
        windSpeedo.setText("");
        weatherIcon.setImageResource(0);
    }


}


