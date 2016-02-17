package es.borjabravo.rxjava.di.components;

import dagger.Component;
import es.borjabravo.rxjava.app.ActivityScope;
import es.borjabravo.rxjava.app.RxJavaComponent;
import es.borjabravo.rxjava.di.modules.MoviesModule;
import es.borjabravo.rxjava.ui.MainActivity;
import es.borjabravo.rxjava.ui.presenters.MoviesPresenter;

/**
 * Created by borja on 16/2/16.
 */

@ActivityScope
@Component(
        dependencies = RxJavaComponent.class,
        modules = MoviesModule.class
)
public interface MoviesComponent {

    void inject(MainActivity activity);

    MoviesPresenter getPresenter();
}