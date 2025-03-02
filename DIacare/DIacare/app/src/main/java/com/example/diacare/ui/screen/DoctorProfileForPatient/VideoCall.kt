package com.example.diacare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiaCareScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Header
        Header()

        Spacer(modifier = Modifier.height(10.dp))

        // Patient Profile Section
        ProfileSection()

        Spacer(modifier = Modifier.height(10.dp))

        // Patient Details
        PatientDetails()

        Spacer(modifier = Modifier.height(15.dp))

        // Buttons: Patient History & View Report
        ActionButtons()

        Spacer(modifier = Modifier.height(15.dp))

        // Video Call Button
        VideoCallButton()

        Spacer(modifier = Modifier.height(15.dp))

        // Bottom Navigation Bar
//        BottomNavigationBar()
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF4AB19D), Color(0xFF1E847F))
                )
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "DiaCare",
            modifier = Modifier.padding(0.dp,8.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004D40)
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_person_24), // Replace with your logo
            contentDescription = "App Logo",
            modifier = Modifier.size(50.dp).padding(0.dp,8.dp)
        )
    }
}

@Composable
fun ProfileSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            // Profile Image Placeholder
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "8:00 PM",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
fun PatientDetails() {
    Column(modifier = Modifier.padding(16.dp) ) {
        DetailText(label = "Name:", value = "Patient Name")
        DetailText(label = "Age:", value = "35")
        DetailText(label = "Gender:", value = "Female")
        DetailText(label = "Weight:", value = "60 kg")
        DetailText(label = "Height:", value = "155 cm")
        DetailText(label = "Diabetes type:", value = "Type 1")
        DetailText(label = "Smoking habit:", value = "NO")
        DetailText(label = "PCOS status:", value = "YES", color = Color.Red)
//        DetailText(label = "Waist circumference:", value = "More than 35.0 Inches")
        DetailText(label = "Physical Activity level:", value = "Regular Exercise OR strenuous work")
        DetailText(label = "Family History of Diabetes:", value = "No family history of diabetes")
        DetailText(label = "HDL Value:", value = "200 mg/dL", color = Color.Blue)
        DetailText(label = "No. of Pregnancy:", value = "2")
    }
}

@Composable
fun DetailText(label: String, value: String, color: Color = Color.Black) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, fontSize = 20.sp, color = color)
    }
}

@Composable
fun ActionButtons() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
//        Button(
//            onClick = { /* TODO: Navigate to Patient History */ },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA726))
//        ) {
//            Text(text = "Patient History", color = Color.White)
//        }

//        Button(
//            onClick = { /* TODO: Open Report */ },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6D4C41))
//        ) {
//            Text(text = "View report", color = Color.White)
//        }
    }
}

@Composable
fun VideoCallButton() {
    Button(
        onClick = { /* TODO: Start Video Call */ },
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF81C784))
    ) {
        Text(text = "Start Video Call", fontSize = 18.sp, color = Color.White)
    }
}

//@Composable
//fun BottomNavigationBar() {
//    BottomAppBar(backgroundColor = Color(0xFF00796B)) {
//        Spacer(modifier = Modifier.weight(1f))
//        IconButton(onClick = { /* TODO: Home */ }) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_home), // Replace with your home icon
//                contentDescription = "Home",
//                tint = Color.White
//            )
//        }
//        Spacer(modifier = Modifier.weight(1f))
//        IconButton(onClick = { /* TODO: Reports */ }) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_report), // Replace with your report icon
//                contentDescription = "Reports",
//                tint = Color.White
//            )
//        }
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}

@Preview(showBackground = true)
@Composable
fun PreviewDiaCareScreen() {
    DiaCareScreen()
}
