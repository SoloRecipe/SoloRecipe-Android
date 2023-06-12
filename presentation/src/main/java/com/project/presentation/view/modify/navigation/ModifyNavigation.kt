package com.project.presentation.view.modify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.presentation.view.modify.ModifyScreen

const val modifyRoute = "modify_route"
        
fun NavController.navigateToModify(index: Long) {
    this.navigate("$modifyRoute/$index")
}

fun NavGraphBuilder.modifyScreen() {
    composable(
        route = "$modifyRoute/{index}",
        arguments = listOf(navArgument("index") { type = NavType.LongType})
    ) { backStackEntry ->
        ModifyScreen(index = backStackEntry.arguments?.getLong("index"))
    }
}