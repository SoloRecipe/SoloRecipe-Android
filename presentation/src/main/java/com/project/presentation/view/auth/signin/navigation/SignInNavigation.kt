package com.project.presentation.view.auth.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.auth.signin.SignInScreen

const val signInRoute = "sign_in_route"

fun NavGraphBuilder.signInScreen(navigateToSignUp: () -> Unit) {
    composable(signInRoute) {
        SignInScreen(navigateToSignUp = navigateToSignUp)
    }
}