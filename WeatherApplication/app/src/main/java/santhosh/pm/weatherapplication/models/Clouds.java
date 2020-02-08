package santhosh.pm.weatherapplication.models;

public class Clouds {
    public float all;

    public float getAll ( ) {
        return all;
    }

    public void setAll ( float all ) {
        this.all = all;
    }

    @Override
    public String toString ( ) {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
