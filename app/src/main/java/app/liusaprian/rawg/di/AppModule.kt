package app.liusaprian.rawg.di

import app.liusaprian.core.domain.usecase.MovieInteractor
import app.liusaprian.core.domain.usecase.MovieUseCase
import app.liusaprian.rawg.detail.DetailViewModel
import app.liusaprian.rawg.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}