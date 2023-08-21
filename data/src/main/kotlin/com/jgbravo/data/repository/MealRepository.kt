package com.jgbravo.data.repository

import com.jgbravo.data.repository.models.MealPlanEntity
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MealRepository {

    fun fetchAllMealPlans(): Flow<List<MealPlanEntity>>
    fun fetchMealPlan(id: String): Flow<MealPlanEntity>
    suspend fun insertMealPlan(mealPlan: MealPlanEntity)
    suspend fun updateMealPlan(mealPlan: MealPlanEntity)
    suspend fun deleteMealPlan(id: ObjectId)
}