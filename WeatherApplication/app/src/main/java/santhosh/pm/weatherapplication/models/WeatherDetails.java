package santhosh.pm.weatherapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDetails {

    public Coord coord;
    public Sys sys;
    public ArrayList<Weather> weather = new ArrayList<Weather>();
    public Main main;
    public Wind wind;
    public Rain rain;

    public Clouds clouds;
    public float dt;
    public int id;
    public String name;
    public float cod;

    @Override
    public String toString ( ) {
        return "WeatherDetails{" +
                "coord=" + coord +
                ", sys=" + sys +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }

    public Coord getCoord ( ) {
        return coord;
    }

    public void setCoord ( Coord coord ) {
        this.coord = coord;
    }

    public Sys getSys ( ) {
        return sys;
    }

    public void setSys ( Sys sys ) {
        this.sys = sys;
    }

    public ArrayList<Weather> getWeather ( ) {
        return weather;
    }

    public void setWeather ( ArrayList<Weather> weather ) {
        this.weather = weather;
    }

    public Main getMain ( ) {
        return main;
    }

    public void setMain ( Main main ) {
        this.main = main;
    }

    public Wind getWind ( ) {
        return wind;
    }

    public void setWind ( Wind wind ) {
        this.wind = wind;
    }

    public Rain getRain ( ) {
        return rain;
    }

    public void setRain ( Rain rain ) {
        this.rain = rain;
    }

    public Clouds getClouds ( ) {
        return clouds;
    }

    public void setClouds ( Clouds clouds ) {
        this.clouds = clouds;
    }

    public float getDt ( ) {
        return dt;
    }

    public void setDt ( float dt ) {
        this.dt = dt;
    }

    public int getId ( ) {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public float getCod ( ) {
        return cod;
    }

    public void setCod ( float cod ) {
        this.cod = cod;
    }
}
