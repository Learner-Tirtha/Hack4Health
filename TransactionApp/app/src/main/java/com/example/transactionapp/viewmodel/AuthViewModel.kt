package com.example.transactionapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.transactionapp.data.User
import com.example.transactionapp.data.PatientDatabase
import com.example.transactionapp.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val usersByRole: LiveData<List<User>>

    init {
        val userDao = PatientDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        usersByRole = repository.getUsersByRole("Doctor") // Example for doctors; can be changed
    }

    fun signup(email: String, password: String, role: String) = viewModelScope.launch {
        val user = User(email = email, password = password, role = role)
        repository.insertUser(user)
    }

    suspend fun login(email: String, password: String): User? {
        return repository.getUserByEmailAndPassword(email, password)
    }
}