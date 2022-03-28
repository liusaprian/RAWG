package app.liusaprian.core.data

import app.liusaprian.core.data.source.local.LocalDataSource
import app.liusaprian.core.data.source.remote.RemoteDataSource
import app.liusaprian.core.data.source.remote.network.ApiResponse
import app.liusaprian.core.data.source.remote.response.BackdropsItem
import app.liusaprian.core.data.source.remote.response.MovieResponse
import app.liusaprian.core.data.source.remote.response.ReviewItem
import app.liusaprian.core.domain.model.Image
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.model.Review
import app.liusaprian.core.domain.repository.IMovieRepository
import app.liusaprian.core.utils.DataMapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {

    override fun getMovieList(category: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovieList(category).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieList(category)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val gameEntity = DataMapper.mapMovieDomainToEntity(movie)
        coroutineScope { localDataSource.setFavoriteMovie(gameEntity, state) }
    }

    override fun getMovieImages(id: Int): Flow<Resource<List<Image>>> =
        object : NetworkBoundResource<List<Image>, List<BackdropsItem>>() {
            override fun loadFromDB(): Flow<List<Image>> {
                return localDataSource.getMovieImages(id).map {
                    DataMapper.mapImageEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Image>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<BackdropsItem>>> =
                remoteDataSource.getMovieImages(id)

            override suspend fun saveCallResult(data: List<BackdropsItem>) {
                val imageList = DataMapper.mapImageResponsesToEntities(data)
                localDataSource.insertImages(imageList)
            }
        }.asFlow()

    override fun getMovieReviews(id: Int): Flow<Resource<List<Review>>> =
        object : NetworkBoundResource<List<Review>, List<ReviewItem>>() {
            override fun loadFromDB(): Flow<List<Review>> {
                return localDataSource.getMovieReviews(id).map {
                    DataMapper.mapReviewEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Review>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ReviewItem>>> =
                remoteDataSource.getMovieReviews(id)

            override suspend fun saveCallResult(data: List<ReviewItem>) {
                val reviewList = DataMapper.mapReviewResponsesToEntities(data)
                localDataSource.insertReviews(reviewList)
            }
        }.asFlow()
}
