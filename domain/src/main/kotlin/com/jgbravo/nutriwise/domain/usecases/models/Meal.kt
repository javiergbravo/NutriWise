package com.jgbravo.nutriwise.domain.usecases.models

import com.jgbravo.nutriwise.common.app.models.MealType

data class Meal(
    val id: String,
    val mealType: MealType,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
)