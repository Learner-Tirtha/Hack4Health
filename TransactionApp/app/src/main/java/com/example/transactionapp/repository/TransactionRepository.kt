package com.example.transactionapp.repository

import androidx.lifecycle.LiveData
import com.example.transactionapp.data.Transaction
import com.example.transactionapp.data.TransactionDao

class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }
}