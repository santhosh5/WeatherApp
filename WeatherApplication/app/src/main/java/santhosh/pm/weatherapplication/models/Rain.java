package santhosh.pm.weatherapplication.models;

public class Rain {

    public float h3;

    public float getH3 ( ) {
        return h3;
    }

    public void setH3 ( float h3 ) {
        this.h3 = h3;
    }

    @Override
    public String toString ( ) {
        return "Rain{" +
                "h3=" + h3 +
                '}';
    }
}
