package com.jgbravo.nutriwise.ui.mealPlan.models

data class Meal(
    val mealType: MealType,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
)
