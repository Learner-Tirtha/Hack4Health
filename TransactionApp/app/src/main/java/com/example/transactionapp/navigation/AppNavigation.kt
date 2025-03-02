package com.example.transactionapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.transactionapp.ui.*
import com.example.transactionapp.viewmodel.AuthViewModel

fun NavGraphBuilder.appNavigation(navController: NavController, authViewModel: AuthViewModel) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("signup") {
            SignupScreen(navController, authViewModel)
        }
    }
    navigation(startDestination = "dashboard", route = "main") {
        composable("dashboard") {
            PatientDashboardScreen(navController)
        }
        composable("doctorList") {
            DoctorListScreen(navController)
        }
        composable("appointment/{doctorId}") { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")?.toInt() ?: 0
            PatientAppointmentScreen(
                doctorId = doctorId,
                navController = navController
            )
        }
        composable("medicationSchedule") {
            PatientMedicationScheduleScreen(navController)
        }
        composable("diabetesCheck") {
            DiabetesCheckScreen(navController)
        }
        composable("doctorRegistration") {
            DoctorRegistrationScreen(navController)
        }
        composable("doctorDashboard") {
            DoctorDashboardScreen(navController)
        }
        composable("adminDashboard") {
            AdminDashboardScreen(navController)
        }
    }
}