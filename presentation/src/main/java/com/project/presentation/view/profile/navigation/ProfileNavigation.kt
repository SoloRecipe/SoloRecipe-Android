package com.project.presentation.view.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.profile.ProfileScreen

const val profileRoute = "profile_route"

fun NavController.navigateToProfile() {
    this.navigate(profileRoute)
}

fun NavGraphBuilder.profileScreen(navigateToSignIn: () -> Unit) {
    composable(profileRoute) {
        ProfileScreen(navigateToSignIn = navigateToSignIn)
    }
}