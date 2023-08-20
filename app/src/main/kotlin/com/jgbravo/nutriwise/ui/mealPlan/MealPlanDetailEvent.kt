package com.jgbravo.nutriwise.ui.mealPlan

import com.jgbravo.nutriwise.base.presentation.BaseEvent
import com.jgbravo.nutriwise.ui.mealPlan.models.Meal

sealed interface MealPlanDetailEvent : BaseEvent {

    data class OnMealClicked(val meal: Meal) : MealPlanDetailEvent
    object OnErrorScreen : MealPlanDetailEvent
}