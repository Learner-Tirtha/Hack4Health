package com.example.userrole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.userrole.data.repository.UserRepository
import com.example.userrole.ui.navigation.AppNavigation
import com.example.userrole.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext) // Use singleton
        val userDao = database.userDao()
        val repository = UserRepository(userDao)
        val viewModel = AuthViewModel(repository)

        setContent {
            AppNavigation(viewModel)
        }
    }
    }
