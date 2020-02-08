package santhosh.pm.weatherapplication.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santhosh.pm.weatherapplication.callbacks.WeatherI;
import santhosh.pm.weatherapplication.data.WeatherDB;
import santhosh.pm.weatherapplication.models.Main;
import santhosh.pm.weatherapplication.models.Weather;
import santhosh.pm.weatherapplication.models.WeatherData;
import santhosh.pm.weatherapplication.models.WeatherDetails;
import santhosh.pm.weatherapplication.util.ApiService;
import santhosh.pm.weatherapplication.util.MainApp;

public class WeatherPresenter implements WeatherI {

    private Context context;
    WeatherDB db;
    private static final String TAG = "WeatherPresenter";

    /**
     * @param context
     * @param cityName
     * Api call is made
     */
    @Override
    public void weatherAPI (Context context,String cityName) {
        this.context = context;
        WeatherI.ApiCallback apiCallback = (ApiCallback) context;
        apiCallback.before();
       ApiService service = MainApp.getRetrofit().create(ApiService.class);
        Call<WeatherDetails> call = service.getCurrentWeatherData(cityName,"f524ad89b205dfc3cb4f4432c15acdfb");

        call.enqueue(new Callback<WeatherDetails>() {
            @Override
            public void onResponse ( Call<WeatherDetails> call, Response<WeatherDetails> response ) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "data " + response.body().getMain(), Toast.LENGTH_LONG).show();
                   Main main = response.body().getMain();
                   List<Weather> weather = response.body().getWeather();
                    boolean b = insertDB(main.getHumidity(),main.getTemp(),main.getTemp_max(),main.getTemp_min(),weather.get(weather.size()-1).getDescription(),cityName);
                    Log.d(TAG, "onResponse: inserted : " +  b);
                }
                    else {
                    try {
                        apiCallback.err(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure ( Call<WeatherDetails> call, Throwable t ) {
                apiCallback.failure(t.getMessage());
            }
        });
    }

    /**
     * inserting data to db
     * @param humidity
     * @param temp
     * @param tempMax
     * @param tempMin
     * @param description
     * @param cityName, city name not required but according to requiremnt inserting to db
     * @return
     */
    @Override
    public boolean insertDB ( float humidity, float temp, float tempMax, float tempMin, String description, String cityName ) {
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature(String.valueOf(temp));
        weatherData.setHumidity(String.valueOf(humidity));
        weatherData.setMinTemp(String.valueOf(tempMin));
        weatherData.setMaxTemp(String.valueOf(tempMax));
        weatherData.setCityName(cityName);
        weatherData.setDesc(description);
       return new WeatherDB(context).insertWeather(weatherData);
    }

    /**
     * get the list of weather data from db
     * @param context  here next time context will be null that's y using context
     * @return
     */
    @Override
    public List<WeatherData> getWeahterList (Context context) {
        return new WeatherDB(context).getWeatherData();
    }


}
