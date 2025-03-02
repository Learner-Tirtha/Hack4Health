package com.example.transactionapp.repository

import com.example.transactionapp.data.User
import com.example.transactionapp.data.UserDao
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun getUserByEmailAndPassword(email: String, password: String): User? = userDao.getUserByEmailAndPassword(email, password)
    fun getUsersByRole(role: String): LiveData<List<User>> = userDao.getUsersByRole(role)
}