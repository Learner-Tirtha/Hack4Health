package com.example.patientcareapp.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.patientcareapp.R
import com.example.patientcareapp.data.DoctorProfile
import com.example.patientcareapp.data.Transaction
import java.util.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transactionapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientAppointmentScreen(doctorId: Int, navController: NavController) {
    val doctorViewModel: DoctorViewModel = viewModel()
    val transactionViewModel: TransactionViewModel = viewModel()
    val doctor by remember { derivedStateOf { doctorViewModel.allDoctors.value?.find { it.id == doctorId } } }
    var showDialog by remember { mutableStateOf(false) }
    var selectedTimeSlot by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    if (doctor == null) {
        navController.popBackStack()
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Appointment with Dr. ${doctor!!.name}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Doctor Profile",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Dr. ${doctor!!.name}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(doctor!!.specialization, fontSize = 18.sp, color = Color.Gray)
                Text("Experience: ${doctor!!.experience} Years", fontSize = 16.sp)
                Text(
                    "Consult Fee: ₹${doctor!!.consultancyFees}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF008080)
                )
            }

            Text(
                text = "Availability:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            val timeSlots = listOf(
                "6:30 PM" to Color(0xFF686464),
                "7:00 PM" to Color(0xFF388E3C),
                "7:30 PM" to Color(0xFF686464),
                "8:00 PM" to Color(0xFF388E3C),
                "8:30 PM" to Color(0xFF686464),
                "9:00 PM" to Color(0xFF388E3C)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(8.dp)
            ) {
                items(timeSlots) { (time, color) ->
                    TimeSlotButton(
                        time = time,
                        color = color,
                        isSelected = selectedTimeSlot == time,
                        onClick = { selectedTimeSlot = time }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                FloatingActionButton(
                    onClick = { if (selectedTimeSlot != null) showDialog = true },
                    containerColor = Color(0xFF008080),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Book Now", tint = Color.White)
                }
            }

            if (showDialog) {
                PaymentDialog(
                    doctor = doctor!!,
                    timeSlot = selectedTimeSlot!!,
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        val upiUri = Uri.parse("upi://pay").buildUpon()
                            .appendQueryParameter("pa", doctor!!.upiId)
                            .appendQueryParameter("pn", doctor!!.name)
                            .appendQueryParameter("am", doctor!!.consultancyFees.toString())
                            .appendQueryParameter("cu", "INR")
                            .build()

                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = upiUri
                        }
                        context.startActivity(intent)

                        transactionViewModel.insert(
                            Transaction(
                                doctorId = doctorId,
                                amount = doctor!!.consultancyFees,
                                timeSlot = selectedTimeSlot!!,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun TimeSlotButton(time: String, color: Color, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF008080) else color
        ),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(time, color = Color.White)
    }
}

@Composable
fun PaymentDialog(
    doctor: DoctorProfile,
    timeSlot: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Appointment Payment") },
        text = {
            Column {
                Text("Doctor: Dr. ${doctor.name}")
                Text("Time: $timeSlot")
                Text("Amount to Pay: ₹${doctor.consultancyFees}", fontWeight = FontWeight.Bold, color = Color(0xFF008080))
                Text("UPI ID: ${doctor.upiId}")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Cancellation Policy:", fontWeight = FontWeight.Bold)
                Text("• >24h: Full refund")
                Text("• <24h: 50% refund")
                Text("• <2h/No-show: No refund")
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008080))
            ) {
                Text("Pay ₹${doctor.consultancyFees}")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancel")
            }
        }
    )
}