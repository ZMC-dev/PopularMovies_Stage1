package app.zoilymontes.com.popularmovies_stage1;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieJsonUtils {


    private static final String MOVIE_RESULTS = "results";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_POSTER = "poster_path";
    private static final String MOVIE_PLOT = "overview";
    private static final String MOVIE_RATING = "vote_average";
    private static final String MOVIE_RELEASE_DATE = "release_date";


    public static Movie[] parseJsonMovie(String jsonMoviesData) throws JSONException {

        JSONObject jsonRoot = new JSONObject(jsonMoviesData);
        JSONArray jsonArrayResult = jsonRoot.getJSONArray(MOVIE_RESULTS);
        Movie[] result = new Movie[jsonArrayResult.length()];
        for (int i = 0; i < jsonArrayResult.length(); i++) {
            Movie movie = new Movie();
            movie.setmTitle(jsonArrayResult.getJSONObject(i).optString(MOVIE_TITLE));
            movie.setmMoviePoster(jsonArrayResult.getJSONObject(i).optString(MOVIE_POSTER));
            movie.setmPlot(jsonArrayResult.getJSONObject(i).optString(MOVIE_PLOT));
            movie.setmRating(jsonArrayResult.getJSONObject(i).optString(MOVIE_RATING));
            movie.setmReleaseDate(jsonArrayResult.getJSONObject(i).optString(MOVIE_RELEASE_DATE));
            result[i] = movie;
        }
        return result;
    }

    public static final String API_KEY = "85241ff5a54a9a54690bd91c4274d78d";
    private static final String LOG_TAG = "MovieJsonUtils";
    private static final String MOVIE_QUERY_API = "api_key";
    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";


    public static URL buildUrl(String movieUrl) {

        Uri uri = Uri.parse(MOVIE_BASE_URL)
                .buildUpon()
                .appendPath(movieUrl)
                .appendQueryParameter(MOVIE_QUERY_API, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problems create url", e);
        }

        return url;
    }

    public static String getResponseFromHttp(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

