package com.example.diacare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun Patient_SignUp() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<String?>(null) }
    var selectedDiabetesType by remember { mutableStateOf<String?>(null) }
    var sugarLevel by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female")
    val diabetesTypes = listOf("Type 1", "Type 2")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8FAF7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "DiaCare",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E7B74),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Input Fields
            items(listOf("Name", "Age", "Weight", "Height")) { field ->
                OutlinedTextField(
                    value = when (field) {
                        "Name" -> name
                        "Age" -> age
                        "Weight" -> weight
                        "Height" -> height
                        else -> ""
                    },
                    onValueChange = { value ->
                        when (field) {
                            "Name" -> name = value
                            "Age" -> age = value
                            "Weight" -> weight = value
                            "Height" -> height = value
                        }
                    },
                    label = { Text(field) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Gender Selection
            item {
                Text(
                    text = "Gender:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    genderOptions.forEach { gender ->
                        Button(
                            onClick = { selectedGender = gender },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedGender == gender) Color(0xFF4AB19D) else Color.LightGray
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(gender, color = Color.White)
                        }
                    }
                }
            }

            // Diabetes Type Selection
            item {
                Text(
                    text = "Diabetes type",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    diabetesTypes.forEach { type ->
                        Button(
                            onClick = { selectedDiabetesType = type },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedDiabetesType == type) Color(0xFFE2A23B) else Color.LightGray
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(type, color = Color.White)
                        }
                    }
                }
            }

            // Sugar Level Input
            item {
                Text(
                    text = "What is your recent sugar level in mg/dl?\n(Value should not be older than 1 year)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = sugarLevel,
                    onValueChange = { sugarLevel = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Enter Sugar Level") }
                )
            }

            // Continue Button
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Handle Continue Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4AB19D)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Continue", color = Color.White)
                }
            }
        }
    }
}

