package com.example.userrole.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userrole.data.model.User
import com.example.userrole.data.model.UserRole
import com.example.userrole.data.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var loginResult: User? = null
        private set

    fun register(email: String, password: String, role: UserRole) {
        viewModelScope.launch {
            val user = User(email = email, password = password, role = role)
            repository.registerUser(user)
        }
    }

    fun login(email: String, password: String, onComplete: (User?) -> Unit) {
        viewModelScope.launch {
            loginResult = repository.loginUser(email, password)
            onComplete(loginResult)
        }
    }
}