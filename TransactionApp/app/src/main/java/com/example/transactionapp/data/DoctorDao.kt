package com.example.transactionapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor_profile ORDER BY happyCount DESC, experience DESC")
    fun getAllDoctors(): LiveData<List<DoctorProfile>>

    @Update
    suspend fun updateDoctor(doctor: DoctorProfile)
}