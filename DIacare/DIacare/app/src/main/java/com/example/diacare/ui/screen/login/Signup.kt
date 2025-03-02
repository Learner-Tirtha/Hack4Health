package com.example.diacare

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorRegistrationScreen() {
    val genderOptions = listOf("Male", "Female")
    var selectedGender by remember { mutableStateOf<String?>(null) }
    var dob by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val inputFields = listOf("Name", "Experience", "Specialization", "Practice License", "Consultancy Fees", "Payment UPI ID")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF4AB19D), Color(0xFF1E847F))))
            .padding(16.dp,30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE8FAF7))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("DiaCare", style = TextStyle(fontSize = 24.sp, color = Color(0xFF1E7B74)))
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(inputFields) { field ->
                    RegistrationField(label = field)
                }

                // Date of Birth Picker
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Date of Birth:", style = MaterialTheme.typography.labelLarge, color = Color(0xFF333333))
                        Button(
                            onClick = { showDatePicker = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = if (dob.isEmpty()) "Select Date of Birth" else dob)
                        }
                        if (dob.isNotEmpty()) {
                            Text(text = "Age: $age years", fontSize = 16.sp, color = Color.Black)
                        }
                    }
                }

                // Gender Selection
                item {
                    GenderSelection(selectedGender) { selectedGender = it }
                }

                // Continue Button
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { Toast.makeText(context, "Registration Submitted", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E7B74)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Continue", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("OK") }
            }
        ) {
            val datePickerState = rememberDatePickerState()
            DatePicker(state = datePickerState)
            LaunchedEffect(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                    val formattedDate = formatDate(selectedDateMillis)
                    val calculatedAge = calculateAge(selectedDateMillis).toString()
                    dob = formattedDate
                    age = calculatedAge
                    showDatePicker = false
                }
            }
        }
    }
}

@Composable
fun GenderSelection(selectedGender: String?, onGenderSelected: (String) -> Unit) {
    val genderOptions = listOf("Male", "Female")
    Column {
        Text("Gender:", style = MaterialTheme.typography.labelLarge, color = Color(0xFF333333))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            genderOptions.forEach { gender ->
                Button(
                    onClick = { onGenderSelected(gender) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedGender == gender) {
                            if (gender == "Male") Color(0xFF4AB19D) else Color(0xFFE2A23B)
                        } else Color.LightGray
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(gender, color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationField(label: String) {
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "$label:", style = MaterialTheme.typography.labelLarge, color = Color(0xFF333333))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

fun formatDate(timeInMillis: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(timeInMillis))
}

fun calculateAge(timeInMillis: Long?): Int {
    if (timeInMillis == null) return 0
    val dob = Calendar.getInstance().apply { time = Date(timeInMillis) }
    val today = Calendar.getInstance()
    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDoctorRegistrationScreen() {
    DoctorRegistrationScreen()
}
