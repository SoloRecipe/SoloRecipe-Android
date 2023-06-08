package com.project.presentation.view.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.main.MainScreen

const val mainRoute = "main_route"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen(
    navigateToProfile: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    navigateToRegistration: () -> Unit
) {
    composable(mainRoute) {
        MainScreen(
            navigateToProfile = navigateToProfile,
            navigateToDetail = navigateToDetail,
            navigateToRegistration = navigateToRegistration
        )
    }
}