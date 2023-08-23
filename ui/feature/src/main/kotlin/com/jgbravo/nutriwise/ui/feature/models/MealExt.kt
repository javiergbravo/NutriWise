package com.jgbravo.nutriwise.ui.feature.models

import com.jgbravo.nutriwise.common.app.models.MealType
import com.jgbravo.nutriwise.common.app.models.MealType.AFTERNOON_SNACK
import com.jgbravo.nutriwise.common.app.models.MealType.BREAKFAST
import com.jgbravo.nutriwise.common.app.models.MealType.DINNER
import com.jgbravo.nutriwise.common.app.models.MealType.LUNCH
import com.jgbravo.nutriwise.common.app.models.MealType.MORNING_SNACK
import com.jgbravo.nutriwise.ui.feature.R

fun MealType.getNameRes(): Int {
    return when (this) {
        BREAKFAST -> R.string.breakfast
        MORNING_SNACK -> R.string.morning_snack
        LUNCH -> R.string.lunch
        AFTERNOON_SNACK -> R.string.afternoon_snack
        DINNER -> R.string.dinner
    }
}