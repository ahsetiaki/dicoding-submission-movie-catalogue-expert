package com.setiaki.moviecatalogueexpert.core.data.remote.api

import com.setiaki.moviecatalogueexpert.core.BuildConfig
import com.setiaki.moviecatalogueexpert.core.data.remote.response.MovieResponse
import com.setiaki.moviecatalogueexpert.core.data.remote.response.MovieTopRatedResponse
import com.setiaki.moviecatalogueexpert.core.data.remote.response.TvShowResponse
import com.setiaki.moviecatalogueexpert.core.data.remote.response.TvShowTopRatedResponse
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface TMDBWebservice {
    @GET("{tmdb_version}/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Path("tmdb_version") tmdbVersion: Int = TMDB_VERSION,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int
    ): MovieTopRatedResponse

    @GET("{tmdb_version}/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Path("tmdb_version") tmdbVersion: Int = TMDB_VERSION,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResponse

    @GET("{tmdb_version}/tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Path("tmdb_version") tmdbVersion: Int = TMDB_VERSION,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int
    ): TvShowTopRatedResponse

    @GET("{tmbd_version}/tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") id: Int,
        @Path("tmbd_version") tmdbVersion: Int = TMDB_VERSION,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): TvShowResponse

    companion object {
        private const val TMDB_VERSION = 3
        private const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val TMDB_STARTING_PAGE_INDEX = 1
        const val NETWORK_SIZE = 20

        private val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

        private const val hostname = "api.themoviedb.org"
        private val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

        fun create(): TMDBWebservice {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBWebservice::class.java)
        }
    }
}