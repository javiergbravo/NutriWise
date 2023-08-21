package com.jgbravo.nutriwise.data.models

import com.jgbravo.nutriwise.common.app.enums.MealType

data class MealPlanDataModel(
    var id: String,
    var person: String,
    var startDate: String?,
    var goal: String?,
    var kcal: Int,
    var meals: List<MealDataModel>,
    var state: MealType
)