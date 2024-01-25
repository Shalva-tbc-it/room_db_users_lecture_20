package com.example.roomdbusers.presentation.screen.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbusers.domain.usecase.update.UpdateUserUseCase
import com.example.roomdbusers.presentation.event.update.UpdateEvent
import com.example.roomdbusers.presentation.mapper.toGetUsers
import com.example.roomdbusers.presentation.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase
): ViewModel() {

    fun onEvent(event: UpdateEvent) {
        when (event) {
            is UpdateEvent.Update -> {
                updateUser(event.users)
            }
        }
    }

    private fun updateUser(users: Users) {
        viewModelScope.launch {
            updateUserUseCase.invoke(users.toGetUsers())
        }
    }

}