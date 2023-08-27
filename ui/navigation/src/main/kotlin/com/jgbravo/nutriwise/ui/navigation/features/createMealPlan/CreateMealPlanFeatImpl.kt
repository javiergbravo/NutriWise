package com.jgbravo.nutriwise.ui.navigation.features.createMealPlan

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanScreen
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanViewModel
import com.jgbravo.nutriwise.ui.navigation.base.destinationComposable
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.CreateMealPlanDestination
import org.koin.androidx.compose.koinViewModel

class CreateMealPlanFeatImpl : CreateMealPlanFeatApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.destinationComposable(
            destination = CreateMealPlanDestination
        ) { backStackEntry ->

            val viewModel = koinViewModel<CreateMealPlanViewModel>()
            val state by viewModel.state.collectAsState()

            CreateMealPlanScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        CreateMealPlanEvent.ClickBack -> {
                            navController.popBackStack()
                        }
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
    }
}