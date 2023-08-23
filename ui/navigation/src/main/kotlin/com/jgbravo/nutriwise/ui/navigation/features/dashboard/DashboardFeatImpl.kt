package com.jgbravo.nutriwise.ui.navigation.features.dashboard

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnMealPlanClicked
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardScreen
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardViewModel
import com.jgbravo.nutriwise.ui.navigation.base.destinationComposable
import com.jgbravo.nutriwise.ui.navigation.features.Destination.DashboardDestination
import com.jgbravo.nutriwise.ui.navigation.features.Destination.MealPlanDetailDestination
import org.koin.androidx.compose.koinViewModel

class DashboardFeatImpl : DashboardFeatApi {

    override val destination = DashboardDestination

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.destinationComposable(destination = destination) {

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
    }
}