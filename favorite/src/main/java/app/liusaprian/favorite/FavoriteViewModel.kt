package app.liusaprian.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    val games = movieUseCase.getFavoriteMovies().asLiveData()

    fun setFavoriteGame(movie: Movie, newState: Boolean) = viewModelScope.launch {
        movieUseCase.setFavoriteMovie(movie, newState)
    }
}