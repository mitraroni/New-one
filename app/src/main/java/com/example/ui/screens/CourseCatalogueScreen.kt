package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Repository
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.WarningAmber

@Composable
fun CourseCatalogueScreen(onSelectCourse: (String) -> Unit) {
  val courses = Repository.sampleCourses
  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf("All") }

  val categories = listOf("All", "CBSE Class 10", "JEE / NEET", "Skill Based")

  val filteredCourses = courses.filter {
    (selectedCategory == "All" || it.category == selectedCategory) &&
      it.title.contains(searchQuery, ignoreCase = true)
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Text(text = "Course Catalogue", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
    }

    // Search bar
    item {
      OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Search subjects, topics, or instructors...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
      )
    }

    // Categories Row
    item {
      LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(categories) { category ->
          FilterChip(
            selected = selectedCategory == category,
            onClick = { selectedCategory = category },
            label = { Text(category) }
          )
        }
      }
    }

    // Courses List
    items(filteredCourses) { course ->
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onSelectCourse(course.id) }
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Badge(containerColor = IndigoPrimary.copy(alpha = 0.1f)) {
              Text(course.category, color = IndigoPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
            Text(text = course.price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = IndigoPrimary)
          }
          Text(text = course.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
          Text(text = course.subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Instructor: ${course.instructor}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.Star, contentDescription = "Rating", tint = WarningAmber, modifier = Modifier.size(16.dp))
              Text(text = "${course.rating} (${course.enrolledCount} enrolled)", style = MaterialTheme.typography.bodySmall)
            }
          }
        }
      }
    }
  }
}
