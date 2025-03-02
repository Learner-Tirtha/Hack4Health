package com.example.diacare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.handwriting.handwritingHandler
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotUIScreen() {
    var userInput by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf("Hello! How can I assist you today?") }
    val coroutineScope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color(0xFF1E7B74))
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text("DiaCare") }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF359E8D)))},
        bottomBar = { BottomNavigationBar(userInput, onInputChange = { userInput = it }, onSend = {
            if (userInput.isNotBlank()) {
                messages.add("You: $userInput")
                val userMessage = userInput
                userInput = ""

                coroutineScope.launch {
                    delay(1000) // Simulate processing delay
                    messages.add("Bot: I see you said '$userMessage'. How else can I assist?")
                }
            }
        }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ChatMessages(messages)
        }
    }
}

@Composable
fun ChatMessages(messages: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.radialGradient(listOf(Color(0xFFE8FAF7), Color.White)))
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        items(messages) { message ->
            val isUser = message.startsWith("You:")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
            ) {
                MessageBubble(message, isUser)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MessageBubble(message: String, isUser: Boolean) {
    Box(
        modifier = Modifier
            .background(
                if (isUser) Color.LightGray else Color(0xFF1E7B74),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {if(isUser)
        Text(
            text = message,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        else Text(   text = message,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White )
    }
}

@Composable
fun BottomNavigationBar(userInput: String, onInputChange: (String) -> Unit, onSend: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF359E8D))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = userInput,
            onValueChange = onInputChange,
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFE8FAF7), shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            decorationBox = { innerTextField ->
                if (userInput.isEmpty()) {
                    Text(
                        text = "Let me know",
                        color = Color.Gray // Hint text color
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSend,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8FAF7))
        ) {
            Text("Send", color = Color.Black) // Adjust text color for better contrast
        }
        Spacer(modifier = Modifier.width(8.dp))

    }
}

@Preview
@Composable
fun PreviewChatbotUIScreen() {
    ChatbotUIScreen()
}
