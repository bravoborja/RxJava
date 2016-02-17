package es.borjabravo.rxjava.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import es.borjabravo.rxjava.ui.interactors.MoviesInteractor;


@Singleton
@Component(
        modules = {
                RxJavaModule.class,
                InteractorsModule.class
        }
)
public interface RxJavaComponent {

    Context getContext();
    MoviesInteractor getMoviesInteractor();
}
