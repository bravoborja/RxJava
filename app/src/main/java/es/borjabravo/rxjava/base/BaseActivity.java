package es.borjabravo.rxjava.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import es.borjabravo.rxjava.app.RxJavaApp;
import es.borjabravo.rxjava.app.RxJavaComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getPresenter() != null)
            getPresenter().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (getPresenter() != null)
            getPresenter().onStop();
    }

    @Nullable
    protected abstract BasePresenter getPresenter();

    private void injectDependencies() {
        setUpComponent(RxJavaApp.getApp(this).getComponent());
    }

    public abstract void setUpComponent(RxJavaComponent appComponent);

}

