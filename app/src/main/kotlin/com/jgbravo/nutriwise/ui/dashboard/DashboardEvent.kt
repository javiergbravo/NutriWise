package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseEvent

sealed interface DashboardEvent : BaseEvent {

    object CreateMealPlan : DashboardEvent
    object OnErrorScreen : DashboardEvent
}