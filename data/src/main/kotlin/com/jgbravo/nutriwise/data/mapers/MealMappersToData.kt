package com.jgbravo.nutriwise.data.mapers

import com.jgbravo.nutriwise.common.app.models.MealType
import com.jgbravo.nutriwise.common.app.models.PlanState
import com.jgbravo.nutriwise.common.exceptions.custom.MappingException
import com.jgbravo.nutriwise.common.exceptions.custom.MappingReason.INVALID_ENUM
import com.jgbravo.nutriwise.common.extensions.getOrThrow
import com.jgbravo.nutriwise.common.utils.DatePatterns
import com.jgbravo.nutriwise.common.utils.DateTimeUtil.toLocalDate
import com.jgbravo.nutriwise.data.db.models.MealEntity
import com.jgbravo.nutriwise.data.db.models.MealPlanEntity
import com.jgbravo.nutriwise.data.models.MealDataModel
import com.jgbravo.nutriwise.data.models.MealPlanDataModel

internal fun MealEntity.mapToData() = MealDataModel(
    type = MealType.values().find {
        it.value == ::type.getOrThrow()
    } ?: throw MappingException("type", INVALID_ENUM),
    carbs = ::carbs.getOrThrow(),
    protein = ::protein.getOrThrow(),
    fat = ::fat.getOrThrow()
)

internal fun MealPlanEntity.mapToData() = MealPlanDataModel(
    id = ::_id.getOrThrow().toString(),
    person = ::person.getOrThrow(),
    startDate = ::startDate.getOrThrow().toLocalDate(DatePatterns.SPANISH_DATE_PATTERN),
    goal = ::goal.getOrThrow(),
    kcal = ::kcal.getOrThrow(),
    meals = meals.toList().map { meal -> meal.mapToData() },
    state = PlanState.values().find {
        it.value == ::state.getOrThrow()
    } ?: throw MappingException("state", INVALID_ENUM)
)