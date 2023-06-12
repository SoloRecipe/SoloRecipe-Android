package com.project.solorecipe

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.project.presentation.view.auth.signin.navigation.navigateToSignIn
import com.project.presentation.view.auth.signin.navigation.signInScreen
import com.project.presentation.view.auth.signup.navigation.navigateToSignUp
import com.project.presentation.view.auth.signup.navigation.signUpScreen
import com.project.presentation.view.detail.navigation.detailScreen
import com.project.presentation.view.detail.navigation.navigateToDetail
import com.project.presentation.view.main.navigation.mainScreen
import com.project.presentation.view.main.navigation.navigateToMain
import com.project.presentation.view.modify.navigation.modifyScreen
import com.project.presentation.view.modify.navigation.navigateToModify
import com.project.presentation.view.profile.navigation.navigateToProfile
import com.project.presentation.view.profile.navigation.profileScreen
import com.project.presentation.view.registration.navigation.navigateToRegistration
import com.project.presentation.view.registration.navigation.registrationScreen
import com.project.presentation.view.search.navigation.navigateToSearch
import com.project.presentation.view.search.navigation.searchScreen

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
        signUpScreen(navigateToSignIn = {
            navController.navigateToSignIn()
        })
        detailScreen(
            navigateToPrevious = {
                navController.popBackStack()
            }
        )
        mainScreen(
            navigateToProfile = {
                navController.navigateToProfile()
            },
            navigateToDetail = {
                navController.navigateToDetail(it)
            },
            navigateToRegistration = {
                navController.navigateToRegistration()
            },
            navigateToSearch = {
                navController.navigateToSearch()
            }
        )
        registrationScreen(
            navigateToMain = {
                navController.navigateToMain()
            },
            navigateToPrevious = {
                navController.popBackStack()
            }
        )
        profileScreen(
            navigateToSignIn = {
                navController.navigateToSignIn()
            },
            navigateToDetail = {
                navController.navigateToDetail(it, true)
            },
            navigateToModify = {
                navController.navigateToModify(it)
            },
            navigateToPrevious = {
                navController.popBackStack()
            }
        )
        searchScreen(
            navigateToPrevious = {
                navController.popBackStack()
            },
            navigateToDetail = {
                navController.navigateToDetail(it)
            }
        )
        modifyScreen(navigateToPrevious = {
            navController.popBackStack()
        })
    }
}