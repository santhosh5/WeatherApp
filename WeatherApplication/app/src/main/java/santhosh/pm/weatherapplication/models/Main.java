package santhosh.pm.weatherapplication.models;

public class Main {

    public float temp;
    public float humidity;
    public float pressure;
    public float temp_min;
    public float temp_max;

    public float getTemp ( ) {
        return temp;
    }

    public void setTemp ( float temp ) {
        this.temp = temp;
    }

    public float getHumidity ( ) {
        return humidity;
    }

    public void setHumidity ( float humidity ) {
        this.humidity = humidity;
    }

    public float getPressure ( ) {
        return pressure;
    }

    public void setPressure ( float pressure ) {
        this.pressure = pressure;
    }

    public float getTemp_min ( ) {
        return temp_min;
    }

    public void setTemp_min ( float temp_min ) {
        this.temp_min = temp_min;
    }

    public float getTemp_max ( ) {
        return temp_max;
    }

    public void setTemp_max ( float temp_max ) {
        this.temp_max = temp_max;
    }

    @Override
    public String toString ( ) {
        return "Rain{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                '}';
    }
}
