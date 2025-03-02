package com.example.diacare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiabetesRiskScreen() {
    var smoking by remember { mutableStateOf("") }
    var waist by remember { mutableStateOf("") }
    var pregnancies by remember { mutableStateOf("") }
    var activity by remember { mutableStateOf("") }
    var familyHistory by remember { mutableStateOf("") }
    var hdl by remember { mutableStateOf("") }
    var pcos by remember { mutableStateOf("") }
    var riskResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8FAF7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Diabetes Risk Assessment",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C5F59),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Smoking
        DropdownField("Do you smoke?", listOf("Yes", "No"), smoking) { smoking = it }

        // Waist Circumference
        DropdownField(
            "Waist Circumference",
            listOf("< 31.5 inches", "31.5 - 35 inches", "> 35 inches"),
            waist
        ) { waist = it }

        // Pregnancies
        DropdownField(
            "Number of Pregnancies",
            listOf("0", "1", "2", "3", "More than 3"),
            pregnancies
        ) { pregnancies = it }

        // Physical Activity
        DropdownField(
            "Physical Activity Level",
            listOf("Regular exercise & strenuous work", "Regular exercise or strenuous work", "No exercise & sedentary work"),
            activity
        ) { activity = it }

        // Family History
        DropdownField(
            "Family History of Diabetes",
            listOf("No family history", "One parent", "Both parents"),
            familyHistory
        ) { familyHistory = it }

        // HDL
        TextField(
            value = hdl,
            onValueChange = { hdl = it },
            label = { Text("Enter your HDL value") },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PCOS
        DropdownField("Do you have PCOS?", listOf("Yes", "No", "I don't know"), pcos) { pcos = it }

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate Button
        Button(
            onClick = {
                riskResult = calculateDiabetesRisk(smoking, waist, pregnancies, activity, familyHistory, hdl, pcos)
            },
            modifier = Modifier.fillMaxWidth(0.7f).height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E7B74))
        ) {
            Text("Calculate Risk", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Result Display
        if (riskResult.isNotEmpty()) {
            Text(
                text = "Your diabetes risk: $riskResult",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
    }
}

// Utility Composable for Dropdowns
@Composable
fun DropdownField(label: String, options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }

    Column {
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(text = selectedText.ifEmpty { "Select an option" })
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    selectedText = option
                    onOptionSelected(option)
                    expanded = false
                })
            }
        }
    }
}

// Logic for Risk Calculation
fun calculateDiabetesRisk(
    smoking: String, waist: String, pregnancies: String,
    activity: String, familyHistory: String, hdl: String, pcos: String
): String {
    var riskScore = 0

    if (smoking == "Yes") riskScore += 1
    when (waist) {
        "> 35 inches" -> riskScore += 2
        "31.5 - 35 inches" -> riskScore += 1
    }
    when (pregnancies) {
        "More than 3" -> riskScore += 2
        "2", "3" -> riskScore += 1
    }
    when (activity) {
        "No exercise & sedentary work" -> riskScore += 2
        "Regular exercise or strenuous work" -> riskScore += 1
    }
    when (familyHistory) {
        "Both parents" -> riskScore += 2
        "One parent" -> riskScore += 1
    }
    hdl.toDoubleOrNull()?.let {
        if (it < 40) riskScore += 2
        else if (it in 40.0..50.0) riskScore += 1
    }
    if (pcos == "Yes") riskScore += 1

    return when {
        riskScore >= 6 -> "High"
        riskScore in 3..5 -> "Moderate"
        else -> "Low"
    }
}
