package com.jgbravo.nutriwise.data.impl.db

import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity
import kotlinx.coroutines.flow.Flow

interface MealDao {

    fun fetchAllMealPlans(): Flow<List<MealPlanEntity>>
    fun fetchMealPlan(id: String): Flow<MealPlanEntity?>
    suspend fun insertMealPlan(mealPlan: MealPlanEntity)
    suspend fun updateMealPlan(mealPlan: MealPlanEntity)
    suspend fun deleteMealPlan(id: String)
}