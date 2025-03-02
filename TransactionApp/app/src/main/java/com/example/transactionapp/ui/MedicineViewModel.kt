package com.example.transactionapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.transactionapp.data.Medicine
import com.example.transactionapp.data.PatientDatabase
import com.example.transactionapp.repository.MedicineRepository
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MedicineRepository
    val allMedicines: LiveData<List<Medicine>>

    init {
        val medicineDao = PatientDatabase.getDatabase(application).medicineDao()
        repository = MedicineRepository(medicineDao)
        allMedicines = repository.allMedicines
    }

    fun getMedicinesByTime(time: String): LiveData<List<Medicine>> = repository.getMedicinesByTime(time)

    fun insertMedicine(medicine: Medicine) = viewModelScope.launch {
        repository.insertMedicine(medicine)
    }
}