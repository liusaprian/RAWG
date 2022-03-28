package app.liusaprian.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import app.liusaprian.core.data.source.local.entity.ImageEntity
import app.liusaprian.core.data.source.local.entity.MovieEntity
import app.liusaprian.core.data.source.local.entity.ReviewEntity

@Database(entities = [MovieEntity::class, ReviewEntity::class, ImageEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun reviewDao(): ReviewDao
    abstract fun imageDao(): ImageDao
}