package es.borjabravo.rxjava.io.callbacks;

import java.util.ArrayList;

import es.borjabravo.rxjava.io.models.Movie;

public interface MoviesCallback {

    void onMoviesFound(ArrayList<Movie> movies);
    void onMovieFound(Movie movie);
    void onCompleted();
}