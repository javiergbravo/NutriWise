package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.logger.Logger
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Error
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Success
import com.jgbravo.nutriwise.domain.usecases.GetAllMealPlans
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.base.BaseViewModel
import com.jgbravo.nutriwise.ui.feature.base.STATE_STOP_TIMEOUT
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnMealPlanClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
    private val getAllMealPlans: GetAllMealPlans
) : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState = MutableStateFlow(DashboardState())
    override val state = combine(mutableState, getAllMealPlans.invoke()) { state, mealPlansResource ->
        when (mealPlansResource) {
            is Error -> mutableState.update { lastState ->
                lastState.copy(
                    error = mealPlansResource.exception.message?.let {
                        UiText.DynamicString(it)
                    } ?: UiText.StringResource(R.string.generic_error)
                )
            }
            is Success -> mutableState.update { lastState ->
                if (mealPlansResource.data == null) {
                    lastState.copy(error = UiText.StringResource(R.string.generic_error))
                } else {
                    lastState.copy(plans = mealPlansResource.data as List<MealPlan>)
                }
            }
        }
        state
    }.onEach {
        Logger.d("DashboardViewModel", "[DashboardState] state=$it")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STATE_STOP_TIMEOUT),
        initialValue = DashboardState()
    )

    override fun onEvent(event: DashboardEvent) {
        Logger.d("DashboardViewModel", "[DashboardEvent] onEvent=$event")
        when (event) {
            OnErrorScreen -> mutableState.update { lastState ->
                lastState.copy(error = null)
            }
            is CreateMealPlan,
            is OnMealPlanClicked -> Unit
        }
    }
}