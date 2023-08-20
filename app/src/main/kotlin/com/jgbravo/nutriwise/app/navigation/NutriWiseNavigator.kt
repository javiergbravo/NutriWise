package com.jgbravo.nutriwise.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.app.navigation.Destination.DashboardDestination
import com.jgbravo.nutriwise.ui.mealPlanDetail.DashboardScreen
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailEvent
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailViewModel
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
            val viewModel = koinViewModel<MealPlanDetailViewModel>()
            val state by viewModel.state.collectAsState()

            DashboardScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is MealPlanDetailEvent.OnMealClicked -> Unit //TODO: Navigate to meal detail
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
    }
}