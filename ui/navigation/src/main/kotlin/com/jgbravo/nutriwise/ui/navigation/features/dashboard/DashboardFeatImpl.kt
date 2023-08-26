package com.jgbravo.nutriwise.ui.navigation.features.dashboard

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnMealPlanClicked
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardScreen
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardViewModel
import com.jgbravo.nutriwise.ui.navigation.base.destinationComposable
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.CreateMealPlanDestination
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.DashboardDestination
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.MealPlanDetailDestination
import org.koin.androidx.compose.koinViewModel

class DashboardFeatImpl : DashboardFeatApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.destinationComposable(destination = DashboardDestination) {

            val viewModel = koinViewModel<DashboardViewModel>()
            val state by viewModel.state.collectAsState()

            DashboardScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        CreateMealPlan -> navController.navigate(
                            route = CreateMealPlanDestination()
                        )
                        is OnMealPlanClicked -> navController.navigate(
                            route = MealPlanDetailDestination(mealPlanId = event.mealPlan.id)
                        )
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
    }
}