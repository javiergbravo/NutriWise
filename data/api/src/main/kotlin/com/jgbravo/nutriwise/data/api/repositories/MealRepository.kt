package com.jgbravo.nutriwise.data.api.repositories

import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.api.models.NewMealPlanDataModel
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getAllMealPlans(): Flow<List<MealPlanDataModel>>
    fun getMealPlan(id: String): Flow<MealPlanDataModel?>
    suspend fun insertMealPlan(mealPlan: NewMealPlanDataModel)
    suspend fun updateMealPlan(mealPlan: MealPlanDataModel)
    suspend fun deleteMealPlan(id: String)
}