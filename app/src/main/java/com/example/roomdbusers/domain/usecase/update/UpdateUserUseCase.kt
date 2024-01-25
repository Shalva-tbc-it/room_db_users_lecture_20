package com.example.roomdbusers.domain.usecase.update

import com.example.roomdbusers.domain.model.GetUsers
import com.example.roomdbusers.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(getUsers: GetUsers) = usersRepository.updateUser(getUsers)

}