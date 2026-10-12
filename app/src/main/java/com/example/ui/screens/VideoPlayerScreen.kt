package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.IndigoPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(lessonId: String, onBack: () -> Unit) {
  var isPlaying by remember { mutableStateOf(true) }
  var playbackSpeed by remember { mutableStateOf("1.0x") }
  var studentNotes by remember { mutableStateOf("Note: Formula verified for quadratic roots.") }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Video Learning Player") },
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
    ) {
      // Simulated Video Player Box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(240.dp)
          .background(Color.Black),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
          IconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = Modifier
              .size(64.dp)
              .background(IndigoPrimary, shape = RoundedCornerShape(32.dp))
          ) {
            Icon(
              imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
              contentDescription = "Play/Pause",
              tint = Color.White,
              modifier = Modifier.size(36.dp)
            )
          }
          Text(
            text = if (isPlaying) "Playing Lesson Audio & Video..." else "Paused",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }

      // Player Controls & Details
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Euclid's Division Lemma & HCF", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
          OutlinedButton(onClick = {
            playbackSpeed = if (playbackSpeed == "1.0x") "1.5x" else if (playbackSpeed == "1.5x") "2.0x" else "1.0x"
          }) {
            Text(playbackSpeed)
          }
        }

        LinearProgressIndicator(
          progress = { 0.45f },
          modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp)),
          color = IndigoPrimary
        )

        // Notes Tab / Section
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Personal Timestamped Notes", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            OutlinedTextField(
              value = studentNotes,
              onValueChange = { studentNotes = it },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(12.dp)
            )
            Button(
              onClick = { /* Save notes */ },
              colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary)
            ) {
              Text("Save Note")
            }
          }
        }
      }
    }
  }
}
