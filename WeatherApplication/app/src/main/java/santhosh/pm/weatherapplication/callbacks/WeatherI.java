package santhosh.pm.weatherapplication.callbacks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import santhosh.pm.weatherapplication.models.WeatherData;

public interface WeatherI {

    void weatherAPI( Context context, String cityName);
    boolean insertDB ( float humidity, float temp, float tempMax, float tempMin, String description, String cityName);
    List<WeatherData> getWeahterList( Context context);

    interface ApiCallback {
        void before();
        void success();
        void failure(String msg);
        void err(String msg);
    }
}
