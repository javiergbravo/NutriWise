package com.jgbravo.nutriwise.data.api.models

import com.jgbravo.nutriwise.common.app.models.MealType

data class MealDataModel(
    val id: String,
    val type: MealType,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
)