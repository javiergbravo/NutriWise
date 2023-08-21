package com.jgbravo.nutriwise.data.repositories

import com.jgbravo.nutriwise.data.models.MealPlanDataModel
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getAllMealPlans(): Flow<List<MealPlanDataModel>>
    fun getMealPlan(id: String): Flow<MealPlanDataModel>
    suspend fun insertMealPlan(mealPlan: MealPlanDataModel)
    suspend fun updateMealPlan(mealPlan: MealPlanDataModel)
    suspend fun deleteMealPlan(id: String)
}