package com.project.presentation.view.auth.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val signInRoute = "sign_in_route"

fun NavGraphBuilder.signInScreen() {
    composable(signInRoute) {
        signInScreen()
    }
}