package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.base.BaseEvent

sealed interface DashboardEvent : BaseEvent {

    object CreateMealPlan : DashboardEvent
    data class OnMealPlanClicked(val mealPlan: MealPlan) : DashboardEvent
    object OnErrorScreen : DashboardEvent
}