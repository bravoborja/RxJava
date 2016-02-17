package es.borjabravo.rxjava.app;

import android.app.Application;
import android.content.Context;

public class RxJavaApp extends Application {

    private RxJavaComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerRxJavaComponent.builder()
                .rxJavaModule(new RxJavaModule(this))
                .build();
    }

    public RxJavaComponent getComponent() {
        return component;
    }

    public static RxJavaApp getApp(Context context) {
        return (RxJavaApp) context.getApplicationContext();
    }
}
