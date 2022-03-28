package app.liusaprian.rawg.home

import androidx.lifecycle.*
import app.liusaprian.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val popularMovie = movieUseCase.getMovieList("popular").asLiveData()
    val nowPlayingMovie = movieUseCase.getMovieList("now_playing").asLiveData()
}