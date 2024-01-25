package com.example.roomdbusers.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbusers.domain.usecase.home.GetAllUsersUseCase
import com.example.roomdbusers.presentation.event.home.HomeEvent
import com.example.roomdbusers.presentation.mapper.toUsers
import com.example.roomdbusers.presentation.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
): ViewModel() {

    private val _users = MutableStateFlow<List<Users>>(emptyList())
    val users: StateFlow<List<Users>> get() = _users


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetAllUsers -> getAllUsers()
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            getAllUsersUseCase.invoke().collect { getUsers ->
                _users.update { value ->
                    getUsers.map {
                        it.toUsers()
                    }
                }
            }
        }
    }

}