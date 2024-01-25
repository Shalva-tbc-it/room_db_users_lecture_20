package com.example.roomdbusers.presentation.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbusers.domain.usecase.add_or_delete.AddOrDeleteUseCaseCollection
import com.example.roomdbusers.presentation.event.add_or_delete.AddOrDeleteEvent
import com.example.roomdbusers.presentation.mapper.toGetUsers
import com.example.roomdbusers.presentation.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrDeleteViewModel @Inject constructor(
    private val useCase: AddOrDeleteUseCaseCollection
) : ViewModel() {

    fun onEvent(event: AddOrDeleteEvent) {
        when (event) {
            is AddOrDeleteEvent.AddUser -> {
                addUser(event.users)
            }
            is AddOrDeleteEvent.Delete -> {
                deleteUser(event.email)
            }
            else -> {

            }
        }
    }

    private fun addUser(users: Users) {
        viewModelScope.launch {
            useCase.addUserUseCase.invoke(users.toGetUsers())
        }
    }

    private fun deleteUser(email: String){
        viewModelScope.launch {
            useCase.deleteUserUseCase.invoke(email = email)
        }
    }

}