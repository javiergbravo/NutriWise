package com.jgbravo.nutriwise.domain.usecases.models

import com.jgbravo.nutriwise.common.app.models.PlanState
import kotlinx.datetime.LocalDate

data class MealPlan(
    val id: String,
    val person: String,
    var startDate: LocalDate,
    var goal: String,
    var kcal: Int,
    var meals: List<Meal>,
    val state: PlanState
)