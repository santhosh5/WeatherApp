package santhosh.pm.weatherapplication.views;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import santhosh.pm.weatherapplication.R;
import santhosh.pm.weatherapplication.adapters.WeatherAdapter;
import santhosh.pm.weatherapplication.callbacks.WeatherI;
import santhosh.pm.weatherapplication.models.WeatherData;
import santhosh.pm.weatherapplication.presenter.WeatherPresenter;
import santhosh.pm.weatherapplication.util.GenralUtil;
import santhosh.pm.weatherapplication.util.MainApp;

public class WeatherActivity extends AppCompatActivity implements WeatherI.ApiCallback, WeatherAdapter.ItemClickI {

    private RecyclerView rvWeather;
    private TextView tVCityName, tvTemp;
    private TextView tvDate, tvClimateDesc;
    private TextView tvMinMaxTemp, tvHumidity;
    private List<WeatherData> weatherDetailsList;
    private String arr[] = {"LONDON","DELHI","MUMBAI","CHENNAI","PARIS","MYSORE"}; // city
    private ProgressBar progressBar;
    private WeatherI weatherI;
    private String degree = "°";
    private String per = "%";
    private String seperator = " / ";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tVCityName = findViewById(R.id.tv_cityname);
        tvDate = findViewById(R.id.tv_date);
        tvDate.setText(GenralUtil.getFormatedDate(System.currentTimeMillis())); // setting the dateformat specified in UI
        tvClimateDesc = findViewById(R.id.tv_clim_desc);
        tvTemp = findViewById(R.id.tv_degree);
        tvMinMaxTemp = findViewById(R.id.tv_temp_collec);
        tvHumidity = findViewById(R.id.tv_humidity);

        progressBar = findViewById(R.id.pb);
        before(); // visible progressbar
        rvWeather = findViewById(R.id.rv_city_weather);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvWeather.setLayoutManager(gridLayoutManager);
        rvWeather.setNestedScrollingEnabled(false);

         weatherI = new WeatherPresenter(); // presenter intialised

        // api call is restriced if data is not available only then api call is made
            if(weatherI.getWeahterList(this) != null && weatherI.getWeahterList(this).size() == 0) {
                if(MainApp.isNetworkAvailable()) {
                for (String s : arr) {
                    weatherI.weatherAPI(this, s);
                }
            }
                else {
                    Toast.makeText(this, "No network available", Toast.LENGTH_SHORT).show();
                }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run ( ) {
                populateRV();
                displayTextValues(null);
            }
        },5000);

}

    /**
     * values are binded to UI
     * @param data
     * if data : null, setting some default value from local db
     */
    private void displayTextValues(WeatherData data) {
        if(data == null) {
            tVCityName.setText(arr[0]);
            if(weatherDetailsList != null && weatherDetailsList.size() > 0) {
                WeatherData d = weatherDetailsList.get(0);
                tvHumidity.setText(d.getHumidity() + per);
                tvMinMaxTemp.setText(d.getMinTemp() + degree + seperator + d.getMaxTemp() + degree);
                tvTemp.setText(d.getTemperature() + degree);
                tvClimateDesc.setText(d.getDesc());
            }
            else {
                Toast.makeText(this, "Sorry no data availble", Toast.LENGTH_LONG).show();
            }
        }
        else {
            tVCityName.setText(data.getCityName());
            tvHumidity.setText(data.getHumidity()+per);
            tvMinMaxTemp.setText(data.getMinTemp()+degree+seperator+data.getMaxTemp()+degree);
            tvTemp.setText(data.getTemperature()+degree);
            tvClimateDesc.setText(data.getDesc());
        }
    }

    @Override
    public void before ( ) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void success ( ) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void failure ( String msg ) {
        Toast.makeText(this, "failure " + msg, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void err ( String msg ) {
        Toast.makeText(this, "error " + msg, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void populateRV() {
        weatherDetailsList = weatherI.getWeahterList(WeatherActivity.this);
        WeatherAdapter weatherAdapter = new WeatherAdapter(this, weatherDetailsList);
        rvWeather.setAdapter(weatherAdapter);
        success();
    }

    /**
     * @param data
     * when user clicks on the particular city, that data is binding to UI
     */
    @Override
    public void itemClick (WeatherData data ) {
        displayTextValues(data);
    }
}
