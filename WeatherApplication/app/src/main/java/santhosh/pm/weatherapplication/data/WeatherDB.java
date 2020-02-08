package santhosh.pm.weatherapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import santhosh.pm.weatherapplication.models.WeatherData;

public class WeatherDB extends SQLiteOpenHelper {

    private static final String TAG = "WeatherDB";
    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME = "Weather.db";

    public WeatherDB( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate ( SQLiteDatabase sqLiteDatabase ) {
        final String SQL_CREATE_WEATHER_TABLE =
                "CREATE TABLE " + DbContract.WeatherEntry.TABLE_WEATHER + " ( "
                        + DbContract.WeatherEntry._ID + " INTEGER PRIMARY KEY, "
                        + DbContract.WeatherEntry.COL_CITY_NAME + " TEXT, "
                        + DbContract.WeatherEntry.COL_TEMP + " TEXT, "
                        + DbContract.WeatherEntry.COL_MIN_TEMP + " TEXT, "
                        + DbContract.WeatherEntry.COL_MAX_TEMP + " TEXT, "
                        + DbContract.WeatherEntry.COL_DESC + " TEXT, "
                        + DbContract.WeatherEntry.COL_HUMDITY + " TEXT "
                        + " )";

        try {
            sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade ( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {

    }

    /**
     * @param weather
     * @return
     * inserting data to db
     */
    public boolean insertWeather(WeatherData weather){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DbContract.WeatherEntry.COL_CITY_NAME, weather.getCityName());
        contentValues.put(DbContract.WeatherEntry.COL_TEMP, weather.getTemperature());
        contentValues.put(DbContract.WeatherEntry.COL_MIN_TEMP, weather.getMinTemp());
        contentValues.put(DbContract.WeatherEntry.COL_MAX_TEMP, weather.getMaxTemp());
        contentValues.put(DbContract.WeatherEntry.COL_HUMDITY, weather.getHumidity());
        contentValues.put(DbContract.WeatherEntry.COL_DESC, weather.getDesc());

        long result = db.insert(DbContract.WeatherEntry.TABLE_WEATHER, null, contentValues);
        db.close();
        return result > 0;
    }

    /**
     * @return
     * getting data from db
     */
    public List<WeatherData> getWeatherData(){
        SQLiteDatabase db = getReadableDatabase();
        WeatherData weatherData = null;
        List<WeatherData> weatherList = new ArrayList<>();

        try {
            Cursor cursor = db.query(DbContract.WeatherEntry.TABLE_WEATHER, null, null, null,
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    weatherData = new WeatherData();
                    String cityName = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_CITY_NAME));
                    String temperature = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_TEMP));
                    String minTemp = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_MIN_TEMP));
                    String maxTemp = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_MAX_TEMP));
                    String humidity = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_HUMDITY));
                    String desc = cursor.getString(cursor.getColumnIndex(DbContract.WeatherEntry.COL_DESC));

                    weatherData.setCityName(cityName);
                    weatherData.setHumidity(humidity);
                    weatherData.setMinTemp(minTemp);
                    weatherData.setMaxTemp(maxTemp);
                    weatherData.setTemperature(temperature);
                    weatherData.setDesc(desc);
                    weatherList.add(weatherData);
                }
                while (cursor.moveToNext());
                cursor.close();
            }
        }
        catch (Exception e) {
            Log.d(TAG, "getWeather: exception " + e.getMessage());
        }
        db.close();
        return weatherList;
    }
}
