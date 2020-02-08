package santhosh.pm.weatherapplication.models;

public class WeatherData {

    private String cityName;
    private String temperature;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private String desc;

    public String getDesc ( ) {
        return desc;
    }

    public void setDesc ( String desc ) {
        this.desc = desc;
    }

    public String getCityName ( ) {
        return cityName;
    }

    public void setCityName ( String cityName ) {
        this.cityName = cityName;
    }

    public String getTemperature ( ) {
        return temperature;
    }

    public void setTemperature ( String temperature ) {
        this.temperature = temperature;
    }

    public String getMinTemp ( ) {
        return minTemp;
    }

    public void setMinTemp ( String minTemp ) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp ( ) {
        return maxTemp;
    }

    public void setMaxTemp ( String maxTemp ) {
        this.maxTemp = maxTemp;
    }

    public String getHumidity ( ) {
        return humidity;
    }

    public void setHumidity ( String humidity ) {
        this.humidity = humidity;
    }

    @Override
    public String toString ( ) {
        return "WeatherData{" +
                "cityName='" + cityName + '\'' +
                ", temperature='" + temperature + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
