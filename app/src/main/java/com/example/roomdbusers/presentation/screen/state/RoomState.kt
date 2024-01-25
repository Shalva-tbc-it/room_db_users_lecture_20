package com.example.roomdbusers.presentation.screen.state

sealed class RoomState {
    data class Success(val message: String) : RoomState()
    data class Error(val message: String) : RoomState()
}
