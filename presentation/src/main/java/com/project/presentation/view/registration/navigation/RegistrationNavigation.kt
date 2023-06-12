package com.project.presentation.view.registration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.view.registration.RegistrationScreen

const val registrationRoute = "registration_route"

fun NavController.navigateToRegistration(type: String = "registration", index: Long? = null) {
    val route = if (index != null) "$registrationRoute/$type?index=$index" else "$registrationRoute/$type"
    this.navigate(route)
}

fun NavGraphBuilder.registrationScreen(
    navigateToMain: () -> Unit,
    navigateToPrevious: () -> Unit
) {
    composable(
        route = "$registrationRoute/{type}?index={index}",
        arguments = listOf(
            navArgument("type") { type = NavType.StringType },
            navArgument("index") {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { backStackEntry ->
        RegistrationScreen(
            type = backStackEntry.arguments?.getString("type"),
            index = backStackEntry.arguments?.getString("index"),
            navigateToMain = navigateToMain,
            navigateToPrevious = navigateToPrevious
        )
    }
}