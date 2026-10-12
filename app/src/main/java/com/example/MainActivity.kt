package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.data.Repository
import com.example.data.UserRole
import com.example.ui.screens.*
import com.example.ui.theme.XenturaltLearnTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      XenturaltLearnTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          XenturaltLearnApp()
        }
      }
    }
  }
}

sealed class Screen {
  object SplashOnboarding : Screen()
  object StudentDashboard : Screen()
  object CourseCatalogue : Screen()
  data class CourseDetail(val courseId: String) : Screen()
  data class VideoPlayer(val lessonId: String) : Screen()
  object AiTutor : Screen()
  object ExamQuiz : Screen()
  object Analytics : Screen()
  object LiveClasses : Screen()
  object ParentDashboard : Screen()
  object TeacherPortal : Screen()
  object AdminDashboard : Screen()
  object Subscriptions : Screen()
  object Support : Screen()
  object Settings : Screen()
}

@Composable
fun XenturaltLearnApp() {
  var currentScreen by remember { mutableStateOf<Screen>(Screen.SplashOnboarding) }
  val user by Repository.currentUser.collectAsState()

  when (val screen = currentScreen) {
    is Screen.SplashOnboarding -> {
      SplashOnboardingScreen(
        onGetStarted = {
          currentScreen = when (user.role) {
            UserRole.PARENT -> Screen.ParentDashboard
            UserRole.TEACHER -> Screen.TeacherPortal
            UserRole.ADMIN, UserRole.SUPER_ADMIN -> Screen.AdminDashboard
            else -> Screen.StudentDashboard
          }
        }
      )
    }
    is Screen.StudentDashboard -> {
      StudentDashboardScreen(
        onNavigateToCourse = { courseId -> currentScreen = Screen.CourseDetail(courseId) },
        onNavigateToAiTutor = { currentScreen = Screen.AiTutor },
        onNavigateToQuiz = { currentScreen = Screen.ExamQuiz },
        onNavigateToLive = { currentScreen = Screen.LiveClasses },
        onNavigateToAnalytics = { currentScreen = Screen.Analytics },
        onNavigateToSupport = { currentScreen = Screen.Support }
      )
    }
    is Screen.CourseCatalogue -> {
      CourseCatalogueScreen(
        onSelectCourse = { courseId -> currentScreen = Screen.CourseDetail(courseId) }
      )
    }
    is Screen.CourseDetail -> {
      CourseDetailScreen(
        courseId = screen.courseId,
        onBack = { currentScreen = Screen.StudentDashboard },
        onStartLesson = { lessonId -> currentScreen = Screen.VideoPlayer(lessonId) }
      )
    }
    is Screen.VideoPlayer -> {
      VideoPlayerScreen(
        lessonId = screen.lessonId,
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.AiTutor -> {
      AiTutorScreen(
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.ExamQuiz -> {
      ExamQuizScreen(
        onBack = { currentScreen = Screen.StudentDashboard },
        onFinishQuiz = { score, total -> currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.Analytics -> {
      AnalyticsScreen(
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.LiveClasses -> {
      LiveClassesScreen(
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.ParentDashboard -> {
      ParentDashboardScreen(
        onBack = { currentScreen = Screen.SplashOnboarding }
      )
    }
    is Screen.TeacherPortal -> {
      TeacherPortalScreen(
        onBack = { currentScreen = Screen.SplashOnboarding }
      )
    }
    is Screen.AdminDashboard -> {
      AdminDashboardScreen(
        onBack = { currentScreen = Screen.SplashOnboarding }
      )
    }
    is Screen.Subscriptions -> {
      SubscriptionsScreen(
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.Support -> {
      SupportScreen(
        onBack = { currentScreen = Screen.StudentDashboard }
      )
    }
    is Screen.Settings -> {
      SettingsScreen(
        onBack = { currentScreen = Screen.StudentDashboard },
        onLogout = { currentScreen = Screen.SplashOnboarding }
      )
    }
  }
}
