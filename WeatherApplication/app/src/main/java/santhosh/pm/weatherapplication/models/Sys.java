package santhosh.pm.weatherapplication.models;

public class Sys {

    public String country;
    public long sunrise;
    public long sunset;

    public String getCountry ( ) {
        return country;
    }

    public void setCountry ( String country ) {
        this.country = country;
    }

    public long getSunrise ( ) {
        return sunrise;
    }

    public void setSunrise ( long sunrise ) {
        this.sunrise = sunrise;
    }

    public long getSunset ( ) {
        return sunset;
    }

    public void setSunset ( long sunset ) {
        this.sunset = sunset;
    }

    @Override
    public String toString ( ) {
        return "Sys{" +
                "country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
