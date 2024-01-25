package com.example.roomdbusers.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdbusers.data.dao.UsersDao
import com.example.roomdbusers.data.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

}