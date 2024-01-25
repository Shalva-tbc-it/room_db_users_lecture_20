package com.example.roomdbusers.presentation.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbusers.domain.usecase.add_or_delete.AddOrDeleteUseCaseCollection
import com.example.roomdbusers.presentation.event.add_or_delete.AddOrDeleteEvent
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
class AddOrDeleteViewModel @Inject constructor(
    private val useCase: AddOrDeleteUseCaseCollection,
) : ViewModel() {

    private val _roomStateFlow = MutableSharedFlow<RoomState>()
    val roomStateFlow : SharedFlow<RoomState> = _roomStateFlow.asSharedFlow()


    fun onEvent(event: AddOrDeleteEvent) {
        when (event) {
            is AddOrDeleteEvent.AddUser -> {
                addUser(event.users)
            }

            is AddOrDeleteEvent.Delete -> {
                deleteUser(event.email)
            }

        }
    }

    private fun addUser(users: Users) {
        viewModelScope.launch {
            val user = useCase.getUserByEmailUseCase(users.email).first()
            if (user.isNotEmpty()) {
                _roomStateFlow.emit(RoomState.Error(message = "User already exists"))
            }
            else {
                useCase.addUserUseCase(users.toGetUsers())
                _roomStateFlow.emit(RoomState.Success(message = "User added successfully"))
            }
        }
    }

    private fun deleteUser(email: String) {
        viewModelScope.launch {
            val user = useCase.getUserByEmailUseCase(email).first()
            if (user.isEmpty()) {
                _roomStateFlow.emit(RoomState.Error(message = "User does not exits"))
            }
            else {
                useCase.deleteUserUseCase.invoke(email = email)
                _roomStateFlow.emit(RoomState.Success(message = "User deleted successfully"))
            }

        }
    }

}