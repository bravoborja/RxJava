package es.borjabravo.rxjava.di.modules;

import dagger.Module;
import dagger.Provides;
import es.borjabravo.rxjava.ui.interactors.MoviesInteractor;
import es.borjabravo.rxjava.ui.presenters.MoviesPresenter;
import es.borjabravo.rxjava.ui.views.MoviesView;

@Module
public class MoviesModule {

    private MoviesView usersView;

    public MoviesModule(MoviesView usersView) {
        this.usersView = usersView;
    }

    @Provides
    public MoviesView provideView() {
        return usersView;
    }

    @Provides
    public MoviesPresenter providePresenter(MoviesView usersView, MoviesInteractor usersInteractor) {
        return new MoviesPresenter(usersView, usersInteractor);
    }
}