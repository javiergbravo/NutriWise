package com.jgbravo.nutriwise.ui.mealPlanDetail

import com.jgbravo.nutriwise.base.presentation.BaseEvent
import com.jgbravo.nutriwise.domain.usecases.models.Meal

sealed interface MealPlanDetailEvent : BaseEvent {

    data class OnMealClicked(val meal: Meal) : MealPlanDetailEvent
    object OnErrorScreen : MealPlanDetailEvent
}