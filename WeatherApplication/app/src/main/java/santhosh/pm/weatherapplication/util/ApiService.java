package santhosh.pm.weatherapplication.util;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import santhosh.pm.weatherapplication.models.WeatherDetails;


public interface ApiService {
        @GET("weather?")
        Call<WeatherDetails> getCurrentWeatherData ( @Query("q") String q, @Query("APPID") String APPID );

}
