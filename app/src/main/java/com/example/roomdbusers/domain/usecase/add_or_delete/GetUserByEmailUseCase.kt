package com.example.roomdbusers.domain.usecase.add_or_delete

import com.example.roomdbusers.domain.model.GetUsers
import com.example.roomdbusers.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(
    private val usersRepository: UsersRepository
){
    suspend operator fun invoke(email: String): Flow<List<GetUsers>> {
        return usersRepository.getUserByEmail(email = email)
    }
}