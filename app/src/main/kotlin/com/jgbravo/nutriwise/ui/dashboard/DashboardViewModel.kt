package com.jgbravo.nutriwise.ui.dashboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.base.presentation.BaseViewModel
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Error
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Success
import com.jgbravo.nutriwise.domain.usecases.GetAllMealPlans
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnMealPlanClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
    private val getAllMealPlans: GetAllMealPlans
) : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState = MutableStateFlow(DashboardState())
    override val state = combine(mutableState, getAllMealPlans.invoke()) { state, mealPlansResource ->
        when (mealPlansResource) {
            is Error -> state.copy(error = mealPlansResource.exception.message)
            is Success -> {
                if (mealPlansResource.data == null) {
                    state.copy(error = "No data")
                } else {
                    state.copy(plans = mealPlansResource.data as List<MealPlan>)
                }
            }
        }
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