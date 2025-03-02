package com.example.userrole.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserRole {
    PATIENT, DOCTOR, ADMIN
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String,
    val role: UserRole
)