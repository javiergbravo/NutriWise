package com.jgbravo.nutriwise.data.api.models

data class NewMealPlanDataModel(
    val person: String,
    var startDate: String,
    var goal: String,
    var kcal: Int,
    var meals: List<MealDataModel>
)