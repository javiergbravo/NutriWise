package com.jgbravo.nutriwise.data.impl.mapers

import com.jgbravo.common.app.dates.DatePattern.SPANISH_DATE_PATTERN
import com.jgbravo.common.app.dates.DateTimeUtil.toLocalDate
import com.jgbravo.common.app.models.MealType
import com.jgbravo.common.app.models.PlanState
import com.jgbravo.common.core.exceptions.custom.MappingException
import com.jgbravo.common.core.exceptions.custom.MappingReason.INVALID_DATE
import com.jgbravo.common.core.exceptions.custom.MappingReason.INVALID_ENUM
import com.jgbravo.common.core.extensions.getOrThrow
import com.jgbravo.nutriwise.data.api.models.MealDataModel
import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.impl.db.models.MealEntity
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity

internal fun MealEntity.mapToData() = MealDataModel(
    id = ::id.getOrThrow().toString(),
    type = MealType.entries.find {
        it.value == ::type.getOrThrow()
    } ?: throw MappingException("type", INVALID_ENUM),
    carbs = ::carbs.getOrThrow(),
    protein = ::protein.getOrThrow(),
    fat = ::fat.getOrThrow()
)

internal fun MealPlanEntity.mapToData() = MealPlanDataModel(
    id = ::id.getOrThrow().toHexString(),
    person = ::person.getOrThrow(),
    startDate = ::startDate.getOrThrow().toLocalDate(
        SPANISH_DATE_PATTERN
    ) ?: throw MappingException("startDate", INVALID_DATE),
    goal = ::goal.getOrThrow(),
    kcal = ::kcal.getOrThrow(),
    meals = meals.toList().map { meal -> meal.mapToData() },
    state = PlanState.valueOf(::state.getOrThrow())
)