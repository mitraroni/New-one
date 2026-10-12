package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Repository
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(courseId: String, onBack: () -> Unit, onStartLesson: (String) -> Unit) {
  val course = Repository.sampleCourses.find { it.id == courseId } ?: Repository.sampleCourses[0]
  val chapters = Repository.sampleChapters

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(course.title, maxLines = 1) },
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
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Badge(containerColor = IndigoPrimary.copy(alpha = 0.1f)) {
              Text(course.subject, color = IndigoPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
            Text(text = course.subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
              Text(text = "Instructor: ${course.instructor}", style = MaterialTheme.typography.bodySmall)
              Text(text = "Price: ${course.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = IndigoPrimary)
            }
          }
        }
      }

      item {
        Text(text = "Course Curriculum", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
      }

      items(chapters) { chapter ->
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = chapter.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            chapter.lessons.forEach { lesson ->
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .clickable { onStartLesson(lesson.id) }
                  .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                  Icon(
                    imageVector = if (lesson.isCompleted) Icons.Default.CheckCircle else Icons.Default.PlayArrow,
                    contentDescription = "Lesson Status",
                    tint = if (lesson.isCompleted) SuccessGreen else IndigoPrimary
                  )
                  Column {
                    Text(text = lesson.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                    Text(text = lesson.duration, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                  }
                }
                TextButton(onClick = { onStartLesson(lesson.id) }) {
                  Text("Watch")
                }
              }
            }
          }
        }
      }
    }
  }
}
