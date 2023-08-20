package com.jgbravo.nutriwise.ui.dashboard.models

import com.jgbravo.data.repository.models.MealEntity
import com.jgbravo.data.repository.models.PlanState
import java.time.LocalDateTime

data class MealPlan(
    val id: String,
    val person: String,
    var startDate: LocalDateTime,
    var description: String,
    var kcal: Int,
    var meals: List<MealEntity>,
    val state: PlanState
)