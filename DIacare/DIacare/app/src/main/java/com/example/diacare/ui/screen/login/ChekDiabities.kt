package com.example.diacare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DiabetesCheckScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8FAF7)) // Background color
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF4AB19D), Color(0xFF1E847F))
                    )
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "DiaCare",
                style = TextStyle(fontSize = 24.sp, color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Slogan
        Text(
            text = "Every step matters—walk towards better health.",
            color = Color(0xFF1E847F),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Question
        Text(
            text = "Do you Have Diabetes?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Yes & No Buttons
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { /* Handle Yes */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4AB19D))
            ) {
                Text("Yes", color = Color.White)
            }

            Button(
                onClick = { /* Handle No */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE2A23B))
            ) {
                Text("No", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Risk Check Button
        Button(
            onClick = { /* Handle Risk Check */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text("Check the Risk of Diabetes", color = Color.White)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDiabetesCheckScreen() {
    DiabetesCheckScreen()
}
