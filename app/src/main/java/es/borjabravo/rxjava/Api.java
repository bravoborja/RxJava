package es.borjabravo.rxjava;

import es.borjabravo.rxjava.io.models.Movie;
import es.borjabravo.rxjava.io.responses.MoviesResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by borja on 16/2/16.
 */
public interface Api {

    @GET("?s=Batman")
    Observable<MoviesResponse> getMoviesFromSearch();

    @GET("?")
    Observable<Movie> getMovieInfo(@Query("i") String i);
}