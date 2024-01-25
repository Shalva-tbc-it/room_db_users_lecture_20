package com.example.roomdbusers.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdbusers.data.entity.UsersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(usersEntity: UsersEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UsersEntity>>

    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUserByEmail(email: String)

    @Update
    suspend fun update(user: UsersEntity)

}