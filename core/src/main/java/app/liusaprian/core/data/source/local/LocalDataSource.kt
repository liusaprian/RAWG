package app.liusaprian.core.data.source.local

import app.liusaprian.core.data.source.local.entity.ImageEntity
import app.liusaprian.core.data.source.local.entity.MovieEntity
import app.liusaprian.core.data.source.local.entity.ReviewEntity
import app.liusaprian.core.data.source.local.room.ImageDao
import app.liusaprian.core.data.source.local.room.MovieDao
import app.liusaprian.core.data.source.local.room.ReviewDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao, private val reviewDao: ReviewDao, private val imageDao: ImageDao) {

    fun getMovieList(category: String): Flow<List<MovieEntity>> = movieDao.getMovieList(category)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    suspend fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        updateMovie(movie)
    }

    private suspend fun updateMovie(movie: MovieEntity) = movieDao.updateMovie(movie)

    fun getMovieReviews(id: Int): Flow<List<ReviewEntity>> = reviewDao.getMovieReviews(id)

    suspend fun insertReviews(reviewList: List<ReviewEntity>) = reviewDao.insertReviews(reviewList)

    fun getMovieImages(id: Int): Flow<List<ImageEntity>> = imageDao.getMovieImages(id)

    suspend fun insertImages(imageList: List<ImageEntity>) = imageDao.insertImages(imageList)
}