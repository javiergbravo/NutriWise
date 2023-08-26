package com.jgbravo.nutriwise.domain.usecases

import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.domain.usecases.mappers.toData
import com.jgbravo.nutriwise.domain.usecases.models.NewMealPlan

class CreateMealPlan(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(mealPlan: NewMealPlan) {
        mealRepository.insertMealPlan(mealPlan = mealPlan.toData())
    }
}