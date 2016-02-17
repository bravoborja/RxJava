package es.borjabravo.rxjava.ui.presenters;

import java.util.ArrayList;

import es.borjabravo.rxjava.base.BasePresenter;
import es.borjabravo.rxjava.io.callbacks.MoviesCallback;
import es.borjabravo.rxjava.io.models.Movie;
import es.borjabravo.rxjava.ui.interactors.MoviesInteractor;
import es.borjabravo.rxjava.ui.views.MoviesView;

/**
 * Created by borja on 16/2/16.
 */
public class MoviesPresenter extends BasePresenter implements MoviesCallback {

    MoviesView moviesView;
    MoviesInteractor moviesInteractor;

    public MoviesPresenter(MoviesView moviesView, MoviesInteractor moviesInteractor) {
        this.moviesView = moviesView;
        this.moviesInteractor = moviesInteractor;
    }

    public void getMoviesFromSearch() {
        moviesInteractor.getMoviesFromSearch(this);
    }

    public void getMoviesFilteredFromSearch() {
        moviesInteractor.getMoviesFilteredFromSearch(this);
    }

    public void getMovieInfo() {
        moviesInteractor.getMovieInfo(this);
    }

    @Override
    public void onMoviesFound(ArrayList<Movie> movies) {
        moviesView.updateMoviesFound(movies);
    }

    @Override
    public void onMovieFound(Movie user) {
        moviesView.updateMovieFound(user);
    }

    @Override
    public void onCompleted() {
        moviesView.onCompletedLoadUsers();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}