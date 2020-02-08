package santhosh.pm.weatherapplication.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class GenralUtil {

    public static String dToStr(float f) {
        return String.valueOf(f);
    }

    /**
     * @param dateInMillis
     * @return
     *method for converting the data format
     */
    public static String getFormatedDate(long dateInMillis) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd. MMMM", Locale.getDefault());
            return sdf.format(dateInMillis);
        }
        catch (Exception e) {
            return  "";
        }
    }
}
