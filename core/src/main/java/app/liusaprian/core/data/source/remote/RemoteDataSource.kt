package app.liusaprian.core.data.source.remote

import app.liusaprian.core.data.source.remote.network.ApiResponse
import app.liusaprian.core.data.source.remote.network.ApiService
import app.liusaprian.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovieList(category: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList(category)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    val genres = apiService.getGenres().genres
                    dataArray.map { movie ->
                        movie.backdropPath = "https://image.tmdb.org/t/p/original${movie.backdropPath}"
                        movie.posterPath = "https://image.tmdb.org/t/p/original${movie.posterPath}"
                        movie.category = category
                        movie.voteAverage /= 2
                        genres.forEach { genre ->
                            if(movie.genreIds[0] == genre.id) {
                                movie.genre = genre.genreName
                            }
                        }
                    }
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieReviews(id: Int): Flow<ApiResponse<List<ReviewItem>>> {
        return flow {
            try {
                val response = apiService.getMovieReviews(id)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    dataArray.map { review ->
                        review.movieId = response.id
                        if(review.authorDetails.rating != null) review.authorDetails.rating!!.div(2)
                        else review.authorDetails.rating = 0.0
                    }
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieImages(id: Int): Flow<ApiResponse<List<BackdropsItem>>> {
        return flow {
            try {
                val response = apiService.getMovieImages(id)
                val dataArray = response.backdrops
                if (dataArray.isNotEmpty()){
                    dataArray.map { image ->
                        image.movieId = id
                        image.filePath = "https://image.tmdb.org/t/p/original${image.filePath}"
                    }
                    emit(ApiResponse.Success(response.backdrops))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}