package es.borjabravo.rxjava.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.borjabravo.rxjava.R;
import es.borjabravo.rxjava.io.models.Movie;

/**
 * Created by borja on 16/2/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Movie> movies;

    public MoviesAdapter() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        notifyDataSetChanged();
    }

    public void addMovies(ArrayList<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((MoviesViewHolder) viewHolder).fill(movies.get(position));
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.movie_title)
        TextView movieTitle;
        @Bind(R.id.movie_plot)
        TextView moviePlot;

        public MoviesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void fill(Movie movie) {
            if (movie != null) {
                movieTitle.setText(movie.getTitle());
                moviePlot.setText(movie.getPlot());
            }
        }
    }
}
