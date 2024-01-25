package com.example.roomdbusers.di

import com.example.roomdbusers.data.repository.UsersRepositoryImpl
import com.example.roomdbusers.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun provideUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository {
        return usersRepositoryImpl
    }

}