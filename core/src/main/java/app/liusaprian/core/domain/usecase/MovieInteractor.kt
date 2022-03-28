package app.liusaprian.core.domain.usecase

import app.liusaprian.core.data.Resource
import app.liusaprian.core.domain.model.Image
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.model.Review
import app.liusaprian.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getMovieList(category: String) = movieRepository.getMovieList(category)

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getMovieReviews(id: Int): Flow<Resource<List<Review>>> = movieRepository.getMovieReviews(id)

    override fun getMovieImages(id: Int): Flow<Resource<List<Image>>> = movieRepository.getMovieImages(id)
}