package com.example.roomdbusers.domain.usecase.home

import com.example.roomdbusers.domain.repository.UsersRepository
import javax.inject.Inject


class GetAllUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke() = usersRepository.getAllUsers()
}