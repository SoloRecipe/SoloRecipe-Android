package com.project.presentation.view.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.presentation.view.search.SearchScreen

const val searchRoute = "search_route"

fun NavController.navigateToSearch() {
    this.navigate(searchRoute)
}

fun NavGraphBuilder.searchScreen(
    navigateToPrevious: () -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    composable(searchRoute) {
        SearchScreen(
            navigateToPrevious = navigateToPrevious,
            navigateToDetail = navigateToDetail
        )
    }
}