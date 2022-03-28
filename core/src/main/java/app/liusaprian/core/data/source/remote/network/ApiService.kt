package app.liusaprian.core.data.source.remote.network

import app.liusaprian.core.BuildConfig
import app.liusaprian.core.data.source.remote.response.GenreResponse
import app.liusaprian.core.data.source.remote.response.ListMovieResponse
import app.liusaprian.core.data.source.remote.response.MovieImageResponse
import app.liusaprian.core.data.source.remote.response.MovieReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ) : ListMovieResponse

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ) : MovieReviewResponse

    @GET("movie/{id}/images")
    suspend fun getMovieImages(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ) : MovieImageResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ) : GenreResponse
}