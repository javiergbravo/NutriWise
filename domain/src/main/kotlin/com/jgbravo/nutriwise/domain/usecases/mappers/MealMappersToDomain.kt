package com.jgbravo.nutriwise.domain.usecases.mappers

import com.jgbravo.nutriwise.common.utils.EMPTY_STRING
import com.jgbravo.nutriwise.domain.usecases.models.Meal
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan

internal fun com.jgbravo.nutriwise.data.api.models.MealDataModel.mapToDomain() = Meal(
    mealType = type,
    carbs = carbs,
    protein = protein,
    fat = fat
)

internal fun com.jgbravo.nutriwise.data.api.models.MealPlanDataModel.mapToDomain() = MealPlan(
    id = id,
    person = person,
    startDate = startDate,
    goal = goal ?: EMPTY_STRING,
    kcal = kcal,
    meals = meals.map { meal -> meal.mapToDomain() },
    state = state
)