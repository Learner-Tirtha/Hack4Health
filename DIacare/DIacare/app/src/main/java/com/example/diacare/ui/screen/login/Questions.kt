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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun DiabetesQuestionnaireScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8FAF7))
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { QuestionWithYesNo("Do you have Smoking Habit?") }

            item {
                QuestionWithMultipleChoices(
                    "Your waist circumference",
                    listOf("Less than 31.5 inches", "31.5 inches - 35.0 inches", "More than 35.0 inches")
                )
            }

            item {
                QuestionWithMultipleChoices(
                    "How many Pregnancies do you have?",
                    listOf("0", "1", "2", "3", "More Than 3")
                )
            }

            item {
                QuestionWithMultipleChoices(
                    "Physical Activity Level",
                    listOf(
                        "Regular Exercise AND strenuous work",
                        "Regular Exercise OR strenuous work",
                        "No exercise AND sedentary work"
                    )
                )
            }

            item {
                QuestionWithMultipleChoices(
                    "Do you have a Family History of Diabetes?",
                    listOf("No family history of diabetes", "Either parent has or had diabetes", "Both parents have or had diabetes")
                )
            }

            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "What is your recent HDL value in mg/dl? (Value should not be older than 1 year)",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "HDL should be in the range of 20-150",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Button(
                        onClick = { /* Skip HDL Input */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text("Skip", color = Color.Black)
                    }
                }
            }

            item {
                QuestionWithYesNo("Have you ever been diagnosed with PCOS (Polycystic Ovary Syndrome)?")
            }

            item {
                Button(
                    onClick = { /* Handle risk assessment */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9534F)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Check the Risk of Diabetes", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun QuestionWithYesNo(question: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question, fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { /* Handle Yes */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
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
    }
}

@Composable
fun QuestionWithMultipleChoices(question: String, choices: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question, fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        choices.forEach { choice ->
            Button(
                onClick = { /* Handle selection */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4AB19D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(choice, color = Color.White)
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDiabetesQuestionnaireScreen() {
    DiabetesQuestionnaireScreen()
}
