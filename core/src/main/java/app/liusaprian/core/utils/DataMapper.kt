package app.liusaprian.core.utils

import app.liusaprian.core.data.source.local.entity.ImageEntity
import app.liusaprian.core.data.source.local.entity.MovieEntity
import app.liusaprian.core.data.source.local.entity.ReviewEntity
import app.liusaprian.core.data.source.remote.response.BackdropsItem
import app.liusaprian.core.data.source.remote.response.MovieResponse
import app.liusaprian.core.data.source.remote.response.ReviewItem
import app.liusaprian.core.domain.model.Image
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.model.Review

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                rating = it.voteAverage,
                backdropPath = it.backdropPath,
                genre = it.genre,
                category = it.category,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                isFavorite = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                category = it.category,
                genre = it.genre,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        rating = input.rating,
        title = input.title,
        genre = input.genre,
        overview = input.overview,
        backdropPath = input.backdropPath,
        category = input.category,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )

    fun mapReviewResponsesToEntities(input: List<ReviewItem>): List<ReviewEntity> {
        val reviewList = ArrayList<ReviewEntity>()
        input.map {
            val review = ReviewEntity(
                id = it.id,
                movieId = it.movieId,
                author = it.author,
                reviewerAvatar = it.authorDetails.avatarPath,
                rating = it.authorDetails.rating,
                content = it.content
            )
            reviewList.add(review)
        }
        return reviewList
    }

    fun mapReviewEntitiesToDomain(input: List<ReviewEntity>): List<Review> =
        input.map {
            Review(
                id = it.id,
                movieId = it.movieId,
                author = it.author,
                reviewerAvatar = it.reviewerAvatar,
                rating = it.rating,
                content = it.content
            )
        }

    fun mapImageResponsesToEntities(input: List<BackdropsItem>): List<ImageEntity> {
        val imageList = ArrayList<ImageEntity>()
        input.map {
            val image = ImageEntity(
                movieId = it.movieId,
                filePath = it.filePath
            )
            imageList.add(image)
        }
        return imageList
    }

    fun mapImageEntitiesToDomain(input: List<ImageEntity>): List<Image> =
        input.map {
            Image(
                movieId = it.movieId,
                filePath = it.filePath
            )
        }
}