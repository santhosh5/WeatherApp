package santhosh.pm.weatherapplication.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainApp extends Application {
    private static final String TAG = MainApp.class.getSimpleName();
    public ApiService apiService;
    private static Retrofit mRetrofit;
    private final static long CACHE_SIZE = 10 * 1024 * 1024; // 10MB Cache size
    private static MainApp mInstance;

    @Override
    public void onCreate ( ) {
        super.onCreate();
        mInstance = this;
        initializeRetrofit();
    }

    /**
     * @return
     * intialising retrofit
     */
    private static Retrofit initializeRetrofit() {

        final Interceptor cacheControlInterceptor = chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (isNetworkAvailable()) {
                int maxSec = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxSec)
                        .build();
            }
            else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };

        // Create Cache
        Cache cache = new Cache(mInstance.getCacheDir(), CACHE_SIZE);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .addNetworkInterceptor(cacheControlInterceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();

        return mRetrofit;
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit != null) {
            return mRetrofit;
        } else {
            return initializeRetrofit();
        }
    }

    /**
     * @return checking the internet connectivity
     */
    public static boolean isNetworkAvailable(){
        final ConnectivityManager connectivityManager = ((ConnectivityManager)
                mInstance.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
