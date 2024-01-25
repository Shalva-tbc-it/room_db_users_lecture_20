package com.example.roomdbusers.presentation.event.home

sealed class HomeEvent {
    data object GetAllUsers : HomeEvent()
}
