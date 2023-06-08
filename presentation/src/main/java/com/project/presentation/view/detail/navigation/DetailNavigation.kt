package com.project.presentation.view.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.detail.DetailScreen


const val detailRoute = "detail_route"

fun NavController.navigateToDetail(index: Long) {
    this.navigate("$detailRoute/$index")
}

fun NavGraphBuilder.detailScreen() {
    composable("$detailRoute/{index}") { backStackEntry ->
        DetailScreen(index = backStackEntry.arguments?.getLong("index"))
    }
}