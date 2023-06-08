package com.project.solorecipe

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.project.presentation.view.auth.signin.navigation.signInScreen
import com.project.presentation.view.auth.signup.navigation.navigateToSignUp
import com.project.presentation.view.auth.signup.navigation.signUpScreen
import com.project.presentation.view.detail.navigation.detailScreen
import com.project.presentation.view.detail.navigation.navigateToDetail
import com.project.presentation.view.main.navigation.mainScreen
import com.project.presentation.view.main.navigation.navigateToMain
import com.project.presentation.view.profile.navigation.navigateToProfile
import com.project.presentation.view.profile.navigation.profileScreen
import com.project.presentation.view.registration.navigation.navigateToRegistration
import com.project.presentation.view.registration.navigation.registrationScreen

@Composable
fun SoloRecipeNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        signInScreen(
            navigateToSignUp = {
                navController.navigateToSignUp()
            },
            navigateToMain = {
                navController.navigateToMain()
            }
        )
        signUpScreen()
        detailScreen()
        mainScreen(
            navigateToProfile = {
                navController.navigateToProfile()
            },
            navigateToDetail = {
                navController.navigateToDetail(it)
            },
            navigateToRegistration = {
                navController.navigateToRegistration()
            }
        )
        profileScreen()
        registrationScreen()
    }
}