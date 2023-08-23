package com.jgbravo.nutriwise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.ui.navigation.base.DestinationNavHost
import com.jgbravo.nutriwise.ui.navigation.base.registerFeature
import com.jgbravo.nutriwise.ui.navigation.features.Destination.DashboardDestination
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatImpl
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatImpl


@Composable
fun NutriWiseNavigator(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    DestinationNavHost(
        navController = navController,
        startDestination = DashboardDestination
    ) {

        registerFeature(
            featureApi = DashboardFeatImpl(), // TODO: change to DashboardFeatApi
            navController = navController,
            modifier = modifier
        )

        registerFeature(
            featureApi = MealPlanDetailFeatImpl(), // TODO: change to MealPlanDetailFeatApi
            navController = navController,
            modifier = modifier
        )

        /*destinationComposable(
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
                        is OnMealClicked -> Unit //TODO: Navigate to meal details
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }*/
    }
}