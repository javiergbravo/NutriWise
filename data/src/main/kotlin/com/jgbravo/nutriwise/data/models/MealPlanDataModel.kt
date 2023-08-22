package com.jgbravo.nutriwise.data.models

import com.jgbravo.nutriwise.common.app.models.PlanState
import kotlinx.datetime.LocalDate

data class MealPlanDataModel(
    var id: String,
    var person: String,
    var startDate: LocalDate,
    var goal: String?,
    var kcal: Int,
    var meals: List<MealDataModel>,
    var state: PlanState
)