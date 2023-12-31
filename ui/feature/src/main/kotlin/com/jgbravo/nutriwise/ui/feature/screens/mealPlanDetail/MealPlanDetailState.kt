package com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail

import com.jgbravo.nutriwise.domain.usecases.models.Meal
import com.jgbravo.nutriwise.ui.feature.base.BaseState

data class MealPlanDetailState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : BaseState