package com.project.presentation.view.auth.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.auth.signup.SignUpScreen

const val signUpRoute = "sign_up_route"

fun NavController.navigateToSignUp() {
    this.navigate(signUpRoute)
}

fun NavGraphBuilder.signUpScreen(navigateToSignIn: () -> Unit) {
    composable(signUpRoute) {
        SignUpScreen(navigateToSignIn = navigateToSignIn)
    }
}