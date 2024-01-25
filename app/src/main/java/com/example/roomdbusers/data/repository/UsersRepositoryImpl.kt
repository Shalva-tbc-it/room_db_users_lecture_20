package com.example.roomdbusers.data.repository

import com.example.roomdbusers.data.dao.UsersDao
import com.example.roomdbusers.data.mapper.toDomain
import com.example.roomdbusers.data.mapper.toUsersEntity
import com.example.roomdbusers.domain.model.GetUsers
import com.example.roomdbusers.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
) : UsersRepository {
    override suspend fun addUsers(getUsers: GetUsers) {
        usersDao.addUser(getUsers.toUsersEntity())
    }

    override fun getAllUsers(): Flow<List<GetUsers>> {
        return usersDao.getAllUsers().map { usersEntity ->
            usersEntity.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getUserByEmail(email: String): Flow<List<GetUsers>> {
        return usersDao.getUserByEmail(email).map {
            it.map { usersEntity ->
                usersEntity.toDomain()
            }
        }
    }

    override suspend fun deleteUsersByEmail(email: String) {
        usersDao.deleteUserByEmail(email = email)
    }

    override suspend fun updateUser(getUsers: GetUsers) {
        usersDao.update(getUsers.toUsersEntity())
    }

}