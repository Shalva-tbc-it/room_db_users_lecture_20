package com.example.roomdbusers.domain.usecase.add_or_delete

import com.example.roomdbusers.domain.repository.UsersRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(email: String) {
        return usersRepository.deleteUsersByEmail(email = email)
    }
}