package com.project.presentation.view.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.view.detail.DetailScreen


const val detailRoute = "detail_route"

fun NavController.navigateToDetail(index: Long) {
    this.navigate("$detailRoute/$index")
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = "$detailRoute/{index}",
        arguments = listOf(navArgument("index") { type = NavType.StringType })
    ) { backStackEntry ->
        DetailScreen(index = backStackEntry.arguments?.getString("index"))
    }
}