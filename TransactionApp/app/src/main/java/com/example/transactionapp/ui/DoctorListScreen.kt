package com.example.transactionapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transactionapp.data.DoctorProfile

@Composable
fun DoctorListScreen(navController: NavController) {
    val viewModel: DoctorViewModel = viewModel()
    val doctors by viewModel.allDoctors.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            item {
                Text(
                    "Available Doctors",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = { navController.navigate("medicationSchedule") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008080))
                ) {
                    Text("View Medication Schedule", color = Color.White)
                }
            }
            item { ToggleButtons() }
            items(doctors) { doctor ->
                DoctorCard(
                    doctor = doctor,
                    onClick = { navController.navigate("appointment/${doctor.id}") },
                    onRate = { updatedDoctor -> viewModel.updateDoctorRating(updatedDoctor) }
                )
            }
        }
    }
}

// ... (rest of the file remains unchanged)

@Composable
fun ToggleButtons() {
    var selectedRole by remember { mutableStateOf("In-Clinic") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(50.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        listOf("In-Clinic", "Video Call").forEach { role ->
            Button(
                onClick = { selectedRole = role },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedRole == role) Color(0xFF1E7B74) else Color.White,
                    contentColor = if (selectedRole == role) Color.White else Color(0xFF1E7B74)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(role)
            }
        }
    }
}

@Composable
fun DoctorCard(
    doctor: DoctorProfile,
    onClick: () -> Unit,
    onRate: (DoctorProfile) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Dr. ${doctor.name}", fontWeight = FontWeight.Bold)
                    Text(text = "Experience: ${doctor.experience} years")
                    Text(text = "Specialization: ${doctor.specialization}")
                    Text(text = "Fees: ₹${doctor.consultancyFees}", color = Color(0xFF008080), fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RatingButton(
                    icon = Icons.Default.Call,
                    count = doctor.happyCount,
                    onClick = { onRate(doctor.copy(happyCount = doctor.happyCount + 1)) },
                    contentDescription = "Happy"
                )
                RatingButton(
                    icon = Icons.Default.Add,
                    count = doctor.neutralCount,
                    onClick = { onRate(doctor.copy(neutralCount = doctor.neutralCount + 1)) },
                    contentDescription = "Neutral"
                )
                RatingButton(
                    icon = Icons.Default.Check,
                    count = doctor.sadCount,
                    onClick = { onRate(doctor.copy(sadCount = doctor.sadCount + 1)) },
                    contentDescription = "Sad"
                )
            }
        }
    }
}

@Composable
fun RatingButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: Int,
    onClick: () -> Unit,
    contentDescription: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color(0xFF1E7B74)
            )
        }
        Text(text = "$count", fontSize = 14.sp)
    }
}