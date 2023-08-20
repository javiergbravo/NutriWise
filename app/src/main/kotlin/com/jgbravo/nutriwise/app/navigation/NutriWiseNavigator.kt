package com.jgbravo.nutriwise.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.app.navigation.Destination.DashboardDestination
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent
import com.jgbravo.nutriwise.ui.dashboard.DashboardScreen
import com.jgbravo.nutriwise.ui.dashboard.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NutriWiseNavigator() {
    val navController = rememberNavController()

    DestinationNavHost(
        navController = navController,
        startDestination = DashboardDestination
    ) {
        destinationComposable(
            destination = DashboardDestination
        ) {
            val viewModel = koinViewModel<DashboardViewModel>()
            val state by viewModel.state.collectAsState()

            DashboardScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is DashboardEvent.OnMealClicked -> Unit //TODO: Navigate to meal detail
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
    }
}