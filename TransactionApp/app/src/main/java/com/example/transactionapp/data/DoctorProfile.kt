package com.example.transactionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Gender { MALE, FEMALE, OTHER }

@Entity(tableName = "doctor_profile")
data class DoctorProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val gender: Gender,
    val experience: Int,
    val specialization: String,
    val practiceLicense: String,
    val consultancyFees: Float,
    val upiId: String,
    val happyCount: Int = 0,    // Number of Happy ratings
     val neutralCount: Int = 0,  // Number of Neutral ratings
    val sadCount: Int = 0       // Number of Sad ratings
)