package com.jgbravo.nutriwise.ui.dashboard

import com.jgbravo.nutriwise.base.presentation.BaseState
import com.jgbravo.nutriwise.ui.dashboard.models.Meal

data class DashboardState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : BaseState