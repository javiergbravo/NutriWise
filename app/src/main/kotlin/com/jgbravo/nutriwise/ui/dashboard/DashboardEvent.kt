package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseEvent
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan

sealed interface DashboardEvent : BaseEvent {

    object CreateMealPlan : DashboardEvent
    data class OnMealPlanClicked(val mealPlan: MealPlan) : DashboardEvent
    object OnErrorScreen : DashboardEvent
}