package com.example.transactionapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.transactionapp.data.Medicine
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transactionapp.ui.Header
import com.example.transactionapp.ui.MedicineViewModel
import com.example.transactionapp.ui.PatientBottomNavigationBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientMedicationScheduleScreen(navController: NavController) {
    val viewModel: MedicineViewModel = viewModel()
    var selectedTime by remember { mutableStateOf("Morning") }
    val medicines by viewModel.getMedicinesByTime(selectedTime).observeAsState(emptyList())

    Scaffold(
        topBar = { Header() },
        bottomBar = { PatientBottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFFE0F2F1), shape = RoundedCornerShape(50.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("Morning", "Afternoon", "Evening").forEach { time ->
                    Button(
                        onClick = { selectedTime = time },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTime == time) Color(0xFF008080) else Color.White,
                            contentColor = if (selectedTime == time) Color.White else Color(0xFF008080)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(time)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Medicine", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text("Dose", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(medicines) { medicine ->
                    MedicineItem(medicine = medicine)
                }
            }
        }
    }
}


@Composable
fun MedicineItem(medicine: Medicine) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFA726))
                .padding(8.dp)
        ) {
            Text(medicine.name, color = Color.White, fontSize = 14.sp)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(medicine.dose, color = Color.Black, fontSize = 14.sp)
        }
    }
}