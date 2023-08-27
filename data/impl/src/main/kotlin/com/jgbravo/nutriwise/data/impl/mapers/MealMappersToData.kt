package com.jgbravo.nutriwise.data.impl.mapers

import com.jgbravo.nutriwise.common.app.models.MealType
import com.jgbravo.nutriwise.common.app.models.PlanState
import com.jgbravo.nutriwise.common.exceptions.custom.MappingException
import com.jgbravo.nutriwise.common.exceptions.custom.MappingReason.INVALID_ENUM
import com.jgbravo.nutriwise.common.extensions.getOrThrow
import com.jgbravo.nutriwise.common.utils.DatePattern.SPANISH_DATE_PATTERN
import com.jgbravo.nutriwise.common.utils.DateTimeUtil.toLocalDate
import com.jgbravo.nutriwise.data.api.models.MealDataModel
import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.impl.db.models.MealEntity
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity

internal fun MealEntity.mapToData() = MealDataModel(
    id = ::_id.getOrThrow().toString(),
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
    startDate = ::startDate.getOrThrow().toLocalDate(
        SPANISH_DATE_PATTERN
    ) ?: throw MappingException("startDate", INVALID_ENUM),
    goal = ::goal.getOrThrow(),
    kcal = ::kcal.getOrThrow(),
    meals = meals.toList().map { meal -> meal.mapToData() },
    state = PlanState.values().find {
        it.value == ::state.getOrThrow()
    } ?: throw MappingException("state", INVALID_ENUM)
)