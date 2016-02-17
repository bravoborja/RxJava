package es.borjabravo.rxjava;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by borja on 16/2/16.
 */
public class RetrofitAdapter {

    private static Retrofit RETROFIT;

    public static Retrofit getInstance() {
        if (RETROFIT == null)
            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();


        return RETROFIT;
    }
}