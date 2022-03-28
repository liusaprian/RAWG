package app.liusaprian.rawg.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovieReviews(id: Int) =
        movieUseCase.getMovieReviews(id).asLiveData()

    fun getMovieImages(id: Int) =
        movieUseCase.getMovieImages(id).asLiveData()

    fun setFavoriteGame(movie: Movie, newState: Boolean) = viewModelScope.launch {
        movieUseCase.setFavoriteMovie(movie, newState)
    }
}