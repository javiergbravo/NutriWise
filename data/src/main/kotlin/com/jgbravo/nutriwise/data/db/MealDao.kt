package com.jgbravo.nutriwise.data.db

import com.jgbravo.nutriwise.data.db.models.MealPlanEntity
import kotlinx.coroutines.flow.Flow

internal interface MealDao {

    fun fetchAllMealPlans(): Flow<List<MealPlanEntity>>
    fun fetchMealPlan(id: String): Flow<MealPlanEntity>
    suspend fun insertMealPlan(mealPlan: MealPlanEntity)
    suspend fun updateMealPlan(mealPlan: MealPlanEntity)
    suspend fun deleteMealPlan(id: String)
}