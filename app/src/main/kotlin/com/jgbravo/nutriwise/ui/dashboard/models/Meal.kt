package com.jgbravo.nutriwise.ui.dashboard.models

data class Meal(
    val mealType: MealType,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
)
