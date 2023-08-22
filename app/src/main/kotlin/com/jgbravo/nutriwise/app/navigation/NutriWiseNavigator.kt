package com.jgbravo.nutriwise.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.app.navigation.Destination.DashboardDestination
import com.jgbravo.nutriwise.app.navigation.Destination.MealPlanDetailDestination
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnMealPlanClicked
import com.jgbravo.nutriwise.ui.dashboard.DashboardScreen
import com.jgbravo.nutriwise.ui.dashboard.DashboardViewModel
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailEvent
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailScreen
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

            val viewModel = koinViewModel<DashboardViewModel>()
            val state by viewModel.state.collectAsState()

            DashboardScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is OnMealPlanClicked -> navController.navigate(
                            MealPlanDetailDestination(mealPlanId = event.mealPlan.id)
                        )
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }

        destinationComposable(
            destination = MealPlanDetailDestination
        ) { backStackEntry ->

            val viewModel = koinViewModel<MealPlanDetailViewModel>()
            val state by viewModel.state.collectAsState()

            val mealPlanIdResult = backStackEntry.arguments?.getString(MealPlanDetailDestination.MEAL_PLAN_ID)

            LaunchedEffect(mealPlanIdResult) {
                backStackEntry.savedStateHandle[MealPlanDetailDestination.MEAL_PLAN_ID] = mealPlanIdResult
            }

            MealPlanDetailScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is MealPlanDetailEvent.OnMealClicked -> Unit //TODO: Navigate to meal details
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
    }
}