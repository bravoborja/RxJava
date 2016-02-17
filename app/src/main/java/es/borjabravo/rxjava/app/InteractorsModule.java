package es.borjabravo.rxjava.app;

import dagger.Module;
import dagger.Provides;
import es.borjabravo.rxjava.Api;
import es.borjabravo.rxjava.ui.interactors.MoviesInteractor;

@Module
public class InteractorsModule {

    @Provides
    public MoviesInteractor provideMoviesInteractor(Api api){
        return new MoviesInteractor(api);
    }
}