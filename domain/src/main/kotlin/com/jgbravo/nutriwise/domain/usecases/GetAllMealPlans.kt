package com.jgbravo.nutriwise.domain.usecases

import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.domain.base.models.utils.mapAsResourceListCatching
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource
import com.jgbravo.nutriwise.domain.usecases.mappers.mapToDomain
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import kotlinx.coroutines.flow.Flow

class GetAllMealPlans(
    private val mealRepository: MealRepository
) {
    operator fun invoke(): Flow<Resource<List<MealPlan>>> = mealRepository.getAllMealPlans()
        .mapAsResourceListCatching(discardErrorElements = true) { it.mapToDomain() }
}