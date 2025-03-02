package com.example.diacare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diacare.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showSystemUi = true)
@Composable
fun PatientDashboard() {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color(0xFF1E7B74))
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFFAF5))
                .padding(16.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "DiaCare",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF005F56)
                )
                IconButton(onClick = { /* Profile Icon Clicked */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24), // Replace with actual drawable
                        contentDescription = "Profile",
                        tint = Color(0xFF005F56)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Main Card
            Card(
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(0xFFFAE6E6),
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .fillMaxSize(),// Adjusted for a balanced layout
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ulcer), // Ensure this exists in drawable
                            contentDescription = "Health Image",
                            modifier = Modifier
                                .size(200.dp) // Adjust size for better proportion
                                .padding(end = 12.dp)
                        )

                        Text(
                            text = "Every step matters—walk towards better health.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1ECCB0),
                            modifier = Modifier.weight(1f) // Allows text to take remaining space
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionCard("Upload Image", R.drawable.upldimg)
                ActionCard("Book Appointment", R.drawable.upldimg)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionCard("Report", R.drawable.report)
                ActionCard("History", R.drawable.history)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // AI Assistant Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
//            Button(
//                onClick = { /* AI Assistant Clicked */ },
//                shape = CircleShape,
//                colors = ButtonDefaults.buttonColors(Color.Green)
//            ) {
//                Text(
//                    text = "AI",
//                    fontSize = 16.sp,
//                    color = Color.White,
//                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//                )
//            }
            }

            Spacer(modifier = Modifier.height(32.dp))

//         Bottom Navigation
//        BottomNavigation(
//            backgroundColor = Color(0xFF005F56),
//            contentColor = Color.White)
//        ) {
//            BottomNavigationItem(
//                selected = true,
//                onClick = { /* Home Clicked */ },
//                icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
//                label = { Text("Home") }
//            )
//            BottomNavigationItem(
//                selected = false,
//                onClick = { /* Appointments Clicked */ },
//                icon = { Icon(painterResource(id = R.drawable.ic_calendar), contentDescription = "Appointments") },
//                label = { Text("Appointments") }
//            )
//            BottomNavigationItem(
//                selected = false,
//                onClick = { /* Profile Clicked */ },
//                icon = { Icon(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Profile") },
//                label = { Text("Profile") }
//            )
//        }
        }
}

@Composable
fun BottomNavigation(backgroundColor: Color, contentColor: Color, content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun ActionCard(title: String, iconRes: Int) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color(0xFF1ECCB0),
        elevation = 4.dp,
        modifier = Modifier
            .size(120.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Color(0xFF237265),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF1E6156),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
