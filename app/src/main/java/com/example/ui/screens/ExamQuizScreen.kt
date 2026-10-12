package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Repository
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamQuizScreen(onBack: () -> Unit, onFinishQuiz: (Int, Int) -> Unit) {
  val questions = Repository.sampleQuestions
  var currentIndex by remember { mutableStateOf(0) }
  var selectedOption by remember { mutableStateOf<Int?>(null) }
  var score by remember { mutableStateOf(0) }
  var showExplanation by remember { mutableStateOf(false) }

  val currentQ = questions[currentIndex]

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Smart Test Engine (${currentIndex + 1}/${questions.size})") },
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
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LinearProgressIndicator(
          progress = { (currentIndex + 1).toFloat() / questions.size },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
          color = IndigoPrimary
        )

        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = currentQ.text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            currentQ.options.forEachIndexed { idx, option ->
              val isSelected = selectedOption == idx
              val isCorrect = idx == currentQ.correctIndex
              val btnColor = if (showExplanation) {
                if (isCorrect) SuccessGreen.copy(alpha = 0.2f)
                else if (isSelected) MaterialTheme.colorScheme.error.copy(alpha = 0.2f)
                else MaterialTheme.colorScheme.surfaceVariant
              } else {
                if (isSelected) IndigoPrimary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
              }

              OutlinedButton(
                onClick = { if (!showExplanation) selectedOption = idx },
                colors = ButtonDefaults.outlinedButtonColors(containerColor = btnColor),
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(text = option, color = MaterialTheme.colorScheme.onSurface)
              }
            }

            if (showExplanation) {
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Explanation: ${currentQ.explanation}",
                style = MaterialTheme.typography.bodyMedium,
                color = IndigoPrimary,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }
      }

      Button(
        onClick = {
          if (!showExplanation) {
            if (selectedOption != null) {
              if (selectedOption == currentQ.correctIndex) score += 10
              showExplanation = true
            }
          } else {
            if (currentIndex < questions.size - 1) {
              currentIndex++
              selectedOption = null
              showExplanation = false
            } else {
              onFinishQuiz(score, questions.size * 10)
            }
          }
        },
        enabled = selectedOption != null,
        colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp),
        shape = RoundedCornerShape(16.dp)
      ) {
        Text(
          text = if (!showExplanation) "Submit Answer" else if (currentIndex < questions.size - 1) "Next Question" else "View Results",
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}
