package com.example.transactionapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicines WHERE time = :time")
    fun getMedicinesByTime(time: String): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): LiveData<List<Medicine>>
}