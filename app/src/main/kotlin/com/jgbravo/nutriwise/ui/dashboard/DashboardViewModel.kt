package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseViewModel
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnErrorScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState: MutableStateFlow<DashboardState>
        get() = TODO("Not yet implemented")
    override val state: StateFlow<DashboardState>
        get() = TODO("Not yet implemented")

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            is CreateMealPlan -> TODO()
            OnErrorScreen -> TODO()
        }
    }
}