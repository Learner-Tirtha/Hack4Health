package com.example.transactionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val doctorId: Int,          // Links to DoctorProfile
    val amount: Float,          // Consultancy fees paid
    val timeSlot: String,       // Selected appointment time
    val timestamp: Long         // When the payment was made
)