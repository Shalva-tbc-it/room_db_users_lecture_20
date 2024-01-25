package com.example.roomdbusers.data.mapper

import com.example.roomdbusers.data.entity.UsersEntity
import com.example.roomdbusers.domain.model.GetUsers

fun UsersEntity.toDomain() = GetUsers(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email,

    )

fun GetUsers.toUsersEntity() = UsersEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email

)