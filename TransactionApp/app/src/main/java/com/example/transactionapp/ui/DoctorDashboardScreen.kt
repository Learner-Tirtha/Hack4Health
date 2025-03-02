package com.example.transactionapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.transactionapp.ui.Header

@Composable
fun DoctorDashboardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Header()

        Spacer(modifier = Modifier.height(8.dp))

        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00CCCC).copy(alpha = 0.2f)) // 50% transparency
                .padding(16.dp)
        ) {
            Text(
                text = "Hi, Dr. Mishra",
                color = Color(0xFF006666),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Content Section
        Column(modifier = Modifier.padding(16.dp)) {
            // Row for "Pinned Cases" & "Patient Stories"
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFB2DFDB), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(150.dp)
                ) {
                    Text(
                        text = "📌 Pinned Cases",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF80CBC4), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(150.dp)
                ) {
                    Text(
                        text = "👥 Patient Stories",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progression Chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4DB6AC), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
                    .height(150.dp)
            ) {
                Text(
                    text = "📈 Progression Chart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cases Status Row
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF00897B), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "📅 Scheduled Cases",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF00796B), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⚡ Ongoing Cases",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF004D40), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✅ Done Cases",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}