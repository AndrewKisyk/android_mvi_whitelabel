package com.example.activesessionschecker

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.activesessionschecker.SessionsDestinationsArgs.SESSION_ID_ARG
import com.example.activesessionschecker.SessionsScreens.SESSIONS_SCREEN
import com.example.activesessionschecker.SessionsScreens.SESSION_DETAIL_SCREEN

private object SessionsScreens {
    const val SESSIONS_SCREEN = "sessions"
    const val SESSION_DETAIL_SCREEN = "session"
}

/**
 * Arguments used in [SessionDestinations] routes
 */
object SessionsDestinationsArgs {
    const val SESSION_ID_ARG = "taskId"
}

/**
 * Destinations used in the [MainActivity]
 */
object TodoDestinations {
    const val SESSIONS_ROUTE = SESSIONS_SCREEN
    const val SESSION_DETAIL_ROUTE = "$SESSION_DETAIL_SCREEN/{$SESSION_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class ActiveSessionNavigation(private val navController: NavHostController) {
    fun navigateToSessions() {
        navController.navigate(
            SESSIONS_SCREEN
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSessionDetail(sessionId: String) {
        navController.navigate("$SESSION_DETAIL_SCREEN/$sessionId")
    }
}
