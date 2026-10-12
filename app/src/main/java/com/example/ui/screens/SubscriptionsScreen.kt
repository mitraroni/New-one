package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.IndigoPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionsScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Premium Subscriptions & Plans") },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
          }
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(padding)
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Badge(containerColor = IndigoPrimary) {
            Text("MOST POPULAR", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
          }
          Text(text = "Xenturalt Learn Annual Pass", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
          Text(text = "₹4,999 / year (Save 60%)", style = MaterialTheme.typography.titleMedium, color = IndigoPrimary, fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.height(4.dp))
          listOf(
            "Unlimited access to Class 1-12 & JEE/NEET courses",
            "Unlimited AI Mentor doubt resolution",
            "Live interactive classes & doubt clearing",
            "Smart test engine with detailed analytics"
          ).forEach { feature ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.Check, contentDescription = "Included", tint = IndigoPrimary, modifier = Modifier.size(18.dp))
              Text(text = feature, style = MaterialTheme.typography.bodySmall)
            }
          }
          Spacer(modifier = Modifier.height(8.dp))
          Button(
            onClick = { /* Checkout */ },
            colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
          ) {
            Text("Subscribe Securely Now")
          }
        }
      }
    }
  }
}
