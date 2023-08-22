package com.jgbravo.nutriwise.data.api.models

import com.jgbravo.nutriwise.common.app.models.MealType

data class MealDataModel(
    var type: MealType,
    var carbs: Int,
    var protein: Int,
    var fat: Int,
)