package com.jgbravo.nutriwise.ui.mealPlanDetail.models

import com.jgbravo.nutriwise.R
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.AFTERNOON_SNACK
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.BREAKFAST
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.DINNER
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.LUNCH
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.MORNING_SNACK

enum class MealType {
    BREAKFAST,
    MORNING_SNACK,
    LUNCH,
    AFTERNOON_SNACK,
    DINNER
}

fun MealType.getNameRes(): Int {
    return when (this) {
        BREAKFAST -> R.string.breakfast
        MORNING_SNACK -> R.string.morning_snack
        LUNCH -> R.string.lunch
        AFTERNOON_SNACK -> R.string.afternoon_snack
        DINNER -> R.string.dinner
    }
}