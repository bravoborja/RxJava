package es.borjabravo.rxjava.io.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import es.borjabravo.rxjava.io.models.Movie;

/**
 * Created by borja on 16/2/16.
 */
public class MoviesResponse {

    @SerializedName("Search")
    ArrayList<Movie> movies;
    String totalResults;
    @SerializedName("Response")
    String response;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
