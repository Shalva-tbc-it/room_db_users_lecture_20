package com.example.roomdbusers.di

import android.content.Context
import androidx.room.Room
import com.example.roomdbusers.data.dao.UsersDao
import com.example.roomdbusers.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "users_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(appDatabase: AppDatabase) : UsersDao {
        return appDatabase.usersDao()
    }

}