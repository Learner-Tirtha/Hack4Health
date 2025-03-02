package com.example.diacare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun DoctorListScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item { TopBar() }
            item { ToggleButtons() }
            items(15) { DoctorCard() } // Replace with dynamic list
        }
//        BottomNavigationBar()
    }
}


@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.horizontalGradient(colors = listOf(Color(0xFF008080), Color(0xFF20B2AA))))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "DiaCare", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Image(
////            imageVector = , // Replace with actual logo
//            contentDescription = "Logo",
//            modifier = Modifier.size(40.dp)
//        )
    }
}

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
fun DoctorList() {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(5) { // Replace with dynamic list
            DoctorCard()
        }
    }
}

@Composable
fun DoctorCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Name:", fontWeight = FontWeight.Bold)
                Text(text = "Experience:")
                Text(text = "Specialization:")
                Text(text = "Treated Patient:")
                Text(text = "Consult fees:")
            }
        }
    }
}

//@Composable
//fun BottomNavigationBar() {
//    BottomAppBar(
//        containerColor = Color(0xFF20B2AA)
//
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        )
//        {
//            IconButton(onClick = { /* Handle Appointments */ }) {
//                Icon(Icons.Default.Menu, contentDescription = "Appointments", tint = Color.White)
//            }
//            IconButton(onClick = { /* Handle Home */ }) {
//                Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
//            }
//            IconButton(onClick = { /* Handle Consultations */ }) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Consultations", tint = Color.White)
//            }
//
//        }
//    }
//}
