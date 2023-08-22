package com.jgbravo.nutriwise.domain.usecases

import com.jgbravo.nutriwise.data.repositories.MealRepository
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource
import com.jgbravo.nutriwise.domain.usecases.mappers.mapToDomain
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetAllMealPlans(
    private val mealRepository: MealRepository
) {
    operator fun invoke(): Flow<Resource<List<MealPlan>>> = mealRepository.getAllMealPlans()
        .catch { Resource.Error(exception = it) }
        .map {
            Resource.Success(it.map { mealPlanDataModel -> mealPlanDataModel.mapToDomain() })
        }
}