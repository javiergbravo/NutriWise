package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseState
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan

data class DashboardState(
    val plans: List<MealPlan> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
) : BaseState