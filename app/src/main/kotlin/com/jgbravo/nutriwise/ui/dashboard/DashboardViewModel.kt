package com.jgbravo.nutriwise.ui.dashboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.data.repository.MealRepository
import com.jgbravo.nutriwise.base.presentation.BaseViewModel
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnMealPlanClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
    private val mealRepository: MealRepository
) : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState = MutableStateFlow(DashboardState())
    override val state = combine(mutableState, mealRepository.fetchAllMealPlans()) { state, mealPlans ->
        /*if (state.plans != mealPlans) {
            state.copy(
                plans = mealPlans
            )
        } else {
            state
        }*/
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            is CreateMealPlan -> TODO("Not yet implemented")
            OnErrorScreen -> mutableState.update {
                it.copy(error = null)
            }
            is OnMealPlanClicked -> Unit
        }
    }
}