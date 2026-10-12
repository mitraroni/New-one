package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Repository
import com.example.data.UserRole
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.VioletAccent

@Composable
fun SplashOnboardingScreen(onGetStarted: () -> Unit) {
  var selectedLanguage by remember { mutableStateOf("English") }
  var selectedRole by remember { mutableStateOf(UserRole.STUDENT) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(IndigoDark, IndigoPrimary, VioletAccent)
        )
      )
      .padding(24.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(modifier = Modifier.height(40.dp))

      // Logo & Branding
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
          shape = RoundedCornerShape(24.dp),
          color = MaterialTheme.colorScheme.surface.copy(alpha = 0.15f),
          modifier = Modifier.size(96.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Default.School,
              contentDescription = "Logo",
              tint = MaterialTheme.colorScheme.onPrimary,
              modifier = Modifier.size(56.dp)
            )
          }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = "Xenturalt Learn",
          style = MaterialTheme.typography.headlineLarge,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "Empowering India's Future with AI-Powered Education",
          style = MaterialTheme.typography.bodyMedium,
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
        )
      }

      // Options Card
      Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          Text(
            text = "Select Language",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Button(
              onClick = { selectedLanguage = "English" },
              colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedLanguage == "English") IndigoPrimary else MaterialTheme.colorScheme.surfaceVariant
              ),
              modifier = Modifier.weight(1f)
            ) {
              Text("English", color = if (selectedLanguage == "English") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Button(
              onClick = { selectedLanguage = "Hindi" },
              colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedLanguage == "Hindi") IndigoPrimary else MaterialTheme.colorScheme.surfaceVariant
              ),
              modifier = Modifier.weight(1f)
            ) {
              Text("हिन्दी (Hindi)", color = if (selectedLanguage == "Hindi") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant)
            }
          }

          Text(
            text = "Primary Role",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf(UserRole.STUDENT, UserRole.PARENT, UserRole.TEACHER, UserRole.ADMIN).forEach { role ->
              OutlinedButton(
                onClick = { selectedRole = role },
                colors = ButtonDefaults.outlinedButtonColors(
                  containerColor = if (selectedRole == role) IndigoPrimary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  text = role.name.lowercase().replaceFirstChar { it.uppercase() },
                  style = MaterialTheme.typography.bodySmall,
                  color = if (selectedRole == role) IndigoPrimary else MaterialTheme.colorScheme.onSurface,
                  maxLines = 1
                )
              }
            }
          }
        }
      }

      // Get Started Button
      Button(
        onClick = {
          Repository.updateRole(selectedRole)
          onGetStarted()
        },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp),
        shape = RoundedCornerShape(16.dp)
      ) {
        Text(
          text = "Continue to Dashboard",
          style = MaterialTheme.typography.titleMedium,
          color = IndigoPrimary,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}
