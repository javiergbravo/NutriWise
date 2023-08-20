package com.jgbravo.nutriwise.ui.dashboard.models

import com.jgbravo.nutriwise.R

enum class MealType {
    BREAKFAST,
    MORNING_SNACK,
    LUNCH,
    AFTERNOON_SNACK,
    DINNER
}

fun MealType.getNameRes(): Int {
    return when (this) {
        MealType.BREAKFAST -> R.string.breakfast
        MealType.MORNING_SNACK -> R.string.morning_snack
        MealType.LUNCH -> R.string.lunch
        MealType.AFTERNOON_SNACK -> R.string.afternoon_snack
        MealType.DINNER -> R.string.dinner
    }
}