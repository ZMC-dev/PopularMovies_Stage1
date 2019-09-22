package app.zoilymontes.com.popularmovies_stage1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import app.zoilymontes.com.popularmovies_stage1.Movie;
import app.zoilymontes.com.popularmovies_stage1.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {


    private static final String URL_IMAGE_PATH = "http://image.tmdb.org/t/p/w185";
    private final Context mContext;
    private Movie[] mMovie = null;
    private final MovieClickListener mMovieClickListener;


    public MovieAdapter(Movie[] movies, Context context, MovieClickListener movieClickListener) {
        mMovie = movies;
        mContext = context;
        mMovieClickListener = movieClickListener;
    }


    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_movie_poster, parent, false);


        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        Picasso.with(mContext)
                .load(URL_IMAGE_PATH.concat(mMovie[position].getmMoviePoster()))
                .fit()
                .into(holder.imageViewHolder);

    }

    @Override
    public int getItemCount() {
        return mMovie.length;
    }

    public interface MovieClickListener {
        void onClickMovie(int position);
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageViewHolder;


        MovieHolder(View itemView) {
            super(itemView);


            imageViewHolder = itemView.findViewById(R.id.iv_list_item_poster);
            imageViewHolder.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            mMovieClickListener.onClickMovie(clickPosition);

        }
    }

}
