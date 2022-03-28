package app.liusaprian.core.data.source.local.room

import androidx.room.*
import app.liusaprian.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE category = :category")
    fun getMovieList(category: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movie: MovieEntity)
}