package com.project.presentation.view.registration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.registration.RegistrationScreen

const val registrationRoute = "registration_route"

fun NavController.navigateToRegistration() {
    this.navigate(registrationRoute)
}

fun NavGraphBuilder.registrationScreen() {
    composable(registrationRoute) {
        RegistrationScreen()
    }
}