package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseEvent
import com.jgbravo.nutriwise.ui.dashboard.models.Meal

sealed interface DashboardEvent: BaseEvent {

    data class OnMealClicked(val meal: Meal): DashboardEvent
    object OnErrorScreen: DashboardEvent
}