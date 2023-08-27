package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan

import com.jgbravo.nutriwise.ui.feature.base.BaseEvent
import kotlinx.datetime.LocalDateTime

interface CreateMealPlanEvent : BaseEvent {

    data class OnPersonNameChanged(val personName: String) : CreateMealPlanEvent
    data class OnStartDateChanged(val startDate: String) : CreateMealPlanEvent
    data class OnGoalChanged(val goal: String) : CreateMealPlanEvent
    data class OnKcalChanged(val kcal: String) : CreateMealPlanEvent
    object ShowCalendar : CreateMealPlanEvent
    data class SelectCalendarDate(val localDateTimeSelected: LocalDateTime) : CreateMealPlanEvent
    object CloseCalendar : CreateMealPlanEvent
    object ClickCreateMealPlan : CreateMealPlanEvent
    object CreateMealPlanSuccess : CreateMealPlanEvent
    object OnDismissBottomSheet : CreateMealPlanEvent
    object ClickBack : CreateMealPlanEvent
}