package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Repository
import com.example.data.UserRole
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningAmber

@Composable
fun StudentDashboardScreen(
  onNavigateToCourse: (String) -> Unit,
  onNavigateToAiTutor: () -> Unit,
  onNavigateToQuiz: () -> Unit,
  onNavigateToLive: () -> Unit,
  onNavigateToAnalytics: () -> Unit,
  onNavigateToSupport: () -> Unit
) {
  val user by Repository.currentUser.collectAsState()
  val courses = Repository.sampleCourses
  val liveClasses = Repository.sampleLiveClasses

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    // Top Bar with Greeting & Streak
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
          Surface(
            shape = CircleShape,
            color = IndigoPrimary.copy(alpha = 0.2f),
            modifier = Modifier.size(48.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(Icons.Default.Person, contentDescription = "Profile", tint = IndigoPrimary)
            }
          }
          Column {
            Text(text = "Hello, ${user.name}!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(text = user.className, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = WarningAmber.copy(alpha =.15f)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(Icons.Default.LocalFireDepartment, contentDescription = "Streak", tint = WarningAmber, modifier = Modifier.size(18.dp))
              Text(text = "${user.streakDays} Days", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = WarningAmber)
            }
          }
        }
      }
    }

    // AI Mentor Banner Card
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onNavigateToAiTutor() }
      ) {
        Row(
          modifier = Modifier.padding(20.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Badge(containerColor = IndigoPrimary) {
              Text("Powered by Gemini AI", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Xenturalt AI Mentor", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = "Ask complex math proofs, chemistry mechanisms or concept doubts instantly.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f))
          }
          FilledIconButton(
            onClick = { onNavigateToAiTutor() },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = IndigoPrimary)
          ) {
            Icon(Icons.Default.SmartToy, contentDescription = "AI Tutor")
          }
        }
      }
    }

    // Continue Learning Card
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Continue Learning", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToCourse(courses[0].id) }
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
              Text(text = courses[0].subject, style = MaterialTheme.typography.labelMedium, color = IndigoPrimary, fontWeight = FontWeight.Bold)
              Text(text = "${(courses[0].progressPercentage * 100).toInt()}% Done", style = MaterialTheme.typography.labelMedium, color = SuccessGreen, fontWeight = FontWeight.Bold)
            }
            Text(text = courses[0].title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            LinearProgressIndicator(
              progress = { courses[0].progressPercentage },
              modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
              color = IndigoPrimary,
              trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
          }
        }
      }
    }

    // Quick Actions Grid
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Quick Tools & Portals", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
          QuickActionButton(title = "Live Classes", icon = Icons.Default.LiveTv, modifier = Modifier.weight(1f), onClick = onNavigateToLive)
          QuickActionButton(title = "Smart Quizzes", icon = Icons.Default.Quiz, modifier = Modifier.weight(1f), onClick = onNavigateToQuiz)
          QuickActionButton(title = "Analytics", icon = Icons.Default.BarChart, modifier = Modifier.weight(1f), onClick = onNavigateToAnalytics)
          QuickActionButton(title = "Help & Support", icon = Icons.Default.SupportAgent, modifier = Modifier.weight(1f), onClick = onNavigateToSupport)
        }
      }
    }

    // Recommended Courses
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Recommended For You", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
          items(courses) { course ->
            Card(
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              modifier = Modifier
                .width(240.dp)
                .clickable { onNavigateToCourse(course.id) }
            ) {
              Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Badge(containerColor = IndigoPrimary.copy(alpha = 0.1f)) {
                  Text(course.category, color = IndigoPrimary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
                Text(text = course.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 2)
                Text(text = "Instructor: ${course.instructor}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                  Text(text = course.price, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = IndigoPrimary)
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = "Rating", tint = WarningAmber, modifier = Modifier.size(16.dp))
                    Text(text = "${course.rating}", style = MaterialTheme.typography.bodySmall)
                  }
                }
              }
            }
          }
        }
      }
    }

    // Upcoming Live Classes
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Upcoming Live Sessions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        liveClasses.forEach { live ->
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(text = live.subject, style = MaterialTheme.typography.labelMedium, color = IndigoPrimary, fontWeight = FontWeight.Bold)
                Text(text = live.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = "${live.teacher} • ${live.time}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
              Button(
                onClick = onNavigateToLive,
                colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
              ) {
                Text("Join", style = MaterialTheme.typography.labelMedium)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun QuickActionButton(title: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    modifier = modifier.clickable { onClick() }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Surface(
        shape = CircleShape,
        color = IndigoPrimary.copy(alpha = 0.1f),
        modifier = Modifier.size(40.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(imageVector = icon, contentDescription = title, tint = IndigoPrimary, modifier = Modifier.size(20.dp))
        }
      }
      Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        maxLines = 1
      )
    }
  }
}
