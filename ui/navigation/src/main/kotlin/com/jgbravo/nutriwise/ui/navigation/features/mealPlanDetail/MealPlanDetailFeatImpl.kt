package com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailEvent.OnMealClicked
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailScreen
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailViewModel
import com.jgbravo.nutriwise.ui.navigation.base.destinationComposable
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.MealPlanDetailDestination
import org.koin.androidx.compose.koinViewModel

class MealPlanDetailFeatImpl : MealPlanDetailFeatApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.destinationComposable(
            destination = MealPlanDetailDestination
        ) { backStackEntry ->

            val viewModel = koinViewModel<MealPlanDetailViewModel>()
            val state by viewModel.state.collectAsState()

            val mealPlanIdResult = backStackEntry.arguments?.getString(AppDestination.MEAL_PLAN_ID)

            LaunchedEffect(mealPlanIdResult) {
                backStackEntry.savedStateHandle[AppDestination.MEAL_PLAN_ID] = mealPlanIdResult
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
        }
    }
}