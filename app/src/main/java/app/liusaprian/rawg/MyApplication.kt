package app.liusaprian.rawg

import android.app.Application
import app.liusaprian.core.di.databaseModule
import app.liusaprian.core.di.networkModule
import app.liusaprian.core.di.repositoryModule
import app.liusaprian.rawg.di.useCaseModule
import app.liusaprian.rawg.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}