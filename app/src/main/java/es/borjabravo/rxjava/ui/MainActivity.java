package es.borjabravo.rxjava.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.borjabravo.rxjava.R;
import es.borjabravo.rxjava.app.RxJavaComponent;
import es.borjabravo.rxjava.base.BaseActivity;
import es.borjabravo.rxjava.base.BasePresenter;
import es.borjabravo.rxjava.di.components.DaggerMoviesComponent;
import es.borjabravo.rxjava.di.modules.MoviesModule;
import es.borjabravo.rxjava.io.models.Movie;
import es.borjabravo.rxjava.ui.adapters.MoviesAdapter;
import es.borjabravo.rxjava.ui.presenters.MoviesPresenter;
import es.borjabravo.rxjava.ui.views.MoviesView;

public class MainActivity extends BaseActivity implements MoviesView {

    private final String TAG = getClass().getName();

    @Inject
    MoviesPresenter moviesPresenter;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    MoviesAdapter moviesAdapter;

    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (movies == null) {
            movies = new ArrayList<>();
        }
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter();
//            moviesPresenter.getMoviesFromSearch();
//            moviesPresenter.getMoviesFilteredFromSearch();
            moviesPresenter.getMovieInfo();
        }
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(moviesAdapter);
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter() {
        return moviesPresenter;
    }

    @Override
    public void setUpComponent(RxJavaComponent appComponent) {
        DaggerMoviesComponent.builder()
                .rxJavaComponent(appComponent)
                .moviesModule(new MoviesModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateMoviesFound(ArrayList<Movie> movies) {
        Log.i(TAG, "Movies: " + movies.size());
        moviesAdapter.addMovies(movies);
    }

    @Override
    public void updateMovieFound(Movie movie) {
        Log.i(TAG, "Movie: " + movie.getTitle());
        moviesAdapter.addMovie(movie);
    }

    @Override
    public void onCompletedLoadUsers() {
        Log.i(TAG, "Completed");
    }
}
