package com.jgbravo.nutriwise.domain.usecases.models

data class NewMealPlan(
    val person: String,
    var startDate: String,
    var goal: String,
    var kcal: Int,
//    var meals: List<Meal>
)