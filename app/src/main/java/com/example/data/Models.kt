package com.example.data

enum class UserRole {
  STUDENT, PARENT, TEACHER, ACADEMIC_MANAGER, SALES_COUNSELLOR, SUPPORT, ADMIN, SUPER_ADMIN
}

data class UserProfile(
  val id: String,
  val name: String,
  val email: String,
  val phone: String,
  val role: UserRole,
  val className: String = "Class 10 - CBSE",
  val streakDays: Int = 7,
  val totalPoints: Int = 1250,
  val avatarUrl: String = ""
)

data class Course(
  val id: String,
  val title: String,
  val subtitle: String,
  val subject: String,
  val category: String, // CBSE, JEE, NEET, Skills
  val instructor: String,
  val rating: Double,
  val enrolledCount: Int,
  val totalLessons: Int,
  val price: String,
  val isFree: Boolean,
  val progressPercentage: Float = 0f
)

data class Chapter(
  val id: String,
  val courseId: String,
  val title: String,
  val lessons: List<Lesson>
)

data class Lesson(
  val id: String,
  val chapterId: String,
  val title: String,
  val duration: String,
  val videoUrl: String,
  val isCompleted: Boolean = false,
  val notes: String = ""
)

data class Question(
  val id: String,
  val text: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String
)

data class LiveClassItem(
  val id: String,
  val title: String,
  val teacher: String,
  val subject: String,
  val time: String,
  val status: String // Upcoming, Live, Completed
)

data class SupportTicket(
  val id: String,
  val subject: String,
  val category: String,
  val status: String, // Open, Resolved
  val lastUpdated: String
)

data class ChatMessage(
  val id: String,
  val sender: String, // "user" or "ai"
  val text: String,
  val timestamp: String
)
