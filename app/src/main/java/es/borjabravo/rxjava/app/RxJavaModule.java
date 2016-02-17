package es.borjabravo.rxjava.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.borjabravo.rxjava.Api;
import es.borjabravo.rxjava.RetrofitAdapter;
import retrofit.Retrofit;

@Module
public class RxJavaModule {

    private RxJavaApp app;

    public RxJavaModule(RxJavaApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return RetrofitAdapter.getInstance();
    }

    @Provides
    @Singleton
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}