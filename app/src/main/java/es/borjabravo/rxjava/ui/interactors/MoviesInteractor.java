package es.borjabravo.rxjava.ui.interactors;

import es.borjabravo.rxjava.Api;
import es.borjabravo.rxjava.io.callbacks.MoviesCallback;
import es.borjabravo.rxjava.io.models.Movie;
import es.borjabravo.rxjava.io.responses.MoviesResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MoviesInteractor {

    Api api;

    public MoviesInteractor(Api api) {
        this.api = api;
    }


    //Simple request - All movies from organization choosen
    public void getMoviesFromSearch(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviesResponse>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        callback.onMoviesFound(moviesResponse.getMovies());
                    }
                });
    }

    //Simple request - All movies from organization choosen - Filtered by only movies
    public void getMoviesFilteredFromSearch(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<MoviesResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(MoviesResponse moviesResponse) {
                        return Observable.from(moviesResponse.getMovies());
                    }
                })
                .filter(new Func1<Movie, Boolean>() {
                    @Override
                    public Boolean call(Movie movie) {
                        return movie.getType().equalsIgnoreCase("movie");
                    }
                })
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(Movie movie) {
                        callback.onMovieFound(movie);
                    }
                });
    }

    //Nested request - Movie detail info
    public void getMovieInfo(final MoviesCallback callback) {
        api.getMoviesFromSearch()
                .flatMap(new Func1<MoviesResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(MoviesResponse moviesResponse) {
                        return Observable.from(moviesResponse.getMovies());
                    }
                })
                .flatMap(new Func1<Movie, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(Movie movie) {
                        return api.getMovieInfo(movie.getImdbID());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(Movie movie) {
                        callback.onMovieFound(movie);
                    }
                });
    }
}