package com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail

import com.jgbravo.nutriwise.domain.usecases.models.Meal
import com.jgbravo.nutriwise.ui.feature.base.BaseEvent

sealed interface MealPlanDetailEvent : BaseEvent {

    data class OnMealClicked(val meal: Meal) : MealPlanDetailEvent
    object OnErrorScreen : MealPlanDetailEvent
}