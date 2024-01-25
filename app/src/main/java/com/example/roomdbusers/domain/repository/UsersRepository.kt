package com.example.roomdbusers.domain.repository

import com.example.roomdbusers.domain.model.GetUsers
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun addUsers(getUsers: GetUsers)

    fun getAllUsers(): Flow<List<GetUsers>>

    suspend fun deleteUsersByEmail(email: String)

    suspend fun updateUser(getUsers: GetUsers)

}