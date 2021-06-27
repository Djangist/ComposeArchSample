package ru.apokhilko.composeapparchitecture.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.apokhilko.composeapparchitecture.data.WeatherDataRepository
import ru.apokhilko.composeapparchitecture.data.WeatherDataRepositoryImpl
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractor
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractorImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindRepository(impl: WeatherDataRepositoryImpl): WeatherDataRepository

    @Binds
    abstract fun bindInteractor(impl: WeatherInteractorImpl): WeatherInteractor
}