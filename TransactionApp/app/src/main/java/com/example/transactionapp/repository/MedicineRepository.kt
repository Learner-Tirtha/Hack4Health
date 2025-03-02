package com.example.transactionapp.repository

import androidx.lifecycle.LiveData
import com.example.transactionapp.data.Medicine
import com.example.transactionapp.data.MedicineDao

class MedicineRepository(private val medicineDao: MedicineDao) {
    fun getMedicinesByTime(time: String): LiveData<List<Medicine>> = medicineDao.getMedicinesByTime(time)
    val allMedicines: LiveData<List<Medicine>> = medicineDao.getAllMedicines()

    suspend fun insertMedicine(medicine:Medicine) {
        medicineDao.insertMedicine(medicine)
    }
}