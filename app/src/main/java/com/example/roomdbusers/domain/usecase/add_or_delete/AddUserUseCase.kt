package com.example.roomdbusers.domain.usecase.add_or_delete

import com.example.roomdbusers.domain.model.GetUsers
import com.example.roomdbusers.domain.repository.UsersRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
)  {

    suspend operator fun invoke(getUsers: GetUsers) {
        usersRepository.addUsers(getUsers = getUsers)
    }

}