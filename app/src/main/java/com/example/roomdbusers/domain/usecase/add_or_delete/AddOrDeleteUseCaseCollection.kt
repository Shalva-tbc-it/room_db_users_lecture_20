package com.example.roomdbusers.domain.usecase.add_or_delete

import javax.inject.Inject

data class AddOrDeleteUseCaseCollection @Inject constructor(
    val addUserUseCase: AddUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val getUserByEmailUseCase: GetUserByEmailUseCase
)