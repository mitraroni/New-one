package com.example.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

object Repository {

  private val _currentUser = MutableStateFlow(
    UserProfile(
      id = "usr_001",
      name = "Aarav Sharma",
      email = "aarav.sharma@xenturalt.com",
      phone = "+91 98765 43210",
      role = UserRole.STUDENT,
      className = "Class 10 - CBSE & JEE Foundation",
      streakDays = 12,
      totalPoints = 2450
    )
  )
  val currentUser: StateFlow<UserProfile> = _currentUser.asStateFlow()

  fun updateRole(role: UserRole) {
    _currentUser.value = _currentUser.value.copy(role = role)
  }

  val sampleCourses = listOf(
    Course(
      id = "c_1",
      title = "Advanced Mathematics Class 10",
      subtitle = "Master Quadratic Equations & Trigonometry with Expert Proofs",
      subject = "Mathematics",
      category = "CBSE Class 10",
      instructor = "Dr. R. K. Verma",
      rating = 4.9,
      enrolledCount = 14200,
      totalLessons = 48,
      price = "₹2,499",
      isFree = false,
      progressPercentage = 0.65f
    ),
    Course(
      id = "c_2",
      title = "Physics: Mechanics & Electromagnetism",
      subtitle = "Comprehensive JEE/NEET Foundation Physics Course",
      subject = "Physics",
      category = "JEE / NEET",
      instructor = "Prof. Ananya Sen",
      rating = 4.8,
      enrolledCount = 18900,
      totalLessons = 60,
      price = "₹3,999",
      isFree = false,
      progressPercentage = 0.30f
    ),
    Course(
      id = "c_3",
      title = "Organic Chemistry Mastery",
      subtitle = "Reaction Mechanisms, Nomenclature & Isomerism simplified",
      subject = "Chemistry",
      category = "JEE / NEET",
      instructor = "Dr. Suresh Nair",
      rating = 4.7,
      enrolledCount = 11500,
      totalLessons = 40,
      price = "₹2,999",
      isFree = false,
      progressPercentage = 0.10f
    ),
    Course(
      id = "c_4",
      title = "English Grammar & Advanced Composition",
      subtitle = "Essay writing, Reading Comprehension & Fluency",
      subject = "English",
      category = "CBSE Class 10",
      instructor = "Priya Nambiar",
      rating = 4.9,
      enrolledCount = 22000,
      totalLessons = 25,
      price = "Free",
      isFree = true,
      progressPercentage = 0.90f
    ),
    Course(
      id = "c_5",
      title = "Python Coding & Logical Reasoning",
      subtitle = "Build problem-solving skills with hands-on coding exercises",
      subject = "Computer Science",
      category = "Skill Based",
      instructor = "Vikramaditya Rao",
      rating = 4.9,
      enrolledCount = 31000,
      totalLessons = 32,
      price = "₹1,999",
      isFree = false,
      progressPercentage = 0.0f
    )
  )

  val sampleChapters = listOf(
    Chapter(
      id = "ch_1",
      courseId = "c_1",
      title = "Chapter 1: Real Numbers & Polynomials",
      lessons = listOf(
        Lesson("l_1", "ch_1", "Euclid's Division Lemma & HCF", "15:42", "https://www.w3schools.com/html/mov_bbb.mp4", true, "Note: Remember prime factorization rule."),
        Lesson("l_2", "ch_1", "Fundamental Theorem of Arithmetic", "18:20", "https://www.w3schools.com/html/mov_bbb.mp4", true, "Note: Every composite number can be expressed as primes."),
        Lesson("l_3", "ch_1", "Geometrical Meaning of Zeroes of a Polynomial", "22:10", "https://www.w3schools.com/html/mov_bbb.mp4", false)
      )
    ),
    Chapter(
      id = "ch_2",
      courseId = "c_1",
      title = "Chapter 2: Quadratic Equations",
      lessons = listOf(
        Lesson("l_4", "ch_2", "Introduction to Quadratic Equations", "20:00", "https://www.w3schools.com/html/mov_bbb.mp4", false),
        Lesson("l_5", "ch_2", "Solution by Factorization & Quadratic Formula", "25:15", "https://www.w3schools.com/html/mov_bbb.mp4", false)
      )
    )
  )

  val sampleQuestions = listOf(
    Question(
      id = "q_1",
      text = "What is the discriminant of a quadratic equation ax² + bx + c = 0?",
      options = listOf("b² - 4ac", "b² + 4ac", "4ac - b²", "b - 4ac"),
      correctIndex = 0,
      explanation = "The discriminant determines the nature of roots and is given by D = b² - 4ac."
    ),
    Question(
      id = "q_2",
      text = "Which of the following is a prime number?",
      options = listOf("91", "87", "97", "85"),
      correctIndex = 2,
      explanation = "97 is not divisible by any number other than 1 and itself, making it prime."
    ),
    Question(
      id = "q_3",
      text = "What is the SI unit of electric current?",
      options = listOf("Volt", "Ampere", "Ohm", "Watt"),
      correctIndex = 1,
      explanation = "Ampere (A) is the SI base unit of electric current."
    )
  )

  val sampleLiveClasses = listOf(
    LiveClassItem("lc_1", "Live Problem Solving: Trigonometric Identities", "Dr. R. K. Verma", "Mathematics", "Today, 5:00 PM IST", "Upcoming"),
    LiveClassItem("lc_2", "NEET Biology Rapid Fire Revision: Human Physiology", "Dr. Meenakshi Iyer", "Biology", "Tomorrow, 10:00 AM IST", "Upcoming"),
    LiveClassItem("lc_3", "Organic Chemistry Reaction Mechanisms", "Dr. Suresh Nair", "Chemistry", "Yesterday, 4:00 PM IST", "Completed")
  )

  val sampleTickets = listOf(
    SupportTicket("t_1", "Doubt in Calculus Chapter 3 Question 14", "Academic", "Open", "2 hours ago"),
    SupportTicket("t_2", "Live class video buffering issue", "Technical", "Resolved", "Yesterday")
  )

  // AI Mentor query
  suspend fun askAiMentor(prompt: String): String = withContext(Dispatchers.IO) {
    try {
      "Xenturalt AI Mentor (Gemini Powered):\n\nRegarding your query on \"$prompt\":\n1. Core Concept: This involves fundamental principles studied in Class 10/12 curricula.\n2. Step-by-Step Breakdown: Identify known parameters, apply standard formulas, and verify boundary conditions.\n3. Practice Tip: Solve at least 3 similar numerical problems to ensure complete mastery."
    } catch (e: Exception) {
      Log.e("Repository", "AI Error", e)
      "Xenturalt AI Mentor: I encountered a network issue while processing your request. Please check your connection."
    }
  }
}
