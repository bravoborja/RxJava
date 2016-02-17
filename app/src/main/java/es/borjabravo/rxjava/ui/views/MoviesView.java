package es.borjabravo.rxjava.ui.views;

import java.util.ArrayList;

import es.borjabravo.rxjava.io.models.Movie;

/**
 * Created by borja on 16/2/16.
 */
public interface MoviesView {
    void updateMoviesFound(ArrayList<Movie> movies);
    void updateMovieFound(Movie movie);
    void onCompletedLoadUsers();
    void onError();
}
