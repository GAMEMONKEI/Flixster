package com.example.flixer_3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.fragment_movies_list,
            container,
            false
        )

        val movieList = view.findViewById<RecyclerView>(R.id.movieList)
        movieList.layoutManager = LinearLayoutManager(requireContext())

        val client = AsyncHttpClient()
        val params = RequestParams()

        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        params["api_key"] = apiKey

        client.get(
            "https://api.themoviedb.org/3/movie/now_playing",
            params,
            object : JsonHttpResponseHandler() {

                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers?,
                    json: JSON?
                ) {
                    Log.d("MoviesFragment", "The connection is working!")

                    val resultsJSON =
                        json?.jsonObject?.get("results") as JSONArray

                    val moviesRawJSON = resultsJSON.toString()

                    val gson = Gson()

                    val arrayMovieType =
                        object : TypeToken<List<Movie>>() {}.type

                    val movies: List<Movie> = gson.fromJson(
                        moviesRawJSON,
                        arrayMovieType
                    )

                    Log.d(
                        "MoviesFragment",
                        "Movies loaded: " + movies.size
                    )

                    movieList.adapter = MovieAdapter(movies)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Log.e("MoviesFragment", "Response failed")
                    Log.e(
                        "MoviesFragment",
                        response ?: "The connection isn't working :("
                    )
                }
            }
        )

        return view
    }
}