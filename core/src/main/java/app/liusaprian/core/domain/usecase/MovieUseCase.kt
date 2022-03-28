package app.liusaprian.core.domain.usecase

import app.liusaprian.core.data.Resource
import app.liusaprian.core.domain.model.Image
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovieList(category: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getMovieReviews(id: Int): Flow<Resource<List<Review>>>
    fun getMovieImages(id: Int): Flow<Resource<List<Image>>>
}