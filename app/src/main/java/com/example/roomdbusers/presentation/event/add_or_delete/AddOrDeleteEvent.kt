package com.example.roomdbusers.presentation.event.add_or_delete

import com.example.roomdbusers.presentation.model.Users

sealed class AddOrDeleteEvent {
    data class AddUser(val users: Users) : AddOrDeleteEvent()
    data class Delete(val email: String) : AddOrDeleteEvent()

}
