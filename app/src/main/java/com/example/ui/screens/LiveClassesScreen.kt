package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveClassesScreen(onBack: () -> Unit) {
  val liveClasses = Repository.sampleLiveClasses

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Live Classes & Interactive Rooms") },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
          }
        }
      )
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(padding),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item {
        Text(text = "Scheduled Live Sessions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
      }

      items(liveClasses) { live ->
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Badge(containerColor = IndigoPrimary.copy(alpha = 0.1f)) {
              Text(live.subject, color = IndigoPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
            Text(text = live.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = "Instructor: ${live.teacher}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "Time: ${live.time}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            Button(
              onClick = { /* Join live session */ },
              colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text("Join Live Interactive Classroom")
            }
          }
        }
      }
    }
  }
}
