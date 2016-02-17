package es.borjabravo.rxjava.ui.interactors;

import es.borjabravo.rxjava.Api;
import es.borjabravo.rxjava.io.callbacks.MoviesCallback;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesInteractor {

    private final String TAG = getClass().getName();

    Api api;

    public MoviesInteractor(Api api) {
        this.api = api;
    }


    //Simple request - All movies from organization choosen
    public void getMoviesFromSearch(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesResponse -> callback.onMoviesFound(moviesResponse.getMovies()),
                        error -> callback.onError(),
                        callback::onCompleted);
    }

    //Simple request - All movies from organization choosen - Filtered by only movies
    public void getMoviesFilteredFromSearch(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(moviesResponse -> Observable.from(moviesResponse.getMovies()))
                .filter(movie -> movie.getType().equalsIgnoreCase("movie"))
                .subscribe(callback::onMovieFound,
                        error -> callback.onError(),
                        callback::onCompleted);
    }

    //Nested request - Movie detail info
    public void getMovieInfo(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .flatMap(moviesResponse -> Observable.from(moviesResponse.getMovies()))
                .flatMap(movie -> api.getMovieInfo(movie.getImdbID()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onMovieFound,
                        error -> callback.onError(),
                        callback::onCompleted);
    }
}