package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.base.BaseState
import com.jgbravo.nutriwise.ui.feature.models.UiText

data class DashboardState(
    val plans: List<MealPlan> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null,
) : BaseState