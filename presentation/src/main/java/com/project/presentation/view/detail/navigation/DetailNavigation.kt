package com.project.presentation.view.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.view.detail.DetailScreen


const val detailRoute = "detail_route"

fun NavController.navigateToDetail(index: Long, isLikedRecipe: Boolean = false) {
    this.navigate("$detailRoute/$index/$isLikedRecipe")
}

fun NavGraphBuilder.detailScreen(
    navigateToPrevious: () -> Unit
) {
    composable(
        route = "$detailRoute/{index}/{isLikedRecipe}",
        arguments = listOf(
            navArgument("index") { type = NavType.StringType },
            navArgument("isLikedRecipe") { type = NavType.BoolType }
        )
    ) { backStackEntry ->
        DetailScreen(
            index = backStackEntry.arguments?.getString("index"),
            isLikedRecipe = backStackEntry.arguments?.getBoolean("isLikedRecipe"),
            navigateToPrevious = navigateToPrevious
        )
    }
}