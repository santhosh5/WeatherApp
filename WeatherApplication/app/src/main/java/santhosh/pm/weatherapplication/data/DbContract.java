package santhosh.pm.weatherapplication.data;

import android.provider.BaseColumns;

public class DbContract {

    public static final class WeatherEntry implements BaseColumns {
        public static final String TABLE_WEATHER = "WEATHER";
        public static final String _ID = "_id";
        public static final String COL_CITY_NAME = "CITY_NAME";
        public static final String COL_TEMP = "TEMPERATURE";
        public static final String COL_MIN_TEMP = "MIN_TEMP";
        public static final String COL_MAX_TEMP = "MAX_TEMP";
        public static final String COL_DESC = "DESC";
        public static final String COL_HUMDITY = "HUMDITY";
    }

}
