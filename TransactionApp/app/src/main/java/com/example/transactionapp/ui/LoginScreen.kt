package com.example.transactionapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.transactionapp.viewmodel.AuthViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Patient") } // Default to Patient
    var isLogin by remember { mutableStateOf(true) } // Toggle between Login and Signup
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isLogin) "Login" else "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Role Selection
        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(role)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Patient", "Doctor", "Admin").forEach { roleOption ->
                    DropdownMenuItem(
                        onClick = {
                            role = roleOption
                            expanded = false
                        },
                        text = { Text(roleOption) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Login/Signup Button
        Button(
            onClick = {
                LaunchedEffect(Unit) {
                    if (isLogin) {
                        // Attempt login
                        val user = authViewModel.login(email, password)
                        if (user != null) {
                            when (user.role) {
                                "Patient" -> navController.navigate("diabetesCheck") { popUpTo("login") { inclusive = true } }
                                "Doctor" -> navController.navigate("doctorDashboard") { popUpTo("login") { inclusive = true } }
                                "Admin" -> navController.navigate("adminDashboard") { popUpTo("login") { inclusive = true } }
                            }
                        } else {
                            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Attempt signup
                        authViewModel.signup(email, password, role)
                        Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(
                            when (role) {
                                "Patient" -> "diabetesCheck"
                                "Doctor" -> "doctorDashboard"
                                "Admin" -> "adminDashboard"
                                else -> "login"
                            }
                        ) { popUpTo("login") { inclusive = true } }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLogin) "Login" else "Sign Up")
        }

        // Toggle between Login and Signup
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { isLogin = !isLogin },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLogin) "Need an account? Sign Up" else "Already have an account? Login")
        }
    }
}