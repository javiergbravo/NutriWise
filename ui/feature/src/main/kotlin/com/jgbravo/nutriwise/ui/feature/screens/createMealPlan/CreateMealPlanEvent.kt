package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan

import com.jgbravo.nutriwise.ui.feature.base.BaseEvent

interface CreateMealPlanEvent : BaseEvent {

    data class OnPersonNameChanged(val personName: String) : CreateMealPlanEvent
    data class OnStartDateChanged(val startDate: String) : CreateMealPlanEvent
    data class OnGoalChanged(val goal: String) : CreateMealPlanEvent
    data class OnKcalChanged(val kcal: String) : CreateMealPlanEvent
    object ClickCreateMealPlan : CreateMealPlanEvent
    object CreateMealPlanSuccess : CreateMealPlanEvent
    object ClickBack : CreateMealPlanEvent
}