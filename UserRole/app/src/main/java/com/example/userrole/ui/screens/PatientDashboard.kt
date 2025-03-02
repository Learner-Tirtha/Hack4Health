package com.example.userrole.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import com.example.userrole.R

@Composable
fun PatientDashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F2F1))
    ) {
        Column(
            modifier = Modifier.weight(1f), // Ensures content takes up available space
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { BannerSection() }
                item { TextSection() }
                item { FeatureGrid() }
                item { QuoteSection() }
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            PatientBottomNavigationBar()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "DiaCare",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00796B)
        )
//        Icon(
//            painter = painterResource(id = R.drawable.baseline_person_24),
//            contentDescription = "Profile",
//            modifier = Modifier.size(40.dp),
//            tint = Color(0xFF00796B)
//        )
    }
}

@Composable
fun BannerSection() {
//    Image(
//        painter = painterResource(id = R.drawable.applogo),
//        contentDescription = "Banner Image",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp)
//            .clip(RoundedCornerShape(20.dp)),
//        contentScale = ContentScale.Crop
//    )
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
fun FeatureGrid() {
    val featureItems = getFeatureItems().chunked(2)
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
                    FeatureCard(feature.title, feature.iconRes)
                }
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, iconRes: Int) {
    Column(
        modifier = Modifier
            .size(140.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { /* Handle Click */ }
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

@Composable
fun PatientBottomNavigationBar() {
    // Placeholder for bottom navigation - implement as needed
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF00796B))
    ) {
        Text(
            text = "Patient Bottom Nav",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun getFeatureItems(): List<FeatureItem> {
    return listOf(
//        FeatureItem("Upload Image", R.drawable.upldimg),
//        FeatureItem("Book Appointment", R.drawable.book),
//        FeatureItem("Appointment History", R.drawable.history),
//        FeatureItem("Reports Section", R.drawable.report),
//        FeatureItem("Your Appointments", R.drawable.appointment),
//        FeatureItem("Ask AI", R.drawable.generative)
    )
}

data class FeatureItem(val title: String, val iconRes: Int)