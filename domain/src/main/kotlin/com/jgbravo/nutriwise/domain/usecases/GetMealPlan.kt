package com.jgbravo.nutriwise.domain.usecases

import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource
import com.jgbravo.nutriwise.domain.usecases.mappers.mapToDomain
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetMealPlan(
    private val mealRepository: MealRepository
) {
    operator fun invoke(id: String): Flow<Resource<MealPlan>> = mealRepository.getMealPlan(id = id)
        .catch { Resource.Error(it) }
        .map {
            Resource.Success(it.mapToDomain())
        }
}