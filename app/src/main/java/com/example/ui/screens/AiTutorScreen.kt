package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.ChatMessage
import com.example.data.Repository
import com.example.ui.theme.IndigoPrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiTutorScreen(onBack: () -> Unit) {
  var messageInput by remember { mutableStateOf("") }
  var chatMessages by remember {
    mutableStateOf(
      listOf(
        ChatMessage(
          id = "m_1",
          sender = "ai",
          text = "Hello! I am your Xenturalt AI Mentor. Ask me any question about Mathematics, Physics, Chemistry, or exam preparation in English or Hindi.",
          timestamp = "Just now"
        )
      )
    )
  }
  val coroutineScope = rememberCoroutineScope()
  var isLoading by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Xenturalt AI Mentor") },
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
      // Chat Messages List
      LazyColumn(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(chatMessages) { msg ->
          val isUser = msg.sender == "user"
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
          ) {
            Card(
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(
                containerColor = if (isUser) IndigoPrimary else MaterialTheme.colorScheme.surface
              ),
              modifier = Modifier.widthIn(max = 280.dp)
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = msg.text,
                  color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                  style = MaterialTheme.typography.bodyMedium
                )
              }
            }
          }
        }
        if (isLoading) {
          item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
              CircularProgressIndicator(modifier = Modifier.size(24.dp), color = IndigoPrimary)
            }
          }
        }
      }

      // Input Bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedTextField(
          value = messageInput,
          onValueChange = { messageInput = it },
          placeholder = { Text("Ask any educational doubt...") },
          modifier = Modifier.weight(1f),
          shape = RoundedCornerShape(24.dp)
        )
        IconButton(
          onClick = {
            if (messageInput.isNotBlank() && !isLoading) {
              val userText = messageInput
              messageInput = ""
              chatMessages = chatMessages + ChatMessage(
                id = System.currentTimeMillis().toString(),
                sender = "user",
                text = userText,
                timestamp = "Now"
              )
              isLoading = true
              coroutineScope.launch {
                val aiReply = Repository.askAiMentor(userText)
                chatMessages = chatMessages + ChatMessage(
                  id = (System.currentTimeMillis() + 1).toString(),
                  sender = "ai",
                  text = aiReply,
                  timestamp = "Now"
                )
                isLoading = false
              }
            }
          },
          modifier = Modifier
            .size(48.dp)
            .background(IndigoPrimary, shape = RoundedCornerShape(24.dp))
        ) {
          Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White)
        }
      }
    }
  }
}
