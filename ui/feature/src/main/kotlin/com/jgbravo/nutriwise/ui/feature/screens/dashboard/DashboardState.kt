package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.base.BaseState

data class DashboardState(
    val plans: List<MealPlan> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
) : BaseState