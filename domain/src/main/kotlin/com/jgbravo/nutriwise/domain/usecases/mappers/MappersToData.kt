package com.jgbravo.nutriwise.domain.usecases.mappers

import com.jgbravo.nutriwise.data.api.models.MealDataModel
import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.api.models.NewMealPlanDataModel
import com.jgbravo.nutriwise.domain.usecases.models.Meal
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.domain.usecases.models.NewMealPlan

internal fun MealPlan.toData() = MealPlanDataModel(
    id = id,
    person = person,
    startDate = startDate,
    goal = goal,
    kcal = kcal,
    meals = meals.map { meal -> meal.toData() },
    state = state
)

internal fun NewMealPlan.toData() = NewMealPlanDataModel(
    person = person,
    startDate = startDate,
    goal = goal,
    kcal = kcal,
//    meals = emptyList(),
)

internal fun Meal.toData() = MealDataModel(
    id = id,
    type = mealType,
    carbs = carbs,
    protein = protein,
    fat = fat
)