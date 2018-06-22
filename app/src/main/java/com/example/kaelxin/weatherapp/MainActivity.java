package com.example.kaelxin.weatherapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    }

}


