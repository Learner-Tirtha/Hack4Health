package com.example.transactionapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.transactionapp.data.DoctorProfile
import com.example.transactionapp.data.PatientDatabase
import com.example.transactionapp.repository.DoctorRepository
import kotlinx.coroutines.launch

class DoctorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DoctorRepository
    val allDoctors: LiveData<List<DoctorProfile>>

    init {
        val doctorDao = PatientDatabase.getDatabase(application).doctorDao()
        repository = DoctorRepository(doctorDao)
        allDoctors = repository.allDoctors
    }

    fun updateDoctorRating(doctor: DoctorProfile) = viewModelScope.launch {
        repository.updateDoctor(doctor)
    }
}