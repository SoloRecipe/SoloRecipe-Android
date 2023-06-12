package com.project.presentation.view.registration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.view.registration.RegistrationScreen

const val registrationRoute = "registration_route"

fun NavController.navigateToRegistration() {
    this.navigate(registrationRoute)
}

fun NavGraphBuilder.registrationScreen(
    navigateToMain: () -> Unit,
    navigateToPrevious: () -> Unit
) {
    composable(route = registrationRoute) {
        RegistrationScreen(
            navigateToMain = navigateToMain,
            navigateToPrevious = navigateToPrevious
        )
    }
}