package com.example.transactionapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.transactionapp.R
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDashboardScreen(navController: NavController) {
    val doctorViewModel: DoctorViewModel = viewModel()
    val medicineViewModel: MedicineViewModel = viewModel()
    val transactionViewModel: TransactionViewModel = viewModel()

    Scaffold(
        topBar = { Header() },
        bottomBar = { PatientBottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0F2F1))
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { BannerSection() }
                item { TextSection() }
                item { FeatureGrid(navController) }
                item { QuoteSection() }
            }
        }
    }
}

// ... (rest of the file remains unchanged)

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF4AB19D), Color(0xFF1E847F))
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF4AB19D), Color(0xFF1E847F))
                    )
                )
                .padding(vertical = 0.dp, horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DiaCare",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(50.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BannerSection() {
    Image(
        painter = painterResource(id = R.drawable.applogo),
        contentDescription = "Banner Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextSection() {
    Text(
        text = "Every step matters—walk towards better health.",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF00796B),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun FeatureGrid(navController: NavController) {
    val featureItems = getFeatureItems().chunked(2) // Splits into rows of 2
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        featureItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { feature ->
                    FeatureCard(feature.title, feature.iconRes, navController)
                }
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, iconRes: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .size(140.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable {
                when (title) {
                    "Book Appointment" -> navController.navigate("doctorList")
                    "Prescriptions" -> navController.navigate("medicationSchedule")
                    else -> {} // Handle other features later if needed
                }
            }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF00796B),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun QuoteSection() {
    Text(
        text = "SMALL STEPS, BIG CHANGES",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )
}

fun getFeatureItems(): List<FeatureItem> {
    return listOf(
        FeatureItem("Upload Image", R.drawable.upldimg),
        FeatureItem("Book Appointment", R.drawable.book),
        FeatureItem("Appointment History", R.drawable.history),
        FeatureItem("Prescriptions", R.drawable.prescription), // Renamed for clarity
        FeatureItem("Your Appointments", R.drawable.appointment),
        FeatureItem("Ask AI", R.drawable.generative)
    )
}

data class FeatureItem(val title: String, val iconRes: Int)

@Composable
fun PatientBottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    val navItems = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("Prescriptions", R.drawable.prescription),
        NavItem("Book Appointment", R.drawable.schedule)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color(0xFF66CCCC)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .clip(RoundedCornerShape(20.dp)),
            backgroundColor = Color(0xFF66CCCC)
        ) {
            navItems.forEachIndexed { index, item ->
                BottomNavItem(
                    icon = item.iconRes,
                    label = item.title,
                    isSelected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        when (item.title) {
                            "Home" -> navController.navigate("dashboard") { popUpTo("dashboard") { inclusive = true } }
                            "Prescriptions" -> navController.navigate("medicationSchedule")
                            "Book Appointment" -> navController.navigate("doctorList")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp, 0.dp, 10.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(30.dp)
            )
        }

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color(0xFF008080) else Color.Gray
        )

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(3.dp)
                    .background(Color(0xFF008080))
            )
        }
    }
}