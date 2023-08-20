package com.jgbravo.nutriwise.ui.mealPlan

import com.jgbravo.nutriwise.base.presentation.BaseState
import com.jgbravo.nutriwise.ui.mealPlan.models.Meal

data class MealPlanDetailState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : BaseState