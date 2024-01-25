package com.example.roomdbusers.presentation.screen.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbusers.domain.usecase.add_or_delete.GetUserByEmailUseCase
import com.example.roomdbusers.domain.usecase.update.UpdateUserUseCase
import com.example.roomdbusers.presentation.event.update.UpdateEvent
import com.example.roomdbusers.presentation.mapper.toGetUsers
import com.example.roomdbusers.presentation.model.Users
import com.example.roomdbusers.presentation.screen.state.RoomState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase
): ViewModel() {

    private val _roomStateFlow = MutableSharedFlow<RoomState>()
    val roomStateFlow : SharedFlow<RoomState> = _roomStateFlow.asSharedFlow()

    fun onEvent(event: UpdateEvent) {
        when (event) {
            is UpdateEvent.Update -> {
                updateUser(event.users)
            }
        }
    }


    private fun updateUser(users: Users) {
        viewModelScope.launch {
            val user = getUserByEmailUseCase.invoke(users.email).first()

            if (user.isEmpty()) {
                _roomStateFlow.emit(RoomState.Success(message = "User updated"))
                updateUserUseCase.invoke(users.toGetUsers())
            } else {
                _roomStateFlow.emit(RoomState.Error(message = "User already exists"))
            }

        }
    }


}