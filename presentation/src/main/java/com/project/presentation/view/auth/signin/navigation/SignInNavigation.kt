package com.project.presentation.view.auth.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.auth.signin.SignInScreen

const val signInRoute = "sign_in_route"

fun NavController.navigateToSignIn() {
    this.navigate(signInRoute)
}
fun NavGraphBuilder.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit
) {
    composable(signInRoute) {
        SignInScreen(
            navigateToSignUp = navigateToSignUp,
            navigateToMain = navigateToMain
        )
    }
}