package com.example.transactionapp.repository

import androidx.lifecycle.LiveData
import com.example.transactionapp.data.DoctorDao
import com.example.transactionapp.data.DoctorProfile

class DoctorRepository(private val doctorDao: DoctorDao) {
    val allDoctors: LiveData<List<DoctorProfile>> = doctorDao.getAllDoctors()

    suspend fun updateDoctor(doctor: DoctorProfile) {
        doctorDao.updateDoctor(doctor)
    }
}