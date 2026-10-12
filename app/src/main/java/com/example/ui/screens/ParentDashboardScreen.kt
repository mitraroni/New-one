package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Repository
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentDashboardScreen(onBack: () -> Unit) {
  val user by Repository.currentUser.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Parent Portal & Guardian Dashboard") },
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
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(text = "Linked Student Profile", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Text(text = user.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = IndigoPrimary)
          Text(text = user.className, style = MaterialTheme.typography.bodyMedium)
          Divider(modifier = Modifier.padding(vertical = 8.dp))
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Learning Streak:", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${user.streakDays} Days Active", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = SuccessGreen)
          }
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Subscription Status:", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Active (Annual Plan)", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = IndigoPrimary)
          }
        }
      }
    }
  }
}
