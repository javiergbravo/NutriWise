package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.domain.base.models.utils.combineWithFlow
import com.jgbravo.nutriwise.domain.usecases.GetAllMealPlans
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.base.BaseViewModel
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnMealPlanClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
    private val getAllMealPlans: GetAllMealPlans
) : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState = MutableStateFlow(DashboardState())
    override val state: StateFlow<DashboardState> = mutableState.combineWithFlow(
        flowWithResource = getAllMealPlans.invoke(),
        onSuccess = { mealPlans ->
            mutableState.update { lastState ->
                lastState.copy(plans = mealPlans)
            }
        },
        onError = { exception ->
            mutableState.update { lastState ->
                lastState.copy(
                    error = exception.message?.let {
                        UiText.DynamicString(it)
                    } ?: UiText.StringResource(R.string.generic_error)
                )
            }
        }
    ).onEach {
        Log.d("DashboardViewModel", "[DashboardState] state=$it")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardState()
    )

    override fun onEvent(event: DashboardEvent) {
        Log.d("DashboardViewModel", "[DashboardEvent] onEvent=$event")
        when (event) {
            OnErrorScreen -> mutableState.update { lastState ->
                lastState.copy(error = null)
            }
            is CreateMealPlan,
            is OnMealPlanClicked -> Unit
        }
    }
}