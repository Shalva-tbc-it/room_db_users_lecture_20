package com.example.roomdbusers.presentation.mapper

import com.example.roomdbusers.domain.model.GetUsers
import com.example.roomdbusers.presentation.model.Users


fun Users.toGetUsers() =
    GetUsers(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )

fun GetUsers.toUsers() =
    Users(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )