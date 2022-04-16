package com.cepfinder.cepfinder.core.di.module

import com.cepfinder.cepfinder.core.repository.IRepository
import com.cepfinder.cepfinder.core.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPokemonRepository(repository: Repository): IRepository
}