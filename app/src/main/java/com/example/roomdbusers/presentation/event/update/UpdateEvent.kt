package com.example.roomdbusers.presentation.event.update

import com.example.roomdbusers.presentation.model.Users

sealed class UpdateEvent {
    data class Update(val users: Users) : UpdateEvent()
}