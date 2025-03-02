package com.example.userrole.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.userrole.LoginScreen
import com.example.userrole.ui.screens.SignupScreen
import com.example.userrole.data.model.UserRole
import com.example.userrole.ui.screens.*
import com.example.userrole.viewmodel.AuthViewModel

@Composable
fun AppNavigation(viewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel,
                onLoginSuccess = { role ->
                    when (role) {
                        UserRole.PATIENT -> navController.navigate("patient_dashboard")
                        UserRole.DOCTOR -> navController.navigate("doctor_dashboard")
                        UserRole.ADMIN -> navController.navigate("admin_dashboard")
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(viewModel) {
                navController.navigate("")
            }
        }

        composable("patient_dashboard") { PatientDashboardScreen() }
        composable("doctor_dashboard") { DoctorDashboardScreen() }
        composable("admin_dashboard") { AdminDashboardScreen() }
    }
}